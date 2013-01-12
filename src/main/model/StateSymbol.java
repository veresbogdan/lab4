package main.model;

public class StateSymbol {

    private State state;
    private String symbol;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "StateSymbol{" +
                "state=" + state +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
