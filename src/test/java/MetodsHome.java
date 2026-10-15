import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class MetodsHome {

    //четное, нечетное+
    public static boolean isEven(int n) {
        if (n % 2 == 0){
            return true;
        }
        else {
            return false;
        }
    }

    //возраст, меньше или больше 18+
    public static String checkAccess(int age) {
        String access;
        if (age >= 18){
            access = "Allowed";
        } else {
            access = "Denied";
        }
        return access;
    }

    //больше меньше 0+
    public static boolean isPositive(int p) {
        return (p >= 0) ? true : false;
    }

    //грейды по баллам+
    public static String getGrade(int score){
        String grade;
        if (score >= 0 && score <= 20){
            grade = "E";
        } else if (score >= 21 && score <= 40) {
            grade = "D";
        } else if (score >= 41 && score <= 60) {
            grade = "C";
        } else if (score >= 61 && score <= 80) {
            grade = "B";
        } else if (score >= 81 && score <= 100) {
            grade = "A";
        } else grade = "invalid";
        return grade;
    }

    //стартовое число+
    public static String blastOff(int start){
        String result = "";
        for (int i = start; i >= 1; i-- ){
            result = result + i + " ";
        }
        result = result + "Поехали!";
        return result;
    }

    //сумма чисел от n до 1 +
    public static int sumToN(int n){
        int sum = 0;
        for (int i = n; i >= 1; i--){
            sum = sum + i;
        }
        return sum;
    }
    //строка Bug --------
    public static boolean hasBug(String[] messages){
        for (int i = 0; i < messages.length; i++) {
            if (messages[i].equals("Bug")){
                return true;
            }

        }
        return false;
    }

    //четные числа из диапазона+
    public static String getEvenInRange(int start, int end){
        String res = "";
        for (int s = start; s <= end; s++){
            if (s % 2 == 0){
                res = res + s + " ";
            }
        }
        return res;
    }

    //большое число в массиве +
    public static int findMax(int[] max1){
        int r = max1[0];
        for (int i = 1; i < max1.length; i++) {
            if (r <= max1[i]){
                r = max1[i];
            }
        }
        return r;

    }

    //обратный порядок массива +
    public static String[] reverse(String[] arr2){
        String[] result = new String[arr2.length];
        for (int i = 0; i < arr2.length; i++) {
            result[i] = arr2[arr2.length - 1 - i];
        }
        return result;
    }

    //среднее число из массива +
    public static int calcAverage(List<Integer> list){
        int sum = 0;
        for (int i = 0; i < list.size(); i++){
            sum = sum+ list.get(i);
        }
        double avg = sum / list.size();
        return (int) avg;
    }

    //исключение строки по имени из списка +
    public static List<String> removeSpecificName(List<String> list2, String nameToRemove){
        List<String> result = new ArrayList<>();
        for (int i = 0; i < list2.size(); i++) {
            if (!list2.get(i).equals(nameToRemove)){
                result.add(list2.get(i));
            } else continue;
        }

        return result;
    }






    public static void main(String[] args) {
        String[] arr1 = {"Hello", "Bug", "World"};
        int[] max1 = {-2, 3, 1, 8, -3, 13, -23, 5, 22, 9};
        String[] arr2 = {"1", "2", "3"};
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);

        List<String> list2 = new ArrayList<>();
        list2.add("Tanya");
        list2.add("Anna");
        list2.add("Tanos");
        list2.add("Tanos");
        list2.add("Galya");
        list2.add("Tanos");

        String nameToRemove = "Tanos";



        System.out.println(isEven(2));
        System.out.println(checkAccess(3));
        System.out.println(isPositive(-3));
        System.out.println(getGrade(50));
        System.out.println(blastOff(8));
        System.out.println(sumToN(7));
        System.out.println(hasBug(arr1));
        System.out.println(getEvenInRange(5,54));
        System.out.println(findMax(max1));
        System.out.println(reverse(arr2));
        System.out.println(calcAverage(list));
        System.out.println(removeSpecificName(list2, nameToRemove));
    }

}
