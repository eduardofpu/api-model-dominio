import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Palavra {

    public static void main(String[] args) {
       // List<Integer> listar = listar();
        //System.out.println(listar);
        //missiValues();

        values();
    }

    public static void values() {
        String values = "?";
        for (int i = 0; i < 2; i++) {
            System.out.print(values.concat(","));
            if (i == 2 - 1) {
                String v = values.replace(",", "");
                System.out.print(v);
            }
        }
    }

    public static void missiValues() {

        String v = "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";

        System.out.println(v.length());
        for (int i = 1; i <= 100; i++) {
            int j = 9;
            if (i == j) {
                String substring = v.substring(i);
                substring.length();
                int total = v.length() - substring.length();
                String substring1 = v.substring(substring.length() - total + 1);
                System.out.println(substring1);

            }

        }
    }

    public static List<Integer> listar() {

        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<Integer> list = new ArrayList<>();


            if(!integers.isEmpty()){
                list.addAll(integers);
            }

            return list;
    }
}


