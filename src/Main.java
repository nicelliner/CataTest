import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("\nInput:");
        String str = in.nextLine();
        System.out.println("\nOutput:");
        System.out.println(calc(str));
    }

    public static String calc(String input) {
        try {
            String[] temp = input.split(" ");

            boolean romanCheck = false;
            if (temp[0].charAt(0) > (int) '9') {
                temp[0] = String.valueOf(romanToInt(temp[0]));
                temp[2] = String.valueOf(romanToInt(temp[2]));
                romanCheck = true;
            }

            int firsInt = Integer.parseInt(temp[0]);
            int secondInt = Integer.parseInt(temp[2]);
            String oper = temp[1];

            int result = 0;
            switch (oper) {
                case "+":
                    result = firsInt + secondInt;
                    break;
                case "-":
                    result = firsInt - secondInt;
                    break;
                case "/":
                    result = firsInt / secondInt;
                    break;
                case "*":
                    result = firsInt * secondInt;
                    break;
                default:
                    throw new IOException();
            }

            if (romanCheck == true) {
                return intToRoman(result);
            }
            return String.valueOf(result);

        } catch (RuntimeException e) {
            return "Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
        } catch (IOException e) {
            return "Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
        }
    }

    public static int romanToInt(String s) {
        int result = 0;
        int prev = getValue(s.charAt(0));
        for(int i = 1; i != s.length(); i++){
            int curr = getValue(s.charAt(i));
            if (prev < curr){
                result -= prev;
            } else {
                result += prev;
            }
            prev = curr;
        }
        result += prev;
        return result;
    }

    public static int getValue(char x) {
        try {
            switch (x) {
                case 'I': return 1;
                case 'V': return 5;
                case 'X': return 10;
                case 'L': return 50;
                case 'C': return 100;
                case 'D': return 500;
                case 'M': return 1000;
                default: throw new IOException();
            }
        } catch (IOException e) {
            System.out.println("Используются разные системы координат");
            System.exit(0);
            return 0;
        }
    }

    public static String intToRoman(int num) {
        try {
            if (num < 0) {
                throw new IOException();
            }

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
        } catch (IOException e) {
            return "В римской системе координат не может получиться отрицательное число";
        }
    }
}