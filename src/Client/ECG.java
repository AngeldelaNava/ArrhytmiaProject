/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

/**
 *
 * @author maria
 */
public class ECG {

    private Integer id;
    private String ecg;
    private String observations;
    private int patient_id;
    private String date;

    public ECG(Integer id, String observations, int patient_id, String date, String ecg) {
        this.id = id;
        this.observations = observations;
        this.patient_id = patient_id;
        this.date = date;
        this.ecg = ecg;
    }

    public ECG(String ecg, String observations, int patient_id) {
        this.ecg = ecg;
        this.observations = observations;
        this.patient_id = patient_id;
    }

    public ECG(String ecg, int patient_id, String date) {
        this.ecg = ecg;
        this.patient_id = patient_id;
        this.date = date;
    }

    public ECG(Integer id, String ecg, String observations, int patient_id) {
        this.id = id;
        this.ecg = ecg;
        this.observations = observations;
        this.patient_id = patient_id;
    }

    public ECG(Integer id, String observations, int patient_id) {
        this.id = id;
        this.observations = observations;
        this.patient_id = patient_id;
    }

    public String getEcg() {
        return ecg;
    }

    public String getObservations() {
        return observations;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setEcg(String ecg) {
        this.ecg = ecg;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "EEGSample{" + "id=" + id + ", ecg=" + ecg + ", observations=" + observations + ", patient_id=" + patient_id + '}';
    }
}
