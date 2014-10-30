package com.example.login;

import java.util.List;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	EditText usernameTxt, passwordTxt;
	Button loginBtn, registerBtn;
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	private String message;
	
	
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
				Toast.makeText(getApplicationContext(),"Authenticating, please wait ...", Toast.LENGTH_SHORT).show();
				String usernameTemp = String.valueOf(usernameTxt.getText());
				String passwordTemp = String.valueOf(passwordTxt.getText());
				
				ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
				
				
				query.whereEqualTo("Username", usernameTemp);
				query.whereEqualTo("Password", passwordTemp);
				
				
				try {
					List<ParseObject> userList=query.find();
					if (userList.size()>0){
						message=usernameTemp;
						Intent intent = new Intent(v.getContext(),ProfileActivity.class);
						//send the user name via intent to be used in profile acitivity
						intent.putExtra(EXTRA_MESSAGE, message);
						startActivity(intent);
						
					}
					else{
						Toast.makeText(getApplicationContext(),"Invalid username or password!", Toast.LENGTH_SHORT).show();
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
				
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
        return super.onOptionsItemSelected(item);
    }
}
