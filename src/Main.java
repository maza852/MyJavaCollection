import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> test = new MyArrayList<>();

        System.out.println("List is empty? " + test.isEmpty());

        System.out.println("Fill list random el");
        for (int i = 0; i < 10; i++) {
            Integer randomNumber = Integer.valueOf((int)(Math.random() * 30));
            test.add(randomNumber);
        }

        System.out.println(test);

        Integer num = test.remove(5);

        System.out.println(test);
        System.out.println("removed el = " + num);

        test.set(3, 999);
        System.out.println(test);

        test.add(2, 777);
        System.out.println(test);

        System.out.println("Is there 999? " + test.contains(999));
        System.out.println("Is there 14? " + test.contains(14));

        //test.bubbleSort();
        //System.out.println(test);

        System.out.println("el[7] = " + test.get(7));
        System.out.println("last el = " + test.get(test.size() - 1));

        System.out.println("Try to remove 999: " + test.remove(Integer.valueOf(999)));
        System.out.println("Try to remove 19: " + test.remove(Integer.valueOf(19)));
        System.out.println(test);

        test.clear();
        System.out.println("List was clear");
        System.out.println("List is empty? " + test.isEmpty());

        test.add(88);
        test.add(12);
        test.add(1, 33);
        test.add(0, 90);
        System.out.println(test);
        test.remove(Integer.valueOf(12));
        System.out.println(test);

        List<Integer> someList = new ArrayList<>() {{
            add(45);
            add(8);
            add(97);
            add(-2);
            add(900);
        }};

        test.addAll(someList);
        System.out.println(test);

        test.sort();
        System.out.println("After bubble sort: " + test);

        String str = "Oh... you think darkness is your ally?";
        List<String> words = new ArrayList<>(Arrays.asList(str.split(" ")));
        MyArrayList<String> test2 = new MyArrayList<>(words);

        System.out.println("new list for test:\n" + test2);
        test2.sort();
        System.out.println("After bubble sort:\n" + test2);
        test2.add(1,"new");
        test2.add("I'm batman!");
        System.out.println(test2);

        System.out.println("Before static sort: " + words);
        var newWords = MyArrayList.staticSort(words);
        System.out.println("After static sort: " + newWords);

        List<Double> doubles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Double randomNum = Double.valueOf((double)Math.round(Math.random() * 1000) / 100d);
            doubles.add(randomNum);
        }
        System.out.println("Before static sort: " + doubles);
        var newDoubles = MyArrayList.staticSort(doubles);
        System.out.println("After static sort: " + newDoubles);
    }
}