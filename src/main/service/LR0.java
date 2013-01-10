package main.service;

import main.grammar.Grammar;
import main.grammar.Production;
import main.model.State;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class LR0 {

    private List<State> states;
    private Grammar grammar;

    public LR0(Grammar grammar){
        this.grammar=grammar;
    }

    public void startInitialPhase(){
        Set<Production> firstSet = new HashSet<Production>();
        Vector<String> res = new Vector<String>();
        State state = new State();

        res.add(grammar.getStartingSymbol());
        Production production = new Production("Y", res, 0);
        firstSet.add(production);

        firstSet = closure(firstSet);
        state.setSetProductions(firstSet);

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
            if(production.getSymbolAfterPoint().equals(symbol)){
                production.movePoint();
                listForClosure.add(production);
            }
        }

        State newState=new State();
        newState.setSetProductions(closure(listForClosure));

        return newState;


    }



}
