package cybac.snake;

import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Vibrator;

import framework.Graphics;
import framework.Game;
import framework.Pixmap;
import framework.Screen;
import framework.Input.TouchEvent;

public class GameScreen extends Screen{
	enum GameState { Ready, Running, Paused, GameOver, Salir}
	
	GameState state = GameState.Running; 
	World world;
	int oldScore = 0;
	boolean shared = false;
	boolean volume = true;
	boolean isPaused = false;
	boolean vibracion = true;
	int level = 0;
	int controlMode = 0;
	
	String score = "0";
	static String player_name ="cybac";
	
	public GameScreen(Game game,int op,int amount) {
		super(game);
		
		world = new World(op,amount); 
		Files.lvs.setLoop(true);
		Files.lvs.play();
		
		vib = (Vibrator) this.game.getAct().getSystemService(this.game.getAct().VIBRATOR_SERVICE);
		
		Settings.load(game.getFileIO());
		volume = Settings.soundEnabled;
		vibracion = Settings.vibrationMode;
		level = Settings.gameLevel;
		controlMode = Settings.controlMode;
	}
	
	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents(); 
		game.getInput().getKeyEvents();
		
		if(state == GameState.Ready) 
			updateReady(touchEvents);
		if(state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if(state == GameState.Paused) 
			updatePaused(touchEvents);
		if(state == GameState.Salir)
			updateSalir(touchEvents);
	}
	
	private void updateReady(List<TouchEvent> touchEvents) {
		if(touchEvents.size() > 0)
			state = GameState.Running;
	}
	
	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		Graphics g= game.getGraphics();
		
		switch(controlMode){
		case 0:
			for(int i = 0; i < touchEvents.size(); i++) {
				TouchEvent event = touchEvents.get(i);
				if(event.tipo == TouchEvent.Pulsado && event.x < 412) {
					if(world.snake.direction == Snake.DOWN || world.snake.direction == Snake.UP && event.y < g.getAlto() ){
						if(event.x < g.getAncho()/2 )
							world.snake.turnLeft();
						if(event.x > g.getAncho()/2 )
							world.snake.turnRight();
						
						return;
					}if(world.snake.direction == Snake.LEFT || world.snake.direction == Snake.RIGHT){
						if(event.y < g.getAlto()/2)
							world.snake.turnUp();
						if(event.y > g.getAlto()/2)
							world.snake.turnDown();
						
						return;
					}
					
				}
			}
			break;
			
		case 1:
			float ID = game.getInput().getAccelY();
			float UD = game.getInput().getAccelX();
			
			if(world.snake.direction == Snake.RIGHT || world.snake.direction == Snake.LEFT){
				if(UD > 0){
					world.snake.turnDown();
					break;
				}else{
					world.snake.turnUp();
					break;
				}
			}else if(world.snake.direction == Snake.UP || world.snake.direction == Snake.DOWN){
				if(ID > 0){
					world.snake.turnRight();
					break;
				}else{
					world.snake.turnLeft();
					break;
				}
			}
			
			break;
		}
		
		for(int i = 0; i < touchEvents.size(); i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.tipo == TouchEvent.Liberado){
				if(enLimite(event,418,252,62,32)){ //Fb Share
					String share= "http://www.facebook.com/sharer.php?s=100&p[url]=https://www.facebook.com/GrupoCYBAC&p[title]=CYBAC Games&p[summary]="+
							"Obtuve "+ score + " Puntos en Glooow Snake"+" En Nivel: " + getLevel();
					
					Web( this.game.getAct(), share);
					
					state = GameState.Paused;
					isPaused = true;
					shared = true;
				}
				else if(enLimite(event,448,286,62,32)){ //Cybac
					Web(this.game.getAct(), "http://www.grupocybac.com/");
					state = GameState.Paused;
					isPaused = true;
				}else if(enLimite(event,416,2,30,50) && isPaused == false){ //Btn Pausa
					state = GameState.Paused;
					isPaused = true;
					Files.lvs.stop();
					Files.lvs.disponer();
					Files.lvs = game.getAudio().cancion("sound/lvl_1.mp3");
				}else if(enLimite(event,448,2,30,50)){ //Btn Salir
					state =  GameState.Salir;
					Files.lvs.stop();
				}else if(enLimite(event,416,106,62,40)){ //Btn Volumen
					if(volume == true)
						volume = false;
					else
						volume = true;
					
					setVolume(volume);
				
				}else if(enLimite(event,416,148,62,40)){ //Btn Vibracion
					if(vibracion == true)
						vibracion = false;
					else
						vibracion = true;
				}
			}
			
		}
		
		world.update(deltaTime); 
		if(world.gameOver) {
			state = GameState.GameOver; 
		}
		
		if(vibracion)
			vib.vibrate(200);
		
				
		
		if(oldScore != world.score) { 
			oldScore = world.score;
			score = "" + oldScore;
		}
	}
	
	private void updatePaused(List<TouchEvent> touchEvents) { 
		for(int i = 0; i < touchEvents.size(); i++) {
			TouchEvent event = touchEvents.get(i); 
			if(event.tipo == TouchEvent.Pulsado) {
				if(enLimite(event,416,54,62,50) && isPaused == true){ //btn play
					state = GameState.Running;
					isPaused = false;
					
					if(shared)
						shared = false;
					
					Files.lvs.setLoop(true);
					Files.lvs.play(); 
				}
				else if(enLimite(event,448,2,30,50)){ //btn salir
					state =  GameState.Salir;
				}else if(enLimite(event,416,252,62,32)){ //btn facebook
					
					String share= "http://www.facebook.com/sharer.php?s=100&p[url]=https://www.facebook.com/GrupoCYBAC&p[title]=CYBAC Games&p[summary]="+
							"Obtuve "+ score + " Puntos en Glooow Snake"+" En Nivel: " + getLevel();
						
						Web( this.game.getAct(), share);
						
						state = GameState.Paused;
						isPaused = true;
						shared = true;
						
				}else if(enLimite(event,448,286,62,32)){ //btn cybac
						Web(this.game.getAct(), "http://www.grupocybac.com/");
						state = GameState.Paused;
						isPaused = true;
				}else if(enLimite(event,416,106,62,40)){ //Btn Volumen
					if(volume == true)
						volume = false;
					else
						volume = true;
					
					setVolume(volume);
				
				}else if(enLimite(event,416,148,62,40)){ //Btn Vibracion
					if(vibracion == true)
						vibracion = false;
					else
						vibracion = true;
				}
			}
		}
	
	}

	private void updateSalir(List<TouchEvent> touchEvents){
			state = GameState.GameOver;	
	}
	
	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		
		g.limpiar(0);
		g.drawPixmap(Files.scene, 0, 0);
		
		drawWorld(world);
		
		if(state == GameState.GameOver)
			GameOver();
	
		
	}
	
	private void drawWorld(World world) { 
		Graphics g = game.getGraphics();	
	
	
		drawStain(g);
		drawSnake(g);
		drawMsg(g);
		drawMenu(g);
		drawWalls(g);
	}
	
	private void drawStain(Graphics g){
		Stain stain = world.stain;
		
		Pixmap stainPixmap = null; 
		if(stain.type == Stain.Verde)
			stainPixmap = Files.stain[0];
		if(stain.type == Stain.Rojo)
			stainPixmap = Files.stain[1];
		if(stain.type == Stain.Azul)
			stainPixmap = Files.stain[2];
		
		int x = stain.x * 32;
		int y = stain.y * 32;
		g.drawPixmap(stainPixmap, x, y);
		
			
	}
	
	private void drawWalls(Graphics g){
		for(int i=0;i<world.walls.size();i++){
			Wall wall = world.walls.get(i);
			g.drawPixmap(Files.wall[1], wall.getPosX() * 32,wall.getPosY()*32);
		}
	}
	
	private void drawSnake(Graphics g){
		int x;
		int y;
		
		Snake snake = world.snake;
		SnakePart head = snake.parts.get(0);
		Pixmap headPixmap = null;
		
		int bc=0;
		for(int i = 1; i < snake.parts.size(); i++) {
			bc = new Random().nextInt(2);
			
			SnakePart part = snake.parts.get(i); 
			x = part.x * 32;
			y = part.y * 32;
			g.drawPixmap(Files.body[bc], x, y);
			
		}
		
		if(snake.direction == Snake.DOWN)
			headPixmap = Files.head[0];
		if(snake.direction == Snake.LEFT)
			headPixmap = Files.head[1];
		if(snake.direction == Snake.RIGHT)
			headPixmap = Files.head[2];
		if(snake.direction == Snake.UP)
			headPixmap = Files.head[3];
	 
		x = head.x * 32+10;
		y = head.y * 32+10;
	
		g.drawPixmap(headPixmap, x - headPixmap.getAncho() / 2, y -headPixmap.getAlto() / 2); 
	
	}
	
	private void drawMenu(Graphics g){
	
		if(isPaused){
			g.drawPixmap(Files.pause, 416, 2); //Pausa
			g.drawPixmap(Files.play, 416, 54 ); //play
		}
		
		if(volume){
			g.drawPixmap(Files.volume, 416, 106); //Volumen
		}
		
		if(vibracion)
			g.drawPixmap(Files.vibrate, 416,148); //Vibracion
		
	//	g.drawPixmap(Files.score,418,212); //Score
		g.drawRect(416, 221, 62, 28, Color.BLACK); //Clear Score
		drawText(g,score,416,221); //Score
		
		if(shared)
			g.drawPixmap(Files.facebook,416,252); //Boton Compartir Score en Facebook
		
	
		
	}
	
	private void drawMsg(Graphics g){
		
		if(state == GameState.Paused)
			g.drawPixmap(Files.pausa, 100, 100);
	}
	
	private void GameOver() {
		if(score.length() > 1){
			RequestPlayerName();
			Settings.addScore(player_name, world.score);
		}
		
		Settings.save(game.getFileIO());
		
		game.setScreen( new MainScreen(game));
		disponer();
	}
	
	public void drawText(Graphics g, String line, int x, int y) {
		for (int i = 0; i < line.length(); i++) {
			char character = line.charAt(i);
			int srcX;
			int srcW;
			switch(character){
				case '0':
					srcX = 0;
					srcW = 10;
					break;
				case '1':
					srcX = 10;
					srcW = 7;
					break;
				default :
					srcX = (character - '0') *10;
					srcW = 10;
					break;
			}
		
			g.drawPixmap(Files.numeros, x+(10*i), y, srcX, 0, srcW, 16);
		}
	}

	@Override
	public void pausa() {
		if(state == GameState.Running){ 
			state = GameState.Paused;
			isPaused = true;
		}
		
		if(world.gameOver) { 
			Settings.addScore(player_name,world.score);
			Settings.save(game.getFileIO());
		}
		
		Files.lvs.stop();
		Files.lvs.disponer();
		Files.lvs = game.getAudio().cancion("sound/lvl_1.mp3");
	}

	@Override
	public void resumen() {
		Files.lvs.setLoop(true);
		Files.lvs.play();
	}

	@Override
	public void disponer() {
		Files.lvs.stop();
		Files.lvs.disponer();
		Files.lvs = game.getAudio().cancion("sound/lvl_1.mp3");
		
	}
	
	private boolean enLimite(TouchEvent event, int x, int y, int width, int height) {
		if(event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1) 
			return true;
		else
			return false;
	}

	private void Web(Activity activity,String pagina){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(pagina));
		activity.startActivity(intent);
	}
	
	private void setVolume(boolean volON){
		AudioManager am = (AudioManager) this.game.getAct().getSystemService(this.game.getAct().AUDIO_SERVICE);
		
		if(!volON){
			for(int i=0;i<20;i++)
				am.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_LOWER,AudioManager.FLAG_SHOW_UI);
		}else{
			for(int i=0;i<20;i++)
				am.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_RAISE,AudioManager.FLAG_SHOW_UI);
		}	
		
	}
	
	private void RequestPlayerName(){
		Intent intent = new Intent(this.game.getAct(),cybac.snake.UN.class);
		game.getAct().startActivity(intent);
	
	}
	
	private String getLevel(){
		String lv = "Facil";
		if(Settings.gameLevel == 1)
			lv = "Normal";
		if(Settings.gameLevel == 2)
			lv = "Dificil";
		if(Settings.gameLevel == 3)
			lv = "Experto";
		if(Settings.gameLevel == 4)
			lv =  "Geek";
			
		return lv;
	}
	
	private Vibrator vib;
	
	
}
