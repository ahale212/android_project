package com.example.alan.sendsms;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    EditText num, message;
    Button btnSendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = (EditText)findViewById(R.id.phoneNumber);
        message = (EditText)findViewById(R.id.message);
        btnSendMessage = (Button)findViewById(R.id.sendButton);

        btnSendMessage.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        sendSms();

                    }


                }
        );
    }

    private void sendSms() {

        String number = num.getText().toString();
        String sms = message.getText().toString();

        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(number, null, sms, null, null);
        Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_LONG).show();
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
}
