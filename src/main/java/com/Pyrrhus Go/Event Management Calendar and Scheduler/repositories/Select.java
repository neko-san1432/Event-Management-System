import java.util.Arrays;

public class Select {
  static SendCommand sendCommand;
  static String command = "";
  static boolean findUsername(String username) {
    command = "select count(*) from users where users.username ='" + username + "';";
    sendCommand = new SendCommand(command, 1, 1);
    return Integer.parseInt(String.valueOf(sendCommand.getInfo())) == 1;
  }
  static boolean findPassword(String username, String password) {
    command = "select count(*) from users where users.username ='" + username + "' and users.upassword ='" + password + "';";
    sendCommand = new SendCommand(command, 1, 1);
    return Integer.parseInt(String.valueOf(sendCommand.getInfo())) == 1;
  }
  static int findID(String username) {
    command = "select faci_id from users where users.username ='" + username + "'";
    sendCommand = new SendCommand(command, 1, 1);
    return Integer.parseInt(String.valueOf(sendCommand.getInfo()));
  }
  static int findVenueName(String vName) {
    command = "select venue_id from venue where venue_name ='" + vName + "'";
    sendCommand = new SendCommand(command, 1, 1);
    return Integer.parseInt(String.valueOf(sendCommand.getInfo()));
  }
  static int findCustID(String fName, String lName){
    command = "select cust_id from customers where customers.fname ='" + fName + "' and customers.lName = '"+lName+"';";
    sendCommand = new SendCommand(command, 1, 1);
    return Integer.parseInt(String.valueOf(sendCommand.getInfo()));
  }
  static Object[] userInfo(String userName) {
    command = "select users.username,concat(users.fname,' ',users.lName),Email,upassword,ubday \n" +
    "from users\n" +
    "where users.username  ='"+userName+"';";
    sendCommand = new SendCommand(command, 2, 5);
    System.out.println(Arrays.toString(sendCommand.getUserData()));
    return sendCommand.getUserData();
  }
}
