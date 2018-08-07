import java.util.*;

public class Teste {

    public static void main(String[] args) {

        List<String> sql = Arrays.asList("name", "fone", "email", "valor");
        List<String> entrada = Arrays.asList("Eduardo", "(34)32225010", "edu@gmail.com", "10.00","");

        Map<String, String> map = new LinkedHashMap<>();


        for (String s : sql) {

            for (String e : entrada) {

                map.put(s, e);

            }
        }

        for(Map.Entry<String,String> entry : map.entrySet()){
            System.out.println(entry.getKey()+" : "+entry.getValue());

        }
    }
}
