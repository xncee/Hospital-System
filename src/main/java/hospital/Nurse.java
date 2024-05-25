package hospital;

public class Nurse extends Person {
    private String department;

    public Nurse(String id, String name, String phoneNumber, String department) {
        super(id, name, phoneNumber);
        this.department = department;
    }

    public static Nurse findNurse(String searchKey, String searchQuery) {
        for (Nurse nurse: nursesList) {
            String s = switch (searchKey) {
                case "nurseName":
                    yield nurse.getName();
                case "phoneNumber":
                    yield nurse.getPhoneNumber();
                case "nurseId":
                    yield nurse.getId();
                default:
                    System.out.println("Invalid searchKey!");
                    yield nurse.getId();
            };

            if (s==null)
                return null;

            s = s.toLowerCase();
            //System.out.println(s);
            if (s.equals(searchQuery)) {
                return nurse;
            }
        }
        return null;
    }

    public static String getNewNurseId() {
        return "N"+(nursesList.size()+1);
    }
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                super.toString() +
                ", department=" + department +
                "}";
    }
}
