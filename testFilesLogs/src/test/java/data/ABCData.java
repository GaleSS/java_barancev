package data;

public class ABCData {
    @Override
    public String toString() {
        return "ABCData{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }

    public void setA(float a) {
        this.a = a;
    }

    public void setB(float b) {
        this.b = b;
    }

    public void setC(float c) {
        this.c = c;
    }

    private float a;
    private float b;
    private float c;

    public float getA() {
        return a;
    }

    public float getB() {
        return b;
    }

    public float getC() {
        return c;
    }

    public int result()
    {
        if (a==b && a==c)
        {
            return 4;
        } else if (a+b+c>1000)
        {
            return 5;
        }
        else
        {
            return 6;
        }
    }

}
