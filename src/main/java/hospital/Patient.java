package hospital;

import java.util.List;

public class Patient extends Person {
    private int age;
    private final String gender;
    private String address;
    private String department;
    private Doctor doctor;
    private Nurse nurse;
    private List<MedicalRecord> medicalRecords;

    public Patient(String id, String name, String phoneNumber, int age, String gender, String address, String department, List<MedicalRecord> medicalRecords, Doctor doctor, Nurse nurse) {
        super(id, name, phoneNumber);
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.medicalRecords = medicalRecords;
        setDoctor(doctor); // set with if?
        setNurse(nurse);
    }

    public void addMedicalRecord(MedicalRecord record) {
        medicalRecords.add(record);
    }

    public static Patient findPatient(String searchKey, String searchQuery) {
        for (Patient patient: patientsList) {
            String s = switch (searchKey) {
                case "patientName":
                    yield patient.getName();
                case "phoneNumber":
                    yield patient.getPhoneNumber();
                case "medicalRecordId": {
                    try {
                        if (medicalRecordsJson.get(searchQuery)==null) {
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
                default:
                    System.out.println("Invalid searchKey!");
                case "patientId":
                    yield patient.getId();
            };

            if (s==null)
                return null;

            s = s.toLowerCase();
            //System.out.println(s);
            if (s.equals(searchQuery.toLowerCase())) {
                return patient;
            }
        }
        return null;
    }

    public static String getNewPatientId() {
        return "P"+(patientsList.size()+1);
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
        return medicalRecords;
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
                ", department=" + department +
                ", doctor=" + doctor.getId() +
                ", nurse=" + nurse.getId() +
                ", medicalRecords=" + medicalRecords +
                "}";
    }
}
