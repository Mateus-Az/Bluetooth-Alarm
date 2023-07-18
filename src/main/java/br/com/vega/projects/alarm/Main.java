package br.com.vega.projects.alarm;

import br.com.vega.projects.bluetooth.BluetoothDeviceDiscovery;

import javax.bluetooth.RemoteDevice;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import java.io.*;
import java.util.Scanner;

public class Main {
    static ClassLoader classLoader;
    static File arquivo;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        BluetoothDeviceDiscovery.discoverBluetoothDevices();

        int a = 1;
        int option = 0;
        String andressFile = null;

        classLoader = Main.class.getClassLoader();
        arquivo = new File(classLoader.getResource("static/input.txt").getFile());
        Scanner scannerLeitura = new Scanner(arquivo);

        for (RemoteDevice remoteDevice : BluetoothDeviceDiscovery.listDevices) {
            System.out.println("========================================");
            try {
                System.out.println(a + " - Opção| Dispositivo: " + remoteDevice.getFriendlyName(false));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Endereço MAC: " + remoteDevice.getBluetoothAddress());
            System.out.println("========================================");
            a++;
        }

        if (arquivo.length() == 0) {
            option = scanner.nextInt();

            if (option >= 1 && option <= BluetoothDeviceDiscovery.listDevices.size()) {

                RemoteDevice selectedDevice = BluetoothDeviceDiscovery.listDevices.get(option - 1);
                andressFile = saveOption(arquivo, selectedDevice);
            }
        } else {
            andressFile = findAndressInFile(scannerLeitura, arquivo);
        }

        try {

            System.out.println("Conectando ao dispositivo: " + andressFile);
            String connectionURL = "btspp://" + andressFile + ":1;authenticate=true;encrypt=true;master=false";
            System.out.println("========================================");

            StreamConnection streamConnection = (StreamConnection) Connector.open(connectionURL);

            RemoteDevice deviceByAndress = findDeviceByAndress(andressFile);

            if (deviceByAndress.authenticate()){
                System.out.println("========================================");
                System.out.println("Conectado com sucesso ao dispositivo: " + andressFile);
                System.out.println();
                System.out.println("Alarm!!! Alarm!!! Alarm!!! Alarm!!!");
                AlarmPlayer.main(args);
            }else{
                System.out.println("Error");
            }
        } catch (IOException e) {
            throw new RuntimeException("Não Autenticado");
        }
    }

    public static String saveOption(File arquivo, RemoteDevice device) {
        if (arquivo.length() == 0) {
            try (FileWriter fw = new FileWriter(arquivo);
                 BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(device.getBluetoothAddress());
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return device.getBluetoothAddress();
    }

    public static String findAndressInFile(Scanner scanner, File arquivo) {
        String andress = null;
        if (arquivo.length() >= 1 && scanner.hasNext()) {
            andress = scanner.nextLine();
        }
        return andress;
    }

    public static RemoteDevice findDeviceByAndress(String andress){
        RemoteDevice device = null;
        for (RemoteDevice remoteDevice : BluetoothDeviceDiscovery.listDevices){
            if (remoteDevice.getBluetoothAddress().equalsIgnoreCase(andress)){
                device = remoteDevice;
            }
        }
        return device;
    }
}
