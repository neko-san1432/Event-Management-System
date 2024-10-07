import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {
  static JButton login = new JButton("Login");
  {
    login.addActionListener(e ->{
      if(Select.findUsername(LoginPanel.userName.getText())&&Select.findPassword(LoginPanel.userName.getText(), String.valueOf(LoginPanel.password.getPassword()))){
        dispose();
        new MainFrame().setVisible(true);
      }
    });
  }
  static LoginPanel loginPanel = new LoginPanel();
  static Registration registration = new Registration();
  public LoginFrame() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(380, 380);
    setResizable(false);
    setLocationRelativeTo(null);
    add(loginPanel);
    add(registration);
  }

  public static void clearFields() {
    LoginPanel.userName.setText("");
    LoginPanel.password.setText("");
  }

  public void close(){dispose();}
  static class LoginPanel extends JLayeredPane {
    JButton
    register = new JButton("Register");
    JLabel forgotPassword = new JLabel("Forgot password?"),
    username = new JLabel("Username:"),
    passw =new JLabel("Password:"),
    image = new JLabel(/*new ImageIcon()*/),
    welcome = new JLabel("Welcome");
    static JTextField userName = new JTextField();
    static JPasswordField password = new JPasswordField();
    public LoginPanel() {
      setSize(380, 380);
      setLayout(null);
      setPropertyValues();
      add(login);
      add(forgotPassword);
      add(userName);
      add(password);
      add(register);
      add(passw);
      add(username);
      add(image);
      add(welcome);
      forgotPassword.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
          //tung mo underline
          forgotPassword.setText("<HTML><U>Forgot password?</U></HTML>");
        }

        @Override
        public void mouseExited(MouseEvent e) {
          //tung mo mawala ang underline
          forgotPassword.setText("Forgot password?");
        }

        @Override
        public void mouseClicked(MouseEvent e) {
          int rng;
          String newPassword = JOptionPane.showInputDialog("Enter Email:");
          String cmd = "UPDATE `event_management`.`users` SET `upassword` = '"+newPassword+"' WHERE (`username` = '"+LoginFrame.LoginPanel.userName.getText()+"')";

          String email = JOptionPane.showInputDialog("Enter Email:");
          rng = new NumberGenerator().randomizer();
          try {
            new SendEmail().sentMail("Verification code", String.valueOf(rng),email);
          } catch (Exception ex) {
            throw new RuntimeException(ex);
          }
          int codeInput = Integer.parseInt(JOptionPane.showInputDialog("Code:"));
          while (codeInput != rng) {
            codeInput = Integer.parseInt(JOptionPane.showInputDialog("Code:\ntype \"0\" to exit"));
            if (codeInput == 0) {
              return;
            }
          }
          SendCommand sc = new SendCommand(cmd,1,0);
        }
      });

    }

    public void setPropertyValues() {
      userName.setSize(75, 20);
      password.setSize(75, 20);
      register.setSize(90, 20);
      login.setSize(75, 20);


      register.addActionListener(e -> {
        setVisible(false);
        registration.setVisible(true);
      });

      userName.setLocation(250,75);
      password.setLocation(250,100);

      login.setLocation(220,125);
      login.setBorderPainted(false);
      login.setContentAreaFilled(false);
      login.setForeground(Color.black);
      register.setLocation(213,150);
      register.setBorderPainted(false);
      register.setContentAreaFilled(false);

      forgotPassword.setSize(150, 20);
      forgotPassword.setForeground(Color.black);
      username.setSize(150,20);
      passw.setSize(150,20);
      passw.setForeground(Color.black);
      image.setSize(170,380);
      welcome.setSize(100,100);
      welcome.setForeground(Color.black);
      image.setBorder(new LineBorder(Color.BLACK));
      image.setOpaque(true);
      image.setBackground(Color.black);
      username.setLocation(180,75);
      username.setForeground(Color.black);
      passw.setLocation(180,100);
      forgotPassword.setLocation(210,175);
      welcome.setLocation(210,0);

      forgotPassword.setFont(new Font("Arial",Font.PLAIN,12));
      userName.setFont(new Font("Arial",Font.PLAIN,12));
      username.setFont(new Font("Arial",Font.PLAIN,12));
      passw.setFont(new Font("Arial",Font.PLAIN,12));
      password.setFont(new Font("Arial",Font.PLAIN,12));
      login.setFont(new Font("Arial",Font.PLAIN,12));
      register.setFont(new Font("Arial",Font.PLAIN,12));
      welcome.setFont(new Font("Arial",Font.BOLD,19));
    }
  }
}
