import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("\nInput:");
        String str = in.nextLine();
        System.out.println("\nOutput:");
        System.out.println(calc(str));
    }

    public static String calc(String input) throws Exception {
        try {
            String[] temp = input.split(" ");
            if (temp.length != 3)
                throw new IOException();

            boolean romanCheck = false;
            if (temp[0].charAt(0) > (int) '9') {
                temp[0] = String.valueOf(romanToInt(temp[0]));
                temp[2] = String.valueOf(romanToInt(temp[2]));
                romanCheck = true;
            }

            int firstInt = Integer.parseInt(temp[0]);
            int secondInt = Integer.parseInt(temp[2]);
            if (firstInt > 10 || firstInt < 1 || secondInt > 10 || secondInt < 1)
                throw new Exception("Числа должны быть в диапозоне от 1 до 10 включительно");

            String oper = temp[1];

            int result = 0;
            switch (oper) {
                case "+":
                    result = firstInt + secondInt;
                    break;
                case "-":
                    result = firstInt - secondInt;
                    break;
                case "/":
                    result = firstInt / secondInt;
                    break;
                case "*":
                    result = firstInt * secondInt;
                    break;
                default:
                    throw new IOException();
            }

            if (romanCheck == true) {
                return intToRoman(result);
            }
            return String.valueOf(result);
        } catch (IOException | RuntimeException e) {
            return "Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
        }
    }

    public static int romanToInt(String s) throws Exception {
        int result = 0;
        int prev = getValue(s.charAt(0));
        for (int i = 1; i != s.length(); i++) {
            int curr = getValue(s.charAt(i));
            if (prev < curr) {
                result -= prev;
            } else {
                result += prev;
            }
            prev = curr;
        }
        result += prev;
        return result;
    }

    public static int getValue(char x) throws Exception {
        switch (x) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: throw new Exception("Используются разные системы координат");
        }
    }

    public static String intToRoman(int num) throws Exception {
        if (num < 0) throw new Exception("В римской системе координат нет отрицательных чисел");
        if (num == 0) throw new Exception("В римской системе координат нет нуля");

        int nums[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int i = 0;
        String result = "";
        while (num > 0) {
            while (nums[i] > num) {
                i++;
            }
            num = num - nums[i];
            result += romans[i];
        }
        return result;
    }

}