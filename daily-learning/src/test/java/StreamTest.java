import com.google.common.collect.Lists;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {


    @Test
    public void test1() {
        List<String> list = Lists.newArrayList(
                "bcd", "cde", "def", "abc");
        List<String> result = list.stream()
                //.parallel()
                .filter(e -> e.length() >= 3)
                .map(e -> e.charAt(0))
                //.peek(System.out :: println)
                //.sorted()
                //.peek(e -> System.out.println("++++" + e))
                .map(e -> String.valueOf(e))
                .collect(Collectors.toList());
        System.out.println("----------------------------");
        System.out.println(result);
    }

}
