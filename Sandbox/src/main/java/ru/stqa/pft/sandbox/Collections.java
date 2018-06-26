package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {

    public static  void main(String[] args)
    {
        String[] langs = {"PHP","JS","Ruby"};

        List<String> good_languages = Arrays.asList("Java", "Python", "C#");

        for (String l : good_languages){
            System.out.println("This is good language - " + l);
        }

    }
}
