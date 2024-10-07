import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainFrame extends JFrame {
  static String userName = LoginFrame.LoginPanel.userName.getText();
  static JButton logout = new JButton("LOGOUT");
  static MainMenu mainMenu = new MainMenu();
  static l_MainMenu l_mainMenu = new l_MainMenu();
  static r_MainMenu r_mainMenu = new r_MainMenu();
  static AccountInfo accountInfos = new AccountInfo(userName);
  static JScrollPane jScrollPane = new JScrollPane(accountInfos);
  static JPanel jp = new JPanel();
  {
    logout.addActionListener(e -> {
      dispose();
      LoginFrame.clearFields();
      new LoginFrame().setVisible(true);
    });
  }
  public MainFrame() {
    setSize(1000, 600);
    setLayout(null);
    getContentPane().setBackground(Color.white);
    setProperties();
    setLocationRelativeTo(null);
    add(l_mainMenu);
    l_mainMenu.setLocation(0, 0);
    add(mainMenu);
    mainMenu.setLocation(103, 0);
    add(r_mainMenu);
    r_mainMenu.setLocation(810, 0);
    jScrollPane.setSize(1000 - 1, 550);
    jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
    jp.setSize(1000 - 150, 550);
    jp.setBorder(new LineBorder(Color.black, 10));
    jp.add(jScrollPane);
    jp.setLocation(100, 0);
    jp.setLayout(null);
    jp.setVisible(false);

//    jScrollPane.setLocation(180,0);

//    add(jScrollPane);
    add(jp);
    setCurrentUser(userName);
  }

  public void setCurrentUser(String user) {
    userName = user;
  }

  public void setProperties() {

    logout.setSize(100, 40);
  }
}