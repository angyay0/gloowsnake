package cybac.snake;

public class Wall {

	private int X;
	private int Y;
	private int Width;
	private int Height;
	
	public Wall(int x,int y){
		X=x;
		Y=y;
	
	}

	public int getPosX(){
		return X;
	}
	
	public int getPosY(){
		return Y;
	}
	
}

