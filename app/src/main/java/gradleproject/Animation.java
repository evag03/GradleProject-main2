package gradleproject;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.ImmutableGraph;
import com.google.common.graph.MutableValueGraph;
import java.awt.Color;
import java.awt.Point;
import java.util.Set;

public class Animation {

  public static void animation(MutableValueGraph<String,Integer> graph) {
    GraphDisplay d = new GraphDisplay(graph);

    d.labelOffset = new Point(0,3);

    // String[] labels = {"C", "E", "F", "A", "D", "B"};
    Set<String> nodes = graph.nodes();

    while (true) {
      for (String lbl: nodes) {
        try {
          Thread.sleep(500);
        } catch (Exception e) {
        }
        d.setColor(lbl,Color.BLUE);
      }
      System.out.println(d.getEdgeSet().size());
      int counter = 0;
      for (Object edge: d.getEdgeSet()) {
        System.out.println(counter);
        counter = counter + 1;
        try {
          Thread.sleep(500);
        } catch (Exception e) {
        }
        d.setColor(edge,Color.GREEN);
      }
      for (String lbl: nodes) {
        try {
          Thread.sleep(500);
        } catch (Exception e) {
        }
        d.setColor(lbl,Color.RED);
      }
      for (Object edge: d.getEdgeSet()) {
        try {
          Thread.sleep(500);
        } catch (Exception e) {
        }
        d.setColor(edge,Color.MAGENTA);
        //System.out.println(d.getColor(edge));
      }
    }
    
  }
  public static void main(String[] args) {
    
  }
}

