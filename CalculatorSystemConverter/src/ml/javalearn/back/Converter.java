package ml.javalearn.back;

import java.util.HashMap;
import java.util.Scanner;

public class Converter {

    private int result = 0;
    private double doubleOfStr;
    private char[] symbols;
    private int[] degrees;
    private StringBuilder string = new StringBuilder();
    private HashMap<Integer, String> map;
    private String[] literals =
            {"\u2080", "\u2081", "\u2082", "\u2083", "\u2084", "\u2085", "\u2086", "\u2087", "\u2088", "\u2089"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Number which you want to convert(in range of 2 to 16 calculate system): ");
        String number = scanner.nextLine();
        System.out.print("Initial system: ");
        int initialSystem = scanner.nextInt();
        System.out.print("Final system: ");
        int finalSystem = scanner.nextInt();
        new Converter().convertType(number, initialSystem, finalSystem);
    }

    private Converter() {}

    private void convertType(String number, int initialSystem, int finalSystem)  {
        symbols = number.toCharArray();
        degrees = new int[symbols.length];
        doubleOfStr = Double.parseDouble(number);

        if (finalSystem == 10) {
            toDecimalSystem(number, initialSystem);
            System.out.println(result + literals[1] + literals[0]);
        } else {
            int test = toDecimalSystem(number, initialSystem), mod;
            if (test > finalSystem) {
                while (test != 0) {
                    mod = test % finalSystem;
                    if (mod < 10) {
                        string.append(mod);
                        test = test / finalSystem;
                    } else if (mod >= 10){
                        //когда остаток больше 10 и он заменяется на букву
                        switch (mod) {
                            case (10):
                                string.append("A");
                                break;
                            case (11):
                                string.append("B");
                                break;
                            case (12):
                                string.append("C");
                                break;
                            case (13):
                                string.append("D");
                                break;
                            case (14):
                                string.append("E");
                                break;
                            case (15):
                                string.append("F");
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + mod);
                        }
                        test = test / finalSystem;
                    }
                }
            } else {
                string.append(test % finalSystem);
            }

            string.reverse();
            if (finalSystem < 10) {
                System.out.println(string + literals[finalSystem]);
            } else {
                String s = String.valueOf(finalSystem).split("", 2)[1];
                int t = Integer.parseInt(s);
                System.out.println(t);
                System.out.println(string + literals[1] + literals[t]);
            }
        }
    }

    private int toDecimalSystem(String number, int initialSystem) {
        if (doubleOfStr % 2 == 0 || doubleOfStr % 2 == 1) {
            reverseArray(symbols);

            for (int i = 0; i < symbols.length; i++) {
                degrees[i] = i;
            }

            for (int i = 0; i < symbols.length; i++) {
                result += Integer.parseInt(String.valueOf(symbols[i])) * Math.pow(initialSystem, degrees[i]);
            }
            return result;
        } else {
            System.out.println("with double");
            //если переданное число с плавающей точкой, доделать!!!
            String firstPart, secondPart;
            String[] parts = number.split("[.]", 2);
            firstPart = parts[0];
            secondPart = parts[1];
            System.out.println(firstPart + "," + secondPart);
            return result;
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
