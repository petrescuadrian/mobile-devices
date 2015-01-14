package com.example.login;

import java.util.List;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends Activity {
	
	EditText PnameTxt, PemailTxt, PusernameTxt;
	Button saveBtn, cancelBtn, weatherBtn, passwordBtn;
	private String ID;
	public final static String EXTRA_MESSAGE2 = "com.example.myfirstapp.MESSAGE";
	private String message;
	
	public void getAccountInfo (){
		Intent intent = getIntent();
	    String usernameTemp = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        
	    //Query the database for the rest of the information
	  	ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
	  	//Select where username is @username and password is @password
	  	query.whereEqualTo("Username", usernameTemp);
	  	try{
	  		List<ParseObject> userList=query.find();
	  		//Fetch the data from the database into the fields on the acitivty
	       	ParseObject user=userList.get(0);
	   		ID=user.getObjectId();
	   		PnameTxt.setText(user.getString("Name"));
	   		PemailTxt.setText(user.getString("Email"));
	   		PusernameTxt.setText(user.getString("Username"));
	  	}
	  	catch (com.parse.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		Parse.initialize(this, "7ss1Nxl1oHRq3vOiwijyQAVTFFJZkg2tohi4Ca8n", "IwiLH4hYQaTEGnxI0YWH6S102pL2Fv0Vxfpm26eR");
		
		PnameTxt = (EditText) findViewById(R.id.txtPname);
        PemailTxt = (EditText) findViewById(R.id.txtPemail);
        PusernameTxt = (EditText) findViewById(R.id.txtPusername);
        
        saveBtn = (Button) findViewById(R.id.btnSave);
        saveBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = getIntent();
			    String usernameTemp = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		        
			    //Query the database for the rest of the information
			  	ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
			  	//Select where username is @username and password is @password
			  	query.whereEqualTo("Username", usernameTemp);
			  	try{
			  		List<ParseObject> userList=query.find();
			  		//Fetch the data from the database into the fields on the acitivty
			       	ParseObject user=userList.get(0);
			   		ID=user.getObjectId();
			   		
			   		user.put("Name", PnameTxt.getText().toString());
			   		user.put("Email", PemailTxt.getText().toString());
			   		user.put("Username", PusernameTxt.getText().toString());
			   		try {
						user.save();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			  	}
			  	catch (com.parse.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
				}
				
			  	PnameTxt.setClickable(false);
				PnameTxt.setCursorVisible(false);
				PnameTxt.setFocusable(false);
				PnameTxt.setFocusableInTouchMode(false);
				
				PemailTxt.setClickable(false);
				PemailTxt.setCursorVisible(false);
				PemailTxt.setFocusable(false);
				PemailTxt.setFocusableInTouchMode(false);
				
				PusernameTxt.setClickable(false);
				PusernameTxt.setCursorVisible(false);
				PusernameTxt.setFocusable(false);
				PusernameTxt.setFocusableInTouchMode(false);
				
				saveBtn.setVisibility(View.INVISIBLE);
				cancelBtn.setVisibility(View.INVISIBLE);
			}
        });
        
        cancelBtn = (Button) findViewById(R.id.btnCancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getAccountInfo();
				
				PnameTxt.setClickable(false);
				PnameTxt.setCursorVisible(false);
				PnameTxt.setFocusable(false);
				PnameTxt.setFocusableInTouchMode(false);
				
				PemailTxt.setClickable(false);
				PemailTxt.setCursorVisible(false);
				PemailTxt.setFocusable(false);
				PemailTxt.setFocusableInTouchMode(false);
				
				PusernameTxt.setClickable(false);
				PusernameTxt.setCursorVisible(false);
				PusernameTxt.setFocusable(false);
				PusernameTxt.setFocusableInTouchMode(false);
				
				saveBtn.setVisibility(View.INVISIBLE);
				cancelBtn.setVisibility(View.INVISIBLE);
			}
        });
        
        weatherBtn = (Button) findViewById(R.id.btnWeather);
        weatherBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(v.getContext(), WeatherActivity.class));	
			}
        });
    
        passwordBtn = (Button) findViewById(R.id.btnPassword);
        passwordBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				message=PusernameTxt.getText().toString();
				Intent intent = new Intent(v.getContext(),ChangePasswordActivity.class);
				//send the user name via intent to be used in profile acitivity
				intent.putExtra(EXTRA_MESSAGE2, message);
				startActivity(intent);
			}
        });
        
        getAccountInfo();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_edit) {
			PnameTxt.setClickable(true);
			PnameTxt.setCursorVisible(true);
			PnameTxt.setFocusable(true);
			PnameTxt.setFocusableInTouchMode(true);
			
			PemailTxt.setClickable(true);
			PemailTxt.setCursorVisible(true);
			PemailTxt.setFocusable(true);
			PemailTxt.setFocusableInTouchMode(true);
			
			PusernameTxt.setClickable(true);
			PusernameTxt.setCursorVisible(true);
			PusernameTxt.setFocusable(true);
			PusernameTxt.setFocusableInTouchMode(true);
			
			saveBtn.setVisibility(View.VISIBLE);
			cancelBtn.setVisibility(View.VISIBLE);
            return true;
        }
		return super.onOptionsItemSelected(item);
	}
}
