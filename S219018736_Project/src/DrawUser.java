import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawUser implements IDrawUserGraph{

	//declaring variables
	private GraphicsContext graphic;
    double y = 0;
    double x = 0;
	public DrawUser(GraphicsContext graphic){
		 this.graphic = graphic;
	 }
	
	@Override
	public void draw(NodeUser user) {
		y = user.getYValue();
		x = user.getXValue();
		
		int latitude = (int)x;
		int longitude = (int)y;
		int latitudeSize = (int)y -1;
		int longitudeSize = (int)x -1;
		
		//setting colour to fill all nodes ovals
		graphic.setFill(Color.LIGHTCORAL);
		graphic.fillOval(x, y, 20, 20);
		
		//setting colour for name,latitude and longitude
		graphic.setFill(Color.LIGHTCORAL);
		graphic.fillText(user.getName()+ ">>" + latitude + ":" + longitude,latitudeSize,longitudeSize,600);
	}

}
