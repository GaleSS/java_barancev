package filemanager;

import data.ABCData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


import static java.lang.System.*;

public class FileManager {

    private static String path = "E:";

    public String getPath() {return path;}
    public void setPath() throws IOException {
      if (path == null)
       {
            out.println("Please, enter path to log files");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            path = in.readLine();
        }
    }

    private ArrayList<String> getStrings(String pathToFile, String fileName) {
        String thisLine;
        ArrayList<String> fileStrings = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(pathToFile+"\\"+fileName));
            while ((thisLine = br.readLine()) != null) {
                fileStrings.add(thisLine);
            }
        }
        catch (IOException e) {
            err.println("Error: " + e);
        }
        return fileStrings;
    }

    public ArrayList<ABCData> loadInputs(String pathToFile){
        ArrayList<ABCData> dataSet = new ArrayList<>();
        ArrayList<String> fileStrings = getStrings(pathToFile,"Object_task_up_input.log");

        fileStrings.remove(0);

        for (String fileString : fileStrings)
        {
            ABCData data = new ABCData();
            String[] split = fileString.split("\\s");
            data.setA(Float.parseFloat(split[0]));
            data.setB(Float.parseFloat(split[1]));
            data.setC(Float.parseFloat(split[2]));
            dataSet.add(data);
        }

        return dataSet;
    }

    public ArrayList<Integer> loadOutputs(String pathToFile)
    {
        ArrayList<Integer> dataSet = new ArrayList<>();
        ArrayList<String> fileStrings = getStrings(pathToFile,"Object_task_up_results.log");

        fileStrings.remove(0);

        for (String fileString : fileStrings)
        {
            dataSet.add(Integer.parseInt(fileString));
        }
        return dataSet;
    }

}
