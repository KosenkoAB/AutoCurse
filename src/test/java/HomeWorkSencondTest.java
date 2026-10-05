import org.assertj.core.internal.Strings;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class HomeWorkSencondTest {


    //@BeforeAll
    //static void init() {System.out.println("All test is running");}

    @BeforeEach
    void every() {System.out.println("========================\nTest method start");}

    @AfterEach
    void everytwo() {System.out.println("Test method end\n========================");}

    //@AfterAll
    //static void com() {System.out.println("Test run is over");}


    @Test
    public void testTrueIsEven() {
       int n = 72;

        boolean result = MetodsHome.isEven(n);

        System.out.println("Ввод четного числа, результат: " + result +" TEST PASSED");

        //Assertions.assertThat(result).isTrue();
    }

    @Test
    public void testFalseIsEven() {
        int n = 73;

        boolean result = MetodsHome.isEven(n);

        System.out.println("Ввод нечетного числа, результат: " + result +" TEST PASSED");

        //Assertions.assertThat(result).isFalse();
    }

    @Test
    public void checkAccess() {
        Random random = new Random();
        int age = random.nextInt(17,18);

        String access = MetodsHome.checkAccess(age);

        System.out.println("Возраст больше или меньше 18, результат: " + access);

    }

    @RepeatedTest(20)
    public void twentyRandom() {
        Random random = new Random();
        int score = random.nextInt(0,99);

        String grade = MetodsHome.getGrade(score);

        System.out.println("Ваш грейд: "+grade +" TEST PASSED");

    }

    @Test
    public void twentyRandomInvalid() {

        int score = -1;

        String grade = MetodsHome.getGrade(score);

        System.out.println(grade +"  grade TEST FAILED");

    }

    @Test
    public void twentyRandomInvalid2() {

        int score = 101;

        String grade = MetodsHome.getGrade(score);

        System.out.println(grade +" grade TEST FAILED");

    }



    @Test
    public void checkZero () {
        int p = 0;
        boolean result = MetodsHome.isPositive(p);
        System.out.println(result +" p = 0 TEST PASSED");
    }

    @Test
    public void checkZero2 () {
        int p = -1;
        boolean result = MetodsHome.isPositive(p);
        System.out.println(result +" p = -1  TEST PASSED");
    }

    @Test
    public void testBlast(){
        Random random = new Random();
        int start = random.nextInt(1,5);

        String result = MetodsHome.blastOff(start);
        System.out.println(result +" TEST PASSED");
    }

    @Test
    public void testBlastNeg(){
        int start = 0;

        String result = MetodsHome.blastOff(start);
        System.out.println(result +" TEST PASSED");
    }

    @Test
    public void testSum() {
        Random random = new Random();
        int n = random.nextInt(1,5);

        Integer sum = MetodsHome.sumToN(n);
        System.out.println(sum +" = Сумма чисел: TEST PASSED");
    }

    @Test
    public void testSumNeg() {
        int n = -10;

        Integer sum = MetodsHome.sumToN(n);
        System.out.println(sum +" = Сумма отрицательных чисел: TEST FAILED");
    }

//    @ParameterizedTest
//    @MethodSource("arrEr")
//    public void testHasBug(String[] messages){
//        Boolean result = MetodsHome.hasBug(messages);
//        System.out.println(result + " TEST PASSED");
//    }
//
//    static Stream<String[]> arrEr() {
//        return Stream.of(
//                new String[]{"Hi", "Hello", "Dodge", "Bug", "Warm", "Test"}
//        );
//    }



    @Test
    public void testInRangePos(){
        Random random = new Random();
        int start = random.nextInt(1,5);

        Random randomtwo = new Random();
        int end = randomtwo.nextInt(6,10);

        String res = MetodsHome.getEvenInRange(start, end);
        System.out.println(res + "TEST PASSED");
    }

    @Test
    public void testInRangeTwo() {
        int start = -9;
        int end = -1;

        String res = MetodsHome.getEvenInRange(start, end);
        System.out.println(res + "TEST PASSED");
    }

    @Test
    public void testInRangeNeg() {
        int start = 9;
        int end = 1;

        String res = MetodsHome.getEvenInRange(start, end);
        System.out.println(res + "TEST FAILED");
    }


    @ParameterizedTest
    @MethodSource("arraysForTest")
    void testFindMax(int[] max1) {
        int r = MetodsHome.findMax(max1);
        System.out.println("TEST PASSED - Максимум: " + r);
    }

    static Stream<int[]> arraysForTest() {
        return Stream.of(
                new int[]{2, 4, 5, 6, 45, 3453},
                new int[]{10, 20, 30},
                new int[]{1, 1, 1}
        );
    }

    @Test
    public void testReverse(){
        String[] arr2 = {"1", "2", "3"};

        String result = Arrays.toString(MetodsHome.reverse(arr2));
        System.out.println(result + "\nTEST PASSED");
    }

    @Test
    public void testAvg (){
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

        double avg = MetodsHome.calcAverage(list);
        System.out.println(avg + " среднее арифметическое - TEST PASSED");

    }

    @Test
    public void testRemove(){
        List<String> list2 = new ArrayList<>();
        list2.add("Tanya");
        list2.add("Anna");
        list2.add("Tanos");
        list2.add("Tanos");
        list2.add("Galya");
        list2.add("Tanos");

        String nameToRemove = "Tanos";

        List<String> result = MetodsHome.removeSpecificName(list2,nameToRemove);
        System.out.println(result);
    }


}
