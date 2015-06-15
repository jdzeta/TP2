package com.ecolem_test.tp2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class AppActivity extends ActionBarActivity {
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String login = settings.getString("current_login", "Not found");

        TextView hello = (TextView)this.findViewById(R.id.act_app_text_hello);
        hello.setText("Bienvenue " + login.toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_app, menu);
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

        if (id == R.id.action_go_main) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        if (id == R.id.action_logout) {
            logOut();
        }

        return super.onOptionsItemSelected(item);
    }

    public void logOut(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.act_app_logout_text).setTitle(R.string.act_app_logout_title);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Log.d("DEBUG : ", "Ok button");
                editor.remove("current_login");
                editor.commit();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("DEBUG : ", "Cancel button");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
