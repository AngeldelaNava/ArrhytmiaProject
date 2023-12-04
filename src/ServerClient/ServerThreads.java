/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerClient;

import Client.ECG;
import Client.Patient;
import ifaces.ECGManager;
import ifaces.Manager;
import ifaces.PatientManager;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
public class ServerThreads implements Runnable {

    public static Socket socket;
    private static Manager manager;
    private static PatientManager patientManager;
    private static ECGManager ECGmanager;
    public static InputStream inputStream;
    public static OutputStream outputStream;
    public static ObjectInputStream objectInputStream;
    public static ObjectOutputStream objectOutputStream;

    public ServerThreads(Socket socket, Manager manager) {
        this.socket = socket;
        this.manager = manager;

    }

    @Override
    public void run() {
        try {
            patientManager = manager.getPatient();
            ECGmanager = manager.getECG();
            Patient p;
            ECG ecg;
            ArrayList<String> ecgs = null;
            ArrayList<Patient> ps = null;
            String username;
            boolean usernameExists;
            int variable;
            inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            objectInputStream = new ObjectInputStream(inputStream);     //se inicializa la variable object inputstream al inputstrema (para poder leer objetos)
            outputStream = socket.getOutputStream();    //se inicializa la variable outputstream al socket (para poder escribir)
            objectOutputStream = new ObjectOutputStream(outputStream);
            int medicoOpaciente = inputStream.read();
            if (medicoOpaciente == 1) { //CLIENTE
                int option = inputStream.read(); //CLIENTE ESCOGE BOTÓN
                switch (option) {
                    case 0:

                        break;
                    case 1: //se abre menu GUI (sign up)
                        username = bufferedReader.readLine();
                        usernameExists = patientManager.verifyUsername(username);
                        objectOutputStream.writeObject(usernameExists);
                        if (usernameExists) {
                            try {
                                p = (Patient) (objectInputStream.readObject());
                                patientManager.addPatient(p);
                                String resultString = new String(p.getPassword());
                                p = patientManager.searchPatient(p.getUsername(), resultString);
                                objectOutputStream.writeObject(p);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(ServerThreads.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                    case 2: //LOG IN
                        username = bufferedReader.readLine();
                        usernameExists = patientManager.verifyUsername(username);
                        objectOutputStream.writeObject(usernameExists);
                        if (usernameExists) {
                            String password = bufferedReader.readLine();
                            boolean correct = patientManager.verifyPassword(username, password);
                            objectOutputStream.writeObject(correct);
                            if (correct) {
                                variable = inputStream.read(); //SI RECIBE UN 0 DE LA CLASE menu TODO CORRECTO
                                if (variable == 0) {
                                    try {
                                        p = patientManager.searchPatient(username, password);
                                        objectOutputStream.writeObject(p);
                                    } catch (EOFException ex) {
                                        Logger.getLogger(ServerThreads.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                int option2 = inputStream.read(); //EN MENU AFTER LOG IN SE ESCOGE VIEW MY DATA 1 O RECORD 2
                                switch (option2) {
                                    case 1:
                                        int patientId = inputStream.read();
                                        ecgs = ECGmanager.findECGByPatientId(patientId);
                                        //outputStream.write(ecgs.size());
                                        for (int i = 0; i < ecgs.size(); i++) {
                                            objectOutputStream.writeObject(ecgs.get(i));
                                        }
                                        int view = inputStream.read();
                                        while (view == 1) { //view signals

                                        }

                                }

                            }
                        }
                        break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerThreads.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
