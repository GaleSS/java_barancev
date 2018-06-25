package ru.stqa.pft.addressbook.model;

public class ContactData {
    private String name;
    private String middlename;
    private String lastname;
    private String group;

    public ContactData(String name, String middlename, String lastname, String group) {
        this.name = name;
        this.middlename = middlename;
        this.lastname = lastname;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getGroup() {
        return group;
    }
}
