package main.grammar;

import java.util.List;
import java.util.Vector;

public class Production {
    private String nonTerminal;
    private Vector<String> results;

    public Production() {
        this.results = new Vector<String>();
    }

    public String getNonTerminal() {
        return nonTerminal;
    }

    public void setNonTerminal(String nonTerminal) {
        this.nonTerminal = nonTerminal;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(Vector<String> results) {
        this.results = results;
    }

    public void addResult(String result) {
        this.results.add(result);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String result: results) {
            stringBuilder.append(result);
            stringBuilder.append(" | ");
        }
        return this.nonTerminal + "->" + stringBuilder.substring(0, stringBuilder.length()-2);
    }

    public boolean hasResult(String result) {
        for (String resultFromList: results) {
            if (resultFromList.equals(result)) {
                return true;
            }
        }
        return false;
    }
}
