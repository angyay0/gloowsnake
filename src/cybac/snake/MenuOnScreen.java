package cybac.snake;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import framework.Game;
import framework.Graphics;
import framework.Pixmap;
import framework.Screen;
import framework.Input.TouchEvent;

public class MenuOnScreen extends Screen {

	String me = "http://www.facebook.com/angyay0";
	
	public MenuOnScreen(Game game) {
		super(game);
		Settings.load(game.getFileIO());
	}

	@Override
	public void update(float tiempoDelta) {		
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		for(int i = 0; i < touchEvents.size(); i++){
			TouchEvent event= touchEvents.get(i);
			if(event.tipo == TouchEvent.Pulsado){
				if(enLimite(event, 356, 212, 32, 32)){ //Boton Home
					Settings.save(game.getFileIO());
					game.setScreen( new MainScreen(game));
				}else if(enLimite(event, 228, 212, 32, 32)){ //Boton Developer
					Web(this.game.getAct(), me);
					pausa();
				}else if(enLimite(event, 339, 180, 32, 32)){ //Boton normal
					Settings.controlMode = 0;
				}else if(enLimite(event, 278, 180, 32, 32)){ //Boton Acelerometro
					Settings.controlMode = 1;	
				}else if(enLimite(event, 292, 146, 64,32)){ //Seleccion de niveles de juego
					Settings.gameLevel++;
					if(Settings.gameLevel >  4)
						Settings.gameLevel = 0;
				}else if(enLimite(event, 280, 114, 32, 32)){//Boton Vibracion
					if(Settings.vibrationMode)
						Settings.vibrationMode = false;
					else 
						Settings.vibrationMode = true;
				}else if(enLimite(event, 360, 82,32, 32)){ //Boton FX
					if(Settings.FxMode)
						Settings.FxMode = false;
					else
						Settings.FxMode = true;
				}else if(enLimite(event, 245, 82, 32, 32)){ //Boton Sonido
					if(Settings.soundEnabled)
						Settings.soundEnabled = false;
					else
						Settings.soundEnabled = true;
				}
			}
		}	
		
		Settings.save(game.getFileIO());
	}

	@Override
	public void present(float tiempoDelta) {
		Graphics g = game.getGraphics();
		Pixmap pixmap = null;
		
		switch(Settings.gameLevel){	
		case 0:
			pixmap = Files.menuItems[7];
			break;
		case 1:
			pixmap = Files.menuItems[8];
			break;
		case 2:
			pixmap = Files.menuItems[9];
			break;
		case 3:
			pixmap = Files.menuItems[10];
			break;
		default:
			pixmap = Files.menuItems[11];
			break;
		}
		
		g.limpiar(0);
		
		g.drawPixmap(Files.Menu, 100, 50); //Menu_on_Screen
		
		if(Settings.soundEnabled) //Sonido
			g.drawPixmap(Files.menuItems[0], 245, 82); // Si
		else
			g.drawPixmap(Files.menuItems[1], 245, 82); // No
		
		if(Settings.FxMode) //FX
			g.drawPixmap(Files.menuItems[0], 360, 82);  //Si
		else
			g.drawPixmap(Files.menuItems[1], 360, 82); //No
		
		if(Settings.vibrationMode) //Vibracion
			g.drawPixmap(Files.menuItems[0], 280, 114); //Si
		else
			g.drawPixmap(Files.menuItems[1], 280, 114); //No
		
		g.drawPixmap(pixmap, 292, 146); //Dificultad actual
		
		g.drawPixmap(Files.menuItems[2], 278, 180); //Iconos de controles // Acelerometro
		g.drawPixmap(Files.menuItems[3], 339, 180); //Normal Touch
		g.drawPixmap(Files.menuItems[6], 228, 212); //Developer
		g.drawPixmap(Files.menuItems[4], 356, 212); //BackHome
			

	}

	@Override
	public void pausa() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resumen() {
		// TODO Auto-generated method stub

	}

	@Override
	public void disponer() {
		
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
}
