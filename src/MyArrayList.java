import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Собственная реализация ArrayList в которой есть методы:
 * add(value) - вставка в конец, add(index, value) - вставка по номеру
 * addAll(Collection) - добавляет в конец все элементы переданной коллекции
 * get(index) - получение элемента по номеру
 * set(index, value) - изменение элемента по номеру
 * remove(index) - удаление по номеру, remove(value) - удаление по значению (первый найденный)
 * contains(value) - проверка на наличие элемента в списке
 * clear() - очистка списка (реальный размер внутреннего массива не изменяется)
 * size() - получение размера списка
 * isEmpty() - проверка на пустоту списка
 * toString() - переопределен для класса
 * bubbleSort() - сортирует список методом пузырька с флагом
 * staticSort(Collection) - статичная сортировка пузырьком переданной внутрь коллекции
 */
public class MyArrayList<T> {
    private Object[] array;
    private int size;
    private static final int DEFAULT_SIZE = 10;

    public MyArrayList() {
        this.array = new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    public MyArrayList(Collection<? extends T> collection) {
        int length = collection.size();
        this.array = new Object[length + length/2];
        this.size = 0;
        addAll(collection);
    }

    public void add(T value) {
        if (isFull()) {
            extendArray();
        }
        array[size] = value;
        size++;
    }

    public void add(int index, T value) {
        if (isIndexInBounds(index)) {
            add(value);                 // сначала добавим новый элемент в конец (чисто чтобы массив расширился, если нужно)
            moveArrayRight(index);      // сдвигаем массив вправо от индексного элемента
            array[index] = value;       // устанавливаем новое значение элементу
        }
    }

    public void addAll(Collection<? extends T> collection) {
        for (T value : collection)
            add(value);
    }

    public void set(int index, T value) {
        if (isIndexInBounds(index)) {
            array[index] = value;
        } else
            throw new ArrayIndexOutOfBoundsException();
    }

    private boolean isFull() {
        return size == array.length;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void extendArray() {
        int newSize = size + size/2;
        Object[] newArray = new Object[newSize];

        for (int i = 0; i < size; i++)
            newArray[i] = array[i];

        array = newArray;
    }

    public T get(int index) {
        if (isIndexInBounds(index))
            return (T) array[index];
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    private boolean isIndexInBounds(int index) {
        return (index >= 0 && index < size);
    }
    /* поиск элемента в коллекции по значению
       вернёт индекс первого такого элемента или -1, если его нет */
    private int getFirstIndexOf(T value) {
        for (int i = 0; i < size(); i++) {
            if (array[i].equals(value))
                return i;
        }
        return -1;
    }

    public boolean contains(T value) {
        return getFirstIndexOf(value) != -1;
    }

    public boolean remove(T value) {
        int index = getFirstIndexOf(value);
        if (index == -1)
            return false;

        remove(index);
        return true;
    }

    public T remove(int index) {
        if (isIndexInBounds(index)) {
            T value = (T) array[index];
            moveArrayLeft(index);
            size--;
            return value;
        } else
            throw new ArrayIndexOutOfBoundsException();
    }
    /* метод смещает все элементы массива относительно переданного индекса-аргумента влево на 1 элемент
       последний элемент массива (до удаления) "обнуляем", т.к. он сдвинулся на 1 элемент влево (в принципе это не обязательно) */
    private void moveArrayLeft(int index) {
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
    }
    /* метод смещает элементы массива вправо на 1 элемент начиная от переданного аргумента индекса
       в цикле идём от предпоследнего до нужного нам по индексу, при этом последний элемент "затрется"
       за границы мы не уйдем, т.к. смещаемся от предпоследнего элемента */
    private void moveArrayRight(int index) {
        for (int i = size - 2; i >= index ; i--) {
            array[i + 1] = array[i];
        }
    }

    public void clear() {
        for (int i = 0; i < size(); i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "{}";

        String result = "{ ";
        for (int i = 0; i < size - 1; i++) {
            result += array[i] + ", ";
        }
        result += array[size - 1] + " }";

        return result;
    }

    public void sort() {
        bubbleSort(this.array, size());
    }

    private static <T extends Comparable<? super T>> void bubbleSort(Object[] arrayObj, int sizeOfArray) {
        boolean flag = false;

        for (int i = 0; i < sizeOfArray - 1 && !flag; i++) {
            flag = true;
            for (int j = 0; j < sizeOfArray - 1 - i; j++) {
                if (((Comparable<T>) arrayObj[j]).compareTo((T) arrayObj[j+1]) > 0) {
                    Object temp = arrayObj[j];
                    arrayObj[j] = arrayObj[j+1];
                    arrayObj[j+1] = temp;
                    flag = false;
                }
            }
        }
    }

    public static <T extends Comparable<? super T>>  List<T> staticSort(List<T> list) {
        Object[] array = list.toArray(new Object[0]);
        bubbleSort(array, array.length);

        ArrayList<T> newList = new ArrayList<T>();
        for (Object obj : array)
            newList.add((T) obj);

        return newList;
    }
}
