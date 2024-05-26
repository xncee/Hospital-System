package hospital;

import design.Color;

public class Person implements HospitalData, Color {
    private String id;
    private String name;
    private String phoneNumber;

    public Person(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id.length()>0)
            this.id = id;
        else
            System.out.println("Invalid id.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length()>0)
            this.name = name;
        else
            System.out.println("Invalid name.");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length()==10)
            this.phoneNumber = phoneNumber;
        else
            System.out.println("Invalid phoneNumber.");
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name=" + name +
                ", phoneNumber=" + phoneNumber;
    }
}
