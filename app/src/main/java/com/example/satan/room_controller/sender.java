package com.example.satan.room_controller;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by satan on 2/6/2017.
 */

public class sender extends AppCompatActivity implements View.OnClickListener {
TextView text;
    Button speak;
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sender);
        text=(TextView)findViewById(R.id.text);
        speak=(Button)findViewById(R.id.speak);
        speak.setOnClickListener(this);
        }
    //getting voice input
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "speak something");
        try {
            startActivityForResult(intent, 1);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "not compatible",
                    Toast.LENGTH_SHORT).show();
        }
    }


//after processing voice

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Toast.makeText(getApplicationContext(),
                            "recognised",
                            Toast.LENGTH_SHORT).show();
                        text.setText(result.get(0));

                }
                break;
            }

        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.speak)
            promptSpeechInput();
    }
}
