package lesson1.task1;

import org.example.lesson1.task1.Logic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.example.lesson1.task1.Logic.bubbleSort;
import static org.example.lesson1.task1.Logic.removeNegAndZero;
import static org.junit.jupiter.api.Assertions.*;

class Task1LogicTest {

    @Test
    @Order(1)
    void testRemoveNegAndZero(){
        int[] array = {0,1,2,3,-1,-2,-3,0};
        int[] expected = {1,2,3};

        int[] actual  = removeNegAndZero(array);

        Assertions.assertArrayEquals(expected,actual);

    }

    @Test
    @Order(2)
    void testRemoveNegAndZeroNullCase(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() -> Logic.removeNegAndZero(null), "Array should not be null");

        assertEquals("Array should not be null",
                exception.getMessage());
    }



    @Test
    @Order(3)
    void testBubbleSort() {
        int[] array = {1,3,5,6,2,7};
        int[] expected = {1,2,3,5,6,7};

        int[] actual  = bubbleSort(array);

        Assertions.assertArrayEquals(expected,actual);

    }

    @Test
    @Order(4)
    void testBubbleSortNullCase(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() -> Logic.removeNegAndZero(null), "Array should not be null");

        assertEquals("Array should not be null",
                exception.getMessage());
    }
}