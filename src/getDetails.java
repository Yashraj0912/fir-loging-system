import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


    public void setFirID( int firId){
        this.FirID= firId;
    }
    private void getCriminalDetails( Connection c , int criminalId){
        try{
        String str = "SELECT * FROM TABLE criminal WHERE adhaar = ?";
        PreparedStatement statment = c.prepareStatement(str);
        statment.setInt(1, criminalId );
            ResultSet rs = statment.executeQuery();
            this.CId = rs.getInt("adhaar");
            this.CAge= rs.getInt("age");
            this.Cname = rs.getString("name");

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private void getVictimDetails( Connection c , int victimId){
        try{
            String str = "SELECT * FROM TABLE victim WHERE adhaar = ?";
            PreparedStatement statment = c.prepareStatement(str);
            statment.setInt(1, victimId );
            ResultSet rs = statment.executeQuery();
            this.VID = rs.getInt("adhaar");
            this.VAge= rs.getInt("age");
            this.Vname = rs.getString("name");

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public void getFirReport(Connection c , int firID){
        try{
            String  str =  "SELECT * FROM TABLE Fir WHERE FirID = ?";
            PreparedStatement statment = c.prepareStatement(str);
            statment.setInt(1,firID);
            ResultSet rsv = statment.executeQuery();
            this.FirReportId = rsv.getString("FirReport");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

}
