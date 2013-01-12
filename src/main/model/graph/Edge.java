package main.model.graph;

public class Edge implements Comparable<Edge>{
	private int soruce;
	private int target;
	private String cost;
	public Edge(Integer origin, Integer target, String cost) {
		this.soruce=origin;
		this.target=target;
		this.cost=cost;
	}

	public int compareTo(Edge e) {
		if(this.soruce<e.soruce)
			return -1;
		else if(this.soruce>e.soruce)
			return 1;
		return 0;
	}
	public int getSoruce() {
		return soruce;
	}
	public void setSoruce(int soruce) {
		this.soruce = soruce;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	


}
