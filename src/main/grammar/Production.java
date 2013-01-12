package main.grammar;

import java.util.List;
import java.util.Set;
import java.util.Vector;

public class Production {
    private String lhs;
    private Vector<String> results;
    private int position=0;
    private int productionNumber;

    public int getProductionNumber() {
        return productionNumber;
    }

    public void setProductionNumber(int productionNumber) {
        this.productionNumber = productionNumber;
    }

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

        }

        return this.lhs +"->"+stringBuilder;
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
        if(position<results.size()){
            return this.getResults().get(position);
        }
        else{
            return null;
        }
    }

    public void movePoint() {
        this.setPosition(this.getPosition()+1);
    }


    public void setPoint(int position){
        this.position=position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Production)) return false;

        Production that = (Production) o;

        if (position != that.position) return false;
        if (!lhs.equals(that.lhs)) return false;
        if (!results.equals(that.results)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lhs.hashCode();
        result = 31 * result + results.hashCode();
        result = 31 * result + position;
        return result;
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
