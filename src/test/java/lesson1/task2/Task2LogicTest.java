package lesson1.task2;

import org.example.lesson1.task2.Logic;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2LogicTest {

    @Test
    void testPrintTop5Hashtags() {
        List<String> list = List.of("#AAA #BBB #CCC #DDD #EEE",
                "#AAA #BBB #CCC #DDD ",
                "#AAA #BBB #CCC",
                "#AAA #BBB",
                "#AAA");

        List<String> actual = Logic.printTop5Hashtags(list);
        List<String> expected = List.of("#AAA", "#BBB", "#CCC", "#DDD", "#EEE");

        assertEquals(expected, actual);
    }

    @Test
    void testPrintTop5HashtagsNullCase() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Logic.printTop5Hashtags(null), "List should not be null");

        assertEquals("List should not be null",
                exception.getMessage());
    }

    @Test
    void testPrintTop5HashtagsEmptyList() {

        List<String> list = List.of();

        List<String> actual = Logic.printTop5Hashtags(list);
        List<String> expected = List.of("","","","","");

        assertEquals(expected, actual);
    }


}
