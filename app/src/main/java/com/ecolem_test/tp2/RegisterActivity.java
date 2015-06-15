package com.ecolem_test.tp2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends ActionBarActivity {
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Button submit = (Button) findViewById(R.id.act_reg_submit_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                submitRegForm();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

    private void submitRegForm(){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String err = "";

        EditText name = (EditText)this.findViewById(R.id.act_reg_text_name);
        EditText email = (EditText)this.findViewById(R.id.act_reg_text_email);
        EditText pwd = (EditText)this.findViewById(R.id.act_reg_pwd);

        if (name.getText().toString().length() == 0 || name.getText().toString().equals(" ")){
            err += "Missing field : Nom";
        }
        if (email.getText().toString().length() == 0 || email.getText().toString().equals(" ")){
            err += "Missing field : Email";
        }
        if (pwd.getText().toString().length() < 6){
            err += "Missing field : Mot de passe";
        }

        if (err.length() > 0) {
            Toast.makeText(getApplicationContext(), err, Toast.LENGTH_SHORT).show();
        }
        else {
            // We need an Editor object to make preference changes.
            // All objects are from android.context.Context
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("name", name.getText().toString());
            editor.putString("current_login", email.getText().toString());
            editor.putString("pwd", pwd.getText().toString());

            // Commit the edits!
            editor.commit();

            Intent intent = new Intent(this, AppActivity.class);
            startActivity(intent);
        }
    }
}
