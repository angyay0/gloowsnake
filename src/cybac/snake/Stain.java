package cybac.snake;

public class Stain {
	public static final int Verde = 0;
	public static final int Rojo = 1; 
	public static final int Azul = 2;
	
	public static final int pVerde = 2;
	public static final int pRojo = 30; 
	public static final int pAzul = 50;
	
	public int x, y;
	public int type;
	
	public Stain(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type; 
	}
}
