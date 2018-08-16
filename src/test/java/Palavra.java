public class Palavra {

    public static void main(String[] args) {



        String values = "?";
        for(int i=0; i<2;i++){
            System.out.print(values.concat(","));
            if(i==2-1){
                String v = values.replace(",","");
                System.out.print(v);
            }
        }
    }
}


