package cybac.snake;

import java.util.ArrayList; 
import java.util.List;

import android.app.Service;
import android.os.Vibrator;

public class Snake {
	public static final int UP = 0;
	public static final int LEFT = 1; 
	public static final int DOWN = 2; 
	public static final int RIGHT = 3;
	
	public List<SnakePart> parts = new ArrayList<SnakePart>(); 
	
	public int direction;	
	
	public Snake() { 
		direction = UP;
		parts.add(new SnakePart(5,6));
		parts.add(new SnakePart(5,7));
		parts.add(new SnakePart(5,8));
	}
	
	public void turnLeft(){
		direction = LEFT;
	}
	
	public void turnRight(){
		direction = RIGHT;
	}
	
	public void turnUp(){
		direction = UP;
	}
	
	public void turnDown(){
		direction = DOWN;
	}
	
	public void eat() {
		SnakePart end = parts.get(parts.size()-1);
		parts.add(new SnakePart(end.x, end.y));
	}

	public void advance() {
		SnakePart head = parts.get(0);

		for(int i = parts.size() - 1; i > 0; i--) {
			SnakePart before = parts.get(i-1); 
			SnakePart part = parts.get(i);
			part.x = before.x;
			part.y = before.y;
		}
		
		if(direction == UP) 
			head.y -=1;
		if(direction == LEFT) 
			head.x -= 1;
		if(direction == DOWN) 
			head.y += 1;
		if(direction == RIGHT) 
			head.x += 1;
		
		if(head.x < 0)
			head.x = 12;
		if(head.x > 12) 
			head.x = 0;
		if(head.y < 0)
			head.y = 9;
		if(head.y > 9) 
			head.y = 0;
		}
	

	public boolean checkBitten() { 
		SnakePart head = parts.get(0);
		for(int i = 1; i <  parts.size(); i++) {
			SnakePart part = parts.get(i);
			if(part.x == head.x && part.y == head.y)
				return true; 

		}
		return false;
	}

	
}
