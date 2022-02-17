import java.util.*;

public class SortingDataIterator<T> implements Iterator<T> {
    private Map<Iterator<T>, T> iteratorMap;
    private Iterator<T> currentIterator;
    private T currentValue;
    private Comparator<T> comparator;

    public SortingDataIterator(List<Iterator<T>> iteratorList, Comparator<T> comparator) {
        this.comparator = comparator;
        this.iteratorMap = new HashMap<>();

        for (Iterator<T> iterator : iteratorList) {
            if (iterator.hasNext()) {
                iteratorMap.put(iterator, iterator.next());
                currentValue = iteratorMap.get(iterator);
                currentIterator = iterator;
            }
        }
    }

    public boolean hasNext() {
        return !iteratorMap.isEmpty();
    }

    public T next() {
        if (!hasNext()) throw new NoSuchElementException();

        findIteratorWithMinElem();
        if (currentIterator.hasNext()) {
            return returnIteratorElem();
        } else {
            return returnIteratorLastElem();
        }
    }

    private T returnIteratorElem() {
        T result = currentValue;
        currentValue = currentIterator.next();
        iteratorMap.put(currentIterator, currentValue);
        return result;
    }

    private void findIteratorWithMinElem() {
        for (HashMap.Entry<Iterator<T>, T> map : iteratorMap.entrySet()) {
            if (currentValue == null || comparator.compare(currentValue, map.getValue()) > 0) {
                currentValue = map.getValue();
                currentIterator = map.getKey();
            }
        }
    }

    private T returnIteratorLastElem() {
        T lastElem = currentValue;
        iteratorMap.remove(currentIterator);
        currentValue = null;
        return lastElem;
    }
}
