package main.service;

import main.grammar.Grammar;
import main.grammar.Production;
import main.model.State;

import javax.swing.*;
import java.util.List;

public class LR0 {

    private List<State> states;
    private Grammar grammar;

    public LR0(Grammar grammar){
        this.grammar=grammar;
    }

    public void addState(State s){
        states.add(s);
    }

    public void goTo(State state,String symbol){

    }



}
