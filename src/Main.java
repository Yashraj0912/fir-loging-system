public class Main {
    public static void main(String[] args) {
        jdbcConnectin obj =new jdbcConnectin();

        registerACase rgc = new registerACase();

        rgc.registeringTheCase( obj.getConnected());

        System.out.println();
    }
}