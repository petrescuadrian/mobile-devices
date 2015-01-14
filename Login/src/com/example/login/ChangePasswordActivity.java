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

public class ChangePasswordActivity extends Activity {
	EditText CPoldpassTxt, CPnewpassTxt, CPconfnewpassTxt;
	Button changepassBtn;
	private String ID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		Parse.initialize(this, "7ss1Nxl1oHRq3vOiwijyQAVTFFJZkg2tohi4Ca8n", "IwiLH4hYQaTEGnxI0YWH6S102pL2Fv0Vxfpm26eR");
		
		CPoldpassTxt = (EditText) findViewById(R.id.txtCPoldpass);
        CPnewpassTxt = (EditText) findViewById(R.id.txtCPnewpass);
        CPconfnewpassTxt = (EditText) findViewById(R.id.txtCPconfnewpass);
        
        changepassBtn = (Button) findViewById(R.id.btnChangePass);
        changepassBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = getIntent();
			    String usernameTemp = intent.getStringExtra(ProfileActivity.EXTRA_MESSAGE2);
		        
			    //Query the database for the rest of the information
			  	ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
			  	//Select where username is @username and password is @password
			  	query.whereEqualTo("Username", usernameTemp);
			  	try{
			  		List<ParseObject> userList=query.find();
			  		//Fetch the data from the database into the fields on the acitivty
			       	ParseObject user=userList.get(0);
			   		ID=user.getObjectId();
					if(CPoldpassTxt.getText().length() != 0)
						if(CPnewpassTxt.getText().length() != 0)
							if(CPconfnewpassTxt.getText().length() != 0)
						   		if(CPoldpassTxt.getText().toString().equals(user.getString("Password"))) {
							   		if(CPnewpassTxt.getText().toString().equals(CPconfnewpassTxt.getText().toString())) {
							   			user.put("Password", CPnewpassTxt.getText().toString());
								   		try {
											user.save();
											Toast.makeText(getApplicationContext(),"Password changed!", Toast.LENGTH_SHORT).show();
											finish();
								   		} catch (ParseException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
							   		}
							   		else {
										Toast.makeText(getApplicationContext(),"The new passwords doesn't match!", Toast.LENGTH_SHORT).show();
							   		}
						   		}
						   		else {
									Toast.makeText(getApplicationContext(), "The old password is incorect!", Toast.LENGTH_SHORT).show();
						   		}
							else Toast.makeText(getApplicationContext(), "Please enter a confirmation password!", Toast.LENGTH_SHORT).show();
						else Toast.makeText(getApplicationContext(), "Please enter a new password!", Toast.LENGTH_SHORT).show();
					else Toast.makeText(getApplicationContext(), "Please enter old password!", Toast.LENGTH_SHORT).show();
			  	}
			  	catch (com.parse.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
				}
				
			}
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_password, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		return super.onOptionsItemSelected(item);
	}
}
