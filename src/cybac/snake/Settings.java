package cybac.snake;

import framework.FileIO;
import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter;


public class Settings {
	public static boolean soundEnabled = true; //Habilitar sonido?
	public static boolean vibrationMode = true; //Opcion de vibracion
	public static boolean FxMode = true; //Opcion de Efectos de Sonido
	public static int controlMode = 0; //Default, punto sen la pantalla //0 = normalTouch //1 = Accelerometer
	public static int gameLevel = 0;//Default, pantalla limpia. 
	public static int[] highscores = new int[] { 1000, 800, 50, 30, 10 }; //High Scores
	public static String[] names = new String[] {"Ariel","Cybac","Angel","Ricardo","Snaky"};
	
	public static void load(FileIO files) { 
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader( files.leerArchivo(".cybac")));
			soundEnabled = Boolean.parseBoolean(in.readLine());
			vibrationMode =  Boolean.parseBoolean(in.readLine());
			FxMode = Boolean.parseBoolean(in.readLine());
			
			controlMode = Integer.parseInt(in.readLine());
			gameLevel = Integer.parseInt(in.readLine());
			
			for (int i = 0; i < 5; i++) {
				highscores[i] = Integer.parseInt(in.readLine());
			}
			
			for(int i = 0;i < 5; i++){
				names[i] = in.readLine();
			}
			
		} catch (IOException e) {
	
		} catch (NumberFormatException e) {
	
		} finally { 
			try {
				if (in != null) in.close();
	
			} catch (IOException e) {	}
		}
	}
	
	public static void save(FileIO files) { 
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter( files.escribirArchivo(".cybac")));
			out.write(Boolean.toString(soundEnabled)); out.write("\n");
			out.write(Boolean.toString(vibrationMode)); out.write("\n");
			out.write(Boolean.toString(FxMode)); out.write("\n");
			
			out.write(Integer.toString(controlMode)); out.write("\n");
			out.write(Integer.toString(gameLevel)); out.write("\n");
			
			for (int i = 0; i < 5; i++) {
				out.write(Integer.toString(highscores[i])); out.write("\n");
			}
			
			for(int i=0; i < 5; i++){
				out.write(names[i]); out.write("\n");
			}
			
		} catch (IOException e) { } 
		
		finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {}
		}
	}
	
	public static void addScore(String name,int score) { 
		for (int i = 0; i < 5; i++) {
			if (highscores[i] < score) { 
				for (int j = 4; j > i; j--){
					highscores[j] = highscores[j - 1];
					names[j] = names[j-1];
				}
				
			highscores[i] = score;
			names[i] = name;
			break;
			} 
		}
	}

}
