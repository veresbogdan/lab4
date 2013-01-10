package main.grammar;

import java.util.List;
import java.util.Set;
import java.util.Vector;

public class Production {
    private String lhs;
    private Vector<String> results;
    private int position=0;

    public Production(String lhs, Vector<String> results, int position) {
        this.lhs = lhs;
        this.results = results;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Production()
    {
        this.results=new Vector<String>();
    }

    public String getLhs() {
        return lhs;
    }

    public void setLhs(String lhs) {
        this.lhs = lhs;
    }

    public Vector<String> getResults() {
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

        return this.lhs +"->"+stringBuilder.substring(0,stringBuilder.length()-2);
    }

    public boolean hasResult(String result) {
        for (String resultFromList: results) {
            if (resultFromList.equals(result)) {
                return true;
            }
        }
        return false;
    }

    public String getSymbolAfterPoint() {
        return this.getResults().get(position);
    }

    public void movePoint() {
        this.setPosition(this.getPosition()+1);
    }


    public void setPoint(int position){
        this.position=position;
    }
}
