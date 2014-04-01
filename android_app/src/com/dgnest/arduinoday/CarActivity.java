package com.dgnest.arduinoday;

import static com.dgnest.utilitarios.BluetoothFragment.enviarPaqBT;
import static com.dgnest.utilitarios.BluetoothFragment.isBTConected;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class CarActivity extends Activity implements OnClickListener {

	Button btnUp, btnDown, btnLeft, btnRight, btnStop;

	public static final String UP = "1";
	public static final String DOWN = "2";
	public static final String RIGHT = "3";
	public static final String LEFT = "4";
	public static final String STOP = "5";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.car_layout);

		btnUp = (Button) findViewById(R.id.btnUp);
		btnDown = (Button) findViewById(R.id.btnDown);
		btnLeft = (Button) findViewById(R.id.btnLeft);
		btnRight = (Button) findViewById(R.id.btnRight);
		btnStop = (Button) findViewById(R.id.btnStop);

		btnUp.setOnClickListener(this);
		btnDown.setOnClickListener(this);
		btnLeft.setOnClickListener(this);
		btnRight.setOnClickListener(this);
		btnStop.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnUp:
			enviarPaq(UP);
			break;
		case R.id.btnDown:
			enviarPaq(DOWN);
			break;
		case R.id.btnLeft:
			enviarPaq(LEFT);
			break;
		case R.id.btnRight:
			enviarPaq(RIGHT);
			break;
		case R.id.btnStop:
			enviarPaq(STOP);
			break;

		default:
			break;
		}

	}

	/**
	 * Funcion para revisar coneccion y enviar paquetes
	 * 
	 * @param msg
	 *            msg a enviar
	 * @since 1.0.0
	 * 
	 */
	private void enviarPaq(String msg) {
		// enviamos paquete si estamos conectados
		if (isBTConected()) {
			enviarPaqBT(msg);

		} else {
			Toast.makeText(this, "No estas conectado", Toast.LENGTH_SHORT)
					.show();
		}
	}
}
