package com.dgnest.utilitarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Fragment para tratamiento del Bluetooth
 * 
 * @author Jorge
 * @author dgNest
 * @since 1.0.0
 * @version 1.0.0
 */
public class BluetoothFragment extends Fragment {

	// variables
	private static final UUID MY_UUID = UUID
			.fromString("00001101-0000-1000-8000-00805F9B34FB");
	private static BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
			.getDefaultAdapter();

	// final variables
	private static final int BLUETOOTH_REQUEST_CODE = 666;

	// variables for BT devices
	private List<BluetoothDevice> device = new ArrayList<BluetoothDevice>();
	private List<String> nameDevice = new ArrayList<String>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return new View(getActivity());
	}

	/**
	 * Activamos Bluetooth si es que no lo estuviera pidiendo permiso
	 * 
	 * @since 1.0.0
	 */
	public void ActivateBT() {
		if (mBluetoothAdapter != null) {
			if (!mBluetoothAdapter.isEnabled()) {
				MyLog("Activando Bluetooth");
				Intent enableBTIntent = new Intent(
						BluetoothAdapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(enableBTIntent, BLUETOOTH_REQUEST_CODE);
			} else {
				MyLog("Bluetooth Activado");
				LoadBT();
			}
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// lanzamos LoadBT() si es que aceptamos encender el BT
		if (requestCode == BLUETOOTH_REQUEST_CODE) {
			switch (resultCode) {
			case Activity.RESULT_OK:
				MyLog("Aceptamos encender el BT y cargamos los pairedDevices");
				LoadBT();
				break;

			case Activity.RESULT_CANCELED:
				Toast.makeText(getActivity(), "Debe activar el Bluetooth",
						Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
		}
	}

	/**
	 * Metodo para cargar todo los dispositivos Bluetooth con los que se
	 * encuentra apareados el dispositivo
	 * 
	 * @since 1.0.0
	 */

	public void LoadBT() {
		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter
				.getBondedDevices();

		for (BluetoothDevice mBluetoothDevice : pairedDevices) {
			device.add(mBluetoothDevice);
			nameDevice.add(mBluetoothDevice.getName());
		}

	}

	private void MyLog(String msg) {
		Log.d("BluetoothClass", msg);
	}
}
