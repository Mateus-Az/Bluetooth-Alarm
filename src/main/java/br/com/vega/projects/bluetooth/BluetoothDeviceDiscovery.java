package br.com.vega.projects.bluetooth;

import lombok.Getter;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import java.util.ArrayList;
import java.util.List;
@Getter
public class BluetoothDeviceDiscovery {
    public static List<RemoteDevice> listDevices;
    private static BluetoothDiscoveryListener listener;

    public static List<RemoteDevice> discoverBluetoothDevices() {
        listDevices = new ArrayList<>();

        try {
            LocalDevice localDevice = LocalDevice.getLocalDevice();
            DiscoveryAgent agent = localDevice.getDiscoveryAgent();

            System.out.println("Iniciando busca por dispositivos bluetooth");
            System.out.println("Relaxe, você só terá que escolher uma vez !!!");
            listener = new BluetoothDiscoveryListener(listDevices);

            boolean started = agent.startInquiry(DiscoveryAgent.GIAC, listener);
            if (started) {
                synchronized (listDevices) {
                    listDevices.wait();
                }
            }
        } catch (BluetoothStateException | InterruptedException e) {
            e.printStackTrace();
        }

        return listDevices;
    }
}
