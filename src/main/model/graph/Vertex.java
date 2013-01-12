package main.model.graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;


public class Vertex{
    private Set<Edge> inbounds=new HashSet<Edge>();
    private Set<Edge> outbounds=new HashSet<Edge>();
    private int origin;
    public Vertex(Integer origin) {
        this.origin=origin;
    }
    public Set<Edge> getInbound() {
        return inbounds;
    }
    public void setInbound(Set<Edge> inbound) {
        this.inbounds = inbound;
    }
    public Set<Edge> getOutbound() {
        return outbounds;
    }
    public void setOutbound(Set<Edge> outbound) {
        this.outbounds = outbound;
    }
    public int getOrigin() {
        return origin;
    }
    public void setOrigin(int origin) {
        this.origin = origin;
    }
    public void addOutbound(Edge e)
    {
        outbounds.add(e);
    }
    public void addInbound(Edge e)
    {
        inbounds.add(e);
    }
    public Edge hasEdgeWith(int targetVertex)
    {


        for(Edge edge:outbounds)
        {
            if(edge.getTarget()==targetVertex)
                return edge;
        }
        return null;
    }
    public int getIndegree()
    {
        return inbounds.size();
    }
    public int getOutDegree()
    {
        return outbounds.size();
    }
    public Set<Edge> iterateThroughOutboundEges()
    {
        return Collections.unmodifiableSet(outbounds);
//		for(Edge edge:outbounds)
//		{
//			System.out.println("Target: "+edge.getTarget() + " Cost:" +edge.getCost());
//		}
    }
    public Set<Edge> iterateThroughInboundEges()
    {
        return Collections.unmodifiableSet(inbounds);
//		for(Edge edge:inbounds)
//		{
//			System.out.println("Source: "+edge.getSoruce() + " Cost:" +edge.getCost());
//		}
    }
    public String getCostToVertex(Vertex target)
    {

        for(Edge edge:outbounds)
        {
            if(edge.getTarget()==target.getOrigin())
            {
                return edge.getCost();
            }
        }
        return null;

    }


}
