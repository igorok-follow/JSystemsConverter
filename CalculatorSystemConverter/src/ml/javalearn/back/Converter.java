package ml.javalearn.back;

import java.util.HashMap;
import java.util.Scanner;

public class Converter {

    private int result = 0;
    private double doubleOfStr;
    private char[] symbols;
    private int[] degrees;
    private String string;
    private HashMap<Integer, String> map;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Number which you want to convert: ");
        String number = scanner.next();
        System.out.print("Initial system: ");
        int initialSystem = scanner.nextInt();
        System.out.print("Final system: ");
        int finalSystem = scanner.nextInt();
        new Converter().convertType(number, initialSystem, finalSystem);
    }

    private Converter() {
        map = new HashMap<>();
        String literal = "208";
        for (int i = 0; i < 10; i++) {
            map.put(i, literal + i);
        }
    }

    private void convertType(String number, int initialSystem, int finalSystem)  {
        symbols = number.toCharArray();
        degrees = new int[symbols.length];
        doubleOfStr = Double.parseDouble(number);

        if (finalSystem == 10) {
            toDecimalSystem(number, initialSystem);
            System.out.println("\n" + result + "\u2081\u2080");
        } else if (finalSystem >1 && finalSystem < 10) {
            toDecimalSystem(number, initialSystem);
            System.out.println("start while");
            while (result % finalSystem != 0 || result % finalSystem != 1) {
                result = result / finalSystem;
                string += String.valueOf(result % finalSystem);
                System.out.println(string);
            }

            System.out.println(string + map.get(finalSystem));
        }
    }

    private void toDecimalSystem(String number, int initialSystem) {
        System.out.println("start decimal ");
        if (doubleOfStr % 2 == 0 || doubleOfStr % 2 == 1) {
            System.out.println("if passed");
            reverseArray(symbols);
            System.out.println("array reserved");
            for (int i = 0; i < symbols.length; i++) {
                degrees[i] = i;
            }

            System.out.println("start write result");
            for (int i = 0; i < symbols.length; i++) {
                result += Integer.parseInt(String.valueOf(symbols[i])) * Math.pow(initialSystem, degrees[i]);
            }
        } else {
            System.out.println("with double");
            //если переданное число с плавающей точкой, доделать!!!
            String firstPart, secondPart;
            String[] parts = number.split("[.]", 2);
            firstPart = parts[0];
            secondPart = parts[1];
            System.out.println(firstPart + "," + secondPart);
        }
    }

    private char[] reverseArray(char[] chars) {
        for(int j=0; j<chars.length/2; j++){
            char temp = chars[j];
            chars[j] = chars[chars.length -j -1];
            chars[chars.length -j -1] = temp;
        }

        return chars;
    }

}
