package hospital;

import design.Color;

import java.util.ArrayList;
import java.util.List;

public class Department implements HospitalData, Color {
    final int MAX_PATIENT_CAPACITY;
    private String id;
    private String name;
    private String description;

    public Department(String id, String name, String description, int MAX_PATIENT_CAPACITY) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.MAX_PATIENT_CAPACITY =MAX_PATIENT_CAPACITY;
    }

    public static List<Department> find(String searchKey, String searchQuery) {
        List<Department> departments = new ArrayList<>();
        for (Department department: departmentsList) {
            String s = switch (searchKey) {
                case "departmentId":
                    yield department.getId();
                case "departmentName":
                    yield department.getName();
                case "departmentDescription":
                    yield department.getDescription();
                default:
                    System.out.println("Invalid searchKey!");
                    yield department.getId();
            };

            if (s==null) break;
            s = s.toLowerCase();
            searchQuery = searchQuery.toLowerCase();

            if (s.equals(searchQuery)) {
                departments.add(department);
            }
            else if (searchKey.equals("departmentDescription") && s.contains(searchQuery)) {
                departments.add(department);
            }
            else if (searchKey.equals("departmentName") && s.startsWith(searchQuery)) {
                departments.add(department);
            }
        }
        return departments;
    }

    public static String getNewId() {
        String str;
        if (!departmentsList.isEmpty()) {
            str = departmentsList.get(departmentsList.size() - 1).getId().split("DP")[1];
        }
        else
            str = "0";
        return "DP"+(Integer.parseInt(str)+1);
    }

    public boolean isFull() {
        List<Patient> patients = Patient.find("departmentName", name);
        return patients.size()>= MAX_PATIENT_CAPACITY;
    }
    public int getMAX_PATIENT_CAPACITY() {
        return MAX_PATIENT_CAPACITY;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id  +
                ", name=" + name  +
                ", maxCapacity="+MAX_PATIENT_CAPACITY +
                ", patients=" + Patient.find("departmentName", name).size() +
                ", isFull=" + (isFull()?RED+"true"+RESET:"false") +
                ", description=" + description +
                "}";
    }
}
