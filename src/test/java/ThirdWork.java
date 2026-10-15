import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;



public class ThirdWork {
    //Позитивный boolean
    @Test
    @Tag("Assert")
    public void testTrueIsEven() {
        int n = 72;
        boolean result = MetodsHome.isEven(n);
        Assertions.assertThat(result).isTrue();

    }



    //Позитивный с массивом вроде как
    @Test
    @Tag("Assert")
    public void assertForReverse() {
        String[] arr2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] arr = {"9", "8", "7", "6", "5", "4", "3", "2", "1"};
        Assertions.assertThat(MetodsHome.reverse(arr2)).isEqualTo(arr);


    }

    //Позитивный с массивом
    @Test
    @Tag("Assert")
    public void testRemoveSpecificName() {
        List<String> expected = new ArrayList<>();
        expected.add("Tanya");
        expected.add("Anna");
        expected.add("Galya");

        String nameToRemove = "Tanos";

        List<String> list2 = new ArrayList<>();
        list2.add("Tanya");
        list2.add("Anna");
        list2.add("Tanos");
        list2.add("Tanos");
        list2.add("Galya");
        list2.add("Tanos");

        Assertions.assertThat(MetodsHome.removeSpecificName(list2,nameToRemove)).isEqualTo(expected);

    }

    //Падающий
    @Test
    @Tag("Assert")
    public void testNegSum(){
        Assertions.assertThat(MetodsHome.sumToN(-3)).isEqualTo(-6);

    }



}
