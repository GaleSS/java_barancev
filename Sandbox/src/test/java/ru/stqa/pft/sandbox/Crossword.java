package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Crossword {
    public static void main(String[] args) {
        int[][] crossword = new int[][]
                {
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> list = detectAllWords(crossword, "home", "same");
        System.out.println(list);


        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        ArrayList<Word> result = new ArrayList<>();
        int hor = crossword[0].length;
        int ver = crossword.length;
        int[] cords = new int[2];
        boolean wordFound;
        int [][] steps = new int [][]{{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};


        for (String word : words)
        {
            int firstChar = word.charAt(0);

            for (int y = 0; y < ver; y++)
            {
                wordFound = false;
                for (int x = 0; x < hor; x++)
                {
                   if (firstChar == crossword[y][x]){
                       for (int[] step : steps)
                       {
                        cords = wordCheck(crossword,word,y,x,step[0],step[1]);
                        if (cords[0] != -1)
                        {
                            Word resultWord = new Word(word);
                            resultWord.setStartPoint(x,y);
                            resultWord.setEndPoint(cords[0],cords[1]);
                            result.add(resultWord);
                            wordFound = true;
                            break;
                        }
                       }
                    }
                    if (wordFound){break;}
                }
                if (wordFound){break;}
            }
        }
        return result;
    }

    private static int[] wordCheck (int[][] crossword, String word, int startY, int startX, int stepY, int stepX)
    {
        int[] cords = new int[2];
        for (int charIndex = 1;charIndex < word.length()-1;charIndex++)
        {
            startY = startY + stepY;
            startX = startX + stepX;
            if (startX < 0 || startX > crossword[0].length-1  ||
                    startY < 0 || startY > crossword.length-1 ||
                    crossword[startY][startX] != word.charAt(charIndex))
            {
                cords [0] = -1;
                cords [1] = -1;
                return cords;
            }
        }
        cords [0] = startX+stepX;
        cords [1] = startY+stepY;
        return cords;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
