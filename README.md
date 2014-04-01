# Aplicación Android Arduino

## Overview

Esta aplicación fue elaborada como un demo para la presentacion "Demo Code Live: Android y Arduino" celebrada en el CTIC-UNI en el evento Arduino Day 2014.
Cada commit son los pasos que se desarrollaron en el evento.

## Prerequisitos
- Android SDK (para este tutorial se usa eclipse como IDE, [descargar](http://developer.android.com/sdk/index.html)).
- Arduino software ([descargar](http://arduino.cc/en/Main/Software)).

## Commits para el tutorial

Puedes revisar cada paso del tutorial usando

~~~
git checkout -f step-?
~~~

### step-0

- Creamos aplicacion Android

### step-1

- Creamos BluetoothFragment para el tratamiento del Bluetooth:
	- Activamos Bluetooth.
	- Cargamos todo los dispositivos apareados al movil.
- Agregamos permisos al manifest para el uso del Bluetooth.

### step-2

- Agregamos Listener a BluetoothFragment para comunicación con otras Acivities

### step-3

- Creamos BluetoothList para seleccionar con que dispositivo conectarse.
	- Creamos su layout (bluetooth_list.xml).
	- Agregamos a Manifest.

### step-4

- Agregamos metodo para conectarse con un dispositivo en BluetoothFragment.
- Implementacion de onItemClickListener en BluetoothList.
- Editamos menu.
- Agregamos onOptionsItemSelected en MainActivity.

### step-5

- Agregamos metodo para enviar paquetes por Bluetooth en BluetoothFragment.
- Creamos layout para el MainActivity.
- Creamos metodo para mandar datos del editText de MainActivity.

### step-6

- Terminamos con Andoid:
	- Creamos metodos para desconectar y apagar Bluetooth al momento de cerrar la aplicación.

### step-7

- Empezamos con Arduino:
	- Creamos proyecto ledArduino
	- Hola Mundo Arduino (Led Blink)

### step-8

- Configuramos y leemos valor por comunicación serial

### step-9

- Creamos nueva activity en android-app
	- LedActivity y led-layout
- Prendemos y apagamos led por comandos recibidos por serial

### step-10

- Agregamos proyecto movilArduino