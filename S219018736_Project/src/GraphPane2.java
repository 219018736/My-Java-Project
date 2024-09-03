import com.jwetherell.algoriths.data.structures.Graph;
import com.jwetherell.algoriths.data.structures.Graph.Vertex;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GraphPane2 extends Canvas{
	
	//declaring variables
  private GraphicsContext graphic;
  private DrawUser Nodes;
  
  public GraphPane2() {
	  super();
	  graphic = getGraphicsContext2D();
	  Nodes = new DrawUser(graphic);
	  setHeight(800);
	  setWidth(900);
  }
  /**
   * drawing nodes that are provided from the graph on pane
   * @param graphs
   */
  public void drawNodes(Graph<NodeUser> graphs) {
	  //Drawing nodes considering user information that already stored on the graph
	  for(Vertex<NodeUser> draw : graphs.getVertices()){
		  draw.getValue().accept(Nodes);
	  }
  }
}
