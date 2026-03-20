public class Main {
    public static void main(String[] args) {
        jdbcConnectin obj =new jdbcConnectin();

//        registerACase rgc = new registerACase(obj.getConnected());
//
//        rgc.registeringTheCase();
        getDetails gs = new getDetails(obj.getConnected());
        gs.callTheDetailMethods(1234);

        System.out.println();
    }
}