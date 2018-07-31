package ru.stqa.pft.addressbook.generator;

import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class GroupDataGenerator {

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        ArrayList<GroupData> groups = generate(count);
        saveToFile(groups,file);
    }

    private static ArrayList<GroupData> generate(int count)
    {
        ArrayList<GroupData> groups = new ArrayList<>();
        for (int i=0;i<count;i++)
        {
            groups.add(new GroupData().withName(String.format("testName%s",i)).withFooter(String.format("testFooter%s",i)).withHeader(String.format("testHeader%s",i)));
        }
        return groups;
    }

    private static void saveToFile(ArrayList<GroupData> groups, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (GroupData group : groups)
        {
            writer.write(String.format("%s;%s;%s\n",group.getName(),group.getHeader(),group.getFooter()));
        }
        writer.close();
    }
}
