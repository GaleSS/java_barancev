package ru.stqa.pft.addressbook.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, lastname);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
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
