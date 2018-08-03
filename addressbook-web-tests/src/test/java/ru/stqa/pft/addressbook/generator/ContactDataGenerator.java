package ru.stqa.pft.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;

public class ContactDataGenerator {

    @Parameter (names = "-c", description = "Quantity of contacts")
    public int count;

    @Parameter (names = "-f", description = "Path to file")
    public String file;

    @Parameter (names = "-d", description = "Format for saving")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander.Builder jcommander = JCommander.newBuilder().addObject(generator);
        try {
            jcommander.build().parse(args);
        } catch (ParameterException e) {
            jcommander.build().usage();
            return;
        }
        generator.run();

    }

    public void run() throws IOException{
        ArrayList<ContactData> contacts = generate(count);
        if (format.equals("csv")) {
            saveToCsvFile(contacts, new File(file));
        } else if (format.equals("xml")){
            saveToXmlFile(contacts, new File(file));
        } else if (format.equals("json")){
            saveToJsonFile(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format "+format);
        }
    }

    public ArrayList<ContactData> generate(int count){

        ArrayList<ContactData> contacts = new ArrayList<ContactData>();

        for (int i=0;i<count;i++){
            contacts.add(new ContactData().withName(String.format("TestName%s",i)).withLastname(String.format("TestLastName%s",i)).withAddress(String.format("TestAddress%s",i))
                    .withEmail(String.format("TestEmail%s",i)).withEmail2(String.format("TestEmail2%s",i)).withEmail3(String.format("TestEmail3%s",i))
                    .withHomePhone(String.format("111 567 %s",i)).withMobilePhone(String.format("222 567 %s",i)).withWorkPhone(String.format("333 567 %s",i)));
        }
        return contacts;
    }

    public void saveToCsvFile (ArrayList<ContactData> contacts, File file)
    {
        try(FileWriter writer = new FileWriter(file)) {
            for (ContactData contact : contacts){
                writer.write(String.format("%s;%s;%s;s%;s%;%s;s%;s%;%s\n", contact.getName(), contact.getLastname(), contact.getAddress(),
                        contact.getEmail(), contact.getEmail2(), contact.getEmail3(), contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()));
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToJsonFile (ArrayList<ContactData> contacts, File file){
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);

        try(FileWriter writer = new FileWriter(file)) {
               writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToXmlFile (ArrayList<ContactData> contacts, File file){
        XStream stream = new XStream();
        stream.processAnnotations(ContactData.class);
        String xml = stream.toXML(contacts);

        try(FileWriter writer = new FileWriter(file)) {
                writer.write(xml);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
