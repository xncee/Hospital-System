package hospital;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Doctor extends Person {
    private String specialization;
    private HashMap<String, String> workingDays;

    public Doctor(String id, String name, String phoneNumber, String specialization) {
        super(id, name, phoneNumber);
        this.specialization = specialization;
    }

    public static List<Doctor> find(String searchKey, String searchQuery) {
        List<Doctor> doctors = new ArrayList<>();
        for (Doctor doctor: doctorsList) {
            String s = switch (searchKey) {
                case "doctorName":
                    yield doctor.getName();
                case "phoneNumber":
                    yield doctor.getPhoneNumber();
                case "doctorId":
                    yield doctor.getId();
                case "specialization":
                    yield doctor.getSpecialization();
                default:
                    System.out.println("Invalid searchKey!");
                    yield doctor.getId();
            };
//            String s;
//            switch (searchKey) {
//                case "doctorName":
//                    s = doctor.getName();
//                    break;
//                case "phoneNumber":
//                    s = doctor.getPhoneNumber();
//                    break;
//                case "doctorId":
//                    s = doctor.getId();
//                    break;
//                default:
//                    System.out.println("Invalid searchKey!");
//                    s = doctor.getId();
//            }
            if (s==null) return null;
            s = s.toLowerCase();
            searchQuery = searchQuery.toLowerCase();

            if (s.equals(searchQuery)) {
                doctors.add(doctor);
            }
            else if (searchKey.equals("doctorName") && s.startsWith(searchQuery)) {
                doctors.add(doctor);
            }
        }
        return doctors;
    }

    public static String getNewDoctorId() {
        return "D"+(doctorsList.size()+1);
    }

    public List<Appointment> getAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        for (Appointment appointment: appointmentsList) {
            if (appointment.getDoctor().getId().equals(getId())) {
                appointments.add(appointment);
            }
        }

        return appointments;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                super.toString() +
                ", specialization=" + specialization +
                "}";
    }
}
