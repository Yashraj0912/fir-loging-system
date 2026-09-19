import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        jdbcConnectin obj =new jdbcConnectin();
        int i =0;
        Scanner sc = new Scanner(System.in );
        while(i != 3){
            System.out.println("enther 1 to register a new case ");
            System.out.println("enter 2 to see existing case");
            System.out.println("enter any number to exist the program");
            i = sc.nextInt();
            if(i==1){
                registerACase rgc = new registerACase(obj.getConnected());

                rgc.registeringTheCase();

            }
            else if(i ==2){
                System.out.println("enter the FIR id of the case");
                int firId = sc.nextInt();
                getDetails gs = new getDetails(obj.getConnected());
                gs.callTheDetailMethods(firId);

            }
            else{
                i =3;

            }
        }

//        registerACase rgc = new registerACase(obj.getConnected());
//
//        rgc.registeringTheCase();
//        getDetails gs = new getDetails(obj.getConnected());


        System.out.println();
    }
}