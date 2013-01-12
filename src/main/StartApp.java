package main;


import main.grammar.Grammar;
import main.service.LR0;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StartApp {
    public static void main(String arg[]) throws IOException {
        Grammar grammar=new Grammar();
        grammar.readGrammarFromFile();
        grammar.printGrammar();
        LR0 lr0=new LR0(grammar);
        lr0.startInitialPhase();
        lr0.printAllStates();
        lr0.printGraph();
        lr0.parseSequence();

        System.out.print("End");
    }

}
