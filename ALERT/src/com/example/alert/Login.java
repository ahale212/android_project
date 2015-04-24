package com.example.alert;

//imports for the login page to work
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * class for the log in page
 * @author adamhale
 *
 */
public class Login extends ActionBarActivity {
	// initialise vars
	private EditText password, username;
	private Button login, newUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// set the link to the correct .xml view
		setContentView(R.layout.activity_login);
		// addListenerOnButton method
		addListenerOnButton();

	}

	// method used to tell button what to do when it is clicked
	public void addListenerOnButton() {
		
		username = (EditText) findViewById(R.id.User_username);
		// link the var password to the edit text id
		password = (EditText) findViewById(R.id.editTextUserID);
		// link the car login to the button id
		login = (Button) findViewById(R.id.Login);
		// set an onClickListener for the button
		login.setOnClickListener(new OnClickListener() {

			@Override
			// method for what button is to do when clicked
			public void onClick(View v) {
				// for password validation
				String checkPassword = password.getText().toString();
				// if the password is equal to that after contentEquals, allow
				// the next page to be opened
				if (checkPassword.contentEquals("")) {
					Intent openDetailsPage = new Intent(
							"android.intent.action.HomeScreen");
					try{
					startActivity(openDetailsPage);
					} catch (Exception e){
						Toast.makeText(getBaseContext(), "can't find class", Toast.LENGTH_SHORT).show();
					}

				}//end of for loop

			}//end of onClick method

		});//end of set onClick listener method
	
		newUser = (Button) findViewById(R.id.NewUser);
		
		newUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent newUser = new Intent("android.intent.action.CreateUser");
				
				startActivity(newUser);
				
			}
		});
	}//end of method

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}//end of method

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}//end of method
		return super.onOptionsItemSelected(item);
	}//end of method
}//end of class
