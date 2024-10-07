import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Cashier extends JPanel {
  static JButton exit = new JButton("X");
  int numberofguest = 0, counter = 0;
  HashMap<Integer, JTextField> name = new HashMap<>();
  HashMap<Integer, JTextField> email = new HashMap<>();
  HashMap<Integer, JButton> del = new HashMap<>();
  ArrayList<Integer> index = new ArrayList<>(),
  remove = new ArrayList<>();
  int venue_id = 0;
  JButton checkout = new JButton("CHECKOUT");
  JLabel venue = new JLabel("Venue:"),
  cPhoneNumber = new JLabel("Phone Number:"),
  hoursOfThEvent = new JLabel("Hours in the event:"),
  numberOfGuests = new JLabel("Number of Guests:"),
  eventName = new JLabel("Event Name:"),
  customerEmail = new JLabel("Customer Email:"),
  customerName = new JLabel("Customer Name:"),
  bookingDate = new JLabel("Date:"),
  guestMailsAndNames = new JLabel("Guests' Names and Email:"),
  time = new JLabel("Event Time:");
  JTextField fName = new JTextField(),
  cEmail = new JTextField(),
  lName = new JTextField(),
  ename = new JTextField();
  JButton add = new JButton("+");
  static JFormattedTextField numberOfGuest = new JFormattedTextField(),
  bookDay = new JFormattedTextField(),
  bookMonth = new JFormattedTextField(),
  bookYear = new JFormattedTextField(),
  hours = new JFormattedTextField(),
  phoneNumber = new JFormattedTextField(),hourT = new JFormattedTextField(), minuteT = new JFormattedTextField();
  JComboBox<String> venues = new JComboBox<>();
  Container jp = new Container();
  SendCommand sc;
  public Cashier(ArrayList<String> Venues) {
    for (String s : Venues) {
      venues.addItem(s);
    }
    setOpaque(true);
    setBackground(Color.white);
    setLayout(null);
    setProperties();
    setSize(425, 400);
    add(customerName);
    add(bookingDate);
    add(guestMailsAndNames);
    add(fName);
    add(lName);
    add(bookMonth);
    add(bookDay);
    add(bookYear);
    addJP();
    add(jp);
    add(email.get(0));
    add(name.get(0));
    add(add);
    //add field for reservation given the date
  }


  public void setProperties() {
    customerEmail.setSize(120, 20);
    customerName.setSize(120, 20);
    bookingDate.setSize(50, 20);
    guestMailsAndNames.setSize(170, 20);
    checkout.setSize(130, 60);
    eventName.setSize(70, 20);
    numberOfGuests.setSize(120, 20);
    hoursOfThEvent.setSize(120, 20);
    cPhoneNumber.setSize(120, 20);
    venue.setSize(40, 20);
    time.setSize(100,20);

    customerName.setLocation(30, 10);
    bookingDate.setLocation(30, 40);
    guestMailsAndNames.setLocation(30, 70);
    customerEmail.setLocation(30, 100);
    eventName.setLocation(30, 130);
    numberOfGuests.setLocation(30, 160);
    hoursOfThEvent.setLocation(30, 190);
    cPhoneNumber.setLocation(30, 220);
    venue.setLocation(30, 250);
    checkout.setLocation(160, 320);
    time.setLocation(30,280);

    fName.setLocation(190, 10);
    lName.setLocation(295, 10);
    bookDay.setLocation(215, 40);
    bookMonth.setLocation(190, 40);
    bookYear.setLocation(240, 40);
    cEmail.setLocation(190, 100);
    ename.setLocation(190, 130);
    numberOfGuest.setLocation(190, 160);
    hours.setLocation(190, 190);
    phoneNumber.setLocation(190, 220);
    hourT.setLocation(190,280);
    minuteT.setLocation(220,280);

    exit.setFont(new Font("Aptos", Font.PLAIN, 12));
    customerName.setFont(new Font("Aptos", Font.PLAIN, 12));
    bookingDate.setFont(new Font("Aptos", Font.PLAIN, 12));
    guestMailsAndNames.setFont(new Font("Aptos", Font.PLAIN, 12));
    customerEmail.setFont(new Font("Aptos", Font.PLAIN, 12));
    checkout.setFont(new Font("Aptos", Font.BOLD, 14));
    eventName.setFont(new Font("Aptos", Font.PLAIN, 12));
    numberOfGuests.setFont(new Font("Aptos", Font.PLAIN, 12));
    hoursOfThEvent.setFont(new Font("Aptos", Font.PLAIN, 12));
    cPhoneNumber.setFont(new Font("Aptos", Font.PLAIN, 12));
    venue.setFont(new Font("Aptos", Font.PLAIN, 12));
    time.setFont(new Font("Aptos", Font.PLAIN, 12));
    fName.setFont(new Font("Aptos", Font.PLAIN, 11));
    lName.setFont(new Font("Aptos", Font.PLAIN, 11));
    cEmail.setFont(new Font("Aptos", Font.PLAIN, 11));
    ename.setFont(new Font("Aptos", Font.PLAIN, 11));
    venues.setFont(new Font("Aptos", Font.PLAIN, 11));
    numberOfGuest.setFont(new Font("Aptos", Font.PLAIN, 11));
    hours.setFont(new Font("Aptos", Font.PLAIN, 11));
    phoneNumber.setFont(new Font("Aptos", Font.PLAIN, 11));
    hourT.setFont(new Font("Aptos", Font.PLAIN, 11));
    minuteT.setFont(new Font("Aptos", Font.PLAIN, 11));

    customerName.setForeground(Color.black);
    bookingDate.setForeground(Color.black);
    guestMailsAndNames.setForeground(Color.black);
    customerEmail.setForeground(Color.black);
    eventName.setForeground(Color.black);
    numberOfGuests.setForeground(Color.black);
    hoursOfThEvent.setForeground(Color.black);
    cPhoneNumber.setForeground(Color.black);
    venue.setForeground(Color.black);
    time.setForeground(Color.black);

    numberOfGuest.setSize(20, 20);
    bookDay.setSize(23, 20);
    bookMonth.setSize(23, 20);
    bookYear.setSize(35, 20);
    hours.setSize(20, 20);
    phoneNumber.setSize(100, 20);
    fName.setSize(100, 20);
    lName.setSize(100, 20);
    minuteT.setSize(20, 20);
    hourT.setSize(20, 20);

    cEmail.setSize(100, 20);
    ename.setSize(100, 20);
    exit.setSize(50, 50);
    exit.setLocation(r_MainMenu.booking.getWidth() - 40, 0);

    exit.setContentAreaFilled(false);
    exit.setBorderPainted(false);
    exit.setForeground(Color.white);
    checkout.setBorder(new LineBorder(Color.black, 3));
    checkout.setForeground(Color.black);
    checkout.setBackground(Color.white);
    numberOfGuest.setBorder(new LineBorder(Color.black, 2));
    bookDay.setBorder(new LineBorder(Color.black, 2));
    bookMonth.setBorder(new LineBorder(Color.black, 2));
    ename.setBorder(new LineBorder(Color.black, 2));
    bookYear.setBorder(new LineBorder(Color.black, 2));
    hours.setBorder(new LineBorder(Color.black, 2));
    phoneNumber.setBorder(new LineBorder(Color.black, 2));
    fName.setBorder(new LineBorder(Color.black, 2));
    lName.setBorder(new LineBorder(Color.black, 2));
    cEmail.setBorder(new LineBorder(Color.black, 2));
    ename.setBorder(new LineBorder(Color.black, 2));
    hourT.setBorder(new LineBorder(Color.black, 2));
    minuteT.setBorder(new LineBorder(Color.black, 2));

    venues.setLocation(190, 250);
    venues.setSize(100, 20);
    venues.setBorder(new LineBorder(Color.black));
    venues.setForeground(Color.black);
    venues.setBackground(Color.white);
    venues.setOpaque(false);
    venues.setLightWeightPopupEnabled(true);
    venues.setEditable(false);
    venues.setFocusable(false);
    venues.scrollRectToVisible(new Rectangle(0, 0, 10, 20));

    checkout.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        checkout.setBorder(new LineBorder(Color.black, 3));
        checkout.setForeground(Color.white);
        checkout.setBackground(Color.black);
      }

      @Override
      public void mouseExited(MouseEvent e) {
        checkout.setBorder(new LineBorder(Color.black, 3));
        checkout.setForeground(Color.black);
        checkout.setBackground(Color.white);
      }
    });
    hours.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c) || hours.getText().length() >= 2) {
          e.consume();
        }
      }
    });
    bookDay.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c) || bookDay.getText().length() >= 2) {
          e.consume();
        }
      }
    });
    bookMonth.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c) || bookMonth.getText().length() >= 2) {
          e.consume();
        }
      }
    });
    bookYear.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c) || bookYear.getText().length() >= 4) {
          e.consume();
        }
      }
    });
    bookMonth.addMouseListener(new MouseAdapter() {
    });
    numberOfGuest.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c) || numberOfGuest.getText().length() >= 2) {
          e.consume();
        }
      }
    });
    venues.addActionListener(e -> {
      switch ((String) Objects.requireNonNull(venues.getSelectedItem())) {
        case "Laderas" -> venue_id = 1;
        case "Megans" -> venue_id = 2;
        case "Crisbel" -> venue_id = 3;
        case "Avenue" -> venue_id = 4;
        case "Daniellas" -> venue_id = 5;
        case "Big-8" -> venue_id = 6;
        default -> throw new IllegalStateException("Unexpected value: " + venues.getSelectedItem());
      }
    });
    exit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        reAddAll();
        r_MainMenu.booking.dispose();
      }
    });

    checkout.addActionListener(e -> {
      if(check()){
        String cmd = "select cost_per_head from venue where venue_name = '"+venues.getSelectedItem()+"'";
        sc = new SendCommand(cmd,1,1);

        int pricePerHead = Integer.parseInt(String.valueOf( sc.getInfo()))/*set price per head, get it in the database*/;
         cmd = "select cost_per_hour from venue where venue_name = '"+venues.getSelectedItem()+"'";
        sc = new SendCommand(cmd,1,1);
        int pricePerHour = Integer.parseInt(String.valueOf( sc.getInfo()));
        int subtotalHead = pricePerHead*Integer.parseInt(numberOfGuest.getText());
        int subtotalPerHour = pricePerHour*Integer.parseInt(hours.getText());
        int total = subtotalPerHour+subtotalHead;



        int choice = JOptionPane.showConfirmDialog(null,"Heads ("+numberOfGuest.getText()+")   : Php "+subtotalHead+"\n" +
                "Hours ("+hours.getText()+ "hrs)   : Php "+subtotalPerHour+"\n" +
                "--------------------------------\n" +
                "TOTAL: Php "+total+"\n" +
                "(30% downpayment)");
        System.out.println(choice);
          if(choice==0){
            cmd = "INSERT INTO `event_management`.`customers` (`fname`, `lname`, `phone_num`, `email`) VALUES ('"+fName.getText()+"', '"+lName.getText()+"', '"+phoneNumber.getText()+"', '"+cEmail.getText()+"');\n";
            sc = new SendCommand(cmd,0,0);
            String formattedDate = MainMenu.currentYear+"-"+MainMenu.currMonth+"-"+MainMenu.currentDay,
            formattedTime = hours.getText()+":"+minuteT.getText();
            cmd = "INSERT INTO `event_management`.`listevents` (`faciID`, `venueID`, `number_of_guests`, `cost`, `custID`, `dates`, `eventName`, `hoursInVenue`, `startOfEvent`) " +
            "VALUES ('"+Select.findID(LoginFrame.LoginPanel.userName.getText())+"', '"+Select.findVenueName((String) venues.getSelectedItem())+"', '"+numberOfGuest.getText()+"', '"+total+"', '"+Select.findCustID(fName.getText(),lName.getText())+"', '"+formattedDate+"', '"+ename.getText()+"', '"+hours.getText()+"', '"+formattedTime+"');";
            sc = new SendCommand(cmd,0,0);
            cmd = "INSERT INTO `event_management`.`transactions` (`total_due`, `total_paid`, `custID`, `stat`, `downpayment`, `event_ID`, `faciID`, `balance`) VALUES ('"+total+"', '"+(int)(total*.40)+"', '"+Select.findCustID(fName.getText(),lName.getText())+"', 'UNPAID', '"+total*.40+"', '"+Select.findCustID(fName.getText(),lName.getText())+"', '"+Select.findID(LoginFrame.LoginPanel.userName.getText())+"', '"+(int)(total-(total*.4))+"');";
            sc = new SendCommand(cmd,0,0);
            System.out.println(Select.findCustID(fName.getText(),lName.getText()));
            for(int i = 0;i< email.size();i++){

              if(!email.get(i).getText().isEmpty()||!name.get(i).getText().isEmpty()){
                try {
                  String message = "Greeting "+name.get(i)+"!!!\nWe would like to inform that you are invite to the "+ename.getText()+ " this "+formattedDate + " at "+ formattedTime+ " located at "+ venues.getSelectedItem()+", Digos City"+
                  "\nSee you there "+name.get(i)+"!!!";
                  new SendEmail().sentMail("Invitation", message,email.get(i).getText());
                } catch (Exception ex) {
                  throw new RuntimeException(ex);
                }
              }
            }
            JOptionPane.showMessageDialog(null, "Success");
            reAddAll();
            r_MainMenu.booking.dispose();
          }
      }else{
        JOptionPane.showMessageDialog(null,"Some fields are black, please complete the form");
      }
    });
    name.put(numberofguest, new JTextField());
    name.get(0).setSize(100, 20);
    name.get(0).setBorder(new LineBorder(Color.black));
    email.put(numberofguest, new JTextField());
    email.get(0).setSize(100, 20);
    email.get(0).setLocation(295, 70);
    email.get(0).setBorder(new LineBorder(Color.black, 2));
    name.get(0).setBorder(new LineBorder(Color.black, 2));
    name.get(0).setLocation(190, 70);
    add.setSize(50, 20);
    add.setLocation(email.get(0).getX() + 85, email.get(0).getY());
    add.setContentAreaFilled(false);
    add.setBorderPainted(false);
    index.add(0);
    numberofguest++;
    counter++;
    add.addActionListener(_ -> {
      final int k = numberofguest - 1;
      name.put(numberofguest, new JTextField("Guest's name"));
      email.put(numberofguest, new JTextField("Guest's email"));
      name.get(numberofguest).setSize(name.get(numberofguest - 1).getWidth(), name.get(numberofguest - 1).getHeight());
      email.get(numberofguest).setSize(email.get(numberofguest - 1).getWidth(), email.get(numberofguest - 1).getHeight());
      name.get(numberofguest).setLocation((int) name.get(numberofguest - 1).getLocation().getX(), (int) name.get(numberofguest - 1).getLocation().getY() + 25);
      email.get(numberofguest).setLocation((int) email.get(numberofguest - 1).getLocation().getX(), (int) email.get(numberofguest - 1).getLocation().getY() + 25);
      email.get(numberofguest).setBorder(new LineBorder(Color.black, 2));
      name.get(numberofguest).setBorder(new LineBorder(Color.black, 2));
      name.get(numberofguest).setFont(new Font("Aptos", Font.PLAIN, 12));
      email.get(numberofguest).setFont(new Font("Aptos", Font.PLAIN, 12));
      name.get(numberofguest).setForeground(Color.black);
      email.get(numberofguest).setForeground(Color.black);
      add.setLocation((int) email.get(numberofguest - 1).getLocation().getX() + 85, (int) email.get(numberofguest - 1).getLocation().getY() + 25);
      index.add(counter);
      jp.setLocation(jp.getX(), jp.getY() + 25);
      addSize();
      counter++;
      del.put(numberofguest - 1, new JButton("-"));
      del.get(numberofguest - 1).setSize(50, 20);
      del.get(numberofguest - 1).setLocation((int) email.get(numberofguest - 1).getLocation().getX() + 85, (int) email.get(numberofguest - 1).getLocation().getY());
      del.get(numberofguest - 1).setContentAreaFilled(false);
      del.get(numberofguest - 1).setBorderPainted(false);
      del.get(numberofguest - 1).addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          remove(name.get(index.get(k)));
          remove(email.get(index.get(k)));
          remove(del.get(index.get(k)));
          remove.add(index.get(k));
          for (int i = k; i < email.size() - 1/*&&remove.contains(index.get(i))*/; i++) {
            del.get(i).setLocation(del.get(i).getX(), del.get(i).getY() - 25);
            email.get(i).setLocation(email.get(i).getX(), email.get(i).getY() - 25);
            name.get(i).setLocation(name.get(i).getX(), name.get(i).getY() - 25);
          }
          add.setLocation((int) email.get(numberofguest - 1).getLocation().getX() + 85, (int) email.get(numberofguest - 1).getLocation().getY() - 25);
          email.get(email.size() - 1).setLocation(email.get(email.size() - 1).getX(), email.get(email.size() - 1).getY() - 25);
          name.get(name.size() - 1).setLocation(name.get(name.size() - 1).getX(), name.get(name.size() - 1).getY() - 25);
          jp.setLocation(jp.getX(), jp.getY() - 25);
          minusSize();
          repaint();
        }
      });
      addItem();
      numberofguest++;
      repaint();

    });
  }

  void addItem() {
    add(name.get(numberofguest));
    add(email.get(numberofguest));
    add(del.get(numberofguest - 1));
  }

  void reAddAll() {
    counter = 0;
    numberofguest = 1;
    jp.setLocation(0, 0);
    email.get(0).setText("");
    name.get(0).setText("");
    cEmail.setText("");
    ename.setText("");
    numberOfGuest.setText("");
    phoneNumber.setText("");
    fName.setText("");
    lName.setText("");
    hours.setText("");
    bookDay.setText("");
    bookMonth.setText("");
    bookYear.setText("");
    add(email.get(0));
    email.get(0).setText("");
    add(name.get(0));
    add.setLocation(email.get(0).getX() + 85, email.get(0).getY());
    name.get(0).setText("");
    add(add);

    if (numberofguest > 0) {
      for (int i = 1; i < email.size(); i++) {
        remove(email.get(i));
        remove(name.get(i));
        email.put(i, null);
        name.put(i, null);
        r_MainMenu.jsp.repaint();
      }
      for (int i = 0; i < del.size(); i++) {
        remove(del.get(i));
        del.put(i, null);
      }
    } else {
      JOptionPane.showMessageDialog(null, "Null fields. Returning...");
      r_MainMenu.booking.repaint();
    }
  }
  boolean check(){
    int maxDays = 0;
    for (int i = 0; i<MainMenu.month.length;i++){
      if(MainMenu.daysPerMonth[i]==MainMenu.daysPerMonth[Integer.parseInt(bookMonth.getText())]){
        maxDays= MainMenu.daysPerMonth[i];
      }
    }
    if(fName.getText().isEmpty()){
      System.out.println(1);return false;}
    if(lName.getText().isEmpty()){System.out.println(2);return false;}
    if(bookDay.getText().isEmpty()||Integer.parseInt(bookDay.getText())>maxDays){System.out.println(3);return false;}
    if (bookMonth.getText().isEmpty()){System.out.println(4);return false;}
    if(bookYear.getText().isEmpty()){System.out.println(5);return false;}
    for(int i =0;i<index.size();i++){
      if(!remove.contains(index.get(i))){
        if(email.get(index.get(i)).getText().isEmpty()){
          return false;
        }
      }
    }
    if(customerEmail.getText().isEmpty()){System.out.println(1);return false;}
    if(numberOfGuest.getText().isEmpty()){System.out.println(2);return false;}
    if(hours.getText().isEmpty()){System.out.println(3);return false;}
      return !phoneNumber.getText().isEmpty();
  }

  void addJP() {
    jp.setSize(425, 400);
    jp.setLocation(0, 0);
    jp.add(checkout);
    jp.add(cEmail);
    jp.add(eventName);
    jp.add(numberOfGuest);
    jp.add(hours);
    jp.add(phoneNumber);
    jp.add(venue);
    jp.add(customerEmail);
    jp.add(numberOfGuests);
    jp.add(hoursOfThEvent);
    jp.add(cPhoneNumber);
    jp.add(ename);
    jp.add(venues);
    jp.add(time);
    jp.add(minuteT);
    jp.add(hourT);
  }

  void addSize() {
    setPreferredSize(new Dimension(getWidth(), getHeight() + 25));
    revalidate();
    repaint();
    r_MainMenu.booking.repaint();
    r_MainMenu.booking.revalidate();
    r_MainMenu.jsp.repaint();
    r_MainMenu.jsp.revalidate();
//    r_MainMenu.c.revalidate();
//    r_MainMenu.c.repaint();
  }

  void minusSize() {
    setPreferredSize(new Dimension(getWidth(), getHeight() - 25));
    revalidate();
    repaint();
    r_MainMenu.booking.repaint();
    r_MainMenu.booking.revalidate();
    r_MainMenu.jsp.repaint();
    r_MainMenu.jsp.revalidate();
//    r_MainMenu.c.revalidate();
//    r_MainMenu.c.repaint();
  }

}
