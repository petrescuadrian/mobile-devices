package com.example.login;

import com.parse.Parse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	EditText usernameTxt, passwordTxt;
	Button loginBtn, registerBtn;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Parse.initialize(this, "7ss1Nxl1oHRq3vOiwijyQAVTFFJZkg2tohi4Ca8n", "IwiLH4hYQaTEGnxI0YWH6S102pL2Fv0Vxfpm26eR");
        usernameTxt = (EditText) findViewById(R.id.txtUsername);
        passwordTxt = (EditText) findViewById(R.id.txtPassowrd);
        
        loginBtn = (Button) findViewById(R.id.btnLogin);
        loginBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String usernameTemp = String.valueOf(usernameTxt.getText());
				String passwordTemp = String.valueOf(passwordTxt.getText());
				
				if ()
				startActivity(new Intent(v.getContext(), ProfileActivity.class));	
			}
        });
        
        registerBtn = (Button) findViewById(R.id.btnRegister);
        registerBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(v.getContext(), RegisterActivity.class));	
			}
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
