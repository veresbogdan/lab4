package main.model;

import main.grammar.Production;

import java.util.Set;

public class State {

    private Set<Production> setProductions;

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
}
