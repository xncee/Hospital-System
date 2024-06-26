package hospital;

import java.util.ArrayList;
import java.util.List;

public class Nurse extends Person {
    private final int MAX_PATIENTS = 10;
    private String departmentName;

    public Nurse(String id, String name, String phoneNumber, String departmentName) {
        super(id, name, phoneNumber);
        this.departmentName = departmentName;
    }

    public static List<Nurse> find(String searchKey, String searchQuery) {
        List<Nurse> nurses = new ArrayList<>();
        for (Nurse nurse: nursesList) {
            String s = switch (searchKey) {
                case "nurseName":
                    yield nurse.getName();
                case "phoneNumber":
                    yield nurse.getPhoneNumber();
                case "nurseId":
                    yield nurse.getId();
                case "departmentName":
                    yield nurse.getDepartmentName();
                default:
                    System.out.println("Invalid searchKey!");
                    yield nurse.getId();
            };

            if (s==null) break;
            s = s.toLowerCase();
            searchQuery = searchQuery.toLowerCase();

            if (s.equals(searchQuery)) {
                nurses.add(nurse);
            }
            else if (searchKey.equals("nurseName") && s.startsWith(searchQuery)) {
                nurses.add(nurse);
            }
            else if (searchKey.equals("departmentName") && s.startsWith(searchQuery)) {
                nurses.add(nurse);
            }
        }
        return nurses;
    }

    public static String getNewId() {
        String str;
        if (!nursesList.isEmpty()) {
            str = nursesList.get(nursesList.size() - 1).getId().split("NU")[1];
        }
        else
            str = "0";
        return "NU"+(Integer.parseInt(str)+1);
    }

    public boolean isAvailable() {
        List<Patient> patients = Patient.find("nurseId", getId());
        return patients.size()< MAX_PATIENTS;
    }
    public static List<Nurse> getAvailableNurses(String departmentName) {
        List<Nurse> nurses = new ArrayList<>();
        for (Nurse nurse: nursesList) {
            if (nurse.getDepartmentName().equalsIgnoreCase(departmentName) && nurse.isAvailable())
                nurses.add(nurse);
        }

        return nurses;
    }
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                super.toString() +
                ", departmentName=" + departmentName +
                "}";
    }
}
