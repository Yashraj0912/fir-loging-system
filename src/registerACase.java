import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class registerACase extends  writeFir{
    private int VID ;
    private String  Vname ;
    private String Cname;
    private int CId;
    private int CaseID;
    private int FirID;
    private int VAge ;
    private int CAge ;
    private  String FirReportId;
    private String satatus ;
    private String verdict ;

    Scanner sc = new Scanner(System.in);

    // register the case and accessing the methods to enter the detains

    public void registeringTheCase(Connection c){
        setVictimDtaile();

        setCriminalDetail();



        generateCasId();

        int t1 =registering(c);

        if (t1==0){
            System.out.println("case allready exist");
            return;
        }


        System.out.println("Now provide the details of incident in the FIR report");

        super.setFirReportId(FirReportId);

        super.startUI();


    }



    // setting case details first
    private void setVictimDtaile (){
        System.out.println("Enter the victim details");

        System.out.println("Enter the name of the victim ");
        this.Vname= sc.nextLine();
        //System.out.println();

        System.out.println("Enter the adhar number of victim");
        this.VID = sc.nextInt();

        System.out.println();
        System.out.println("Enter date of birth of victim");
        // aadd dob part later

        System.out.println("Enter the age of the victim ");
        this.VAge = sc.nextInt();
        sc.nextLine();

    }

    // method for the victim details

    private void setCriminalDetail(){
        System.out.println("Now enter the details of the criminal");
        System.out.println();
        System.out.println("Enter the name of criminal");

        this.Cname = sc.nextLine();

        System.out.println("Enter the adhaar number of the criminal");
        this.CId =sc.nextInt();

        System.out.println();
        System.out.println("Enter date of birth of cictim");
        // aadd dob part later

        System.out.println("Enter the age of the Cictim ");
        this.CAge =sc.nextInt();

    }

    private void generateCasId(){
        String a = String.valueOf(VID);
        String b = String.valueOf(CId);

        String caseid = a.substring(0, Math.min(2,a.length())) + b.substring(0, Math.min(2,b.length()));
        FirReportId=caseid;
        CaseID = Integer.parseInt(caseid);
        FirID = CaseID;
    }

    private int registering(Connection c){
        try{

            String strVictim = String.format("INSERT INTO victim(adhaar , name , age) values (?,?,?)");
            PreparedStatement statment = c.prepareStatement(strVictim);
            statment.setInt(1,VID);
            statment.setString(2,Vname);
            statment.setInt(3,VAge);

            // now criminal details to be entered
            String strCriminal = String.format("INSERT INTO criminal(adhaar,name,age,verdict) values (?,?,?,?)");
            PreparedStatement criminalstatment = c.prepareStatement(strCriminal);
            criminalstatment.setInt(1,CId);
            criminalstatment.setString(2,Cname);
            criminalstatment.setInt(3,CAge);
            criminalstatment.setString(4,"pending");



            // now case making inputs in the fir table

            String strFir = String.format("INSERT INTO Fir(FirID,victimID,criminalID,caseID,FirReport) values (?,?,?,?,?)");
            PreparedStatement FirStatment = c.prepareStatement(strFir);
            FirStatment.setInt(1,FirID);
            FirStatment.setInt(2,VID);
            FirStatment.setInt(3,CId);
            FirStatment.setInt(4,CaseID);
            FirStatment.setString(5,FirReportId);

            String strCase =
                    "INSERT INTO caseT(caseID,statusC) VALUES (?,?)";

            PreparedStatement caseStatement = c.prepareStatement(strCase);
            caseStatement.setInt(1, CaseID);
            caseStatement.setString(2,"running");



            // to execute update in the table and see
            int i1= statment.executeUpdate();
            int i2= criminalstatment.executeUpdate();
            int i3 = FirStatment.executeUpdate();
            int i4 = caseStatement.executeUpdate();
            if(i3==0){
                return 0;
            }
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }



}
