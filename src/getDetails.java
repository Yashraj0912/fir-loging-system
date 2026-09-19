import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class getDetails {
    private int VID ;
    private String  Vname ;
    private String Cname;
    private int CId;
    private int CaseID;
    private int FirID;
    private int VAge ;
    private int CAge ;
    private  String FirReportId;

    Connection c;


    //setting the connection in the class

    public  getDetails (Connection c){
        super();
        this.c = c;

    }



    // now the methhod to call the detail related methods
    public void callTheDetailMethods(int firid){
        setFirID(firid);
        System.out.println("fir id is set ");
        Scanner sc = new Scanner(System.in);
        int i=0;
        while(i !=4 ) {
            System.out.println("to get the details of the victim enter 1 ");
            System.out.println("to get the details of the criminal enter 2 ");
            System.out.println("to see the fir enter the 3");
            System.out.println("enter 4 to exit ");
            i = sc.nextInt();
            switch (i) {
                case 1: {
                    getVictimDetails(VID);
                    break;
                }
                case 2: {
                    getCriminalDetails(CId);
                    break;
                }
                case 3: {
                    getFirReport(FirReportId);
                    break;
                }
                case 4: {
                    System.out.println("existing ..");
                }
                default: {
                    System.out.println("enter the valid value ");
                }

            }
        }

        sc.close();


    }



    // set the fir id and add the details regarding the case and fir
    private void setFirID( int firId){
        this.FirID= firId;

        try {
            String str = "SELECT * FROM Fir WHERE FirID = ? ";
            PreparedStatement preparedStatement = c.prepareStatement(str);
            preparedStatement.setInt(1,FirID);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                this.VID = rs.getInt("victimID");
                this.CId = rs.getInt("criminalID");
                this.FirReportId = rs.getString("FirReport");
            } else {
                System.out.println("FIR not found!");
                rs.close();
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    //function for the objec to get the details through the firId only




    private void getCriminalDetails( int criminalId){
        try{
        String str = "SELECT * FROM criminal WHERE adhaar = ?";
        PreparedStatement statment = c.prepareStatement(str);
        statment.setInt(1, criminalId );
            ResultSet rs = statment.executeQuery();


            if (rs.next()) {
                this.CId = rs.getInt("adhaar");
                this.CAge = rs.getInt("age");
                this.Cname = rs.getString("name");

                System.out.println("\n--- Criminal Details ---");
                System.out.println("ID: " + CId);
                System.out.println("Name: " + Cname);
                System.out.println("Age: " + CAge);
            } else {
                System.out.println("Criminal not found!");
            }

            rs.close();
            statment.close();

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private void getVictimDetails( int victimId){
        try{
            String str = "SELECT * FROM victim WHERE adhaar = ?";
            PreparedStatement statment = c.prepareStatement(str);
            statment.setInt(1, victimId );
            ResultSet rs = statment.executeQuery();



            if (rs.next()) {
                this.VID = rs.getInt("adhaar");
                this.VAge = rs.getInt("age");
                this.Vname = rs.getString("name");

                System.out.println("\n--- Victim Details ---");
                System.out.println("ID: " + VID);
                System.out.println("Name: " + Vname);
                System.out.println("Age: " + VAge);
            } else {
                System.out.println("Victim not found!");
            }
            rs.close();
            statment.close();

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public void getFirReport( String FirReportID){
        String path = "src/firs";
        new FileViewer(path, FirReportID);

    }

}
