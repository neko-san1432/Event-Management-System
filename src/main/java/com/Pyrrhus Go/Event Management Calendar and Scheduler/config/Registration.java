import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Registration extends JPanel {

  JButton submit = new JButton("Submit"){
    @Override
    public void addActionListener(ActionListener l) {
      //gEmail verification before adding the user in the database
    }
  },clear = new JButton("Clear"){
    @Override
    public void addActionListener(ActionListener l) {
      firstName.setText("");
      lastName.setText("");
      email.setText("");
      birthMonth.setText("");
      birthDate.setText("");
      birthYear.setText("");
      username.setText("");
    }
  },back = new JButton("Back");
  JTextField firstName = new JTextField(),
  lastName = new JTextField(),
  email = new JTextField();
  JFormattedTextField birthMonth = new JFormattedTextField(),
  birthDate = new JFormattedTextField(),
  birthYear = new JFormattedTextField(),
  username = new JFormattedTextField();
  JLabel fName =new JLabel("First name:"),
  lName = new JLabel("Last name:"),
  mail = new JLabel("E-mail:"),
  birthDay= new JLabel("Birthday:"),
  uName = new JLabel("Username:");

  public Registration(){
    setVisible(false);
    setSize(1000,500);
    setPropertyValues();
    setLayout(null);
    add(submit);
    add(back);
    add(submit);
    add(firstName);
    add(lastName);
    add(email);
    add(birthMonth);
    add(birthDate);
    add(birthYear);
    add(username);
    add(fName);
    add(lName);
    add(mail);
    add(birthDay);
    add(uName);
  }
  public void setPropertyValues(){

    submit.setSize(75,20);
    clear.setSize(75,20);
    back.setSize(75,20);
    back.addActionListener(e ->{
      setVisible(false);
      LoginFrame.loginPanel.setVisible(true);
    });

    submit.setLocation(250,0);
    clear.setLocation(250,100);
    back.setLocation(250,200);

    firstName.setSize(100,20);
    lastName.setSize(100,20);
    email.setSize(150,20);
    birthMonth.setSize(30,20);
    birthDate.setSize(30,20);
    birthYear.setSize(50,20);
    username.setSize(100,20);

    firstName.setLocation(100, 0);
    lastName.setLocation(100, 20);
    email.setLocation(100, 40);
    birthMonth.setLocation(100, 60);
    birthDate.setLocation(130, 60);
    birthYear.setLocation(160, 60);
    username.setLocation(100, 120);

    fName.setSize(100,20);
    lName.setSize(100,20);
    mail.setSize(100,20);
    uName.setSize(100,20);
    birthDay.setSize(100,20);

    fName.setLocation(0, 0);
    lName.setLocation(0, 20);
    mail.setLocation(0, 40);
    birthDay.setLocation(0, 60);
    uName.setLocation(0, 120);

    birthDate.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if(!Character.isDigit(c)){
          e.consume();
        }
      }
    });
    birthMonth.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if(!Character.isDigit(c)){
          e.consume();
        }
      }
    });
    birthYear.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if(!Character.isDigit(c)){
          e.consume();
        }
      }
    });

  }
  public void sendToDatabase(){}
  public void checkInput(){}
}
