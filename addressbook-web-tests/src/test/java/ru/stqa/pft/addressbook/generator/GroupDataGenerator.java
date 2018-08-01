package ru.stqa.pft.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class GroupDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "File format")
    public String format;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander.Builder jcommander = JCommander.newBuilder().addObject(generator);
        try {
            jcommander.build().parse(args);
        } catch (ParameterException e) {
            jcommander.build().usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        ArrayList<GroupData> groups = generate(count);
        if (format.equals("csv")) {
            saveToCsvFile(groups, new File(file));
        } else if (format.equals("xml")){
            saveToXmlFile(groups, new File(file));
        } else if (format.equals("json")){
            saveToJsonFile(groups, new File(file));
        } else {
            System.out.println("Unrecognized format "+format);
        }

    }

     private ArrayList<GroupData> generate(int count) {
        ArrayList<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format("testName%s", i)).withFooter(String.format("testFooter%s", i)).withHeader(String.format("testHeader%s", i)));
        }
        return groups;
    }

    private void saveToCsvFile(ArrayList<GroupData> groups, File file) {
        try (Writer writer = new FileWriter(file)) {
            for (GroupData group : groups) {
                writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void saveToXmlFile(ArrayList<GroupData> groups, File file) {
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        String xml = xStream.toXML(groups);

        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToJsonFile(ArrayList<GroupData> groups, File file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);

        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
