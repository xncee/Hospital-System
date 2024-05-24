package hospital;

import java.time.LocalDate;

public class Appointment implements HospitalData {
    private String id;
    private Patient patient;
    private Doctor doctor;
    private LocalDate date;
    private String description;

    public Appointment(String id, Patient patient, Doctor doctor, LocalDate date, String description) {
        setId(id);
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id.startsWith("AP"))
            this.id = id;
        else
            System.out.println("Appointment must start with 'AP'.");

    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctorId) {
        this.doctor = doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                ", id="+id +
                ", patient="+patient.getId() +
                ", doctor="+doctor.getId() +
                ", date="+date +
                ", description=" + description +
                "}";
    }
}
