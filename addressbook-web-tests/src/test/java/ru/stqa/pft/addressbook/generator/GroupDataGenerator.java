package ru.stqa.pft.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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
        saveToFile(groups, new File(file));
    }

    private ArrayList<GroupData> generate(int count) {
        ArrayList<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format("testName%s", i)).withFooter(String.format("testFooter%s", i)).withHeader(String.format("testHeader%s", i)));
        }
        return groups;
    }

    private void saveToFile(ArrayList<GroupData> groups, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (GroupData group : groups) {
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
        }
        writer.close();
    }
}
