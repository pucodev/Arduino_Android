package com.dgnest.arduinoday;

import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
public class BluetoothList extends FragmentActivity implements BTListener,
		OnItemClickListener {

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
		lista.setOnItemClickListener(this);
		
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long id) {
		Tarea mTarea = new Tarea();
		mTarea.execute(position);
		setPd("Conectando");
	}

	class Tarea extends AsyncTask<Integer, Integer, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Integer... params) {
			btFragment.connectDevice(params[0]);
			return btFragment.isBTConected();
		}

		@Override
		protected void onPostExecute(Boolean isConected) {
			super.onPostExecute(isConected);
			pd.dismiss();
			if (isConected) {
				finish();
			} else {
				Toast.makeText(BluetoothList.this, "Error. Intente Nuevamente",
						Toast.LENGTH_SHORT).show();
			}
		}

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
