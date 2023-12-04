/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package console;

import Client.SocketObject;
import GUI.MenuGUI;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import jdbc.JDBCManager;

/**
 *
 * @author maria
 */
public class menu {

    private static MenuGUI menuInstance;

    public static void main(String[] args) throws Exception { //establece conexión con el servidor a través del socket
        SocketObject socket = new SocketObject();
        JDBCManager manager = new JDBCManager();

        try {
            Socket s = new Socket("localhost", 10000);
            socket.setSocket(s);
            //OutputStream y ObjectOutputStream es para que el cliente envíe datos y objetos
            //InputStream y ObjectInputStream es para que el cliente reciba datos y objetos
            //con el getOutputStream o getInputStream cogemos channels
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

            socket.setSocket(s);
            socket.setOutputStream(oos);
            socket.setObjectOutputStream(oos);
            socket.setInputStream(ois);
            socket.setObjectInputStream(ois);

            // Realizar operaciones de escritura/lectura según sea necesario
            socket.getOutputStream().write(1);
            menuInstance = new MenuGUI(socket, manager);
            JFrame frame = new JFrame("Menu");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(menuInstance);
            frame.pack();
            frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
            frame.setVisible(true); //se abre GUI
            //frame.dispose();
        } catch (IOException ex) {
            System.out.println("Error in connection");//se imprime mensaje de error
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);//se registra la excepcion
            System.exit(-1);//se sale del programa con un codigo de error
        }
    }
}
