package hospital;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.List;

public interface HospitalData {
    File patientsFile = new File("patients.json");
    ObjectNode patientsJson = patientsFile.read();
    List<Patient> patientsList = new ArrayList<>();
    File doctorsFile = new File("doctors.json");
    ObjectNode doctorsJson = doctorsFile.read();
    List<Doctor> doctorsList = new ArrayList<>();
    File nursesFile = new File("nurses.json");
    ObjectNode nursesJson = nursesFile.read();
    List<Nurse> nursesList = new ArrayList<>();
    File medicalRecordsFile = new File("medicalRecords.json"); // array node
    ObjectNode medicalRecordsJson = medicalRecordsFile.read();
    List<List<MedicalRecord>> medicalRecordsList = new ArrayList<>();
    File appointmentsFile = new File("appointments.json"); // array node
    ObjectNode appointmentsJson = appointmentsFile.read();
    List<Appointment> appointmentsList = new ArrayList<>();


}
