package com.example.login;

import com.parse.Parse;
import com.parse.ParseObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	
	EditText RnameTxt, RemailTxt, RusernameTxt, RpasswordTxt;
	Button RregisterBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		Parse.initialize(this, "7ss1Nxl1oHRq3vOiwijyQAVTFFJZkg2tohi4Ca8n", "IwiLH4hYQaTEGnxI0YWH6S102pL2Fv0Vxfpm26eR");
		RnameTxt = (EditText) findViewById(R.id.txtR_Name);
		RemailTxt = (EditText) findViewById(R.id.txtR_Email);
		RusernameTxt = (EditText) findViewById(R.id.txtR_Username);
		RpasswordTxt = (EditText) findViewById(R.id.txtR_Password);
		
		RregisterBtn = (Button) findViewById(R.id.btnR_Register);
		RregisterBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ParseObject testObject = new ParseObject("TestObject");
				testObject.put("Name", String.valueOf(RnameTxt.getText()));
				testObject.put("Email", String.valueOf(RemailTxt.getText()));
				testObject.put("Username", String.valueOf(RusernameTxt.getText()));
				testObject.put("Password", String.valueOf(RpasswordTxt.getText()));
				testObject.saveInBackground();
				Toast.makeText(getApplicationContext(),"Your account has been created!", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(v.getContext(), MainActivity.class));	
			}
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
