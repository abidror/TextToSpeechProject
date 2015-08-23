package com.example.texttospeechproject;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {

	private TextToSpeech tts;
	private EditText text;
	private Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		tts = new TextToSpeech(this, this);
		text = (EditText) findViewById(R.id.editText1);
		btn = (Button)findViewById(R.id.button1);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				speakOut();
			}
		});
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
		if( tts != null)
		{
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		
		if( status == TextToSpeech.SUCCESS)
		{
			int result = tts.setLanguage(Locale.US);
			
			if( result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
			{
				Log.e("TTS", "This language is not supported");
				
				
			}
			else
			{
				btn.setEnabled(true);
				speakOut();
			}
		}
	}
	
	private void speakOut()
	{
		String txt = text.getText().toString();
		
		tts.speak( txt, TextToSpeech.QUEUE_FLUSH, null);
	}

}
