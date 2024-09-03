
public class NodeUser implements Comparable<NodeUser>,IDrawableUserGraph {

	public double YValue;
	public double XValue;
	public String Name;
	
	public NodeUser(String Name,double Xvalue,double Yvalue) {
		this.Name = Name;
		XValue = Xvalue;
		YValue =Yvalue;
	 }
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public double getYValue() {
		return YValue;
	}
	
	public void setYValue(double YValue) {
		this.YValue = YValue;
	}
	
	public double getXValue() {
		return XValue;
	}
	
	public void setXValue(double XValue) {
		this.XValue = XValue;
	}
	public void accept(IDrawUserGraph draw) {
		draw.draw(this);
	}
	@Override
	public int compareTo(NodeUser o) {
		if(this.YValue != o.YValue ) {
			return -1;
		}
		if(this.XValue !=o.XValue) {
			return -1;
		}
		return 0;
	}

}
