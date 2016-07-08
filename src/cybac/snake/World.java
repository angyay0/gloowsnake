package cybac.snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.os.Vibrator;

public class World {
	static final int WORLD_WIDTH = 13;
	static final int WORLD_HEIGHT = 10;
	static float TICK_INITIAL = 0.5f;
	static float TICK_DECREMENT = 0.05f;
	static boolean vib = false;
	static boolean wallAdded = false;
	int opcion,amount;
	
	public Snake snake;
	public Stain stain;
	
	public boolean gameOver = false;
	public int score = 0;
	public int SCORE_INCREMENT = 0;
	
	boolean fields[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT]; 
	public Random random = new Random();
	float tickTime = 0;
	static float tick = TICK_INITIAL;
	static List<Wall> walls = new ArrayList<Wall>();
	
	
	public World(int opcion,int amount) {
		this.opcion = opcion;
		this.amount = amount;
		snake = new Snake();
		placeStain();
	}
	
	private void addWall(int q){
		for(int i=0; i< q;i++){
			int wX = random.nextInt(WORLD_WIDTH-1);
			int wY = random.nextInt(WORLD_HEIGHT-1);
		
			while (true) {
				if (fields[wX][wY] == false &&fields[wX][wY+1] == false) 
					break;
				wX += 1;
				if (wX >= WORLD_WIDTH) {
					wX = 0;
					wY += 1;
					if (wY >= WORLD_HEIGHT) {
						wY = 0;
				}
			}
			}
			walls.add( new Wall(wX,wY));
		}
		wallAdded = true;
		
		
	}
	
	private void placeStain() {
		for (int x = 0; x < WORLD_WIDTH; x++) {
			for (int y = 0; y < WORLD_HEIGHT; y++) { 
				fields[x][y] = false;
			} 
		}
		
		
		for (int i = 0; i < snake.parts.size(); i++) {
			SnakePart part = snake.parts.get(i);
			fields[part.x][part.y] = true; 
		}
		
		if(walls.size()>0){
			for(int i=0;i<walls.size();i++){
				Wall wall = walls.get(i);
				fields[wall.getPosX()][wall.getPosY()] = true;
				fields[wall.getPosX()][wall.getPosY()+1] = true;
			}
		}
		
		int stainX = random.nextInt(WORLD_WIDTH);
		int stainY = random.nextInt(WORLD_HEIGHT); 
		
		while (true) {
				if (fields[stainX][stainY] == false) 
					break;
				stainX += 1;
				if (stainX >= WORLD_WIDTH) {
					stainX = 0;
					stainY += 1;
					if (stainY >= WORLD_HEIGHT) {
						stainY = 0;
					}
				}
		}
		
		int aux= random.nextInt(3);
		stain = new Stain(stainX, stainY, aux);
		
		if(aux == 0)
			SCORE_INCREMENT= Stain.pVerde;
		if(aux == 1)
			SCORE_INCREMENT= Stain.pRojo;
		if(aux == 2)
			SCORE_INCREMENT= Stain.pAzul;
	
	}
	
	public void update(float deltaTime) { 
		if (gameOver)
			return;
		if(opcion == 1 && wallAdded == false)
			addWall(amount);
		
		tickTime += deltaTime;
		while (tickTime > tick) { 
			tickTime -= tick; snake.advance();
			if (snake.checkBitten()) {
				gameOver = true;
				return; 
			}
			
			if(checkWallCollide()){
				gameOver = true;
				return;
			}
				
			SnakePart head = snake.parts.get(0);
				if (head.x == stain.x && head.y == stain.y) {
					score += SCORE_INCREMENT;
					snake.eat();
					vib = true;
					if (snake.parts.size() == WORLD_WIDTH * WORLD_HEIGHT) {
						gameOver = true;
						return;
					}else {
						placeStain(); 
					}
					
					if (score % 100 == 0 && tick - TICK_DECREMENT > 0) {
						tick -= TICK_DECREMENT;
					}
				}
		}
	}
	
	
	private boolean checkWallCollide(){
		SnakePart head = snake.parts.get(0);
		for(int i=0;i<walls.size();i++){
			Wall wallp = walls.get(i);
			if(head.x == wallp.getPosX() && (head.y == wallp.getPosY() || head.y == wallp.getPosY()+1))
				return true;
		}
		return false;
		
	}
	
}
