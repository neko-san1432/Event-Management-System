import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;

public class MainMenu extends JLayeredPane {
  static ArrayList<String> availableVenues;
  static UserIdentifier userIdentifier = new UserIdentifier();
  static String[] month = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"},
  weekdays = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
  static JComboBox<String> cMonth = new JComboBox<>();
  static JComboBox<Integer> cYear = new JComboBox<>();
  static {
    for (int i = 0; i < 12; i++) {
      cMonth.addItem(month[i]);
    }
    for (int i = 0; i < 75; i++) {
      cYear.addItem(Integer.parseInt("20" + (24 + i)));
    }
    cMonth.setSelectedItem(month[5]);
  }
  static int currentYear = cYear.getItemAt(cYear.getSelectedIndex()), currentDay = 0,
          currMonth = 6;

  JButton book = new JButton();

  static JLabel[] venues = new JLabel[6];
  int currentIndex = 5;
  SendCommand sendCommand;
  static int[] daysPerMonth = {Month.valueOf(month[0]).maxLength(),
  Month.valueOf(month[1]).maxLength(), Month.valueOf(month[2]).maxLength(), Month.valueOf(month[3]).maxLength(),
  Month.valueOf(month[4]).maxLength(), Month.valueOf(month[5]).maxLength(), Month.valueOf(month[6]).maxLength(),
  Month.valueOf(month[7]).maxLength(), Month.valueOf(month[8]).maxLength(), Month.valueOf(month[9]).maxLength(),
  Month.valueOf(month[10]).maxLength(), Month.valueOf(month[11]).maxLength()};
  HashMap<String, Integer> dayPerMonth = new HashMap<>();
  JLabel[][] dayLabels = new JLabel[7][6];
  JLabel[] weekendsLabel = new JLabel[7];
  String currentMonth = (String) cMonth.getSelectedItem();
  LocalDate localDate = LocalDate.of(currentYear, Month.valueOf(currentMonth), 1);
  DayOfWeek dayOfWeek = DayOfWeek.from(localDate);
  String startWeekdays = dayOfWeek.name();
  JButton logout = new JButton("logout");

  {
    for (int i = 0; i < venues.length; i++) {
      venues[i] = new JLabel();
    }
    venues[0].setText("Laderas - vacant");
    venues[1].setText("Megans - vacant");
    venues[2].setText("Crisbel - vacant");
    venues[3].setText("Avenue - vacant");
    venues[4].setText("Daniellas - vacant");
    venues[5].setText("Big-8 - vacant");
  }

  {
    for (int i = 0; i < 12; i++) {
      dayPerMonth.put(month[i], daysPerMonth[i]);
    }
  }
  int currentEndOfTheMonth = dayPerMonth.get(currentMonth);

  public MainMenu() {
    setOpaque(true);
    setBackground(Color.white);
    setPropertyValues();
    emptyborder();
    setSize(700, 550);
    setLayout(null);
    add(cYear);
    add(cMonth);
    for (int i = 0; i < dayLabels.length; i++) {
      for (int j = 0; j < dayLabels[0].length; j++) {
        dayLabels[i][j] = new JLabel();
        dayLabels[i][j].setSize(65, 65);
        dayLabels[i][j].setLocation((i * 65) + 120, (j * 65) + 155);
        dayLabels[i][j].setFont(new Font("Aptos", Font.PLAIN, 14));
        dayLabels[i][j].setBackground(Color.WHITE);
        dayLabels[i][j].setForeground(Color.black);
        dayLabels[i][j].setOpaque(true);
        dayLabels[i][j].setHorizontalAlignment(JLabel.CENTER);
        dayLabels[i][j].setVerticalAlignment(JLabel.CENTER);
        int finalI1 = i;
        int finalJ1 = j;
        dayLabels[i][j].addMouseListener(new MouseAdapter() {
          @Override
          public void mouseEntered(MouseEvent e) {
            dayLabels[finalI1][finalJ1].setBackground(Color.black);
            dayLabels[finalI1][finalJ1].setForeground(Color.white);
          }

          @Override
          public void mouseExited(MouseEvent e) {
            dayLabels[finalI1][finalJ1].setBackground(Color.WHITE);
            dayLabels[finalI1][finalJ1].setForeground(Color.black);
          }
        });
        int finalI = i;
        int finalJ = j;
        dayLabels[i][j].addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            r_MainMenu.book.setVisible(true);
            currentDay = Integer.parseInt(dayLabels[finalI][finalJ].getText());
            if (!userIdentifier.isAdmin()) {
              r_MainMenu.book.setVisible(true);
            } else {
              r_MainMenu.book.setVisible(true);
            }
            int convertMonth = 0;
            for (int i = 0; i<MainMenu.month.length;i++){
              if(MainMenu.month[i].equals(month[currentIndex])){
                convertMonth =(i+1);
              }
            }
            availableVenues = new ArrayList<>();
            if (!dayLabels[finalI][finalJ].getText().isEmpty()) {
              r_MainMenu.date.setText(month[currentIndex] + "/" + dayLabels[finalI][finalJ].getText() + "/" + currentYear);
              String command = "select count(*) from listevents where dates = '" + currentYear + "-" + convertMonth + "-" + dayLabels[finalI][finalJ].getText() + "'";
              sendCommand = new SendCommand(command, 1, 1);
              if (!(Integer.parseInt(String.valueOf(sendCommand.getInfo())) > 5)) {
                book.setEnabled(true);
                command = "SELECT count(*) from listevents where venueID = 1 and dates = '" + currentYear + "-" + convertMonth + "-" + dayLabels[finalI][finalJ].getText() + "'; ";
                sendCommand = new SendCommand(command, 1, 1);
                if (Integer.parseInt(String.valueOf(sendCommand.getInfo())) > 0) {
                  venues[0].setText("Laderas - occupied");
                } else {
                  venues[0].setText("Laderas - vacant");
                  availableVenues.add("Laderas");
                }
                command = "SELECT count(*) from listevents where venueID = 2 and dates = '" + currentYear + "-" + convertMonth + "-" + dayLabels[finalI][finalJ].getText() + "'; ";
                sendCommand = new SendCommand(command, 1, 1);
                if (Integer.parseInt(String.valueOf(sendCommand.getInfo())) > 0) {
                  venues[1].setText("Megans - occupied");
                } else {
                  venues[1].setText("Megans - vacant");
                  availableVenues.add("Megans");
                }
                command = "SELECT count(*) from listevents where venueID = 3 and dates = '" + currentYear + "-" + convertMonth + "-" + dayLabels[finalI][finalJ].getText() + "'; ";
                sendCommand = new SendCommand(command, 1, 1);
                if (Integer.parseInt(String.valueOf(sendCommand.getInfo())) > 0) {
                  venues[2].setText("Crisbel - occupied");
                } else {
                  venues[2].setText("Crisbel - vacant");
                  availableVenues.add("Crisbel");
                }
                command = "SELECT count(*) from listevents where venueID = 4 and dates = '" + currentYear + "-" + convertMonth + "-" + dayLabels[finalI][finalJ].getText() + "'; ";
                sendCommand = new SendCommand(command, 1, 1);
                if (Integer.parseInt(String.valueOf(sendCommand.getInfo())) > 0) {
                  venues[3].setText("Avenue - occupied");
                } else {
                  venues[3].setText("Avenue - vacant");
                  availableVenues.add("Avenue");
                }
                command = "SELECT count(*) from listevents where venueID = 5 and dates = '" + currentYear + "-" + convertMonth + "-" + dayLabels[finalI][finalJ].getText() + "'; ";
                sendCommand = new SendCommand(command, 1, 1);
                if (Integer.parseInt(String.valueOf(sendCommand.getInfo())) > 0) {
                  venues[4].setText("Daniellas - occupied");
                } else {
                  venues[4].setText("Daniellas - vacant");
                  availableVenues.add("Daniellas");
                }
                command = "SELECT count(*) from listevents where venueID = 6 and dates = '" + currentYear + "-" + convertMonth + "-" + dayLabels[finalI][finalJ].getText() + "'; ";
                sendCommand = new SendCommand(command, 1, 1);
                if (Integer.parseInt(String.valueOf(sendCommand.getInfo())) > 0) {
                  venues[5].setText("Big-8 - occupied");
                } else {
                  venues[5].setText("Big-8 - vacant");
                  availableVenues.add("Big-8");
                }
                for(int i = 0;i<venues.length;i++){
                  venues[i].setVisible(true);
                }
              } else {
                venues[0].setText("Laderas - occupied");
                venues[1].setText("Megans - occupied");
                venues[2].setText("Crisbel - occupied");
                venues[3].setText("Avenue - occupied");
                venues[4].setText("Daniellas - occupied");
                venues[5].setText("Big-8 - occupied");

                for(int i = 0;i<venues.length;i++){
                  venues[i].setVisible(true);
                }

                book.setEnabled(false);
              }
            }
          }
        });
        add(dayLabels[i][j]);
      }
    }
    for (int i = 0; i < weekendsLabel.length; i++) {
      weekendsLabel[i] = new JLabel(weekdays[i]);
      weekendsLabel[i].setSize(65, 65);
      weekendsLabel[i].setLocation((i * 65) + 120, 90);
      weekendsLabel[i].setBorder(new LineBorder(Color.black));
      weekendsLabel[i].setFont(new Font("Aptos", Font.PLAIN, 12));
      weekendsLabel[i].setOpaque(true);
      weekendsLabel[i].setBackground(Color.black);
      weekendsLabel[i].setForeground(Color.white);
      weekendsLabel[i].setVerticalAlignment(JLabel.CENTER);
      weekendsLabel[i].setHorizontalAlignment(JLabel.CENTER);
      add(weekendsLabel[i]);
    }
    int p = 1;
    int condition = 1;
    for (int i = 0; i < dayLabels[0].length; i++) {
      for (JLabel[] dayLabel : dayLabels) {
        if (condition >= setter() && condition < currentEndOfTheMonth + setter()) {
          dayLabel[i].setVisible(true);
          dayLabel[i].setText(String.valueOf(p));
          p++;
        } else {
          dayLabel[i].setVisible(false);
        }
        condition++;
      }
    }
    add(logout);
  }

  public void reDate() {
    localDate = LocalDate.of(currentYear, Month.valueOf(currentMonth), 1);
    dayOfWeek = DayOfWeek.from(localDate);
    startWeekdays = dayOfWeek.name();
  }

  public void setPropertyValues() {
    book.setSize(100, 100);
    cMonth.setSize(120, 40);
    cMonth.setForeground(Color.black);
    cMonth.setBackground(Color.white);
    cYear.setBackground(Color.white);
    cMonth.setOpaque(true);
    cYear.setOpaque(true);
    cMonth.setLocation(40, 30);
    cYear.setSize(80, 40);
    cYear.setLocation(580, 30);
    cYear.setFocusable(false);
    cYear.setForeground(Color.black);
    cYear.setFont(new Font("Calibri", Font.PLAIN, 20));
    cMonth.setFocusable(false);
    cMonth.setFont(new Font("Calibri", Font.PLAIN, 20));
    cMonth.setOpaque(true);
    cYear.setOpaque(true);
    for (int i = 0; i < cMonth.getComponentCount(); i++) {
      if (cMonth.getComponent(i) instanceof JComponent) {
        ((JComponent) cMonth.getComponent(i)).setBorder(new EmptyBorder(0, 0, 0, 0));
      }
      if (cMonth.getComponent(i) instanceof AbstractButton) {
        ((AbstractButton) cMonth.getComponent(i)).setBorderPainted(false);
      }
    }
    for (int i = 0; i < cYear.getComponentCount(); i++) {
      if (cYear.getComponent(i) instanceof JComponent) {
        ((JComponent) cYear.getComponent(0)).setBorder(new EmptyBorder(0, 0, 0, 0));
      }
      if (cYear.getComponent(i) instanceof AbstractButton) {
        ((AbstractButton) cYear.getComponent(0)).setBorderPainted(false);
      }
    }
    cMonth.addActionListener(e -> {
      currMonth = cMonth.getSelectedIndex() + 1;
      currentMonth = (String) cMonth.getSelectedItem();
      currentEndOfTheMonth = dayPerMonth.get(currentMonth);
      updateForward();
    });
    cYear.addActionListener(e -> {
      currentYear = (int) cYear.getSelectedItem();
      updateByYears();
    });
    book.addActionListener(E -> {
//       final Cashier cashier = new Cashier(availableVenues,currentYear,currMonth,currentDay);
//        cashier.setVisible(true);
    });
    book.setFont(new Font("Aptos", Font.PLAIN, 12));
  }

  public void updateForward() {
    reDate();
    clear();
    int p = 1;
    int condition = 1;
    for (int i = 0; i < dayLabels[0].length; i++) {
      for (JLabel[] dayLabel : dayLabels) {
        if (condition >= setter() && condition < currentEndOfTheMonth + setter()) {
          dayLabel[i].setVisible(true);
          dayLabel[i].setText(String.valueOf(p));
          p++;
        } else {
          dayLabel[i].setVisible(false);
        }
        condition++;
      }
    }
  }


  public void updateByYears() {
    reDate();
    clear();
    int p = 1;
    int condition = 1;
    for (int i = 0; i < dayLabels[0].length; i++) {
      for (JLabel[] dayLabel : dayLabels) {
        if (condition >= setter() && condition < currentEndOfTheMonth + setter()) {
          dayLabel[i].setVisible(true);
          dayLabel[i].setText(String.valueOf(p));
          p++;
        } else {
          dayLabel[i].setVisible(false);
        }
        condition++;
      }
    }
  }

  public void clear() {
    for (JLabel[] jLabels : dayLabels) {
      for (int j = 0; j < dayLabels[0].length; j++) {
        jLabels[j].setText("");
      }
    }
  }

  public int setter() {
    return switch (startWeekdays) {
      case "MONDAY" -> 2;
      case "TUESDAY" -> 3;
      case "WEDNESDAY" -> 4;
      case "THURSDAY" -> 5;
      case "FRIDAY" -> 6;
      case "SATURDAY" -> 7;
      case "SUNDAY" -> 1;
      default -> 0;
    };
  }

  public void emptyborder() {
    cMonth.setBorder(null);
    repaint();
  }
}
