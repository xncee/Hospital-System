package hospital;

import java.time.LocalDate;
import java.util.*;

public class Main implements HospitalData, Color {
    static Scanner input = new Scanner(System.in);
    // medical record (((id))) for new patients??
    // handle null value
    static Hospital hospital = new Hospital("Al-Hayat Hospital", 700);

    public static void main(String[] args) {
        //hospital.printAll();
        homePage();
    }

    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds* 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void homePage() {
        wait(1);
        System.out.println("\n"+"# Home Page");

        System.out.println("1. Patient");
        System.out.println("2. Medical Staff");
        System.out.println("3. Medical Records");
        System.out.println("4. Appointments");
        System.out.println("99. Exit.. ");
        int c = getUserInput(new int[] {1, 2, 3, 4, 99});

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
                medicalRecordPage();
                break;
            case 4:
                appointmentsPage();
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
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 99});

        switch (c) {
            case 1: {
                for (Patient patient: patientsList) {
                    System.out.println(patient);
                }
                if (patientsList.isEmpty())
                    System.out.println(RED+"There's no patients!"+RESET);
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
                nursePage();
                break;
            }
            case 99:
                homePage();
                break;
        }
        medicalStaffPage();
    }

    public static void medicalRecordPage() {
        wait(1);
        System.out.println("\n# Medical Record Page");
        System.out.println("1. Print All MedicalRecords");
        System.out.println("2. Search");
        System.out.println("3. Manage MedicalRecord");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 99});

        switch (c) {
            case 1: {
                for (List<MedicalRecord> medicalRecord: medicalRecordsList) {
                    System.out.println(Arrays.toString(medicalRecord.toArray()));
                }
                break;
            }
            case 2: {
                medicalRecordSearchPage();
                break;
            }
            case 3: {
                medicalRecordManagementPage();
                break;
            }
            case 99:
                homePage();
                break;
        }
        medicalRecordPage();
    }
    public static void doctorPage() {
        wait(1);
        System.out.println("\n# Doctor Page");
        System.out.println("1. Print All Doctors");
        System.out.println("2. Search");
        System.out.println("3. Manage Doctor");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 99});

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
                doctorManagementPage();
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
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 99});

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
                nurseManagementPage();
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
        System.out.println("1. search by patientId");
        System.out.println("2. Search by patientName");
        System.out.println("3. search by phoneNumber");
        System.out.println("4. search by medicalRecordId");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 4, 99});

        switch (c) {
            case 1: {
                Patient patient = validatePatient();
                //Patient patient = Patient.find("patientId", patientId);
                if (patient!=null) {
                    System.out.println(GREEN + "Patient found: " + RESET);
                    System.out.println(patient);
                }
                break;
            }
            case 2: {
                System.out.println("Enter patientName: ");
                String patientName = input.nextLine().toLowerCase();

                Patient patient = Patient.find("patientName", patientName);
                if (patient != null) {
                    System.out.println("Patient found: ");
                    System.out.println(patient);
                } else {
                    System.out.println(RED+"Patient was not found!"+RESET);
                }
                break;
            }
            case 3: {
                System.out.println("Enter phoneNumber: ");
                String phoneNumber = input.nextLine();

                Patient patient = Patient.find("phoneNumber", phoneNumber);
                if (patient != null) {
                    System.out.println(GREEN+"Patient found: "+RESET);
                    System.out.println(patient);
                } else {
                    System.out.println(RED+"Patient was not found!"+RESET);
                }
                break;
            }
            case 4: {
                System.out.println("Enter medicalRecordId: ");
                String medicalRecordId = input.nextLine();

                Patient patient = Patient.find("medicalRecordId", medicalRecordId);
                if (patient != null) {
                    System.out.println(GREEN+"Patient found: "+RESET);
                    System.out.println(patient);
                } else {
                    System.out.println(RED+"Patient was not found!"+RESET);
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
                Doctor doctor = validateDoctor();
                if (doctor!=null) {
                    System.out.println(GREEN + "Doctor found: " + RESET);
                    System.out.println(doctor);
                }
                break;
            }
            case 2: {
                System.out.println("Enter doctorName: ");
                String doctorName = input.nextLine().toLowerCase();

                Doctor doctor = Doctor.find("doctorName", doctorName);
                if (doctor != null) {
                    System.out.println(GREEN+"Doctor found: "+RESET);
                    System.out.println(doctor);
                }
                else {
                    System.out.println(RED+"Doctor was not found!"+RESET);
                }
                break;
            }
            case 3: {
                System.out.println("Enter phoneNumber: ");
                String phoneNumber = input.nextLine();

                Doctor doctor = Doctor.find("phoneNumber", phoneNumber);
                if (doctor != null) {
                    System.out.println(GREEN+"Doctor found: "+RESET);
                    System.out.println(doctor);
                } else {
                    System.out.println(RED+"Doctor was not found!"+RESET);
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
                Nurse nurse = validateNurse();
                if (nurse!=null) {
                    System.out.println(GREEN + "Nurse found: " + RESET);
                    System.out.println(nurse);
                }
                break;
            }
            case 2: {
                System.out.println("Enter nurseName: ");
                String nurseName = input.nextLine().toLowerCase();

                Nurse nurse = Nurse.findNurse("nurseName", nurseName);
                if (nurse != null) {
                    System.out.println(GREEN+"Nurse found: "+RESET);
                    System.out.println(nurse);
                }
                else {
                    System.out.println(RED+"Nurse was not found!"+RESET);
                }
                break;
            }
            case 3: {
                System.out.println("Enter phoneNumber: ");
                String phoneNumber = input.nextLine();

                Nurse nurse = Nurse.findNurse("phoneNumber", phoneNumber);
                if (nurse != null) {
                    System.out.println(GREEN+"Nurse found: "+RESET);
                    System.out.println(nurse);
                } else {
                    System.out.println(RED+"Nurse was not found!"+RESET);
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

    public static void medicalRecordSearchPage() {
        medicalRecordSearchPage(false);
    }
    public static void medicalRecordSearchPage(boolean showOneTime) {
        wait(1);
        System.out.println("\n# Medical Records Search Page");
        System.out.println("1. Search by medicalRecordId");
        System.out.println("2. Search by patientId");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 99});

        switch (c) {
            case 1: {
                System.out.println("Enter medicalRecordId: ");
                String medicalRecordsId = input.nextLine();
                List<MedicalRecord> medicalRecords = MedicalRecord.findMedicalRecords("medicalRecordsId", medicalRecordsId);

                if (medicalRecords != null) {
                    System.out.println(GREEN+"MedicalRecords found: "+RESET);
                    System.out.println(medicalRecords);
                }
                else {
                    System.out.println(RED+"MedicalRecords were not found!"+RESET);
                }
                break;
            }
            case 2: {
                Patient patient = validatePatient();
                if (patient==null) {
                    medicalRecordSearchPage();
                    break;
                }
                String patientId = patient.getId();
                List<MedicalRecord> medicalRecords = MedicalRecord.findMedicalRecords("patientId", patientId);

                if (medicalRecords != null) {
                    System.out.println(GREEN+"MedicalRecords found: "+RESET);
                    System.out.println(medicalRecords);
                }
                else {
                    System.out.println(RED+"MedicalRecords were not found!"+RESET);
                }
                break;
            }
            case 99: {
                medicalRecordPage();
                break;
            }
        }

        if (!showOneTime)
            medicalRecordSearchPage();
    }

    public static void appointmentsSearchPage() {
        appointmentSearchPage(false);
    }
    public static void appointmentSearchPage(boolean showOneTime) {
        wait(1);
        System.out.println("\n# Appointments Search Page");
        System.out.println("1. search by appointmentId");
        System.out.println("2. search by patientId");
        System.out.println("3. search by doctorId");
        System.out.println("4. search by date");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 4, 9});

        switch (c) {
            case 1: {
                Appointment appointment = validateAppointment();
                if (appointment!=null) {
                    System.out.println(GREEN + "Appointment found: " + RESET);
                    System.out.println(appointment);
                }
                break;
            }
            case 2: {
                Patient patient = validatePatient();
                if (patient==null) {
                    break;
                }
                String patientId = patient.getId();

                List<Appointment> appointments = Appointment.find("patientId", patientId);
                if (appointments.isEmpty() || appointments==null) {
                    System.out.println(RED+"No Results!"+RESET);
                }
                else {
                    System.out.println(GREEN+"Results: "+RESET);
                    for (Appointment appointment: appointments) {
                        System.out.println(appointment);
                    }
                }
                break;
            }
            case 3: {
                String doctorId = validateDoctor().getId();

                List<Appointment> appointments = Appointment.find("doctorId", doctorId);
                if (appointments.isEmpty() || appointments==null) {
                    System.out.println(RED+"No Results!"+RESET);
                }
                else {
                    System.out.println(GREEN+"Results: "+RESET);
                    for (Appointment appointment: appointments) {
                        System.out.println(appointment);
                    }
                }
                break;
            }
            case 4: {
                LocalDate date = validateDate();
                List<Appointment> appointments = Appointment.find("date", String.valueOf(date));
                if (appointments.isEmpty() || appointments==null) {
                    System.out.println(RED+"No Results!"+RESET);
                }
                else {
                    System.out.println(GREEN+"Results: "+RESET);
                    for (Appointment appointment: appointments) {
                        System.out.println(appointment);
                    }
                }
                break;
            }
            case 99:
                if (!showOneTime)
                    appointmentsManagementPage();
                break;
        }
        if (!showOneTime)
            appointmentsSearchPage();
    }

    public static Doctor validateDoctor() {
        Doctor doctor;
        while (true) {
            System.out.println("Enter doctorId (enter '#' to search or '=' to quit): ");
            String doctorId = input.nextLine().toLowerCase();
            if (doctorId.equals("#")) {
                doctorSearchPage(true);
                continue;
            }
            else if (doctorId.equals("=")) {
                return null;
            }

            doctor = Doctor.find("doctorId", doctorId);
            if (doctor==null)
                System.out.println(RED+"Invalid Doctor!"+RESET);
            else
               return doctor;
        }
    }
    public static Nurse validateNurse() {
        Nurse nurse;
        while (true) {
            System.out.println("Enter nurseId (enter '#' to search or '=' to quit): ");
            String nurseId = input.nextLine().toLowerCase();;
            if (nurseId.equals("#")) {
                nurseSearchPage(true);
                continue;
            }
            else if (nurseId.equals("=")) {
                return null;
            }

            nurse = Nurse.findNurse("nurseId", nurseId);
            if (nurse==null)
                System.out.println(RED+"Invalid Nurse!"+RESET);
            else
                return nurse;
         }
    }
    public static Patient validatePatient() {
        Patient patient;
        while (true) {
            System.out.println("Enter patientId (enter '#' to search or '=' to quit): ");
            String patientId = input.nextLine().toLowerCase();;
            if (patientId.equals("#")) {
                patientSearchPage(true);
                continue;
            }
            else if (patientId.equals("=")) {
                return null;
            }

            patient = Patient.find("patientId", patientId);
            if (patient==null)
                System.out.println(RED+"Invalid Patient!"+RESET);
            else
                return patient;
        }
    }

    public static Appointment validateAppointment() {
        Appointment appointment;
        while (true) {
            System.out.println("Enter appointmentId (enter '#' to search or '=' to quit): ");
            String appointmentId = input.nextLine().toLowerCase();;
            if (appointmentId.equals("#")) {
                appointmentSearchPage(true);
                continue;
            }
            else if (appointmentId.equals("=")) {
                return null;
            }

            //List<Appointment> a = ;
            try {
                appointment = Appointment.find("appointmentId", appointmentId).get(0);
            } catch (Exception e) {
                appointment = null;
            }

            if (appointment==null)
                System.out.println(RED+"Invalid Appointment!"+RESET);
            else
                return appointment;
        }
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
                if (doctorsList.isEmpty()) {
                    System.out.println(RED + "There's no Doctors!" + RESET);
                    patientManagementPage();
                }
                if (nursesList.isEmpty()) {
                    System.out.println(RED + "There's no Nurses!" + RESET);
                    patientManagementPage();
                }

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

                Doctor doctor = validateDoctor();
                if (doctor==null)
                    break;
                Nurse nurse = validateNurse();
                if (nurse==null)
                    break;

                List<MedicalRecord> medicalRecords = new ArrayList<>();
                System.out.println("*Medical Record*");
                System.out.println("Enter diagnose: ");
                String diagnose = input.nextLine();
                System.out.println("Enter treatment: ");
                String treatment = input.nextLine();
                LocalDate date = validateDate();
                medicalRecords.add(new MedicalRecord(MedicalRecord.getNewMedicalRecordId(), id, diagnose, treatment, date));
                hospital.add(medicalRecords);
                hospital.add(new Patient(id, name, phoneNumber, age, gender, address, department, medicalRecords, doctor, nurse));
                System.out.println(GREEN+"Patient Added."+RESET);
                break;
            }
            case 2: {
                Patient patient = validatePatient();
                if (patient==null)
                    break;

                if (hospital.remove(patient))
                    System.out.println(YELLOW+"Patient was removed."+RESET);
                else
                    System.out.println(RED+"Patient wasn't removed."+RESET);
                break;
            }
            case 3: {
                changePatientInfoPage();
                break;
            }
            case 99: {
                patientPage();
                break;
            }
        }
        patientManagementPage();
    }

    public static void appointmentsPage() {
        wait(1);
        System.out.println("\n# Appointments Page");
        System.out.println("1. Search");
        System.out.println("2. Manage Appointments");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 99});

        switch (c) {
            case 1: {
                appointmentsSearchPage();
                break;
            }
            case 2: {
                appointmentsManagementPage();
                break;
            }
            case 99:
                homePage();
                break;
        }
        appointmentsPage();
    }

    public static void appointmentsManagementPage() {
        wait(1);
        System.out.println("\n# Manage Appointments Page");
        System.out.println("1. Add Appointment");
        System.out.println("2. Cancel Appointment");
        System.out.println("3. Change Appointment Date");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 99});

        switch (c) {
            case 1: {
                String id = Appointment.getNewAppointmentId();
                Patient patient = validatePatient();
                if (patient==null)
                    break;
                Doctor doctor = validateDoctor();
                if (doctor==null)
                    break;
                LocalDate date = validateDate();
                if (date==null)
                    break;
                String description = input.nextLine();

                hospital.add(new Appointment(id, patient, doctor, date, description));
                System.out.println(GREEN+"Appointment has been added."+RESET);
                break;
            }
            case 2: {
                Appointment appointment = validateAppointment();
                if (appointment==null)
                    break;
                hospital.remove(appointment);
                System.out.println(YELLOW+"Appointment has been canceled."+RESET);
                break;
            }
            case 3: {
                Appointment appointment = validateAppointment();
                if (appointment==null)
                    break;
                LocalDate date = validateDate();
                appointment.setDate(date);
                hospital.updateData();
                System.out.println(GREEN+"Appointment date has been changed."+RESET);
                break;
            }
            case 99:
                appointmentsPage();
                break;
        }
        appointmentsManagementPage();
    }
    public static String validateDoctorName() {
        System.out.println("Enter doctorName: ");
        String name = input.nextLine();
        for (Doctor doctor: doctorsList) {
            if (name.equals(doctor.getName())) {
                System.out.println(RED+"This doctorName is already registered!"+RESET);
                validateDoctorName();
                break;
            }
        }
        return name;
    }
    public static void doctorManagementPage() {
        wait(1);
        System.out.println("\n# Doctor Management Page");
        System.out.println("1. Add Doctor");
        System.out.println("2. Remove Doctor");
        System.out.println("3. Change Info");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 99});

        switch (c) {
            case 1: {
                String id = Doctor.getNewDoctorId();
                String name = validateDoctorName();

                System.out.println("Enter phoneNumber: ");
                String phoneNumber = input.nextLine();
                System.out.println("Enter specialization: ");
                String specialization = input.nextLine();

                hospital.add(new Doctor(id, name, phoneNumber, specialization));
                System.out.println(GREEN+"Dr. "+name+" was added."+RESET);
                break;
            }
            case 2: {
                Doctor doctor = validateDoctor();
                if (doctor==null)
                    break;
                hospital.remove(doctor);
                System.out.println(YELLOW+"Dr. "+doctor.getName()+" was removed."+RESET);
                break;
            }
            case 3: {
                changeDoctorInfoPage();
                break;
            }
            case 99:
                doctorPage();
                break;
        }
        doctorManagementPage();
    }

    public static String validateNurseName() {
        System.out.println("Enter nurseName: ");
        String name = input.nextLine();
        for (Nurse nurse: nursesList) {
            if (name.equals(nurse.getName())) {
                System.out.println(RED+"This nurseName is already registered!"+RESET);
                validateNurseName();
                break;
            }
        }
        return name;
    }

    public static void nurseManagementPage() {
        wait(1);
        System.out.println("\n# Nurse ManagementPage");
        System.out.println("1. Add Nurse");
        System.out.println("2. Remove Nurse");
        System.out.println("3. Change Info");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 99});

        switch (c) {
            case 1: {
                String id = Nurse.getNewNurseId();
                String name = validateNurseName();
                System.out.println("Enter phoneNumber: ");
                String phoneNumber = input.nextLine();
                System.out.println("Enter department: ");
                String department = input.nextLine();

                hospital.add(new Nurse(id, name, phoneNumber, department));
                System.out.println(GREEN+"Nurse. "+name+" was added."+RESET);
                break;
            }
            case 2: {
                Nurse nurse = validateNurse();
                hospital.remove(nurse);
                System.out.println(YELLOW+"Nurse. "+nurse.getName()+" was removed."+RESET);
                break;
            }
            case 3: {
                changeNurseInfoPage();
                break;
            }
            case 99:
                nursePage();
                break;
        }
        nurseManagementPage();
    }

    public static LocalDate validateDate() {
        try {
            System.out.println("Enter date (YYYY-MM-dd) (keep blank for current date): ");
            String strDate = input.nextLine();
            if (strDate.isEmpty())
                return LocalDate.now();

            return LocalDate.parse(strDate);
        }
        catch (Exception e) {
            return validateDate();
        }
    }
    public static void changePatientInfoPage() {
        wait(1);
        System.out.println("\n# Change Patient Info Page");
        System.out.println("1. Change phoneNumber");
        System.out.println("2. Change address");
        System.out.println("3. Change department");
        System.out.println("4. Change doctor");
        System.out.println("5. Change nurse");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 4, 5, 99});

        switch (c) {
            case 1: {
                Patient patient = validatePatient();
                if (patient==null)
                    break;
                System.out.println("Enter phoneNumber: ");
                String phoneNumber = input.nextLine();
                patient.setPhoneNumber(phoneNumber);
                hospital.updateData();
                System.out.println(GREEN+"phoneNumber has been updated."+RESET);
                break;
            }
            case 2: {
                Patient patient = validatePatient();
                if (patient==null)
                    break;
                System.out.println("Enter address: ");
                String address = input.nextLine();
                patient.setAddress(address);
                hospital.updateData();
                System.out.println(GREEN+"address has been updated."+RESET);
                break;
            }
            case 3: {
                Patient patient = validatePatient();
                if (patient==null)
                    break;
                System.out.println("Enter department: ");
                String department = input.nextLine();
                patient.setDepartment(department);
                hospital.updateData();
                System.out.println(GREEN+"department has been updated."+RESET);
                break;
            }
            case 4: {
                Patient patient = validatePatient();
                if (patient==null)
                    break;
                Doctor doctor = validateDoctor();
                if (doctor==null)
                    break;
                patient.setDoctor(doctor);
                hospital.updateData();
                System.out.println(GREEN+"Doctor has been updated."+RESET);
                break;
            }
            case 5: {
                Patient patient = validatePatient();
                if (patient==null)
                    break;
                Nurse nurse = validateNurse();
                if (nurse==null)
                    break;
                patient.setNurse(nurse);
                hospital.updateData();
                System.out.println(GREEN+"Nurse has been updated."+RESET);
                break;
            }
            case 99: {
                patientManagementPage();
                break;
            }
        }
        changePatientInfoPage();
    }

    public static void changeDoctorInfoPage() {
        wait(1);
        System.out.println("\n# Change Doctor Info Page");
        System.out.println("1. Change phoneNumber");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 99});

        switch (c) {
            case 1: {
                Doctor doctor = validateDoctor();
                if (doctor==null)
                    break;
                System.out.println("Enter phoneNumber: ");
                String phoneNumber = input.nextLine();
                doctor.setPhoneNumber(phoneNumber);
                hospital.updateData();
                System.out.println(GREEN+"phoneNumber has been updated."+RESET);
                break;
            }
            case 99: {
                doctorManagementPage();
                break;
            }
        }
        changeDoctorInfoPage();
    }

    public static void changeNurseInfoPage() {
        wait(1);
        System.out.println("\n# Change Nurse Info Page");
        System.out.println("1. Change phoneNumber");
        System.out.println("2. Change department");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 99});

        switch (c) {
            case 1: {
                Nurse nurse = validateNurse();
                if (nurse==null)
                    break;
                System.out.println("Enter phoneNumber: ");
                String phoneNumber = input.nextLine();
                nurse.setPhoneNumber(phoneNumber);
                hospital.updateData();
                System.out.println(GREEN+"phoneNumber has been updated."+RESET);
                break;
            }
            case 2:
                Nurse nurse = validateNurse();
                if (nurse==null)
                    break;
                System.out.println("Enter department: ");
                String department = input.nextLine();
                nurse.setDepartment(department);
                hospital.updateData();
                System.out.println(GREEN+"department has been updated."+RESET);
                break;
            case 99: {
                nurseManagementPage();
                break;
            }
        }
        changeNurseInfoPage();
    }
    public static void medicalRecordManagementPage() {
        wait(1);
        System.out.println("\n# Medical Record Management Page");
        System.out.println("1. Add MedicalRecord");
        System.out.println("2. Remove MedicalRecord");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 99});

        switch (c) {
            case 1: {
                Patient patient = validatePatient();
                if (patient==null)
                    break;
                String patientId = patient.getId();
                List<MedicalRecord> medicalRecords = MedicalRecord.findMedicalRecords("patientId", patientId);
                if (medicalRecords==null)
                    medicalRecords = new ArrayList<>();

                String id = medicalRecords.isEmpty()?MedicalRecord.getNewMedicalRecordId():medicalRecords.get(0).getId();
                System.out.println("Enter diagnose: ");
                String diagnose = input.nextLine();
                System.out.println("Enter treatment: ");
                String treatment = input.nextLine();
                LocalDate date = validateDate();

                medicalRecords.add(new MedicalRecord(id, patientId, diagnose, treatment, date));
                hospital.add(medicalRecords);
                System.out.println(GREEN+"MedicalRecord has been added."+RESET);
                break;
            }
            case 2: {
                boolean removed = false;
                System.out.println("Enter medicalRecordID: ");
                String medicalRecordId = input.nextLine();
                out:for (List<MedicalRecord> record: medicalRecordsList) {
                    for (MedicalRecord r: record) {
                        if (r.getId().equals(medicalRecordId)) {
                            hospital.remove(record);
                            removed = true;
                            break out;
                        }
                        else
                            break;
                    }
                }
                if (removed) {
                    hospital.updateData();
                    System.out.println(YELLOW+"MedicalRecord has been removed."+RESET);
                }
                else
                    System.out.println(RED+"MedicalRecord wasn't found!"+RESET);
                break;
            }
            case 99: {
                medicalRecordPage();
                break;
            }
        }
        medicalRecordManagementPage();
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
