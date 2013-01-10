package main.grammar;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Grammar {
    private List<String> terminals;
    private List<String> nonTerminals;
    private List<Production> productions;
    private String startingSymbol;

    public  Grammar() {
        this.nonTerminals = new ArrayList<String>();
        this.terminals = new ArrayList<String>();
        this.productions = new ArrayList<Production>();
    }

    public void readGrammarFromFile() {
        try {
            FileInputStream fStream = new FileInputStream("grammar.txt");
            DataInputStream in = new DataInputStream(fStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            int index = 0;
            while ((strLine = br.readLine()) != null) {
                strLine.trim();
                StringTokenizer stringTokenizer = new StringTokenizer(strLine," ");

                if (index == 0) {
                    while (stringTokenizer.hasMoreElements()) {
                        String stringToken =stringTokenizer.nextElement().toString();
                        if(stringToken != null)
                            terminals.add(stringToken );
                    }
                } else {
                    if (index == 1) {
                        while (stringTokenizer.hasMoreTokens()) {
                            nonTerminals.add(stringTokenizer.nextToken());
                        }
                    } else {
                        if (index == 2) {
                            startingSymbol = strLine;
                        } else {
                            Production production = new Production();
                            String[] splited = strLine.split("->");
                            production.setLhs(splited[0].trim());
                            stringTokenizer=new StringTokenizer(splited[1],"|");
                            while(stringTokenizer.hasMoreTokens()) {
                                production.addResult((stringTokenizer.nextToken().trim()));
                            }

                            this.productions.add(production);
                        }
                    }
                }
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isGrammarRegular() {
        for(Production production:productions) {
            for(String result:production.getResults()) {
                if (result.length() > 2) {
                    return false;
                }
                if (this.isTerminal(String.valueOf(result.charAt(0)))) {
                    return false;
                }
                if ((result.length() == 2 && this.isTerminal(String.valueOf(result.charAt(0))))) {
                    return false;
                }
                if (String.valueOf(result.charAt(0)).equals("@") && production.getLhs().equals(this.startingSymbol)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isNonTerminal(String symbol) {
        for (String nonTerminal: this.nonTerminals) {
            if (nonTerminal.equals(symbol)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTerminal(String symbol) {
        for (String terminal: this.terminals) {
            if (terminal.equals(symbol)) {
                return true;
            }
        }
        return false;
    }

    public String getStartingSymbol() {
        return startingSymbol;
    }

    public void setStartingSymbol(String startingSymbol) {
        this.startingSymbol = startingSymbol;
    }

    public void addNonTerminal(String nonTerminal) {
        this.nonTerminals.add(nonTerminal);
    }

    public void addTerminal(String terminal) {
        this.terminals.add(terminal);
    }

    public void addProduction(Production prod) {
        this.productions.add(prod);
    }

    public void printGrammar() {
        System.out.println("\nSet of non-terminals");

        for (String nonTerm: nonTerminals) {
            System.out.print(nonTerm + " ");
        }
        System.out.println("\nSet of terminals ");
        System.out.println("\nStarting symbol " + startingSymbol);

        for (String term: terminals) {
            System.out.print(term + " ");
        }
        System.out.println("\nSet of productions ");
        for (Production production: productions) {
            System.out.println(production.toString() + " ");
        }
    }

    public Production getProductionFromList(String prod) {
        for(Production production: productions) {
            if(production.getLhs().equals(prod)) {
                return production;
            }
        }
        return null;
    }


    public Set<Production> getProductionsWithAGivenLHS(String symbol){
        Set<Production> listNewProductions=new HashSet<Production>();
        for(Production production:this.productions){
            if(production.getLhs().equals(symbol)){
                listNewProductions.add(new Production(production.getLhs(),production.getResults(),0));
            }
        }
        return listNewProductions;
    }

    public Set<String> getAllSymbols(){
        Set<String> setSymbols=new HashSet<String>();
        setSymbols.addAll(this.nonTerminals);
        setSymbols.addAll(this.terminals);
        return setSymbols;
    }
}
