package ru.stqa.pft.addressbook.model;

import ru.stqa.pft.addressbook.tests.ContactDataTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactData {
    private int id;
    private String name = "";
    private String email = "";
    private String email2 = "";
    private String email3 = "";
    private String allEmails = "";
    private String lastname = "";
    private String group;
    private String address = "";
    private String homePhone = "";
    private String mobilePhone = "";
    private String workPhone = "";
    private String allPhones = "";

    public File getPhoto() {
        return photo;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    private File photo;


    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public ContactData withName(String name)
    {
        this.name = name;
        return this;
    }

    public ContactData withEmail(String email)
    {
        this.email = email;
        return this;
    }

    public ContactData withLastname(String lastname)
    {
        this.lastname = lastname;
        return this;
    }

    public ContactData withGroup(String group)
    {
        this.group = group;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
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

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", lastname='" + lastname + '\'' +
                ", group='" + group + '\'' +
                ", address='" + address + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", allPhones='" + allPhones + '\'' +
                ", allEmails='" + allEmails + '\'' +
                '}';
    }

    public String getAllPhones() {
        return allPhones;
    }

    public static String cleaned(String phone)
    {
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }

    public static String mergeEmails(ContactData contact)
    {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3()).stream().filter(s -> !s.equals("")).collect(Collectors.joining("\n"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(allEmails, that.allEmails) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(address, that.address) &&
                Objects.equals(allPhones, that.allPhones);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, allEmails, lastname, address, allPhones);
    }

    public static String mergePhones(ContactData contact)
    {
        return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone()).stream().filter(s -> !s.equals(""))
                .map(ContactData::cleaned).collect(Collectors.joining("\n"));
    }
}
