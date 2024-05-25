package hospital;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Appointment> find(String searchKey, String searchQuery) {
        List<Appointment> appointments = new ArrayList<>();
        for (Appointment appointment: appointmentsList) {
            String s = switch (searchKey) {
                case "appointmentId":
                    yield appointment.getId();
                case "patientId":
                    yield appointment.getPatient().getId();
                case "doctorId":
                    yield appointment.getDoctor().getId();
                case "date":
                    yield String.valueOf(appointment.getDate());
                default:
                    System.out.println("Invalid searchKey!");
                    yield appointment.getId();
            };

            s = s.toLowerCase();
            if (s.equals(searchQuery.toLowerCase())) {
                appointments.add(appointment);
            }
        }
        return appointments;
    }

    public static String getNewAppointmentId() {
        return "AP"+(appointmentsList.size()+1);
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
                "id=" + id +
                ", patient=" + (patient==null?null:patient.getId()) +
                ", doctor="+ (doctor==null?null:doctor.getId()) +
                ", date=" + date +
                ", description=" + description +
                "}";
    }
}
