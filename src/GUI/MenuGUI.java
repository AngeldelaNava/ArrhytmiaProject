/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package GUI;

import Client.SocketObject;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import jdbc.JDBCManager;

/**
 *
 * @author maria
 */
public class MenuGUI extends javax.swing.JPanel implements WindowListener {

    private SocketObject socket;
    private MenuGUI menu;
    private JDBCManager manager;
    public LogIn login;
    public SignUp signup;
    private JFrame frame = new JFrame();
    private LogInPanel loginPanel;
    private SignUpPanel signUpPanel;

    public class SignUpPanel extends javax.swing.JPanel {

    }

    public class LogInPanel extends javax.swing.JPanel {

    }

    public JButton getLoginbutton() {
        return LogInButton;
    }

    public JButton getSignUpButton() {
        return SignUpButton;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Creates new customizer MenuGUI
     *
     * @param socket
     * @param manager
     */
    public MenuGUI(SocketObject socket, JDBCManager manager) {

        this.socket = socket;
        this.manager = manager;
        // lo añado porque sino sale que el manager es null en la línea 133 al hacer manager.conect()
        initComponents(); //inicia componentes gráficos de la ventana
        addWindowListener(this); //lo implementa la clase
    }

    public MenuGUI() {
        initComponents();
        addWindowListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        SignUpButton = new javax.swing.JButton();
        LogInButton = new javax.swing.JButton();
        Exit = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 255, 204));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setText("Welcome");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 110, -1));
        jLabel1.getAccessibleContext().setAccessibleDescription("");

        SignUpButton.setText("Sign up");
        SignUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpButtonActionPerformed(evt);
            }
        });
        add(SignUpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, -1));

        LogInButton.setText("Log in");
        LogInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogInButtonActionPerformed(evt);
            }
        });
        add(LogInButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, -1, -1));

        Exit.setBackground(new java.awt.Color(204, 204, 204));
        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        add(Exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 50, 15));
    }// </editor-fold>//GEN-END:initComponents

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed

        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    private void SignUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpButtonActionPerformed
        if (manager == null) {
            // Inicializa el objeto manager si es null
            manager = new JDBCManager();
        }
        manager.connect();
        signup = new SignUp(socket, manager);
        frame.dispose();
        JFrame frame = new JFrame("Sign up");
        frame.setContentPane(signup);  // Establecer el contenido en lugar de agregar el JFrame
        frame.pack();
        frame.setVisible(true);
        /*JFrame frame = new JFrame("Sign up");
        signup.setFrame(new JFrame());
        signup.setSignup(signup); // aqui estaba log in // //
        signup.getFrame().add(signup);
        signup.getFrame().pack();
        signup.getFrame().setVisible(true);
        //signup.setVisible(true);
        //frame.dispose();*/
        try {
            socket.getOutputStream().write(1); //CASE 1
        } catch (IOException ex) {
            Logger.getLogger(MenuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_SignUpButtonActionPerformed

    private void LogInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogInButtonActionPerformed
        if (manager == null) {
            // Inicializa el objeto manager si es null
            manager = new JDBCManager();
        }
        manager.connect();
        login = new LogIn(socket, manager);
        frame.dispose();
        JFrame frame = new JFrame("Log in");
        login.setFrame(new JFrame());
        login.setLogin(login);
        login.getFrame().add(login);
        login.getFrame().pack();
        login.getFrame().setVisible(true);
        frame.dispose();
        //login.setVisible(true);//se muestra ventana

        try {
            socket.getOutputStream().write(2); //CASE 2
        } catch (IOException ex) {
            Logger.getLogger(MenuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_LogInButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Exit;
    private javax.swing.JButton LogInButton;
    private javax.swing.JButton SignUpButton;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                MenuGUI menuGUI = new MenuGUI();
                menuGUI.setFrame(new JFrame());
                menuGUI.frame.add(menuGUI);
                menuGUI.frame.pack();
                menuGUI.frame.setVisible(true);

            }
        });
    }

    public void setMenu(MenuGUI menu) {
        this.menu = menu;
    }

    public MenuGUI getMenu() {
        return menu;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        releaseResources(socket.getObjectInputStream(), socket.getObjectOutputStream(), socket.getSocket(),
                socket.getInputStream(), socket.getOutputStream());
    }

    private static void releaseResources(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream, Socket socket,
            InputStream inputStream, OutputStream outputStream) {
        try {
            objectInputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            objectOutputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            outputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            inputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
