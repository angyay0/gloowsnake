package cybac.snake;

import framework.Graphics;
import framework.Graphics.FormatoPixeles;
import framework.Screen;
import framework.Game;
import framework.FileIO;

public class SplashScreen extends Screen{
	private float time,time2;
	private float dif;
	private int x=153;
	
	public SplashScreen(Game game){
		super(game);
		time= (float) (System.nanoTime()/1000000000.0);
	}

	@Override
	public void update(float tiempoDelta) {
		Graphics g= game.getGraphics();
		
		//Carga de Recursos Para SplashScreen
		Files.bar= g.pixMap("splash/bar.png",FormatoPixeles.RGB565 );
		Files.cybac = g.pixMap("cybac.png", FormatoPixeles.RGB565);
		Files.logo= g.pixMap("cybac_dots.png", FormatoPixeles.RGB565);
		Files.pasto= g.pixMap("splash/pasto.png", FormatoPixeles.RGB565);
		Files.block[0]= g.pixMap("splash/oc.png", FormatoPixeles.RGB565);
		Files.block[1]= g.pixMap("splash/imix.png", FormatoPixeles.RGB565);
		Files.block[2]= g.pixMap("splash/ik.png", FormatoPixeles.RGB565);
		Files.block[3]= g.pixMap("splash/cimi.png", FormatoPixeles.RGB565);
		Files.block[4]= g.pixMap("splash/lamat.png", FormatoPixeles.RGB565);
		Files.loading= g.pixMap("splash/loading.png", FormatoPixeles.RGB565);
		//SplashScreen
		
		g.limpiar(0);
		
		time2= (float) (System.nanoTime()/1000000000.0);
		dif= time2-time;
		
		
		if(dif < 3)
			g.drawPixmap(Files.cybac, 100,30 );
		
		else if(dif > 3 && dif < 12){
			
			g.drawPixmap(Files.logo, 150, 0);
			g.drawPixmap(Files.bar, (g.getAncho()/2)-(g.getAncho()/4)+20,(g.getAlto()/2)+(g.getAlto()/4));
			g.drawPixmap(Files.loading, 80,g.getAlto()/2 +40);
			
			
			
			if(dif > 4){
				g.drawPixmap(Files.block[0], x, g.getAlto()-66);
				
				//MainScreen Recursos
				Files.Title =  g.pixMap("main/Title.png", FormatoPixeles.RGB565);
				Files.Menu = g.pixMap("main/Menu_Main.png", FormatoPixeles.RGB565);
				Files.menuItems[0] = g.pixMap("main/si.png", FormatoPixeles.RGB565);
				Files.menuItems[1] = g.pixMap("main/no.png", FormatoPixeles.RGB565);
				Files.menuItems[2] = g.pixMap("main/accel_control.png", FormatoPixeles.RGB565);
				Files.menuItems[3] = g.pixMap("main/normal_control.png", FormatoPixeles.RGB565);
				Files.menuItems[4] = g.pixMap("main/home.png", FormatoPixeles.RGB565);
				Files.menuItems[5] = g.pixMap("main/High_Scores.png", FormatoPixeles.RGB565);
				Files.menuItems[6] = g.pixMap("main/Developer.png", FormatoPixeles.RGB565);
				Files.menuItems[7] = g.pixMap("main/facil.png", FormatoPixeles.RGB565);
				Files.menuItems[8] = g.pixMap("main/normal.png", FormatoPixeles.RGB565);
				Files.menuItems[9] = g.pixMap("main/dificil.png", FormatoPixeles.RGB565);
				Files.menuItems[10] = g.pixMap("main/experto.png", FormatoPixeles.RGB565);
				Files.menuItems[11] = g.pixMap("main/geek.png", FormatoPixeles.RGB565);
				
				Files.theme = game.getAudio().cancion("sound/Opening.mp3");
				
				for(int i=0;i<3;i++)
					Files.Go[i] = g.pixMap("main/i"+(i+1)+".png", FormatoPixeles.RGB565); 
				
				
				//MainScreen Recursos
				
				if(dif > 5.50){
					g.drawPixmap(Files.block[1], x+(34*1), g.getAlto()-65);
					/* GameScreen */
					Files.scene = g.pixMap("main/scene.png", FormatoPixeles.RGB565);
					Files.body[0] = g.pixMap("snake/body1.png", FormatoPixeles.RGB565);
					Files.body[1] = g.pixMap("snake/body2.png", FormatoPixeles.RGB565);
				//	Files.body[2] = g.pixMap("snake/BodyB.png", FormatoPixeles.RGB565);
					
					Files.stain[0] = g.pixMap("snake/StainG.png", FormatoPixeles.RGB565);
					Files.stain[1] = g.pixMap("snake/StainR.png", FormatoPixeles.RGB565);
					Files.stain[2] = g.pixMap("snake/StainB.png", FormatoPixeles.RGB565);
					
					Files.head[0] = g.pixMap("snake/head.png", FormatoPixeles.RGB565);
					Files.head[1] = g.pixMap("snake/head-l.png", FormatoPixeles.RGB565);
					Files.head[2] = g.pixMap("snake/head-r.png", FormatoPixeles.RGB565);
					Files.head[3] = g.pixMap("snake/head-u.png", FormatoPixeles.RGB565);
					
					Files.wall[0] = g.pixMap("snake/walla.png", FormatoPixeles.RGB565);
					Files.wall[1] = g.pixMap("snake/wallb.png",FormatoPixeles.RGB565);
					Files.lvs = game.getAudio().cancion("sound/lvl_1.mp3");
					/* GameScreen */
					
					if(dif > 6.70){
						g.drawPixmap(Files.block[2], x+(34*2), g.getAlto()-65);
					
						/* FX */		
						Files.pausa = g.pixMap("fx/pausan.png", FormatoPixeles.RGB565);
						Files.score = g.pixMap("fx/score.png", FormatoPixeles.RGB565);
						Files.numeros = g.pixMap("fx/numero.png", FormatoPixeles.RGB565);
						Files.info = g.pixMap("fx/info.jpg", FormatoPixeles.RGB565);
						Files.facebook = g.pixMap("fx/bt_facebook_on.png", FormatoPixeles.RGB565);
						Files.play = g.pixMap("fx/bt_play_on.png", FormatoPixeles.RGB565);
						Files.pause = g.pixMap("fx/bt_pause_on.png", FormatoPixeles.RGB565);
						Files.exit = g.pixMap("fx/bt_salir_on.png", FormatoPixeles.RGB565);
						Files.vibrate = g.pixMap("fx/bt_vibrar_on.png", FormatoPixeles.RGB565);
						Files.volume = g.pixMap("fx/bt_volume_on.png", FormatoPixeles.RGB565);
					
						
						for(int i=0; i<10;i++)
							Files.dotA[i] = g.pixMap("dotA/"+i+".png", FormatoPixeles.RGB565);
						
						/* FX */
						
						if(dif > 8.12){
							g.drawPixmap(Files.block[3], x+(34*3), g.getAlto()-65);
							
							if(dif > 10)
								g.drawPixmap(Files.block[4], x+(34*4), g.getAlto()-65);

								Settings.load(game.getFileIO());
						}
						
					}
				}
			}
			
			g.drawPixmap(Files.pasto, 80, g.getAlto()-41);
	
		}
		
		else if(dif > 13)
			game.setScreen( new MainScreen(game));
		
		
			
	}

	@Override
	public void present(float tiempoDelta) {	
	}

	@Override
	public void pausa() {
		
	}

	@Override
	public void resumen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disponer() {
	}


}
