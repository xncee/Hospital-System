package hospital;

import design.Color;

import java.time.LocalDate;
import java.util.*;

/*
Emergency Department (ED): Handles urgent and life-threatening conditions requiring immediate attention.
Intensive Care Unit (ICU): Provides care for critically ill patients who need constant monitoring and support.
Cardiology: Focuses on heart-related conditions, offering diagnostic and treatment services for cardiovascular diseases.
Oncology: Specializes in the diagnosis and treatment of cancer, including chemotherapy, radiation therapy, and surgery.
Pediatrics: Provides medical care for infants, children, and adolescents.
Obstetrics and Gynecology (OB/GYN): Manages pregnancy, childbirth, and disorders of the female reproductive system.
Surgery: Performs surgical procedures for various conditions, often divided into sub-specialties like orthopedic, cardiovascular, and neurosurgery.
Neurology: Diagnoses and treats disorders of the nervous system, including the brain, spinal cord, and nerves.
Radiology: Uses imaging techniques like X-rays, CT scans, MRIs, and ultrasounds to diagnose and treat diseases.
Anesthesiology: Manages pain and provides anesthesia during surgeries and other medical procedures.
Orthopedics: Treats musculoskeletal system issues, including bones, joints, ligaments, tendons, and muscles.
Gastroenterology: Focuses on the digestive system and its disorders.
Nephrology: Specializes in kidney care and treating kidney-related diseases.
Urology: Deals with the urinary system and male reproductive organs.
Endocrinology: Treats hormonal imbalances and disorders, including diabetes and thyroid issues.
Dermatology: Manages skin-related conditions.
Psychiatry and Psychology: Provides mental health services, including therapy and psychiatric medication management.
Pathology: Examines tissues, cells, and bodily fluids to diagnose diseases.
Ophthalmology: Focuses on eye care and vision-related issues.
Otolaryngology (ENT): Treats conditions related to the ear, nose, and throat.
Rehabilitation Services: Provides physical therapy, occupational therapy, and speech therapy to help patients recover and improve function.
Pharmacy: Manages the dispensing of medications and provides pharmaceutical care.
Laboratory Services: Conducts various tests on blood, tissue, and other samples to aid in diagnosis and treatment.
Infectious Disease: Specializes in the treatment of infections, including those caused by bacteria, viruses, fungi, and parasites.
Palliative Care: Offers care aimed at providing relief from the symptoms and stress of serious illness.
 */
// add a menu for departments
public class Main implements HospitalData, Color {
    static Scanner input = new Scanner(System.in);
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
        System.out.println("3. Manage Patients");
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
        System.out.println("1. Search");
        System.out.println("2. Manage MedicalRecord");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 99});

        switch (c) {
            case 1: {
                medicalRecordSearchPage();
                break;
            }
            case 2: {
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
        System.out.println("3. Manage Doctors");
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
        System.out.println("1. Search by patientId");
        System.out.println("2. Search by patientName");
        System.out.println("3. Search by phoneNumber");
        System.out.println("4. Search by medicalRecordId");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 4, 99});

        switch (c) {
            case 1: {
                Patient patient = validatePatient();

                if (patient!=null) {
                    System.out.println(GREEN + "Patient found: " + RESET);
                    System.out.println(patient);
                }
                break;
            }
            case 2: {
                System.out.println("Enter patientName: ");
                String patientName = input.nextLine().toLowerCase();

                List<Patient> patients = Patient.find("patientName", patientName);
                if (!patients.isEmpty()) {
                    System.out.println(GREEN+"Results: "+RESET);
                    for (Patient patient: patients) {
                        System.out.println(patient);
                    }
                }
                else {
                    System.out.println(RED+"No Results!"+RESET);
                }
                break;
            }
            case 3: {
                System.out.println("Enter phoneNumber: ");
                String phoneNumber = input.nextLine();

                List<Patient> patients = Patient.find("phoneNumber", phoneNumber);
                if (!patients.isEmpty()) {
                    System.out.println(GREEN+"Results: "+RESET);
                    for (Patient patient: patients) {
                        System.out.println(patient);
                    }
                }
                else {
                    System.out.println(RED+"No Results!"+RESET);
                }
                break;
            }
            case 4: {
                System.out.println("Enter medicalRecordId: ");
                String medicalRecordId = input.nextLine();

                List<Patient> patients = Patient.find("medicalRecordId", medicalRecordId);
                if (!patients.isEmpty()) {
                    System.out.println(GREEN+"Patient found: "+RESET);
                    System.out.println(patients.get(0));
                } else {
                    System.out.println(RED+"No Results!"+RESET);
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
        System.out.println("3. Search by phoneNumber");
        System.out.println("4. Search by specialization");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 4, 99});

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

                List<Doctor> doctors = Doctor.find("doctorName", doctorName);
                if (!doctors.isEmpty()) {
                    System.out.println(GREEN+"Results: "+RESET);
                    for (Doctor doctor: doctors) {
                        System.out.println(doctor);
                    }
                }
                else {
                    System.out.println(RED+"No Results!"+RESET);
                }
                break;
            }
            case 3: {
                System.out.println("Enter phoneNumber: ");
                String phoneNumber = input.nextLine();

                List<Doctor> doctors = Doctor.find("phoneNumber", phoneNumber);
                if (!doctors.isEmpty()) {
                    System.out.println(GREEN+"Results: "+RESET);
                    for (Doctor doctor: doctors) {
                        System.out.println(doctor);
                    }
                } else {
                    System.out.println(RED+"No Results!"+RESET);
                }
                break;
            }
            case 4: {
                System.out.println("Enter specialization: ");
                String specialization = input.nextLine();

                List<Doctor> doctors = Doctor.find("specialization", specialization);
                if (!doctors.isEmpty()) {
                    System.out.println(GREEN+"Results: "+RESET);
                    for (Doctor doctor: doctors) {
                        System.out.println(doctor);
                    }
                } else {
                    System.out.println(RED+"No Results!"+RESET);
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
        System.out.println("3. Search by phoneNumber");
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

                List<Nurse> nurses = Nurse.find("nurseName", nurseName);
                if (!nurses.isEmpty()) {
                    System.out.println(GREEN+"Results: "+RESET);
                    for (Nurse nurse: nurses) {
                        System.out.println(nurse);
                    }
                }
                else {
                    System.out.println(RED+"No Results!"+RESET);
                }
                break;
            }
            case 3: {
                System.out.println("Enter phoneNumber: ");
                String phoneNumber = input.nextLine();

                List<Nurse> nurses = Nurse.find("phoneNumber", phoneNumber);
                if (!nurses.isEmpty()) {
                    System.out.println(GREEN+"Results: "+RESET);
                    for (Nurse nurse: nurses) {
                        System.out.println(nurse);
                    }
                }
                else {
                    System.out.println(RED+"No Results!"+RESET);
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
                    System.out.println(GREEN+"Results: "+RESET);
                    for (MedicalRecord r: medicalRecords) {
                        System.out.println(r);
                    }
                }
                else {
                    System.out.println(RED+"No Results!"+RESET);
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
                    System.out.println(GREEN+"Results: "+RESET);
                    for (MedicalRecord r: medicalRecords) {
                        System.out.println(r);
                    }
                }
                else {
                    System.out.println(RED+"No Results!"+RESET);
                }
                break;
            }
            case 99: {
                if (!showOneTime)
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
        System.out.println("1. Search by appointmentId");
        System.out.println("2. Search by patientId");
        System.out.println("3. Search by doctorId");
        System.out.println("4. Search by date");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 4, 99});

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
                if (!appointments.isEmpty()) {
                    System.out.println(GREEN+"Results: "+RESET);
                    for (Appointment appointment: appointments) {
                        System.out.println(appointment);
                    }
                }
                else {
                    System.out.println(RED+"No Results!"+RESET);
                }
                break;
            }
            case 3: {
                String doctorId = validateDoctor().getId();

                List<Appointment> appointments = Appointment.find("doctorId", doctorId);
                if (!appointments.isEmpty()) {
                    System.out.println(GREEN+"Results: "+RESET);
                    for (Appointment appointment: appointments) {
                        System.out.println(appointment);
                    }
                }
                else {
                    System.out.println(RED+"No Results!"+RESET);
                }
                break;
            }
            case 4: {
                LocalDate date = validateDate();
                List<Appointment> appointments = Appointment.find("date", String.valueOf(date));
                if (!appointments.isEmpty()) {
                    System.out.println(GREEN+"Results: "+RESET);
                    for (Appointment appointment: appointments) {
                        System.out.println(appointment);
                    }
                }
                else {
                    System.out.println(RED+"No Results!"+RESET);
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

            List<Doctor> doctors = Doctor.find("doctorId", doctorId);

            if (doctors.isEmpty()) {
                System.out.println(RED + "No Results!" + RESET);
                continue;
            }

            doctor = doctors.get(0);
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

            List<Nurse> nurses = Nurse.find("nurseId", nurseId);

            if (nurses.isEmpty()) {
                System.out.println(RED + "No Results!" + RESET);
                continue;
            }

            nurse = nurses.get(0);
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

            List<Patient> patients = Patient.find("patientId", patientId);

            if (patients.isEmpty()) {
                System.out.println(RED + "No Results!" + RESET);
                continue;
            }

            patient = patients.get(0);
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

            List<Appointment> appointments = Appointment.find("appointmentId", appointmentId);

            if (appointments.isEmpty()) {
                System.out.println(RED + "No Results!" + RESET);
                continue;
            }

            appointment = appointments.get(0);
            return appointment;
        }
    }
    public static void patientManagementPage() {
        wait(1);
        System.out.println("\n# Patient Management Page");
        System.out.println("1. Add Patient");
        System.out.println("2. Remove Patient");
        System.out.println("3. Update Information");
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
                if (doctor==null) break;
                //Nurse nurse = validateNurse();
                Nurse nurse = getRandomNurse(Nurse.getAvailableNurses());
                if (nurse==null) break;

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
                System.out.println(GREEN+"Patient added successfully."+RESET);
                break;
            }
            case 2: {
                Patient patient = validatePatient();
                if (patient==null)
                    break;

                if (hospital.remove(patient))
                    System.out.println(YELLOW+"Patient removed successfully."+RESET);
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
        System.out.println("1. Schedule Appointment");
        System.out.println("2. Reschedule Appointment");
        System.out.println("3. Cancel Appointment");
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
                System.out.println("Enter description: ");
                String description = input.nextLine();

                hospital.add(new Appointment(id, patient, doctor, date, description));
                System.out.println(GREEN+"Appointment scheduled successfully."+RESET);
                break;
            }
            case 2: {
                Appointment appointment = validateAppointment();
                if (appointment==null)
                    break;
                LocalDate date = validateDate();
                appointment.setDate(date);
                hospital.updateData();
                System.out.println(GREEN+"Appointment rescheduled successfully."+RESET);
                break;
            }
            case 3: {
                Appointment appointment = validateAppointment();
                if (appointment==null)
                    break;
                hospital.remove(appointment);
                System.out.println(YELLOW+"Appointment canceled successfully."+RESET);
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
                System.out.println(RED+"doctorName is already registered!"+RESET);
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
        System.out.println("3. Update Info");
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
                System.out.println(GREEN+"Dr. "+name+" added successfully."+RESET);
                break;
            }
            case 2: {
                Doctor doctor = validateDoctor();
                if (doctor==null)
                    break;
                hospital.remove(doctor);
                System.out.println(YELLOW+"Dr. "+doctor.getName()+" removed successfully."+RESET);
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
                System.out.println(RED+"nurseName is already registered!"+RESET);
                validateNurseName();
                break;
            }
        }
        return name;
    }

    public static double getRandomNumber(double min, double max) {
        return min+Math.random()*(max-min);
    }

    public static Nurse getRandomNurse(List<Nurse> nurses) {
        if (nurses.isEmpty()) return null;

        int randomInt = (int) (getRandomNumber(0, nurses.size()));
        return nurses.get(randomInt);
    }
    public static void nurseManagementPage() {
        wait(1);
        System.out.println("\n# Nurse ManagementPage");
        System.out.println("1. Add Nurse");
        System.out.println("2. Remove Nurse");
        System.out.println("3. Update Info");
        System.out.println("99. <<");
        int c = getUserInput(new int[] {1, 2, 3, 99});

        sw:switch (c) {
            case 1: {
                String id = Nurse.getNewNurseId();
                //System.out.println(id);
                String name = validateNurseName();
                System.out.println("Enter phoneNumber: ");
                String phoneNumber = input.nextLine();
                System.out.println("Enter department: ");
                String department = input.nextLine();

                hospital.add(new Nurse(id, name, phoneNumber, department));
                System.out.println(GREEN+"Nurse. "+name+" added successfully."+RESET);
                break;
            }
            case 2: {
                Nurse nurse = validateNurse();
                if (nurse == null) break;

                List<Patient> patients = Patient.find("nurseId", nurse.getId());

                if (!patients.isEmpty()) {
                    // consider replacing the nurse in this case.
                    System.out.println(RED+"Nurse is assigned to "+patients.size()+" Patient(s)."+RESET);
                    List<Nurse> availableNurses = Nurse.getAvailableNurses();
                    availableNurses.remove(nurse);
                    if (availableNurses.isEmpty()) {
                        System.out.println(RED + "No other Nurse(s) are available!" + RESET);
                        break;
                    }
                    else {
                        for (Patient patient: patients) {
                            Nurse replacementNurse = getRandomNurse(availableNurses);
                            if (replacementNurse==null) {
                                System.out.println(RED + "Available Nurses weren't enough to replace Nurse. " + nurse.getName() + RESET);
                                hospital.updatePatients();
                                break sw;
                                // a label is used to break the entire switch.
                                // using break statement without a label would just break the loop and not the switch.
                            }

                            if (replacementNurse.isAvailable()) {
                                patient.setNurse(replacementNurse);
                            }
                            else {
                                availableNurses.remove(replacementNurse);
                                patients.add(patient);
                            }
                        }
                        System.out.println(PURPLE+"Nurse. "+nurse.getName()+" was replaced with other Nurses."+RESET);
                        hospital.updatePatients();
                    }
                }
                hospital.remove(nurse);
                System.out.println(YELLOW+"Nurse. "+nurse.getName()+" removed successfully."+RESET);
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
        System.out.println("1. Update phoneNumber");
        System.out.println("2. Update address");
        System.out.println("3. Update department");
        System.out.println("4. Update doctor");
        System.out.println("5. Update nurse");
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
                System.out.println(GREEN+"phoneNumber updated successfully."+RESET);
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
                System.out.println(GREEN+"address updated successfully."+RESET);
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
                System.out.println(GREEN+"department updated successfully."+RESET);
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
                System.out.println(GREEN+"Doctor updated successfully."+RESET);
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
                System.out.println(GREEN+"Nurse updated successfully."+RESET);
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
        System.out.println("1. Update phoneNumber");
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
                System.out.println(GREEN+"phoneNumber updated successfully."+RESET);
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
        System.out.println("1. Update phoneNumber");
        System.out.println("2. Update department");
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
                System.out.println(GREEN+"phoneNumber updated successfully."+RESET);
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
                System.out.println(GREEN+"department updated successfully."+RESET);
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
                if (patient==null) break;
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
                patient.setMedicalRecords(medicalRecords);
                hospital.add(medicalRecords);
                System.out.println(GREEN+"MedicalRecord added successfully."+RESET);
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
                    System.out.println(YELLOW+"MedicalRecord removed successfully."+RESET);
                }
                else
                    System.out.println(RED+"Not Results!"+RESET);
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
