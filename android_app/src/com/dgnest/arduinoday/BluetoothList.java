package com.dgnest.arduinoday;

import java.util.List;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dgnest.utilitarios.BluetoothFragment;
import com.dgnest.utilitarios.BluetoothFragment.BTListener;

/**
 * Activity para mostrar una lista y conectarse con algun dispositivo Bluetooth
 * 
 * @author Jorge
 * @author dgNest
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class BluetoothList extends FragmentActivity implements BTListener {

	private ListView lista;
	private BluetoothFragment btFragment;

	private ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bluetooth_list);

		lista = (ListView) findViewById(R.id.lvBTList);
		btFragment = (BluetoothFragment) getSupportFragmentManager()
				.findFragmentById(R.id.fragmentBT);

		btFragment.setBTListener(this);
		
		setPd("Cargando");
		btFragment.ActivateBT();

	}

	/**
	 * Listener de BluetoothFragment
	 */
	@Override
	public void onFinishedLoadBT(List<String> nameDevices) {
		lista.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, nameDevices));
		pd.dismiss();

	}

	/**
	 * Configurando Progress Dialog
	 * 
	 * @param msg
	 *            mensaje a mostrar
	 * @since 1.0.0
	 */
	private void setPd(String msg) {
		pd = new ProgressDialog(this);
		pd.setIndeterminate(true);
		pd.setMessage(msg);
		pd.setCancelable(true);
		pd.show();
	}
	
}
