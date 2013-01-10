package main;


import main.grammar.Grammar;

public class StartApp {
    public static void main(String arg[])
    {
        Grammar grammar=new Grammar();
        grammar.readGrammarFromFile();
        grammar.printGrammar();

    }

}
