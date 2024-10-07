public class UserIdentifier {
  SendCommand sendCommand;
  String username = /*MainFrame.userName*/"Krupot";
  public boolean isAdmin(){
    String command = "select users.access_level from users where username = '"+username+"'";
    sendCommand = new SendCommand(command,1,1);
    return Integer.parseInt(String.valueOf(sendCommand.getInfo())) == 1;
  }
}
