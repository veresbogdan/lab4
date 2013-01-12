package main.service;

import main.grammar.Grammar;
import main.grammar.Production;
import main.model.State;
import main.model.StateSymbol;
import main.model.graph.DirectedGraph;
import main.model.graph.Vertex;

import java.io.*;
import java.util.*;

public class LR0 {

    private List<State> states;
    private Grammar grammar;
    private DirectedGraph LRTable=new DirectedGraph();
    private Stack<String> inputSequence = new Stack<String>();
    private Stack<Integer> result = new Stack<Integer>();

    public LR0(Grammar grammar){
        this.grammar=grammar;
        this.states=new LinkedList<State>();
    }

    public void startInitialPhase(){
        int indexOfSet=0;
        Set<Production> firstSet = new HashSet<Production>();
        Vector<String> res = new Vector<String>();
        State state = new State();

        res.add(grammar.getStartingSymbol());
        Production production = new Production("Y", res, 0,0);
        firstSet.add(production);

        firstSet = closure(firstSet);
        state.setSetProductions(firstSet);
        state.setNumberOfState(indexOfSet++);
        makeActionForState(state);
        states.add(state);

        Vertex vertex=new Vertex(state.getNumberOfState());
        LRTable.addVertex(vertex);
//        LRTable.addEdge(currentState,newState,symbol);

        Queue<State> stateQueue = new LinkedList<State>();
        stateQueue.add(state);

        while(!stateQueue.isEmpty()){
            State currentState = stateQueue.poll();
            for(String symbol:grammar.getAllSymbols()){
                State newState=goTo(currentState,symbol);
                makeActionForState(newState);
                if(newState!=null && !newState.getSetProductions().isEmpty() && !states.contains(newState)){
                    stateQueue.add(newState);
                    newState.setNumberOfState(indexOfSet++);
                    states.add(newState);
                    this.addStateToTable(newState,currentState,symbol);

                } else{
                    if(newState!=null && !newState.getSetProductions().isEmpty() ){
                        State newState2=this.getStateAtIndex(newState);
                        this.addStateToTableOldVertex(newState2, currentState, symbol);
                    }
                }
            }
        }
        System.out.print(states.size());
    }

    public void makeActionForState(State newState){
        if(newState.stateIsAccept(grammar.getStartingSymbol())){
            newState.setAction("accept");
        }
        else{
            int stateIsReduce=newState.stateIsReduce(grammar.getProductions());
            if(stateIsReduce!=-1){
                newState.setAction(Integer.toString(stateIsReduce));
            }
            else{
                newState.setAction("shift");

            }
        }
    }
    private void addStateToTable(State newState,State currentState,String symbol) {
        Vertex vertex=new Vertex(newState.getNumberOfState());
        LRTable.addVertex(vertex);
        LRTable.addEdge(currentState,newState,symbol);
    }

    private void addStateToTableOldVertex(State newState,State currentState,String symbol) {
        Vertex vertex=LRTable.getVertexIndexWithIndex(newState.getNumberOfState());
        LRTable.addVertex(vertex);
        LRTable.addEdge(currentState,newState,symbol);
    }

    public void addState(State s){
        states.add(s);
    }


    public Set<Production> closure(Set<Production> productions){
        Set<Production> newStateProductions=new HashSet<Production>();

        for(Production production:productions){
            String symbol=production.getSymbolAfterPoint();
            if(symbol!=null && grammar.isNonTerminal(symbol)){
                Set<Production> newProductions =grammar.getProductionsWithAGivenLHS(symbol);
                for(Production productionInner:newProductions) {
                    if(!productions.contains(productionInner)){
                       newStateProductions.add(productionInner);
                    }
                }
                newStateProductions=closure(newStateProductions);
            }
        }
        newStateProductions.addAll(productions);
        return newStateProductions;
    }

    public State goTo(State state,String symbol){
        Set<Production> listForClosure=new HashSet<Production>();
        for(Production production:state.getSetProductions()){
            if(production.getSymbolAfterPoint()!=null && production.getSymbolAfterPoint().equals(symbol)){
                Production newProd=new Production();
                newProd.setLhs(production.getLhs());
                newProd.setPosition(production.getPosition() + 1);
                newProd.setResults(production.getResults());
                newProd.setProductionNumber(production.getProductionNumber());
                listForClosure.add(newProd);
            }
        }

        State newState=new State();
        newState.setSetProductions(closure(listForClosure));

        return newState;


    }

    public void getInputSequenceFromFile() throws IOException {
        FileInputStream fStream = new FileInputStream("sequence.txt");
        DataInputStream in = new DataInputStream(fStream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        List<String> list = new ArrayList<String>();

        while ((strLine = br.readLine()) != null) {
            strLine.trim();
            StringTokenizer stringTokenizer = new StringTokenizer(strLine," ");
            while (stringTokenizer.hasMoreElements()) {
                String stringToken = stringTokenizer.nextElement().toString();
                if (stringToken != null) {
                    list.add(stringToken);
                }
            }
        }
        Integer listSize = list.size();
        for(int i = listSize-1; i >= 0; i--) {
            inputSequence.push(list.get(i));
        }
    }

    public boolean parseSequence() throws IOException {
        Stack<StateSymbol> stateSymbols = new Stack<StateSymbol>();
        inputSequence.push("$");
        getInputSequenceFromFile();


        StateSymbol initial = new StateSymbol();
        initial.setState(states.get(0));
        stateSymbols.push(initial);

        while (!inputSequence.isEmpty()) {
            if (stateSymbols.peek().getState().getAction().equals("shift")) {
                StateSymbol next = new StateSymbol();
                next.setSymbol(inputSequence.pop());
                if (!next.getSymbol().equals("$")) {

                    if (getNextStateFromTable(stateSymbols.peek().getState(), next.getSymbol()) != null) {
                        next.setState(getNextStateFromTable(stateSymbols.peek().getState(), next.getSymbol()));
                        stateSymbols.push(next);
                    }
                }
            } else {

                if (stateSymbols.peek().getState().getAction().equals("accept") && inputSequence.peek().equals("$")) {
                    return true;

                } else {
                    Integer reduceIndex = Integer.parseInt(stateSymbols.peek().getState().getAction());
                    Production reduceProduction = grammar.getListProductions().get(reduceIndex-1);

                    result.push(reduceIndex);

                    for (int i = 0; i < reduceProduction.getResults().size(); i++) {
                        stateSymbols.pop();
                    }

                    StateSymbol next = new StateSymbol();
                    next.setSymbol(reduceProduction.getLhs());
                    if(getNextStateFromTable(stateSymbols.peek().getState(), reduceProduction.getLhs())!=null)  {
                        next.setState(getNextStateFromTable(stateSymbols.peek().getState(), reduceProduction.getLhs()));
                    }
                    else {
                        return false;
                    }
                    stateSymbols.push(next);
                }
            }
        }

        return false;
    }

    private State getNextStateFromTable(State state, String symbol) {
        int numberOfState=LRTable.getTargetVertexForCost(state.getNumberOfState(),symbol);
        if(numberOfState==-1){
            return null;
        }
            else{
            return states.get(numberOfState);
        }
    }

    public void printAllStates(){
        for(State state:this.states){
            System.out.println(state.toString());
        }
    }

    public void printGraph(){
        LRTable.printGraph();
//       List<Production> listProd=grammar.getListProductions();
    }

    public State getStateAtIndex(State state){
        Iterator<State> iterator=states.iterator();
        while(iterator.hasNext()){
            State stateInner=iterator.next();
            if (stateInner.equals(state)){
                return stateInner;
            }
        }
        return null;
    }
}
