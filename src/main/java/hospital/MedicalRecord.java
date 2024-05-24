package hospital;

import java.time.LocalDate;

public class MedicalRecord implements HospitalData {
    private String id;
    private String patientId;
    private String diagnose;
    private String treatment;
    private LocalDate date;

    public MedicalRecord(String id, String patientId, String diagnose, String treatment, LocalDate date) {
        setId(id);
        this.patientId = patientId;
        this.diagnose = diagnose;
        this.treatment = treatment;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id.startsWith("MR"))
            this.id = id;
        else
            System.out.println("Medical Record id must start with 'MR'.");
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientID) {
        this.patientId = patientId;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", diagnose=" + diagnose +
                ", treatment=" + treatment +
                ", date=" + date +
                "}";
    }
}
