package croc.models;

import com.badlogic.gdx.math.Rectangle;

public class DataRectangle extends Rectangle {
	
	public DataRectangle(int x, int y, int height, int width, int value){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.value = value;
	}

	public int value;
	
}
