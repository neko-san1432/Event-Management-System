import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class SendCommand {
    //Data container declaration
    static String obj = "";
    static Object[] objs;
    static Object[][] objects;

    public SendCommand(String command, int request, int attributes) {
        //Database credentials
        String url = "jdbc:mysql://127.0.0.1:3301/event_management";
        String username = "root";
        String pass = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//Getting class for MySQL Connector
            Connection connection = DriverManager.getConnection(url, username, pass);
            Statement statement = connection.createStatement();
            if (request == 1 && attributes > 1) {
                //Data gathering
                ResultSet resultset = statement.executeQuery(command);
                ArrayList<String> list = new ArrayList<>();
                StringBuilder data = new StringBuilder();
                while (resultset.next()) {
                    for (int i = 1; i <= attributes; i++) {
                        data.append(resultset.getString(i)).append(", ");
                    }
                    list.add(String.valueOf(data));
                    data = new StringBuilder();
                }
                objects = new Object[list.size()][];
                for (int i = 0; i < list.size(); i++) {
                    objects[i] = list.get(i).split(", ");
                    System.out.println(Arrays.toString(objects[i]));
                }
            } else if (request == 0&&attributes==0) {
                //Execute
                statement.execute(command);
            } else if (request == 1 && attributes == 1) {
                //Single attribute getter
                ResultSet resultset = statement.executeQuery(command);
                while(resultset.next()){
                obj = resultset.getString(1);}
            } else if (request == 2) {
                ResultSet resultset = statement.executeQuery(command);
                ArrayList<String> list = new ArrayList<>();
                StringBuilder data = new StringBuilder();
                while (resultset.next()){
                    for (int i = 1; i <= attributes; i++) {
                        data.append(resultset.getString(i)).append(", ");
                    }
                }
                list.add(String.valueOf(data));
                objs = list.get(0).split(", ");
                System.out.println(Arrays.toString(objs));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //getters
    public Object[][] getData() {
        return objects;
    }

    public void setInfo(String data) {
        obj = data;
    }

    public Object getInfo() {
        return obj;
    }

    public Object[] getUserData() {
        return objs;
    }
}
