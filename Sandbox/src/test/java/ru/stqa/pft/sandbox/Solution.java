package ru.stqa.pft.sandbox;
/* Сумма 10 чисел
Вывести на экран сумму чисел от 1 до 10 построчно (должно быть 10 строк):
1
1+2=3
1+2+3=6
1+2+3+4=10
...
*/

public class Solution
{
    public static void main(String[] args)
    {
        String leftPart = "1";
        int rightPart = 1;
        System.out.println(leftPart);
        for (int i=2;i<11;i++)
        {
            rightPart = rightPart + i;
            leftPart = leftPart + "+"+i;
            System.out.println(leftPart+"="+rightPart);
        }
    }


}