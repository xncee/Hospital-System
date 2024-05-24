package hospital;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main implements HospitalData, Color {
    /*
    Main

        // read the file and initialize these arrays.

        +main(args: String[]): void
            file = JsonFileReader / FileReader and split
            initializePatients();
            initializeDoctors();

        +initializePatients(Hospital hospital): void
        +initializeDoctors(Hospital hospital): void

    Hospital
        +HOSPITAL_NAME: String // final
        +MAX_CAPACITY: int = value // final static
        +patients: List<Patient>
        +doctors: List<Doctor>
        +nurses: List<Nurse>
        +medicalRecords: List<MedicalRecord>
        +appointments: List<Appointment>
        +log: List<String>

        +Hospital(hospitalName: String, MAX_CAPACITY: int)
        +addPatient(Patient patient): void
        +addDoctor(Doctor doctor): void
        +addNurse(Nurse nurse): void
        +addMedicalRecord(MedicalRecord medicalRecord): void
        +addAppointment(Appointment appointment): void

    Person extends Hospital
        -id: String
        -name: String
        -phoneNumber: String

        +Person(id: int, name: String, phoneNumber: String)

        +setters & getters

     <<interface>>

        public boolean isAvailable();
        public void checkIn();
        public void checkOut();

    Patient extends Person implements
        -age: int
        -gender: String
        -address: String
        -department: String
        -medicalRecord: List<MedicalRecord>

        +Patient(name: String, id: int, department: String, medicalRecord: String[])
        +addMedicalRecord(MedicalRecord)
        +getMedicalRecord(): String
        +assignDoctor(Doctor doctor): void
        +assignNurse(Nurse nurse): void

    Doctor extends Person implements
        -specialization: String

        +Doctor(id: int, name: String, phoneNumber: int, specialization: String)

    Nurse extends Person implements
        -department: String

        +Nurse(id: int, name: String, phoneNumber: int, department: String)

    Appointment
        -id: String
        -patient: Patient;
        -doctor: Doctor;
        -date: Date;
        -description: String;

        +Appointment(id: int, patient: Patient, doctor: Doctor, date: Date, description: String)

        +setters & getters

    MedicalRecord
        -patient: Patient;
        -diagnose: String;
        -treatment: String;
        -date: Date;

        +MedicalRecord(patient: Patient, diagnose: String, treatment: String, date: Date)

        +setters & getters
     */
    static Scanner input = new Scanner(System.in);
    // medical record (((id))) for new patients??
    static Hospital hospital = new Hospital("Al-Hayat Hospital", 700);

    public static void main(String[] args) {
        //hospital.printAll();
        homePage();
    }

    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void homePage() {
        wait(1);
        System.out.println("\n"+YELLOW_BACKGROUND+"# Home Page"+RESET);

        System.out.println("1. Patient");
        System.out.println("2. Medical Staff");
        System.out.println("3. Medical Records");
        System.out.println("99. Exit.. ");
        int c = getUserInput(new int[] {1, 2, 3, 99});

        switch (c) {
            case 1: {
                patientPage();
                break;
            }
            case 2: {
                medicalStaffPage();
                break;
            }
            case 3:
                medicalRecordsPage();
                break;
            case 99:
                System.exit(0);
        }
        homePage();
    }

    public static void patientPage() {
        wait(1);
        System.out.println("\n# Patient Page");
        System.out.println("1. Print All Patients");
        System.out.println("2. Search");
        System.out.println("3. Manage Patient");
        System.out.println("4. Appointments");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 4, 99});

        switch (c) {
            case 1: {

                for (Patient patient: patientsList) {
                    System.out.println(patient);
                }
                break;
            }
            case 2: {
                patientSearchPage();
                break;
            }
            case 3: {
                patientManagementPage();
                break;
            }
            case 99:
                homePage();
                break;
        }
        patientPage();
    }

    public static void medicalStaffPage() {
        wait(1);
        System.out.println("\n# Medical Staff Page");
        System.out.println("1. Doctors");
        System.out.println("2. Nurses");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 99});

        switch (c) {
            case 1: {
                doctorPage();
                break;
            }
            case 2: {
                //nursesPage();
                break;
            }
            case 99:
                homePage();
                break;
        }
    }

    public static void medicalRecordsPage() {
        wait(1);
        System.out.println("\n# Medical Records Page");
        System.out.println("1. Print All MedicalRecords");
        System.out.println("1. Print All MedicalRecords");
    }
    public static void doctorPage() {
        wait(1);
        System.out.println("\n# Doctor Page");
        System.out.println("1. Print All Doctors");
        System.out.println("2. Search");
        System.out.println("3. Manage Doctor");
        System.out.println("4. Appointments");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 4, 99});

        switch (c) {
            case 1: {
                for (Doctor doctor: doctorsList) {
                    System.out.println(doctor);
                }
                break;
            }
            case 2: {
                doctorSearchPage();
                break;
            }
            case 3: {
                //doctorManagementPage();
                break;
            }
            case 4: {
                //doctorAppointmentsPage()
                break;
            }
            case 99:
                medicalStaffPage();
                break;
        }
        doctorPage();
    }

    public static void nursePage() {
        wait(1);
        System.out.println("\n# Nurse Page");
        System.out.println("1. Print All Nurses");
        System.out.println("2. Search");
        System.out.println("3. Manage Nurse");
        System.out.println("4. Appointments");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 4, 99});

        switch (c) {
            case 1: {
                for (Nurse nurse: nursesList) {
                    System.out.println(nurse);
                }
                break;
            }
            case 2: {
                nurseSearchPage();
                break;
            }
            case 3: {
                //nurseManagementPage();
                break;
            }
            case 4: {
                //nurseAppointmentsPage()
                break;
            }
            case 99:
                medicalStaffPage();
                break;
        }
        nursePage();
    }

    public static void patientSearchPage() {
        patientSearchPage(false);
    }
    public static void patientSearchPage(boolean showOneTime) {
        wait(1);
        System.out.println("\n# Patient Search Page");
        System.out.println("1. Search by patientId");
        System.out.println("2. Search by patientName");
        System.out.println("3. search by phoneNumber");
        System.out.println("4. search by medicalRecordId");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 4, 99});

        switch (c) {
            case 1: {
                System.out.println("Enter patientId: ");
                String patientId = input.nextLine().toLowerCase();

                Patient patient = Patient.findPatient("patientId", patientId);
                if (patient != null) {
                    System.out.println("Patient found: ");
                    System.out.println(patient);
                } else {
                    System.out.println("Patient was not found!");
                }
                break;
            }
            case 2: {
                System.out.println("Enter patientName: ");
                String patientName = input.nextLine().toLowerCase();

                Patient patient = Patient.findPatient("patientName", patientName);
                if (patient != null) {
                    System.out.println("Patient found: ");
                    System.out.println(patient);
                } else {
                    System.out.println("Patient was not found!");
                }
                break;
            }
            case 3: {
                System.out.println("Enter phoneNumber: ");
                String phoneNumber = input.nextLine();

                Patient patient = Patient.findPatient("phoneNumber", phoneNumber);
                if (patient != null) {
                    System.out.println("Patient found: ");
                    System.out.println(patient);
                } else {
                    System.out.println("Patient was not found!");
                }
                break;
            }
            case 4: {
                System.out.println("Enter medicalRecordId: ");
                String medicalRecordId = input.nextLine();

                Patient patient = Patient.findPatient("medicalRecordId", medicalRecordId);
                if (patient != null) {
                    System.out.println("Patient found: ");
                    System.out.println(patient);
                } else {
                    System.out.println("Patient was not found!");
                }
                break;
            }
            case 99:
                if (!showOneTime)
                    patientPage();
                break;
        }
        if (!showOneTime) {
            patientSearchPage();
        }
    }

    public static void doctorSearchPage() {
        doctorSearchPage(false);
    }
    public static void doctorSearchPage(boolean showOneTime) {
        wait(1);
        System.out.println("\n# Doctor Search Page");
        System.out.println("1. Search by doctorId");
        System.out.println("2. Search by doctorName");
        System.out.println("3. search by phoneNumber");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 99});

        switch (c) {
            case 1: {
                System.out.println("Enter doctorId: ");
                String doctorId = input.nextLine().toLowerCase();

                Doctor doctor = Doctor.findDoctor("doctorId", doctorId);
                if (doctor != null) {
                    System.out.println("Doctor found: ");
                    System.out.println(doctor);
                }
                else {
                    System.out.println("Doctor was not found!");
                }
                break;
            }
            case 2: {
                System.out.println("Enter doctorName: ");
                String doctorName = input.nextLine().toLowerCase();

                Doctor doctor = Doctor.findDoctor("doctorName", doctorName);
                if (doctor != null) {
                    System.out.println("Doctor found: ");
                    System.out.println(doctor);
                }
                else {
                    System.out.println("Doctor was not found!");
                }
                break;
            }
            case 3: {
                System.out.println("Enter phoneNumber: ");
                String phoneNumber = input.nextLine();

                Doctor doctor = Doctor.findDoctor("phoneNumber", phoneNumber);
                if (doctor != null) {
                    System.out.println("Doctor found: ");
                    System.out.println(doctor);
                } else {
                    System.out.println("Doctor was not found!");
                }
                break;
            }
            case 99:
                if (!showOneTime)
                    doctorPage();
                break;
        }
        if (!showOneTime)
            doctorSearchPage();
    }

    public static void nurseSearchPage() {
        nurseSearchPage(false);
    }
    public static void nurseSearchPage(boolean showOneTime) {
        wait(1);
        System.out.println("\n# Nurse Search Page");
        System.out.println("1. Search by nurseId");
        System.out.println("2. Search by nurseName");
        System.out.println("3. search by phoneNumber");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 99});

        switch (c) {
            case 1: {
                System.out.println("Enter nurseId: ");
                String nurseId = input.nextLine().toLowerCase();

                Nurse nurse = Nurse.findNurse("nurseId", nurseId);
                if (nurse != null) {
                    System.out.println("Nurse found: ");
                    System.out.println(nurse);
                }
                else {
                    System.out.println("Nurse was not found!");
                }
                break;
            }
            case 2: {
                System.out.println("Enter nurseName: ");
                String nurseName = input.nextLine().toLowerCase();

                Nurse nurse = Nurse.findNurse("nurseName", nurseName);
                if (nurse != null) {
                    System.out.println("Nurse found: ");
                    System.out.println(nurse);
                }
                else {
                    System.out.println("Nurse was not found!");
                }
                break;
            }
            case 3: {
                System.out.println("Enter phoneNumber: ");
                String phoneNumber = input.nextLine();

                Nurse nurse = Nurse.findNurse("phoneNumber", phoneNumber);
                if (nurse != null) {
                    System.out.println("Nurse found: ");
                    System.out.println(nurse);
                } else {
                    System.out.println("Nurse was not found!");
                }
                break;
            }
            case 99:
                if (!showOneTime)
                    nursePage();
                break;
        }
        if (!showOneTime)
            nurseSearchPage();
    }

    public static Doctor validateDoctor() {
        Doctor doctor;
        while (true) {
            System.out.println("Enter doctorId (enter # to search): ");
            String doctorId = input.nextLine().toLowerCase();
            if (doctorId.equals("#")) {
                doctorSearchPage(true);
                continue;
            }

            doctor = Doctor.findDoctor("doctorId", doctorId);
            if (doctor==null)
                System.out.println("Invalid Doctor!");
            else
               return doctor;
        }
        //System.out.println(doctor);
        //return null;
    }
    public static Nurse validateNurse() {
        Nurse nurse;
        while (true) {
            System.out.println("Enter nurseId (enter # to search): ");
            String nurseId = input.nextLine().toLowerCase();;
            if (nurseId.equals("#")) {
                nurseSearchPage(true);
                continue;
            }

            nurse = Nurse.findNurse("nurseId", nurseId);
            if (nurse==null)
                System.out.println("Invalid Nurse!");
            else
                return nurse;
         }
        //System.out.println(nurse);
        //return null;
    }
    public static Patient validatePatient() {
        Patient patient;
        while (true) {
            System.out.println("Enter patientId (enter # to search): ");
            String patientId = input.nextLine().toLowerCase();;
            if (patientId.equals("#")) {
                doctorSearchPage(true);
                continue;
            }

            patient = Patient.findPatient("patientId", patientId);
            if (patient==null)
                System.out.println("Invalid Patient!");
            else
                return patient;
        }

        //return null;
    }
    public static void patientManagementPage() {
        wait(1);
        System.out.println("\n# Patient Management Page");
        System.out.println("1. Add Patient");
        System.out.println("2. Remove Patient");
        System.out.println("3. Change Information");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 99});

        switch (c) {
            case 1: {
                String id = Patient.getNewPatientId();
                System.out.println("Enter patientName: ");
                String name = input.nextLine();
                System.out.println("Enter phoneNumber: "); // validation?
                String phoneNumber = input.nextLine();
                System.out.println("Enter age: ");
                int age = input.nextInt(); input.nextLine();
                System.out.println("Enter gender: ");
                String gender = input.nextLine();
                System.out.println("Enter address: ");
                String address = input.nextLine();
                System.out.println("Enter department: ");
                String department = input.nextLine();
                List<MedicalRecord> medicalRecord = new ArrayList<>();
                Doctor doctor = validateDoctor();
                Nurse nurse = validateNurse();

                hospital.add(new Patient(id, name, phoneNumber, age, gender, address, department, medicalRecord, doctor, nurse));
                System.out.println(GREEN+"Patient Added."+RESET);
                break;
            }
            case 2: {
                Patient patient = validatePatient();

                if (hospital.remove(patient))
                    System.out.println(YELLOW+"Patient was removed."+RESET);
                else
                    System.out.println(RED+"Patient wasn't removed."+RESET);
                break;
            }
            case 3: {
                //changePatientInfoPage();
                break;
            }
            case 99: {
                patientPage();
                break;
            }
        }
        patientManagementPage();
    }

    public static int getUserInput(int[] choices) {
        int choice;
        try {
            choice = input.nextInt();
            input.nextLine();
            for (int c: choices) {
                if (c==choice) {
                    return choice;
                }
            }
        }
        catch (Exception e) {
            input.nextLine();
        }

        System.out.println("Invalid input!");
        return getUserInput(choices);
    }

}
