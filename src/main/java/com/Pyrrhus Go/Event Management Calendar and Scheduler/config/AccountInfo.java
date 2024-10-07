import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class AccountInfo extends JPanel {
  String currentEmail= "";
  UserIdentifier userIdentifier = new UserIdentifier();
  DataPreparation dataPreparation = new DataPreparation();
  JTable eventTable, transactionTable;
  JLabel name = new JLabel(), userName = new JLabel(), email = new JLabel(),birthday = new JLabel(),password = new JLabel("PASSWORD: ********");
  JButton editProfile = new JButton("<HTML><U>Edit Profile</U></HTML>")
,showPassword = new JButton("P"),edit = new JButton("Edit"),delete = new JButton("Delete");
  JScrollPane scrollPaneForEvents, scrollPaneForTransactions;
  String[] adminAttributes, clientAttributes;
  Object[][] adminData, clientData;

  {
    if (userIdentifier.isAdmin()) {
      adminData = dataPreparation.prepareEventDataForAdmin();
      adminAttributes = new String[]{"Event ID", "Event Name", "Event Date", "Venue", "Facilitator Name", "Number of Guest", "Time Start"};
      eventTable = new JTable(adminData, adminAttributes);
    } else {
      clientData = dataPreparation.prepareEventDataForClient();
      clientAttributes = new String[]{"Event ID", "Event Name", "Event Date", "Venue", "Number of Guest", "Guest Email"};
      eventTable = new JTable(clientData, clientAttributes);
    }
    scrollPaneForEvents = new JScrollPane(eventTable);
  }{
    if (userIdentifier.isAdmin()) {
      adminData = dataPreparation.prepareTransactionDataForAdmin();
      adminAttributes = new String[]{"Transaction ID", "Event ID", "Facilitator Name", "Customer Name", "Total Cost", "Balance", "Status"};
      transactionTable = new JTable(adminData, adminAttributes);
    } else {
      clientData = dataPreparation.prepareTransactionDataForClient();
      clientAttributes = new String[]{"Transaction ID", "Event Name","Venue", "Customer Name","Total Cost", "Balance", "Status"};
      transactionTable = new JTable(clientData, clientAttributes);
    }
    scrollPaneForTransactions = new JScrollPane(transactionTable);
    repaint();
    revalidate();
  }
  JPanel transactionPanel = new JPanel(),eventPanel = new JPanel(), otherComponents = new JPanel();
  public AccountInfo(String username) {
    System.out.println(Arrays.toString(dataPreparation.prepareTransactionDataForAdmin()[0]));
    System.out.println(username);
    setLayout(null);
    setOpaque(true);
    setBackground(Color.white);
    setPreferredSize(new Dimension(1000 -150, 1000));
    Object[] userData =  Select.userInfo(username);
    setProperty(userData);
    setPanels();
    add(transactionPanel);
    add(eventPanel);
    add(name);
    add(password);
    add(birthday);
    add(email);
    add(showPassword);
    add(userName);
    add(password);
    add(editProfile);
    add(edit);
    add(delete);
    repaint();
  }
  public void reScan(){
    {
      if (userIdentifier.isAdmin()) {
        adminData = dataPreparation.prepareEventDataForAdmin();
        adminAttributes = new String[]{"Event ID", "Event Name", "Event Date", "Venue", "Facilitator Name", "Number of Guest","Time Start"};
        eventTable = new JTable(adminData, adminAttributes);
        repaint();
        revalidate();
      } else {
        clientData = dataPreparation.prepareEventDataForClient();
        clientAttributes = new String[]{"Event ID", "Event Name", "Event Date", "Venue", "Number of Guest", "Guest Email"};
        eventTable = new JTable(clientData, clientAttributes);
      }
      scrollPaneForEvents = new JScrollPane(eventTable);

      if (userIdentifier.isAdmin()) {
        adminData = dataPreparation.prepareTransactionDataForAdmin();
        adminAttributes = new String[]{"Transaction ID", "Event ID", "Facilitator Name", "Customer Name", "Total Cost", "Balance", "Status"};
        transactionTable = new JTable(adminData, adminAttributes);
      } else {
        clientData = dataPreparation.prepareTransactionDataForClient();
        clientAttributes = new String[]{"Transaction ID", "Event Name","Venue", "Customer Name","Total Cost", "Balance", "Status"};
        transactionTable = new JTable(clientData, clientAttributes);
      }
      scrollPaneForTransactions = new JScrollPane(transactionTable);
    }
  }
  void setCurrentEmail(String email){
    this.currentEmail = email;
  }
  public void setProperty(Object[] userData) {
    setCurrentEmail((String) userData[2]);
    JTableHeader jtht = transactionTable.getTableHeader();
    jtht.setBackground(Color.black);
    jtht.setForeground(Color.white);
    jtht.setPreferredSize(new Dimension(100,50));
    jtht.setFont(new Font("Aptos", Font.PLAIN, 14));
    transactionTable.setBorder(new LineBorder(Color.black,2));
    transactionTable.setGridColor(Color.black);
    transactionTable.setTableHeader(jtht);
    transactionTable.setBackground(Color.white);
    transactionTable.setOpaque(true);
    transactionTable.setDragEnabled(false);
    transactionTable.getColumn("Transaction ID").setPreferredWidth(85);
    if(userIdentifier.isAdmin()) {
      transactionTable.getColumn("Facilitator Name").setPreferredWidth(120);
    }
    transactionTable.getColumn("Customer Name").setPreferredWidth(120);
    transactionTable.getColumn("Total Cost").setPreferredWidth(80);
    transactionTable.getColumn("Balance").setPreferredWidth(80);
    transactionTable.getColumn("Status").setPreferredWidth(80);
    JTableHeader jthe = eventTable.getTableHeader();
    jthe.setBackground(Color.black);
    jthe.setForeground(Color.white);
    jthe.setPreferredSize(new Dimension(100,50));
    jthe.setFont(new Font("Aptos", Font.PLAIN, 14));
    eventTable.setDragEnabled(false);
    eventTable.setGridColor(Color.black);
    eventTable.setTableHeader(jthe);
    eventTable.setBackground(Color.white);
    eventTable.setOpaque(true);
    scrollPaneForEvents.setSize(725, 300);
    eventTable.getColumn("Event ID").setPreferredWidth(60);
    eventTable.getColumn("Event Name").setPreferredWidth(80);
    eventTable.getColumn("Event Date").setPreferredWidth(80);
    if(userIdentifier.isAdmin()) {
      eventTable.getColumn("Facilitator Name").setPreferredWidth(80);
    }
    eventTable.getColumn("Venue").setPreferredWidth(40);
    eventTable.getColumn("Number of Guest").setPreferredWidth(80);
    eventTable.getColumn("Time Start").setPreferredWidth(80);
    name.setSize(530, 50);
    name.setLocation(10, 10);
    name.setText("NAME: "+ userData[1]);
    name.setFont(new Font("Aptos", Font.BOLD, 40));
    email.setText("E-MAIL: "+ userData[2] );
    email.setFont(new Font("Aptos", Font.BOLD, 14));
    email.setSize(250,50);
    email.setLocation(10,50);
    userName.setText("USERNAME: "+ userData[0]);
    userName.setFont(new Font("Aptos", Font.BOLD, 14));
    userName.setSize(200,100);
    userName.setLocation(10,60);
    birthday.setText("BIRTHDATE: "+ userData[4]);
    birthday.setFont(new Font("Aptos", Font.BOLD, 14));
    birthday.setSize(200,50);
    birthday.setLocation(10,120);
    password.setSize(200,50);
    password.setLocation(10,150);
    password.setFont(new Font("Aptos", Font.BOLD, 14));
    editProfile.setFont(new Font("Aptos", Font.BOLD, 14));
    showPassword.setFont(new Font("Aptos", Font.BOLD, 14));
    showPassword.setFocusable(false);
    showPassword.setSize(50,50);
    showPassword.setLocation(210,150);
    showPassword.setBorderPainted(false);
    showPassword.setContentAreaFilled(false);
    showPassword.addActionListener(e -> {
      if(password.getText().equals("PASSWORD: ********")){
        password.setText("PASSWORD: "+userData[3]);
      }else{
        password.setText("PASSWORD: ********");
      }
    });
    edit.setFont(new Font("Aptos", Font.BOLD, 14));
    edit.setSize(100,60);
    edit.setLocation(10, 900);
    edit.setBorder(new LineBorder(Color.black,3));
    edit.setForeground(Color.black);
    edit.setBackground(Color.white);
    edit.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        edit.setBorder(new LineBorder(Color.black, 3));
        edit.setForeground(Color.white);
        edit.setBackground(Color.black);
      }

      @Override
      public void mouseExited(MouseEvent e) {
        edit.setBorder(new LineBorder(Color.black, 3));
        edit.setForeground(Color.black);
        edit.setBackground(Color.white);
      }
    });
    edit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        edit();
      }
    });
    delete.setFont(new Font("Aptos", Font.BOLD, 14));
    delete.setSize(100,60);
    delete.setBorder(new LineBorder(Color.black,3));
    delete.setForeground(Color.black);
    delete.setBackground(Color.white);
    delete.setLocation(1000-400, 1000-100);
    delete.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        edit.setBorder(new LineBorder(Color.black, 3));
        edit.setForeground(Color.white);
        edit.setBackground(Color.black);
      }

      @Override
      public void mouseExited(MouseEvent e) {
        edit.setBorder(new LineBorder(Color.black, 3));
        edit.setForeground(Color.black);
        edit.setBackground(Color.white);
      }
    });
    delete.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        delete();
      }
    });
    delete.setContentAreaFilled(false);
    editProfile.setSize(300,100);
    editProfile.setContentAreaFilled(false);
    editProfile.setBorderPainted(false);
    editProfile.setLocation(650,130);
    editProfile.setFocusable(false);
    editProfile.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        updateProfile();
      }
    });
  }
  SendCommand sc;
  String cmd;
  void delete(){
    int id =Integer.parseInt(JOptionPane.showInputDialog("Event ID:"));
    String cmd = "DELETE FROM `event_management`.`listevents` WHERE (`eventID` = '"+id+"');\n";
    sc = new SendCommand(cmd,0,0);
  }
  void edit(){
    int choiceToedit = Integer.parseInt(JOptionPane.showInputDialog("Type what you want to edit:\n" +
    "1 - Facilitator Name, 2 - Venue, 3 - Cost, 4 - Date, 5 - Time, 6 - Number of Hours"));
    if(choiceToedit ==1) {
      int id =Integer.parseInt(JOptionPane.showInputDialog("Event ID:"));
      String fname = JOptionPane.showInputDialog("First Name:");
      String lname = JOptionPane.showInputDialog("Last Name:");
      cmd = "UPDATE `event_management`.`users` SET `fname` = '"+fname+"', `lname` = '"+lname+"' WHERE (`faci_id` = '"+id+"');";
      sc = new SendCommand(cmd,0,0);
    }
    if (choiceToedit == 2) {
      int id =Integer.parseInt(JOptionPane.showInputDialog("Event ID:"));
      int venueID = Integer.parseInt(JOptionPane.showInputDialog("Venue ID:\n" +
      "1. Laderas\n" +
      "2. Megans\n" +
      "3. Crisbel\n" +
      "4. Avenue\n" +
      "5. Daniellas\n" +
      "6. Big-8\n" ));
      cmd = "UPDATE `event_management`.`listevents` SET `venueID` = '"+venueID+"' WHERE (`eventID` = '"+id+"');";
      sc = new SendCommand(cmd,0,0);
      return;
    }
    if (choiceToedit == 3) {
      int id =Integer.parseInt(JOptionPane.showInputDialog("Event ID:"));
      int cost = Integer.parseInt(JOptionPane.showInputDialog("Cost Value:"));
      cmd = "UPDATE `event_management`.`transactions` SET `total_due` = '"+cost+"' WHERE (`trans_id` = '"+id+"');";
      sc = new SendCommand(cmd,0,0);
      reScan();
      return;
    }
    if (choiceToedit == 4) {
      int id =Integer.parseInt(JOptionPane.showInputDialog("Event ID:"));
      String  date = JOptionPane.showInputDialog("Date:" +
      "\nFormat YYYY-MM-DD");
      cmd = "UPDATE `event_management`.`listevents` SET `dates` = '"+date+"' WHERE (`eventID` = '"+id+"');";
      sc = new SendCommand(cmd,0,0);
      return;
    }
    if (choiceToedit == 5){
      int id =Integer.parseInt(JOptionPane.showInputDialog("Event ID:"));
      String  time = JOptionPane.showInputDialog("Time:" +
      "\nFormat hh:mm");
      cmd = "UPDATE `event_management`.`listevents` SET `startOfEvent` = '"+time+"' WHERE (`eventID` = '"+id+"');";
      sc = new SendCommand(cmd,0,0);
      reScan();
      return;
    }
    if (choiceToedit == 6){
      int id =Integer.parseInt(JOptionPane.showInputDialog("Event ID:"));
      int hours = Integer.parseInt(JOptionPane.showInputDialog("Hours in the event:"));
      cmd = "UPDATE `event_management`.`listevents` SET `hoursInVenue` = '"+hours+"' WHERE (`eventID` = '"+id+"')";
      sc = new SendCommand(cmd,0,0);
      reScan();
      return;
    }
    if(choiceToedit>6){
      JOptionPane.showMessageDialog(null, "Invalid input");
      edit();
    }

  }
  public void updateProfile(){
    int choice = Integer.parseInt(JOptionPane.showInputDialog("Choose what to edit:\n" +
    "1. E-mail\n" +
    "2. Username\n" +
    "3. Birthdate\n" +
    "4. Password\n"));
    int rng = new NumberGenerator().randomizer();
    try {
      new SendEmail().sentMail("Verification code", String.valueOf(rng),currentEmail);
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
    if(choice==1){
      String newEmail = JOptionPane.showInputDialog("New Email:");
      cmd = "UPDATE `event_management`.`users` SET `email` = '"+newEmail+"' WHERE (`username` = '"+LoginFrame.LoginPanel.userName.getText()+"')";

      rng = new NumberGenerator().randomizer();
      try {
        new SendEmail().sentMail("Verification code", String.valueOf(rng),currentEmail);
      } catch (Exception ex) {
        throw new RuntimeException(ex);
      }
      codeInput = Integer.parseInt(JOptionPane.showInputDialog("Code:"));
      while (codeInput != rng) {
        codeInput = Integer.parseInt(JOptionPane.showInputDialog("Code:\ntype \"0\" to exit"));
        if (codeInput == 0) {
          return;
        }
      }
      sc = new SendCommand(cmd,1,0);
    }
    if(choice==2){
      String newUsername = JOptionPane.showInputDialog("New username:");
      cmd = "UPDATE `event_management`.`users` SET `username` = '"+newUsername+"' WHERE (`username` = '"+LoginFrame.LoginPanel.userName.getText()+"')";
      sc = new SendCommand(cmd,1,0);
    }
    if(choice==3){
      String newBirthdate = JOptionPane.showInputDialog("New birthdate:\n" +
      "Please follow this format YYYY-MM-DD");
      cmd = "UPDATE `event_management`.`ubday` SET `username` = '"+newBirthdate+"' WHERE (`username` = '"+LoginFrame.LoginPanel.userName.getText()+"')";
      sc = new SendCommand(cmd,1,0);
    }
    if(choice==4){
      String newPassword = JOptionPane.showInputDialog("New password:");
        cmd = "UPDATE `event_management`.`users` SET `upassword` = '"+newPassword+"' WHERE (`username` = '"+LoginFrame.LoginPanel.userName.getText()+"')";
      rng = new NumberGenerator().randomizer();
      try {
        new SendEmail().sentMail("Verification code", String.valueOf(rng),currentEmail);
      } catch (Exception ex) {
        throw new RuntimeException(ex);
      }
      codeInput = Integer.parseInt(JOptionPane.showInputDialog("Code:"));
      while (codeInput != rng) {
        codeInput = Integer.parseInt(JOptionPane.showInputDialog("Code:\ntype \"0\" to exit"));
        if (codeInput == 0) {
          return;
        }
      }
      sc = new SendCommand(cmd,1,0);
    }

  }
  public void setPanels(){
    transactionPanel.setLayout(null);
    transactionPanel.setSize(725, 300);
    transactionPanel.setLocation(60, 510);
    transactionPanel.add(scrollPaneForTransactions);
    eventPanel.setLayout(null);
    eventPanel.setSize(725, 300);
    eventPanel.add(scrollPaneForEvents);
    eventPanel.setLocation(60, 200);
    otherComponents.setLayout(null);
    otherComponents.setSize(100,100);
  }
}
