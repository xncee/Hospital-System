package hospital;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {
    private String specialization;

    public Doctor(String id, String name, String phoneNumber, String specialization) {
        super(id, name, phoneNumber);
        this.specialization = specialization;
    }

    public static Doctor find(String searchKey, String searchQuery) {
        for (Doctor doctor: doctorsList) {
            String s = switch (searchKey) {
                case "doctorName":
                    yield doctor.getName();
                case "phoneNumber":
                    yield doctor.getPhoneNumber();
                case "doctorId":
                    yield doctor.getId();
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
            if (s==null)
                return null;

            s = s.toLowerCase();

            if (s.equals(searchQuery.toLowerCase())) {
                return doctor;
            }
        }
        return null;
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
