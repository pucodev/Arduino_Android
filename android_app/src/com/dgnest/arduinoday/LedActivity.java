package com.dgnest.arduinoday;

import static com.dgnest.utilitarios.BluetoothFragment.enviarPaqBT;
import static com.dgnest.utilitarios.BluetoothFragment.isBTConected;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Activity para el funcionamiento de prendido y apagado de Led
 * 
 * @author Jorge
 * @author dgNest
 * @since 1.0.0
 * @version 1.0.0
 * 
 */

public class LedActivity extends Activity implements OnClickListener {

	// Variables
	private ToggleButton btn;
	public static final String LED_OFF = "0";
	public static final String LED_ON = "1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.led_layout);

		btn = (ToggleButton) findViewById(R.id.toggleButton1);
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == btn) {
			// Verificamos estado de ToggleButton
			if (btn.isChecked()) {
				enviarPaq(LED_OFF);
			} else {
				enviarPaq(LED_ON);
			}
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
