import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class SortingDataIteratorTest {
    List<Integer> list1;
    List<Integer> list2;
    List<Iterator<Integer>> iteratorList;
    SortingDataIterator<Integer> iterator;

    @Test
    private void shouldReturnFirstElemWhenCallingNextOneTime() {
        list1 = List.of(1, 3);
        list2 = List.of(2, 4);
        iteratorList = Arrays.asList(list1.iterator(), list2.iterator());
        iterator = new SortingDataIterator<>(iteratorList, Integer::compareTo);

        Assert.assertEquals((int) iterator.next(), 1);
    }

    @Test
    private void shouldReturnSecondElemWhenCallingNextTwoTimes() {
        list1 = List.of(1, 3);
        list2 = List.of(2, 4);
        iteratorList = Arrays.asList(list1.iterator(), list2.iterator());
        iterator = new SortingDataIterator<>(iteratorList, Integer::compareTo);
        iterator.next();

        Assert.assertEquals((int) iterator.next(), 2);
    }

    @Test
    private void shouldReturnThirdElemWhenCallingNextThreeTimes() {
        list1 = List.of(1, 3);
        list2 = List.of(2, 4);
        iteratorList = Arrays.asList(list1.iterator(), list2.iterator());
        iterator = new SortingDataIterator<>(iteratorList, Integer::compareTo);
        callingIteratorNextNTimes(2, iterator);

        Assert.assertEquals((int) iterator.next(), 3);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    private void shouldThrowNoSuchElementExceptionWhenCallingNextIfTheIterationHasNoMoreElem() {
        list1 = List.of(1, 3);
        list2 = List.of(2, 4);
        iteratorList = Arrays.asList(list1.iterator(), list2.iterator());
        iterator = new SortingDataIterator<>(iteratorList, Integer::compareTo);
        callingIteratorNextNTimes(5, iterator);
    }

    @Test
    private void shouldReturnTrueWhenCallingHasNextIfIterationHasMoreElem() {
        list1 = List.of(1, 3);
        list2 = List.of(2, 4);
        iteratorList = Arrays.asList(list1.iterator(), list2.iterator());
        iterator = new SortingDataIterator<>(iteratorList, Integer::compareTo);
        callingIteratorNextNTimes(2, iterator);

        Assert.assertTrue(iterator.hasNext());
    }

    @Test
    private void shouldReturnFalseWhenCallingHasNextIfTheIterationHasNoMoreElem() {
        list1 = List.of(1, 3);
        list2 = List.of(2, 4);
        iteratorList = Arrays.asList(list1.iterator(), list2.iterator());
        iterator = new SortingDataIterator<>(iteratorList, Integer::compareTo);
        callingIteratorNextNTimes(4, iterator);

        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    private void shouldReturnTrueWhenCallingHasNextAfterCallingNextIfIterationHasLastOrMoreElem() {
        list1 = List.of(1, 3);
        list2 = List.of(2, 4);
        iteratorList = Arrays.asList(list1.iterator(), list2.iterator());
        iterator = new SortingDataIterator<>(iteratorList, Integer::compareTo);
        callingIteratorNextNTimes(3, iterator);

        Assert.assertTrue(iterator.hasNext());
    }

    @Test
    private void shouldReturnNextElemWhenCallingNextAfterCallingHasNextIfIterationHasMoreElem() {
        list1 = List.of(1, 3);
        list2 = List.of(2, 4);
        iteratorList = Arrays.asList(list1.iterator(), list2.iterator());
        iterator = new SortingDataIterator<>(iteratorList, Integer::compareTo);
        iterator.hasNext();

        Assert.assertEquals((int) iterator.next(), 1);
    }

    @Test
    private void shouldReturnSecondElemWhenCallingNextTwoTimesIfOneListIsEmpty() {
        list1 = List.of(1, 3);
        list2 = new ArrayList<>();
        iteratorList = Arrays.asList(list1.iterator(), list2.iterator());
        iterator = new SortingDataIterator<>(iteratorList, Integer::compareTo);
        iterator.next();

        Assert.assertEquals((int) iterator.next(), 3);
    }

    @Test
    private void shouldReturnFirstElemWhenCallingNextOneTimeIfListWithStrings() {
        List<String> list1 = List.of("1", "4");
        List<String> list2 = List.of("2", "3");
        List<Iterator<String>> iteratorList = Arrays.asList(list1.iterator(), list2.iterator());
        SortingDataIterator<String> iterator = new SortingDataIterator<>(iteratorList, String::compareTo);

        Assert.assertEquals(iterator.next(), "1");
    }

    private void callingIteratorNextNTimes(int n, Iterator iterator) {
        while (n > 0) {
            iterator.next();
            n--;
        }
    }
}
