
import java.lang.Math;


public class lab1_pr
{
    public static void main(String[] args)
    {
        int max_c = 23;
        int min_c = 5;
        int[] c = new int[(max_c - min_c) / 2 + 1]; // считаю количество нечётных чисел в диапазоне и создаю массив c
        for(int i = max_c, j = 0; i >= min_c; i--)
        {
            if (i % 2 != 0)
            {
                c[j] = i;
                j++;
            }
        }
        float[] x = new float[12];
		float max_x = 2.0f;
		float min_x = -3.0f;
		int d = (int)Math.abs(min_x) + (int)Math.abs(max_x);
        for(int i = 0; i < x.length; i++)
        {
            float rand1 = (float) Math.random();
            if (rand1 > 1.0f)
            {
                rand1 = rand1 - (rand1 % 1);
            }
            x[i] = rand1 * d - (int)Math.abs(min_x);
        }

        float[][] a = new float[10][12]; // решил назвать двумерный массив буквой а, чтобы не повторяться
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 12; j++)
            {
                switch (c[i]) {
                    case 15 -> a[i][j] = (float) Math.pow(((Math.PI) / Math.pow((1 - Math.exp(x[j])), 2.0)), 2.0);
                    case 5, 13, 19, 21, 23 -> a[i][j] = (float) Math.cos(Math.sin(Math.pow(x[j], ((double) 1 / 3))));
                    default -> a[i][j] = (float) Math.cos(Math.asin(Math.pow((1 / (Math.exp(Math.abs(x[j])))), 2.0)));
                }
                System.out.printf("%5.3f ", a[i][j]);
            }
            System.out.println("");
        }
    }

}