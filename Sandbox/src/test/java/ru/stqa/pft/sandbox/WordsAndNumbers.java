package ru.stqa.pft.sandbox;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.SortedMap;

import static jdk.nashorn.internal.runtime.JSType.isNumber;

/* Задача по алгоритмам
Задача: Пользователь вводит с клавиатуры список слов (и чисел). Слова вывести в возрастающем порядке, числа - в убывающем.
Пример ввода:
Вишня
1
Боб
3
Яблоко
2
0
Арбуз
Пример вывода:
Арбуз
3
Боб
2
Вишня
1
0
Яблоко
*/

public class WordsAndNumbers
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<String>();
        while (true)
        {
            String s = reader.readLine();
            if (s.isEmpty()) break;
            list.add(s);
        }

        String[] array = list.toArray(new String[list.size()]);
        sort(array);

        for (String x : array)
        {
            System.out.println(x);
        }
    }

    public static void sort(String[] array)
    {
        //напишите тут ваш код
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> numbers = new ArrayList<>();
        ArrayList<Integer> indexesWords = new ArrayList<>();
        ArrayList<Integer> indexesNumbers = new ArrayList<>();

        for (int i = 0; i < array.length; i++)
        {
            if (isNumber(array[i])){
                numbers.add(array[i]);
                indexesNumbers.add(i);
            } else {
                words.add(array[i]);
                indexesWords.add(i);
            }
        }
        Collections.sort(numbers,(a, b) -> b.compareTo(a));
        Collections.sort(words);

        addSorted(array,numbers,indexesNumbers);
        addSorted(array,words,indexesWords);
    }

    //Метод для добавления по сохраненым индексам отсортированных списков в массив
    public static void addSorted (String[] array, ArrayList<String> sorted, ArrayList<Integer> indexesSorted)
    {
        for (int i=0; i < sorted.size();i++)
        {
            array[indexesSorted.get(i)] = sorted.get(i);
        }
    }

    //Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b)
    {
        return a.compareTo(b) > 0;
    }


    //строка - это на самом деле число?
    public static boolean isNumber(String s)
    {
        if (s.length() == 0) return false;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++)
        {
            char c = chars[i];
            if ((i != 0 && c == '-') //есть '-' внутри строки
                    || (!Character.isDigit(c) && c != '-') ) // не цифра и не начинается с '-'
            {
                return false;
            }
        }
        return true;
    }
}
