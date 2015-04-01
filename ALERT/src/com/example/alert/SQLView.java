package com.example.alert;

import java.sql.SQLException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SQLView extends Activity implements OnClickListener{
	
	int num = 0;
	int num2 = 0;
	int rowId;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sql_view);
		
		
		Intent i = getIntent();
		
		rowId = i.getExtras().getInt("rowId");
		
		final TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
		final TextView tv2 = (TextView) findViewById(R.id.buttonNumbers);
		MainDetails info = new MainDetails(this);
		try {
			info.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final ArrayList<String> data = info.getDataDetails(rowId);
		final ArrayList<Integer> data2 = info.getDataBodyCounts(rowId);
		info.close();
		
		Button but = (Button) findViewById(R.id.btCountUp);
		but.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (num == data.size()){
					num=0;
				}
				if (num2 == data2.size()){
					num2=0;
				}
					tv.setText(data.get(num) + "" + data.size());
					
				if (data2.get(num2) == null){
					Toast.makeText(getBaseContext(), "empty thinf", Toast.LENGTH_SHORT).show();
				} else
					tv2.setText(""+data2.get(num2) + "" + data2.size());
					num++;	
					num2++;	
			}
		});
		
}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
