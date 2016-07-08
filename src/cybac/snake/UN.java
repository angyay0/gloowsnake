package cybac.snake;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class UN extends Activity implements OnClickListener{
	private Button okButton;
	private EditText retriever;
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.username);
		retriever = (EditText) findViewById(R.id.Retriever);
		okButton = (Button) findViewById(R.id.bok);
		okButton.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		if(v == okButton){
			GameScreen.player_name = retriever.getText().toString();
			EXIT();
		}
	}
	
	private void EXIT(){
		super.onDestroy();
		super.finish();
	}

}
