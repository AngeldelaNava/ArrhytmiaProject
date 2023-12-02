/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import Client.ECG;
import ifaces.ECGManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
public class JDBCECGManager implements ECGManager {

    private JDBCManager manager;
    private Connection c;

    public JDBCECGManager(JDBCManager manager) {
        this.manager = manager;
    }

    public JDBCECGManager(Connection c) {
        this.c = c;
    }

    @Override
    public void addECG(ECG ecg) {
        try {
            String sql = "INSERT INTO ECG (ecg, patientId, date) VALUES (?, ?, ?)";
            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(ecg.getEcg());
            byte[] bytes = bos.toByteArray();
            prep.setString(1, ecg.getEcg());//Revisar que esté bien
            prep.setInt(2, ecg.getPatient_id());
            prep.setString(3, ecg.getDate());
            prep.executeUpdate();
            prep.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ECG findECG(int id) {
        ECG ecg = null;
        try {
            String sql = "SELECT * FROM ECG WHERE id = ?";
            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            if (rs.next()) {
                int patient_id = rs.getInt("patientId");
                String ecgList = rs.getString("ecg");
                String date = rs.getString("date");
                ecg = new ECG(id, patient_id, date, ecgList);
            }
            rs.close();
            prep.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ecg;
    }

    @Override
    public ArrayList<String> findECGByPatientId(int id) {
        ArrayList<String> ecgs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ECG WHERE patient_id = ?";
            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
            String ecgList = rs.getString("ecg");
            ecgs.add(ecgList);
            }
            rs.close();
            prep.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ecgs;
    }

    @Override
    public void deleteECG(int id) {
        try {
            String sql = "DELETE FROM ECG WHERE id = ?";
            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
            prep.setInt(1, id);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setECG(ECG ecg, int id) {
        try {
            String sql = "UPDATE ECG SET ecg = ?, patientId = ? WHERE id = ?";
            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(ecg.getEcg());
            byte[] bytes = bos.toByteArray();
            prep.setString(1, ecg.getEcg());//Revisar que esté bien
            prep.setInt(2, ecg.getPatient_id());
            prep.setInt(3, id);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JDBCECGManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
