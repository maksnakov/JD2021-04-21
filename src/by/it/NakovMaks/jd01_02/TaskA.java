package by.it.NakovMaks.jd01_02;

import java.util.Arrays;
import java.util.Scanner;

public class TaskA {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        step1(array);
        step2(array);
        step3(array);
    }


    private static void step1(int[] array) {
        int min = array[0];
        int max = array[0];
        for (int element : array) {
            if (min > element) {
                min = element;
            }
            if (max < element) {
                max = element;
            }
        }
        System.out.println(min + " " + max);
    }

    private static void step2(int[] array) {
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }

        double avg = sum / array.length;
        for (int element : array) {
            if (element < avg) {

                System.out.print(element + " ");
            }
        }
        System.out.println();
    }

    private static void step3(int[] array) {
        int min = array[0];
        for (int element : array) {
            if (min > element) {
                min = element;
            }
        }
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == min) {
                System.out.print(i + " ");
            }
        }
    }
}
