package hospital;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private int age;
    private final String gender;
    private String address;
    private String departmentName;
    private Doctor doctor;
    private Nurse nurse;
    private List<MedicalRecord> medicalRecords;

    public Patient(String id, String name, String phoneNumber, int age, String gender, String address, String departmentName, List<MedicalRecord> medicalRecords, Doctor doctor, Nurse nurse) {
        super(id, name, phoneNumber);
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.departmentName = departmentName;
        this.medicalRecords = medicalRecords;
        setDoctor(doctor); // set with if?
        setNurse(nurse);
    }

    public static List<Patient> find(String searchKey, String searchQuery) {
        List<Patient> patients = new ArrayList<>();
        for (Patient patient: patientsList) {
            String s = switch (searchKey) {
                case "patientName":
                    yield patient.getName();
                case "phoneNumber":
                    yield patient.getPhoneNumber();
                case "nurseId":
                    yield patient.getNurse().getId();
                case "doctorId":
                    yield patient.getDoctor().getId();
                case "medicalRecordId": {
                    try {
                        if (medicalRecordsJson.get(searchQuery.toUpperCase())==null) {
                            System.out.println("medicalRecord was not found!");
                            yield null;
                        }
                        //System.out.println(patient.getMedicalRecord().get(0).getId());
                        yield patient.getMedicalRecords().get(0).getId();
                    }
                    catch (Exception e) {
                        System.out.println("medicalRecord was not found!");
                        yield null;
                    }
                }
                case "patientId":
                    yield patient.getId();
                case "departmentName":
                    yield patient.getDepartmentName();
                default:
                    System.out.println("Invalid searchKey!");
                    yield patient.getId();
            };

            if (s==null) break;
            s = s.toLowerCase();
            searchQuery = searchQuery.toLowerCase();

            if (s.equals(searchQuery)) {
                patients.add(patient);
            }
            else if ((searchKey.equals("patientName") || searchKey.equals("phoneNumber")) && s.startsWith(searchQuery)) {
                patients.add(patient);
            }
        }
        return patients;
    }

    public static String getNewPatientId() {
        String str;
        if (patientsList.isEmpty()) {
            str = patientsList.get(patientsList.size() - 1).getId().split("P")[1];
        }
        else
            str = "0";
        return "P"+(Integer.parseInt(str)+1);
    }

    public List<Appointment> getAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        for (Appointment appointment: appointmentsList) {
            if (appointment.getPatient().getId().equals(getId())) {
                appointments.add(appointment);
            }
        }

        return appointments;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        if (doctor!=null)
            this.doctor = doctor;
        else
            System.out.println("Invalid Doctor.");
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        if (nurse!=null)
            this.nurse = nurse;
        else
            System.out.println("Invalid Nurse.");
    }

    public List<MedicalRecord> getMedicalRecords() {
        if (medicalRecords!=null)
            return medicalRecords;
        return new ArrayList<>();
    }

    public void setMedicalRecords(List<MedicalRecord> medicalRecord) {
        this.medicalRecords = medicalRecord;
    }

    //@Override
    public String toString() {
        return "Patient{"+
                super.toString() +
                ", age=" + age +
                ", gender=" + gender +
                ", address=" + address +
                ", departmentName=" + departmentName +
                ", doctor=" + (doctor==null?null:doctor.getId()) +
                ", nurse=" + (nurse==null?null:nurse.getId()) +
                ", medicalRecords=" + medicalRecords +
                "}";
    }
}
