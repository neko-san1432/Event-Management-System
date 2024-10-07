package javad;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;

public class Calendar extends JFrame {

  int currentIndex = 5;
  String[] month = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"},
  weekdays = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
  int[] daysPerMonth = {Month.valueOf(month[0]).maxLength(),
  Month.valueOf(month[1]).maxLength(), Month.valueOf(month[2]).maxLength(), Month.valueOf(month[3]).maxLength(),
  Month.valueOf(month[4]).maxLength(), Month.valueOf(month[5]).maxLength(), Month.valueOf(month[6]).maxLength(),
  Month.valueOf(month[7]).maxLength(), Month.valueOf(month[8]).maxLength(), Month.valueOf(month[9]).maxLength(),
  Month.valueOf(month[10]).maxLength(), Month.valueOf(month[11]).maxLength()};
  HashMap<String, Integer> dayPerMonth = new HashMap<>();
  JLabel[][] dayLabels = new JLabel[7][6];
  JLabel[] weekendsLabel = new JLabel[7];
  int currentYear = 2024;
  String currentMonth = month[currentIndex];
  JLabel next = new JLabel("next"), back = new JLabel("back"),
  cmonth = new JLabel(currentMonth), nextYear = new JLabel("2025");
  LocalDate localDate = LocalDate.of(currentYear,Month.valueOf(month[currentIndex]),1);
  DayOfWeek dayOfWeek = DayOfWeek.from(localDate);
  String startWeekdays = dayOfWeek.name();
  {
    for (int i = 0; i < 12; i++) {
      dayPerMonth.put(month[i], daysPerMonth[i]);
    }
  }
  int currentEndOfTheMonth = dayPerMonth.get(currentMonth);
  public void reDate(){
    localDate = LocalDate.of(currentYear,Month.valueOf(month[currentIndex]),1);
     dayOfWeek = DayOfWeek.from(localDate);
     startWeekdays = dayOfWeek.name();
  }

  public Calendar() {
    setSize(500, 700);
    setLayout(null);
    for (int i = 0; i < dayLabels.length; i++) {
      for (int j = 0; j < dayLabels[0].length; j++) {
        dayLabels[i][j] = new JLabel();
        dayLabels[i][j].setSize(60, 60);
        dayLabels[i][j].setLocation(i * 60, (j * 60) + 60);
        dayLabels[i][j].setBorder(new LineBorder(Color.black));
        int finalI = i;
        int finalJ = j;
        dayLabels[i][j].addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            System.out.println(dayLabels[finalI][finalJ].getText());
          }
        });
        add(dayLabels[i][j]);
      }
    }
    for (int i = 0; i < weekendsLabel.length; i++) {
      weekendsLabel[i] = new JLabel(weekdays[i]);
      weekendsLabel[i].setSize(60, 60);
      weekendsLabel[i].setLocation(i * 60, 0);
      weekendsLabel[i].setBorder(new LineBorder(Color.black));
      add(weekendsLabel[i]);
    }
    setProperty();
    add(back);
    add(next);
    int p = 1;
    int condition = 1;
    for (int i = 0; i < dayLabels[0].length; i++) {
      for (JLabel[] dayLabel : dayLabels) {
        if (condition >= setter() && condition < currentEndOfTheMonth + setter()) {
          dayLabel[i].setText(String.valueOf(p));
          p++;
        }
        condition++;
      }
    }
    add(nextYear);
    add(cmonth);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Calendar().setVisible(true));
  }



  public void updateForward() {
    if (currentIndex != 11) {
      currentIndex++;
    } else {
      currentIndex = 0;
    }
    reDate();
    clear();
    int p = 1;
    int condition = 1;
    for (int i = 0; i < dayLabels[0].length; i++) {
      for (JLabel[] dayLabel : dayLabels) {
        if (condition >= setter() && condition <= currentEndOfTheMonth + setter()) {
          dayLabel[i].setText(String.valueOf(p));
          p++;
        }
        condition++;
      }
    }
    cmonth.setText(month[currentIndex]);
  }

  public void updateBackward() {
    if (currentIndex != 0) {
      currentIndex--;
    } else {
      currentIndex = 11;
    }
    reDate();
    clear();
    int p = 1;
    int condition = 1;
    for (int i = 0; i < dayLabels[0].length; i++) {
      for (JLabel[] dayLabel : dayLabels) {
        if (condition >= setter() && condition <= currentEndOfTheMonth + setter()) {
          dayLabel[i].setText(String.valueOf(p));
          p++;
        }
        condition++;
      }
    }
    cmonth.setText(month[currentIndex]);
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

  public void setProperty() {
    next.setSize(100, 50);
    back.setSize(100, 50);
    next.setLocation(0, 430);
    back.setLocation(0, 480);
    next.setBorder(new LineBorder(Color.black));
    back.setBorder(new LineBorder(Color.black));
    next.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
//        System.out.println(countBlankTiles());
        updateForward();
      }
    });
    back.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        updateBackward();
      }
    });
    nextYear.setSize(100, 50);
    nextYear.setLocation(0, 520);
    nextYear.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        updateForwardYears();
      }
    });
    cmonth.setSize(100, 100);
    cmonth.setLocation(100, 520);
  }

  public void updateForwardYears() {
    currentYear++;
    reDate();
    clear();
    int p = 1;
    int condition = 1;
    for (int i = 0; i < dayLabels[0].length; i++) {
      for (JLabel[] dayLabel : dayLabels) {
        if (condition >= setter() && condition <= currentEndOfTheMonth + setter()) {
          dayLabel[i].setText(String.valueOf(p));
          p++;
        }
        condition++;
      }
    }
    cmonth.setText(month[currentIndex]);
  }

  public void updateBackwardYears() {
    currentYear--;
    reDate();
    clear();
    int p = 1;
    int condition = 1;
    for (int i = 0; i < dayLabels[0].length; i++) {
      for (JLabel[] dayLabel : dayLabels) {
        if (condition >= setter() && condition <= currentEndOfTheMonth + setter()) {
          dayLabel[i].setText(String.valueOf(p));
          p++;
        }
        condition++;
      }
    }
  }
}