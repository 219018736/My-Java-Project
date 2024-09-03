import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.jwetherell.algoriths.data.structures.Graph;

public class UserGraph extends Graph<NodeUser> {
 
	//declaring my variables
	private Scanner scan;
	private NodeUser node = null;
	private String path = "data/User.txt";
	
	public UserGraph() {
		super();
		File file = new File(path);
		try {
			File newFile = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			path = newFile.getParent() + "data/User.txt";
			scan = new Scanner(file);
		}catch(IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Reading nodes users in the graph to create graph of users
	 */
	public void readFromFile() {
		
		double Yvalue = 0;
		double Xvalue = 0;
		
		//StringTokenizer token = null;
		while(scan.hasNext()) {
			String lineSearch = scan.nextLine();
			StringTokenizer token = new StringTokenizer(lineSearch,"#");
			String token1 = token.nextToken();
			StringTokenizer token2 = new StringTokenizer(token1);
			
			//reading user information
			Yvalue = Double.parseDouble(token2.nextToken());
			Xvalue = Double.parseDouble(token2.nextToken());
			node = new NodeUser(token2.nextToken(),Xvalue,Yvalue);
			
			//create vertex
			Vertex<NodeUser> vertexList = new Vertex<>(node);
			getVertices().add(vertexList);
		}
		
	}
	/**
	 * function returning graph of users
	 * @return graph
	 */
	public Graph<NodeUser> Graph(){
		return this;
	}
}
