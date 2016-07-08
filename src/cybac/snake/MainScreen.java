package cybac.snake;

import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import framework.Graphics;
import framework.Pixmap;
import framework.Screen;
import framework.Game;
import framework.Input.TouchEvent;


public class MainScreen extends Screen {
	boolean bol=false;
	int gop=0; //Control de animacion de Pulsacion
	int goc=0; //Control de animacion del logo de cybac
	float dt,pt,pt2,dt2;
	private World world;
	
	public MainScreen(Game game){
		super(game);
		Files.theme.play();
		pt =pt2=  System.nanoTime()/1000000000;
		world = new World(0,0);
		world.snake.direction = Snake.LEFT;
		Settings.load(game.getFileIO());
		
	}

	@Override
	public void update(float tiempoDelta) {
		Settings.load(game.getFileIO());
		Graphics g= game.getGraphics();
		int level = Settings.gameLevel;
		
		
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		g.limpiar(0);
		
		for(int i = 0; i < touchEvents.size(); i++){
			TouchEvent event= touchEvents.get(i);
			
			if(event.tipo == TouchEvent.Pulsado){
				if(enLimite(event,360, 140,50,50)){
					game.setScreen( new MenuOnScreen(game));
				}else if(enLimite(event,100, g.getAlto()/2+80,300,32)){
					//Play
					Files.theme.stop();
					bol=true; 
					pt=dt=0;
					game.setScreen( new GameScreen(game,1,level));	
				}	
			}
		}
			
	}

	@Override
	public void present(float tiempoDelta) {
		Graphics g= game.getGraphics();
		
		dt = System.nanoTime()/1000000000;
		if(dt-pt >= 0.80 && !bol){
			gop ++;
			if(gop>2)
				gop=0;
		}
		pt=dt;
		//BlackBackground
		g.limpiar(0);
	
		if(!bol)
			Files.theme.play();	

			
		//Title	
		g.drawPixmap(Files.Title,100, 25);
		g.drawPixmap(Files.Go[gop],100, g.getAlto()/2+80);
		g.drawPixmap(Files.info, 360, 140); //menu OnScreen
		g.drawPixmap(Files.menuItems[5], 50, 140); //HighScores
		//Title
			
		animateSnake(g,tiempoDelta);
		animateCybac(g);
		
	}

	private void animateSnake(Graphics g,float deltaTime){
		int x,y;
		int bc;
		
		world.update(deltaTime);
		
		Snake snake = world.snake;
		SnakePart head = snake.parts.get(0);
		
		for(int i = 1; i < snake.parts.size(); i++) {
			bc = new Random().nextInt(2);
			
			SnakePart part = snake.parts.get(i); 
			x = part.x * 32;
			y = part.y * 32;
			g.drawPixmap(Files.body[bc], x, y);
		}
		
		x = head.x * 32+10;
		y = head.y * 32+10;
	
		g.drawPixmap(Files.head[1], x - Files.head[1].getAncho() / 2, y -Files.head[1].getAlto() / 2); 
	
	}
	
	private void animateCybac(Graphics g){
		dt2 = System.nanoTime()/1000000000;
		if(dt2-pt2 >= 0.08){
			goc++;
			if(goc>9)
				goc=0;
		pt2=dt2;
		}
		
		g.drawPixmap(Files.dotA[goc],0,0);
			
	}
	
	
	@Override
	public void pausa() {
		Files.theme.stop();
		Files.theme = game.getAudio().cancion("sound/Opening.mp3");

	}

	@Override
	public void resumen() {
		Files.theme.play();

	}

	@Override
	public void disponer() {
		Files.theme.stop();
		Files.theme.disponer();
		Files.theme = game.getAudio().cancion("sound/Opening.mp3");

	}
	
	private boolean enLimite(TouchEvent event, int x, int y, int width, int height) {
		if(event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1) 
			return true;
		else
			return false;
	}
	 
	
}
