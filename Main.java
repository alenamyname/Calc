package com.example.lesson2;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException
    {
        Scanner s = new Scanner(System.in);
        String string = s.nextLine();
        System.out.println(calc(string));
    }

    public static String calc(String input) throws IOException
    {
        String example[] = input.split(" ");
        if (example.length > 3)
            throw new IOException();
        if (example.length < 2 )
            throw new IOException();

        String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] arabic = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        int[] digitsAr = {0, 0};
        int[] digitsRom ={0, 0};
        boolean[] isRoman = {false, false};
        int k = 0;

        for (int i = 0; i < example.length; i++) {
            for (int j = 0; j < 10; j++) {
                if (example[i].equals(roman[j])) {
                    digitsRom[k] = j + 1;
                    isRoman[k] = true;
                    ++k;
                } else if (example[i].equals(arabic[j])) {
                    digitsAr[k] = j + 1;
                    ++k;
                }
            }
        }

        if (isRoman[0] != isRoman[1])
            throw new IOException();

        if (example[1].equals("-") && isRoman[0] && isRoman[1] & digitsRom[0] <= digitsRom[1])
            throw new IOException();

        String result = "";
        if(isRoman[0] && isRoman[1])
        {
            switch(example[1])
            {
                case "+":
                    result = integer_to_roman(digitsRom[0] + digitsRom[1]);
                    break;

                case "-":
                    result = integer_to_roman(digitsRom[0] - digitsRom[1]);
                    break;

                case "*":
                    result = integer_to_roman(digitsRom[0] * digitsRom[1]);
                    break;

                case "/":
                    result = integer_to_roman(digitsRom[0] / digitsRom[1]);
            }
        }
        else
        {
            switch(example[1]) // DRY omitted
            {
                case "+":
                    result = Integer.toString(digitsAr[0] + digitsAr[1]);
                    break;

                case "-":
                    result = Integer.toString(digitsAr[0] - digitsAr[1]);
                    break;

                case "*":
                    result = Integer.toString(digitsAr[0] * digitsAr[1]);
                    break;

                case "/":
                    result = Integer.toString(digitsAr[0] / digitsAr[1]);
            }
        }
        return result;
    }

    public static String integer_to_roman(int num) {
        String[] roman = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        int[] values = {1, 4, 5, 9, 10, 40, 50, 90};
        int i = values.length - 1;
        String answer = "";
        while (num > 0) {
            while (num >= values[i]) {
                answer += roman[i];
                num -= values[i];
            }
            --i;
        }
        return answer;
    }
}
