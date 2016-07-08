package cybac.snake;

import framework.Screen;
import framework.complete.MainThread;

public class Launcher extends MainThread {

	public Screen getStartScreen() {
		return new SplashScreen(this);
	}
	
	@Override
	public boolean onSearchRequested(){
		return false;
	}
	

}
