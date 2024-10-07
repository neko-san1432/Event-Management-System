package javad;

import javax.swing.*;
import java.awt.*;

public class test2 extends JFrame {
  String[] col=new String[]{"a", "b","c","d","e"};
  Object[][] data= new Object[][]{{1,2,3,0,0}, {3,4,5,null,0}, {5,6,7,0,0}, {7,8,9,0,0}, {9,0,1,0,0}};
  JTable b = new JTable(data,col);
  JPanel c= new JPanel();
  Scrollbar sb = new Scrollbar(Scrollbar.VERTICAL);
  {

  }
  JScrollPane a = new JScrollPane(b);
  public test2() {
    setProperties();
    c.setSize(500,1000);
//    c.setAutoscrolls(true);
    c.setLayout(null);
    c.add(b);
    setSize(500, 500);
    setLocationRelativeTo(null);
    setLayout(null);
    c.add(b);
    a.add(sb);
    add(b);

  }
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      new test2().setVisible(true);
    });
  }

  public void setProperties() {
    a.setSize(500, 500);
    b.setSize(200, 200);
    b.setLocation(0,200);
  }
}
