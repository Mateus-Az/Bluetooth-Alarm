package br.com.vega.projects.bluetooth;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import java.util.List;

public class BluetoothDiscoveryListener implements DiscoveryListener {

    private final List<RemoteDevice> listDevices;

    public BluetoothDiscoveryListener(List<RemoteDevice> listDevices) {
        this.listDevices = listDevices;
    }

    @Override
    public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {
        listDevices.add(remoteDevice);
    }

    @Override
    public void inquiryCompleted(int discType) {
        synchronized (listDevices) {
            listDevices.notifyAll();
        }
    }

    @Override
    public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
    }

    @Override
    public void serviceSearchCompleted(int transID, int respCode) {
    }
}
