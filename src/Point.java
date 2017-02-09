import java.awt.Color;

public class Point {
	private int x;
	private int y;
	private Color color;
	public static final int DIAMETER=30;//直径
	public Point(int x,int y,Color color){  
	      this.x=x;  
	      this.y=y;  
	      this.color=color;  
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}  
	
}
