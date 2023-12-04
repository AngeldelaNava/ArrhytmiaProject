/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerClient;

import ifaces.Manager;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.JDBCManager;

/**
 *
 * @author beatr
 */
public class Server {

    private static final int PORT = 10000;
    //private List<ClientHandler> clients = new ArrayList<>();
    private static Manager manager;

    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket = null;
        InputStream inputStream = null;
        manager = new JDBCManager();
        manager.connect();
        manager.createTables();
        try {
            serverSocket = new ServerSocket(10000);
           // System.out.println("servidor iniciado");
     
            new Server().startServer(serverSocket);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startServer(ServerSocket serverSocket) {
        try {
            System.out.println("Server listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection from IP:" + clientSocket.getInetAddress());

                // Create a new thread for each client
                //ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                //clients.add(clientHandler);
                //new Thread(clientHandler).start();
                new Thread(new ServerThreads(clientSocket, manager)).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            releaseResources(serverSocket);
        }
    }

    private static void releaseResources(ServerSocket serverSocket) {
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
