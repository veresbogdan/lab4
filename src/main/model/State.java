package main.model;

import main.grammar.Production;

import java.util.HashSet;
import java.util.Set;

public class State {

    private Set<Production> setProductions;

    public State() {
    }

    public State(State currentState) {
        setProductions=new HashSet<Production>();
    }

    public void addProduction(Production production) {
        setProductions.add(production);
    }

    public int getNumberProductions() {
        return setProductions.size();
    }

    public Set<Production> getSetProductions() {
        return setProductions;
    }

    public void setSetProductions(Set<Production> setProductions) {
        this.setProductions = setProductions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;

        State state = (State) o;

        if (setProductions != null ? !setProductions.equals(state.setProductions) : state.setProductions != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return setProductions != null ? setProductions.hashCode() : 0;
    }

    public Object clone(){
        try {
           return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}
