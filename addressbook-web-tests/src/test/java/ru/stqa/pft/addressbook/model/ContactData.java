package ru.stqa.pft.addressbook.model;

public class ContactData {
    private String name;
    private String email;
    private String lastname;
    private String group;

    public ContactData(String name, String email, String lastname, String group) {
        this.name = name;
        this.email = email;
        this.lastname = lastname;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLastname() {
        return lastname;
    }

    public String getGroup() {
        return group;
    }
}
