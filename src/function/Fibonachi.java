package function;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yurik on 02.09.16.
 */
public class Fibonachi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();
        System.out.println(getFibonachi(value).toString());
    }

    private static List getFibonachi(int value) {
        List<Integer> list = new ArrayList<>();
        int backValue = 0;
        int beforeBackValue = 0;
        for (int i = 0; ; i++) {
            if (i == 0) {
                beforeBackValue = 1;
                list.add(1);
            } else if (i == 1) {
                backValue = 1;
                list.add(1);
            } else {
                if ((backValue + beforeBackValue) > value) break;
                list.add(backValue + beforeBackValue);
                beforeBackValue = backValue;
                backValue = list.get(i);
            }
        }
        return list;
    }
}
