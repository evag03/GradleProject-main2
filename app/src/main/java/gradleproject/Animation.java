package gradleproject;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.ImmutableGraph;
import com.google.common.graph.MutableValueGraph;
import java.awt.Color;
import java.awt.Point;

public class Animation {

  public static void animation(MutableValueGraph<String,Integer> graph) {
    GraphDisplay d = new GraphDisplay(graph);
    d.labelOffset = new Point(0,3);

    String[] labels = {"C", "E", "F", "A", "D", "B"};
    while (true) {
      for (String lbl: labels) {
        try {
          Thread.sleep(500);
        } catch (Exception e) {
        }
        d.setColor(lbl,Color.BLUE);
      }
      for (Object edge: d.getEdgeSet()) {
        try {
          Thread.sleep(500);
        } catch (Exception e) {
        }
        d.setColor(edge,Color.GREEN);
      }
      for (String lbl: labels) {
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
      }
    }
    
  }
  public static void main(String[] args) {
    
  }
}

