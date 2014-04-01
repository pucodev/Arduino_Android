package com.dgnest.utilitarios;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
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
	private BluetoothDevice actualDevice = null;
	private static Boolean isBTConected = false;

	private static BluetoothSocket mSocket;
	private static OutputStream mOutStream;
	private static InputStream mInStream;

	// listener
	BTListener listener = null;

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

		if (listener != null) {
			// Mandamos por una interface los nombres de los dispositivos
			// apareados
			listener.onFinishedLoadBT(nameDevice);
		}

	}

	/**
	 * conectar con el dispositivo seleccionado
	 * 
	 * @param idDevice
	 *            posición del dispositivo a conectarse
	 * @since 1.0.0
	 */
	public void connectDevice(int idDevice) {
		actualDevice = device.get(idDevice);

		BluetoothSocket tmp = null;
		try {
			tmp = actualDevice
					.createInsecureRfcommSocketToServiceRecord(MY_UUID);
		} catch (IOException e) {
		}

		mSocket = tmp;
		try {
			mSocket.connect();
			isBTConected = true;
			iniConection();
		} catch (IOException e) {
			isBTConected = false;
		}
	}

	private void iniConection() {
		InputStream tmpIn = null;
		OutputStream tmpOut = null;
		try {
			tmpIn = mSocket.getInputStream();
			tmpOut = mSocket.getOutputStream();
		} catch (IOException e) {
			Log.e("Error", e + "");
		}

		mInStream = tmpIn;
		mOutStream = tmpOut;
	}

	/**
	 * Aseguramos que estamos conectado
	 * 
	 * @return si esta conectado con algun dispositivo
	 * @since 1.0.0
	 */
	public static Boolean isBTConected() {
		return isBTConected;
	}
	
	/**
	 * Metodo para enviar paquetes por Bluetooth
	 * 
	 * @param msg
	 *            mensaje a enviar
	 * @since 1.0.0
	 */
	public static void enviarPaqBT(String msg) {
		if (isBTConected == true) {
			for (Byte letra : msg.getBytes())
				try {
					mOutStream.write(letra);

					Log.d("Mensaje Bluetooth", "Enviando dato = " + msg);
				} catch (IOException e) {
					Log.d("Mensaje Bluetooth", "No se envio dato = " + msg);
				}
		}
	}

	/**
	 * Listener para la comunicación con otras Activity
	 * 
	 * @author Jorge
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	public interface BTListener {
		void onFinishedLoadBT(List<String> nombres);
	}

	public void setBTListener(BTListener listener) {
		this.listener = listener;
	}

	private void MyLog(String msg) {
		Log.d("BluetoothClass", msg);
	}
}
