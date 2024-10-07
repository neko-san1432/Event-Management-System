package javad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLConnector {
  public static void main(String[] args) {
    String url = "jdbc:mysql://127.0.0.1:3301/event_management";
    String username = "root";
    String pass = "root";
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection   = DriverManager.getConnection(url,username,pass);
      Statement statement = connection.createStatement();
      /*ResultSet resultset =*/statement.execute  ("insert emailqueue(email,eventID) values('asda',1); ");
//      while (resultset.next()){
//        System.out.println(resultset.getString(1)+" "+ resultset.getString(2) + " " + resultset.getString(3));
//      }
//      statement.
      connection.close();
    }catch(Exception e){
      System.out.println(e);
    }
  }
}
