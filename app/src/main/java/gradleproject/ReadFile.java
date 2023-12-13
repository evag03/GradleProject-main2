package gradleproject;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

import gradleproject.GraphDisplay.Pair;


/**
 * Initializes the ReadFile class, which contains methods for computating the dataset.
 */
public class ReadFile {
  /**
  * File reader for dataset; takes in a file with a corresponding file name and reads it, placing it into a scanner.
  * @param filename (String) The name of the file being read
  * @param file (File) File being read
  */
  public static ArrayList<String[]> ReadInFile(MutableValueGraph<String,Integer> graph, HashSet<String> listOfVertices) {
    String filename = "C:\\Users\\Eva\\Downloads\\GradleProject-main\\app\\src\\main\\java\\gradleproject\\TexasRoadnetworks.txt";
    File file = null;

    Scanner scanner = null;
      
    try {
      file = new File(filename);
    }
    // find exactly what exception this is supposed to be
    catch (Exception e) {
      System.out.println("Cannot locate file.");
      System.exit(-1);
    }

    try {
      scanner = new Scanner(file);
    }
    catch (Exception e){
      System.out.println("scanner not working");
      e.printStackTrace();
      System.exit(-1);
    }
    

    //Need to initialize nodes in text files
    // Node fromNodeID = new ReadFile(File[0]);
    // Node ToNodeID = File[1];

    ArrayList<String[]> edgeSets = new ArrayList<>();

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] vertices = line.split("\t");
      // ArrayList<String> verticesList = new ArrayList<String>(Arrays.asList(vertices));
      // System.out.println(verticesList.toString());

      //System.out.println("1st node: " + vertices[0] + " 2nd node: " + vertices[1]);

      graph.addNode(vertices[0]);
      graph.putEdgeValue(vertices[0], vertices[1], 1);

      String[] connections = {vertices[0], vertices[1], "1"};
      edgeSets.add(connections);
      
      
      if (!listOfVertices.contains(vertices[0])) {
        listOfVertices.add(vertices[0]);
      }
      // vertices[0].addNode();
    }

    //new GraphDisplay(graph);

    scanner.close();
    return edgeSets;
  }

  // turned source into an integer because it made more sense to me to have the starting node be an integer; it also fixed some errors later
  // are we missing any parameters? I saw edgeSet in the 2nd version but assuming that got replaced by listOfVerticies?
  // not sure why a 'Return type for the method is missing' error is popping up
  public static ArrayList<Object> DijkstraAlgorithm(MutableValueGraph<String, Integer> graph, String source) {
    //psuedocode from Wikipedia page:
    //  1  function Dijkstra(Graph, source):
    //  2 
    //  3      for each vertex v in Graph.Vertices:
    //  4          dist[v] ← INFINITY
    //  5          prev[v] ← UNDEFINED
    //  6          add v to Q
    //  7      dist[source] ← 0
    //  8      
    //  9      while Q is not empty:
    // 10          u ← vertex in Q with min dist[u]
    // 11          remove u from Q
    // 12          
    // 13          for each neighbor v of u still in Q:
    // 14              alt ← dist[u] + Graph.Edges(u, v)
    // 15              if alt < dist[v]:
    // 16                  dist[v] ← alt
    // 17                  prev[v] ← u
    // 18
    // 19      return dist[], prev[]

    // start with endpoint that isn't original endpoint
    // think of each node as having a link in the chain to the next node
     String startNode = "0";
     HashMap<String, Integer> distance = new HashMap<String, Integer>();
     HashMap<String, String> via = new HashMap<String, String>();
     ArrayList<String> Q = new ArrayList<>();

     new GraphDisplay(graph);
     


     //for each vertex v in Graph.Vertices;
     for (String v: graph.nodes()) {
        // dist[v] ← INFINITY
        distance.put(v, Integer.MAX_VALUE);
        // prev[v] ← UNDEFINED
        via.put(v, "undefined");
        // add v to Q
        Q.add(v);
      }
      //System.out.println(Q.toString());

      //we got two graphs, so fixing that here.
      
      
      // dist[source] ← 0
      distance.replace(source, 0);
    
            
      // while Q is not empty:
      while (Q != null && Q.size() > 0) {
        // u ← vertex in Q with min dist[u]
        String u = null;
        int minValue = 99999;

        for (String i: Q) {
          //System.out.println(distance.keySet());
          //System.out.println(distance.get(i));
          
          if (minValue > distance.get(i)) {
            minValue = distance.get(i);
            u = i;
          }
        }

        //remove u from Q
        u = Q.get(Q.indexOf(u));
        Q.remove(u);

        // for each neighbor v of u still in Q:
        // this for loop is actually just lookin
        for (String v: graph.successors(u)) {
          if (Q.contains(v)) {
            //int alt = distance[u] + graph.edges(u, v);
            //.edgeValueOrDefault() gives the value of the edge or -1 if the edge's value is not found.
            int prevdistance = graph.edgeValueOrDefault(u,v , -1);
            int alt = distance.get(u) + graph.edgeValueOrDefault(u, v, -1);
            
            // if alt < dist[v]:
            if (alt < distance.get(v)) {

            // via gets updated to = neighbor because neighbor is via
            // insert/modify location being looked at (key), via is the node that brought you there (where distance is)
            
            // dist[v] ← alt
              distance.replace(v, alt);
            // prev[v] ← u
              via.replace(v, u);
            }
          }
          
      }
    }

    ArrayList<Object> distanceAndVia = new ArrayList<Object>();
    distanceAndVia.add(distance);
    distanceAndVia.add(via);
    return distanceAndVia;
  }
  

    /**
     * Returns the maximum value found in the specified range of a given array.
     * @param arr A given array of floats.
     * @param lo The lowest index at which the method will start looking for a maximum value.
     * @param hi The highest index at which the method will stop looking for a maximum value.
     * @return The maximum value found in the array within the specified range.
    */
    public static float max(int[] arr, int lo, int hi) { 

        if (arr.length == 0 || hi == 0) {
                return Float.NaN;
        }
        
        else {
            float maxValue = arr[lo];
            for (int i = lo; i < hi; i++) {
                    if (maxValue < arr[i]) {
                        maxValue = arr[i];
                    }
                    else {
                        continue;
                    }
                }
            return maxValue;
        }
        
    }
    /**
     * Prints the number of nodes, number of edges, maximum node degree, average node degree, and other characteristics of the dataset
     * @param listofVerticies (HashSet<String>) The verticies in the dataset being analyzed
     * @param graph (MutableValueGraph<String,Integer>) The graph graphic that will display the dataset in a graph form
     * @param edgeSets (ArrayList<String[]>) A group of the edges in the dataset
     */
    public static void printStatistics(HashSet<String> listOfVertices, MutableValueGraph<String,Integer> graph, ArrayList<String[]> edgeSets) {
      System.out.println("Number of nodes in this graph: " + graph.nodes().size());
      System.err.println("Number of edges: " + graph.edges().size());
      
      int[] storedEdges = new int[edgeSets.size()];
      for (int i = 0; i < listOfVertices.size(); i++) {
        // storedEdges.add(storedEdges.size()-1, graph.degree(listOfVertices.toArray()[i].toString()));
        storedEdges[i] = graph.degree(listOfVertices.toArray()[i].toString());
      }
      System.out.println("Maximum node degree: " + max(storedEdges, 0, storedEdges.length));

      //avg node degree
      int numberOfEdges = 0;
      for (int i = 0; i < graph.edges().size(); i++) {
        numberOfEdges++;
      }
      int avgDegree = numberOfEdges/listOfVertices.size();
      System.out.println("The average node degree: " + avgDegree);

    }
  

    public static void main(String[] args) {
      //MutableGraph<String> graph = GraphBuilder.directed().build();
      MutableValueGraph<String,Integer> graph = ValueGraphBuilder.undirected().build();
      HashSet<String> listOfVertices = new HashSet<>();
      ArrayList<String[]> edgeSets = ReadFile.ReadInFile(graph, listOfVertices);
      
      //System.out.println(listOfVertices.toString());
      //System.out.println(listOfVertices.size());
      int i = 0;
      // while (i < graph.nodes().size()) {
      //   System.out.println(graph.nodes().toArray()[i].toString());
      //   i++;
      // };
      
      // Not sure how to fix this error
      System.out.println(DijkstraAlgorithm(graph,"0"));

      printStatistics(listOfVertices, graph, edgeSets);
      //Animation animatedVersion = new Animation();
      Animation.animation(graph);


      
    }
}
