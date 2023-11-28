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
        SocketObject socket;
        socket = new SocketObject();
        JDBCManager manager;
        manager = new JDBCManager();

        try {
            socket.setSocket(new Socket("localhost", 9000));
            //OutputStream y ObjectOutputStream es para que el cliente envíe datos y objetos
            //InputStream y ObjectInputStream es para que el cliente reciba datos y objetos
            //con el getOutputStream o getInputStream cogemos channels
            socket.setOutputStream(socket.getSocket().getOutputStream());
            socket.setObjectOutputStream(new ObjectOutputStream(socket.getOutputStream()));
            socket.setInputStream(socket.getSocket().getInputStream());
            socket.setObjectInputStream(new ObjectInputStream(socket.getInputStream()));
        } catch (IOException ex) {
            System.out.println("Error in connection");//se imprime mensaje de error
            System.exit(-1);//se sale del programa con un codigo de error
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);//se registra la excepcion
        }
        //
        menuInstance = new MenuGUI(socket, manager);
        JFrame frame = new JFrame();
        menuInstance.setFrame(frame);
        menuInstance.setMenu(menuInstance); //se asigna la instancia
        menuInstance.getFrame().add(menuInstance);
        menuInstance.getFrame().pack();
        menuInstance.getFrame().setVisible(true); // se abre GUI
        menuInstance.setVisible(true); //se abre GUI
    }
}
