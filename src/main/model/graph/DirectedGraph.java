package main.model.graph;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


public class DirectedGraph {
	private Map<Integer,Vertex> vertices=new HashMap<Integer, Vertex>();

	int noEdges, noVertices;
	public Vertex getVertex(int number)
	{
		return vertices.get(number);
	}
    public Map<Integer,Vertex> getVertices()
    {
        return vertices;
    }

//	public void readGraph() throws FileNotFoundException
//	{
//
//		//origin,target,cost
//		try {
//
//			  FileInputStream fstream;
//
//				fstream = new FileInputStream("graph1k.txt");
//
//			  DataInputStream in = new DataInputStream(fstream);
//			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
//			  String strLine= br.readLine();
//			  String[] splitedString=strLine.split(" ");
//			  noVertices=Integer.valueOf(splitedString[0]);
//			  noEdges=Integer.valueOf(splitedString[1]);
//			  Integer origin,target,cost;
//			  Edge newEdge;
//
//			  while ((strLine = br.readLine()) != null)   {
//				  splitedString=strLine.split(" ");
//				  origin=Integer.parseInt(splitedString[0]);
//				  target=Integer.parseInt(splitedString[1]);
//				  cost=Integer.parseInt(splitedString[2]);
//				  newEdge=new Edge(origin,target,cost);
//				  mapCurrentSide(origin, target, cost,newEdge);
//				  mapOtherSide(origin, target, cost,newEdge);
//
//			  }
//			  in.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	}
	public void mapOtherSide(Integer origin, Integer target, Integer cost,Edge newEdge)
	{
		Vertex currentVertex;
		if(vertices.get(target)==null)
		  {
			  //vertex is not mapped yet
			  currentVertex=new Vertex(target);
			  currentVertex.addInbound(newEdge);
			  vertices.put(target,currentVertex );
		  }
		  else
			  
		  {
			  currentVertex=vertices.get(target);
			  currentVertex.addInbound(newEdge);
		  }
	}
	public void mapCurrentSide(Integer origin, Integer target, Integer cost,Edge newEdge)
	{
		Vertex currentVertex;
		  if(vertices.get(origin)==null)
		  {
			  //vertex is not mapped yet
			  currentVertex=new Vertex(origin);
			  currentVertex.addOutbound(newEdge);
			  vertices.put(origin,currentVertex );
			  
			  //mapp other side
			 
			  
		  }
		  else
			  
		  {
			  currentVertex=vertices.get(origin);
			  currentVertex.addOutbound(newEdge);
		  }
	}
	public int getNumberOfVertices()
	{
		return noVertices;
	}
	public Edge getEdgeBetweenVertices(int originVertex,int target)
	{
		Vertex origin=vertices.get(originVertex);
		return origin.hasEdgeWith(target);
	}

	public String getEdgeCost(int origin,int target)
	{
		Vertex vertex=vertices.get(origin);
		Edge edge=vertex.hasEdgeWith(target);
		if(edge!=null)
		{
			return edge.getCost();
		}
		return (String) null;
	}


    public void addVertex(Vertex vertex) {
        vertices.put(vertex.getOrigin(),vertex);
    }
}
