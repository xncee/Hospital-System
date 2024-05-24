package hospital;

import javax.print.Doc;

public class Doctor extends Person {
    private String specialization;

    public Doctor(String id, String name, String phoneNumber, String specialization) {
        super(id, name, phoneNumber);
        this.specialization = specialization;
    }

    public static Doctor findDoctor(String searchKey, String searchQuery) {
        for (Doctor doctor: doctorsList) {
            String s = switch (searchKey) {
                case "doctorName":
                    yield doctor.getName();
                case "phoneNumber":
                    yield doctor.getPhoneNumber();
                default:
                    System.out.println("Invalid searchKey!");
                case "doctorId":
                    yield doctor.getId();
            };

            if (s==null)
                return null;

            s = s.toLowerCase();
            //System.out.println(s);
            if (s.equals(searchQuery.toLowerCase())) {
                return doctor;
            }
        }
        return null;
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
