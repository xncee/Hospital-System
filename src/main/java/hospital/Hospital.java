package hospital;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.management.InvalidAttributeValueException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Hospital implements HospitalData, Manageable, Color {
    public final String HOSPITAL_NAME;
    public final int  MAX_CAPACITY;

    // make methods to store data after changing it (updatePatients, Doctors, Nurses, MedicalRecords, Appointments)
    public Hospital(String HOSPITAL_NAME, int MAX_CAPACITY) {
        this.HOSPITAL_NAME = HOSPITAL_NAME;
        this.MAX_CAPACITY = MAX_CAPACITY;
        System.out.println(WHITE_BACKGROUND+"# "+HOSPITAL_NAME+RESET);

        // The order of these invocations is critical.
        initializeMedicalRecords();
        //System.out.println("MedicalRecords Initialized Successfully.");
        initializeDoctors();
        //System.out.println("Doctors Initialized Successfully.");
        initializeNurses();
        //System.out.println("Nurses Initialized Successfully.");
        initializePatients();
        //System.out.println("Patients Initialized Successfully.");
        initializeAppointmentsList();
        //System.out.println("Appointments Initialized Successfully.");
        if (doctorsList.isEmpty())
            System.out.println(RED+"There is no doctors!"+RESET);
        if (nursesList.isEmpty())
            System.out.println(RED+"There is no nurses!"+RESET);
    }

    public List getList(Object o) throws InvalidAttributeValueException {
        if (o instanceof Patient)
            return patientsList;

        else if (o instanceof Doctor)
            return doctorsList;

        else if (o instanceof Nurse)
            return nursesList;

        else if (o instanceof List)
            return medicalRecordsList;

        throw new InvalidAttributeValueException("Invalid Argument.");
    }

    @Override
    public boolean remove(Object o) {
        List list;
        try {
            list = getList(o);
        } catch (InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }

        boolean removed = list.remove(o);
        if (removed)
            updateDate();
        return removed;
    }

    @Override
    public void add(Object o) {
        List list;
        try {
            list = getList(o);
            //System.out.println(list);
        } catch (InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }

        if (o instanceof Patient && list.size()>=MAX_CAPACITY) {
            System.out.println(RED+"Maximum Capacity has been reached!"+RESET);
            return;
        }
        list.add(o);
        updateDate();
    }

    protected void printAll() {
        System.out.println("\nMedicalRecords: ");
        for (List<MedicalRecord> r: medicalRecordsList) {
            System.out.println(Arrays.toString(r.toArray()));
        }

        System.out.println("\nDoctors: ");
        for (Doctor d: doctorsList) {
            System.out.println(d);
        }

        System.out.println("\nPatients: ");
        for (Patient p: patientsList) {
            System.out.println(p);
        }

        System.out.println("\nNurses: ");
        for (Nurse n: nursesList) {
            System.out.println(n);
        }

        System.out.println("\nAppointments: ");
        for (Appointment a: appointmentsList) {
            System.out.println(a);
        }
    }

    public Patient getPatient(JsonNode node, String id) {
        String name = node.get("name").asText();
        String phoneNumber = node.get("phoneNumber").asText();
        int age = node.get("age").asInt();
        String gender = node.get("gender").asText();
        String address = node.get("address").asText();
        String department = node.get("department").asText();
        List<MedicalRecord> medicalRecords = getMedicalRecords(node.get("medicalRecords").asText());
        Doctor doctor = getDotor(node.get("doctor").asText());
        Nurse nurse = getNurse(node.get("nurse").asText());

        return new Patient(id, name, phoneNumber, age, gender, address, department, medicalRecords, doctor, nurse);
    }
    public Patient getPatient(String id) {
        for (Patient patient: patientsList) {
            if (patient.getId().equals(id))
                return patient;
        }

        return null;
    }

    public Doctor getDotor(JsonNode node, String id) {
        String name = node.get("name").asText();
        String phoneNumber = node.get("phoneNumber").asText();
        String specialization = node.get("specialization").asText();

        return new Doctor(id, name, phoneNumber, specialization);
    }
    public Doctor getDotor(String id) {
        for (Doctor doctor: doctorsList) {
            if (doctor.getId().equals(id))
                return doctor;
        }

        return null;
    }

    public Nurse getNurse(JsonNode node, String id) {
        String name = node.get("name").asText();
        String phoneNumber = node.get("phoneNumber").asText();
        String department = node.get("department").asText();

        return new Nurse(id, name, phoneNumber, department);
    }
    public Nurse getNurse(String id) {
        for (Nurse nurse: nursesList) {
            if (nurse.getId().equals(id))
                return nurse;
        }

        return null;
    }

    public List<MedicalRecord> getMedicalRecords(JsonNode node, String id) {
        List<MedicalRecord> medicalRecords = new ArrayList<>();

        for (JsonNode jsonNode: node) {
            // medical records are initialized before medical patients
            String patient = jsonNode.get("patient").asText();
            String diagnose = jsonNode.get("diagnose").asText();
            String treatment = jsonNode.get("treatment").asText();
            LocalDate date = LocalDate.parse(jsonNode.get("date").asText());

            medicalRecords.add(new MedicalRecord(id, patient, diagnose, treatment, date));
        }

        return medicalRecords;
    }
    public List<MedicalRecord> getMedicalRecords(String id) {
        for (List<MedicalRecord> record: medicalRecordsList) {
            for (MedicalRecord r: record) {
                if (r.getId().equals(id)) {
                    return record;
                }
                break;
            }
        }

        return null;
    }

    public Appointment getAppointment(JsonNode node, String id) {
        Patient patient = getPatient(node.get("patient").asText());
        Doctor doctor = getDotor(node.get("doctor").asText());
        LocalDate date = LocalDate.parse(node.get("date").asText());
        String description = node.get("description").asText();

        return new Appointment(id, patient, doctor, date, description);
    }

    private void initializePatients() {
        Iterator<String> ids = patientsJson.fieldNames();
        for (JsonNode node: patientsJson) {
            patientsList.add(getPatient(node, ids.next()));
        }
    }

    private void initializeDoctors() {
        Iterator<String> ids = doctorsJson.fieldNames();
        for (JsonNode node: doctorsJson) {
            doctorsList.add(getDotor(node, ids.next()));
        }
    }

    private void initializeNurses() {
        Iterator<String> ids = nursesJson.fieldNames();
        for (JsonNode node: nursesJson) {
            nursesList.add(getNurse(node, ids.next()));
        }
    }

    private void initializeMedicalRecords() {
        Iterator<String> ids = medicalRecordsJson.fieldNames();
        for (JsonNode node: medicalRecordsJson) {
            medicalRecordsList.add(getMedicalRecords(node, ids.next()));
        }
    }

    private void initializeAppointmentsList() {
        Iterator<String> ids = appointmentsJson.fieldNames();
        for (JsonNode node: appointmentsJson) {
            appointmentsList.add(getAppointment(node, ids.next()));
        }
    }

    public void updatePatients() {
        for (Patient patient: patientsList) {
            patientsJson.put(
                    patient.getId(),
                    new ObjectMapper().createObjectNode()
                            .put("name", patient.getName())
                            .put("phoneNumber", patient.getPhoneNumber())
                            .put("age", patient.getAge())
                            .put("gender", patient.getGender())
                            .put("address", patient.getAddress())
                            .put("department", patient.getDepartment())
                            .put("medicalRecords", patient.getMedicalRecords().isEmpty()?null:patient.getMedicalRecords().get(0).getId())
                            .put("doctor", patient.getDoctor().getId())
                            .put("nurse", patient.getNurse().getId())
            );
        }
        //System.out.println(patientsJson.toPrettyString());
        patientsFile.write();
    }

    public void updateDoctors() {
        for (Doctor doctor: doctorsList) {
            doctorsJson.put(
                    doctor.getId(),
                    new ObjectMapper().createObjectNode()
                            .put("name", doctor.getName())
                            .put("phoneNumber", doctor.getPhoneNumber())
                            .put("specialization", doctor.getSpecialization())
            );
        }
        //System.out.println(doctorsJson.toPrettyString());
        doctorsFile.write();
    }

    public void updateNurses() {
        for (Nurse nurse: nursesList) {
            doctorsJson.put(
                    nurse.getId(),
                    new ObjectMapper().createObjectNode()
                            .put("name", nurse.getName())
                            .put("phoneNumber", nurse.getPhoneNumber())
                            .put("department", nurse.getDepartment())
            );
        }
        //System.out.println(nursesJson.toPrettyString());
        nursesFile.write();
    }

    public void updateMedicalRecords() {
        for (List<MedicalRecord> record: medicalRecordsList) {
            List<JsonNode> nodes = new ArrayList<>();
            String id = record.get(0).getId();
            for (MedicalRecord r: record) {
                ObjectNode tempNode = new ObjectMapper().createObjectNode();
                tempNode.put("patient", r.getPatientId())
                        .put("diagnose", r.getDiagnose())
                        .put("treatment", r.getTreatment())
                        .put("date", String.valueOf(r.getDate()));
                nodes.add(tempNode);
            }
            ArrayNode arr = new ObjectMapper().valueToTree(nodes);
            medicalRecordsJson.putArray(id).addAll(arr);
        }
        //System.out.println(medicalRecordsJson.toPrettyString());
        medicalRecordsFile.write();
    }

    public void updateAppointments() {
        for (Appointment appointment: appointmentsList) {
            doctorsJson.put(
                    appointment.getId(),
                    new ObjectMapper().createObjectNode()
                            .put("patient", appointment.getPatient().getId())
                            .put("doctor", appointment.getDoctor().getId())
                            .put("date", String.valueOf(appointment.getDate()))
                            .put("description", appointment.getDescription())
            );
        }
        //System.out.println(appointmentsJson.toPrettyString());
        appointmentsFile.write();
    }

    @Override
    public void updateDate() {
        updatePatients();
        updateDoctors();
        updateNurses();
        updateMedicalRecords();
        updateAppointments();
    }

}

