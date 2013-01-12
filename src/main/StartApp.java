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
        System.out.println("----------------------------------------------");
        System.out.println("Canonical states \n");
        lr0.printAllStates();
        System.out.println("----------------------------------------------");
        System.out.println("LR(0) table \n");
        lr0.printGraph(lr0);
        System.out.println("----------------------------------------------");
        System.out.println("Parsing the sequence \n");
        lr0.parseSequence();

        System.out.print("End");
    }

}
