package com.dgnest.arduinoday;

import static com.dgnest.utilitarios.BluetoothFragment.disableBT;
import static com.dgnest.utilitarios.BluetoothFragment.enviarPaqBT;
import static com.dgnest.utilitarios.BluetoothFragment.isBTConected;
import static com.dgnest.utilitarios.BluetoothFragment.stopBT;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	// variables
	private Button mSend, mLed;
	private EditText etData2Send;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mSend = (Button) this.findViewById(R.id.buttonSend);
		mLed = (Button) this.findViewById(R.id.btnLed);
		etData2Send = (EditText) this.findViewById(R.id.etData2Send);

		mSend.setOnClickListener(this);
		mLed.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_conect:
			Intent intentBTList = new Intent(this, BluetoothList.class);
			startActivity(intentBTList);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onClick(View v) {
		if (v == mSend) {
			String msg = etData2Send.getText().toString();
			// enviamos paquete si estamos conectados
			if (isBTConected()) {
				enviarPaqBT(msg);
				etData2Send.setText("");
				
			} else {
				Toast.makeText(this, "No estas conectado", Toast.LENGTH_SHORT)
						.show();
			}
		}
		
		if(v == mLed){
			Intent ledIntent = new Intent(getApplication(), LedActivity.class);
			startActivity(ledIntent);
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if (isFinishing()) {
			stopBT();
			disableBT();
		}
	}

}
