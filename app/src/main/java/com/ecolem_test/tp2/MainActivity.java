package com.ecolem_test.tp2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        String current_login = settings.getString("current_login", "Not found");
        if (!current_login.equals("Not found")){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        // Example setting sound mode
        boolean silent = settings.getBoolean("silentMode", false);
        setSilent(silent);


        final Button login = (Button) findViewById(R.id.act_main_login_btn);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                loginBtnClick(v);
            }
        });

        final Button register = (Button) findViewById(R.id.act_main_register_btn);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                registerBtnClick(v);
            }
        });

    }

    private void setSilent(boolean silent) {
        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (!silent) {
            audio.setRingerMode(0);
        } else {
            audio.setRingerMode(1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void registerBtnClick(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    private void loginBtnClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
