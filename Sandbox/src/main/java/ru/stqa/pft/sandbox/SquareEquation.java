package ru.stqa.pft.sandbox;

public class SquareEquation {

    private double a;
    private double b;
    private double c;
    private double d;

    public SquareEquation(double a,double b,double c)
    {
        this.a = a;
        this.b = b;
        this.c = c;

        this.d = b*b - 4*a*c;
    }

    public int quantityDesicions()
    {
        if (d > 0){return 2;}
        else if (d == 0) {return 1;}
        else {return 0;}
    }


}
