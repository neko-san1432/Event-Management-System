package javad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class test extends JFrame {
  static int numberofguest = 0, counter = 0;
  static HashMap<Integer, JTextField> name = new HashMap<>();
  static HashMap<Integer, JTextField> email = new HashMap<>();
  static HashMap<Integer, JButton> del = new HashMap<>();
  static ArrayList<Integer> index = new ArrayList<>(),remove = new ArrayList<>();
  JButton add = new JButton();

  public test() {
    setLocationRelativeTo(null);
    setLayout(null);
    setSize(500, 500);
    properties();
    add(email.get(0));
    add(name.get(0));
    add(add);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new test().setVisible(true));
  }

  void properties() {
    add.setSize(20, 10);
    add.setLocation(250, 250);
    name.put(numberofguest, new JTextField(String.valueOf(counter)));
    name.get(0).setSize(30, 10);
    name.get(0).setLocation(0, 0);
    email.put(numberofguest, new JTextField(String.valueOf(counter)));
    email.get(0).setSize(30, 10);
    email.get(0).setLocation(30, 0);
    index.add(0);
    numberofguest++;
    counter++;
    add.addActionListener(_ -> {
      final int k = numberofguest-1;
      name.put(numberofguest, new JTextField(String.valueOf(counter)));
      email.put(numberofguest, new JTextField(String.valueOf(counter)));
      name.get(numberofguest).setSize(name.get(numberofguest - 1).getWidth(), name.get(numberofguest - 1).getHeight());
      email.get(numberofguest).setSize(email.get(numberofguest - 1).getWidth(), email.get(numberofguest - 1).getHeight());
      name.get(numberofguest).setLocation((int) name.get(numberofguest - 1).getLocation().getX(), (int) name.get(numberofguest - 1).getLocation().getY() + 10);
      email.get(numberofguest).setLocation((int) email.get(numberofguest - 1).getLocation().getX(), (int) email.get(numberofguest - 1).getLocation().getY() + 10);
      index.add(counter);
      counter++;
      del.put(numberofguest - 1, new JButton());
      del.get(numberofguest - 1).setSize(20, 10);
      del.get(numberofguest - 1).setLocation((int) email.get(numberofguest - 1).getLocation().getX() * 2, (int) email.get(numberofguest - 1).getLocation().getY());
      del.get(numberofguest - 1).addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          remove(name.get(index.get(k)));
          remove(email.get(index.get(k)));
          remove(del.get(index.get(k)));
          remove.add(index.get(k));
          System.out.println("HIT");
          for(int i = k ; i<email.size()-1/*&&remove.contains(index.get(i))*/; i++){
            del.get(i).setLocation(del.get(i).getX(),del.get(i).getY()-10);
            email.get(i).setLocation(email.get(i).getX(),email.get(i).getY()-10);
            name.get(i).setLocation(name.get(i).getX(),name.get(i).getY()-10);
            System.out.println(i);
//            repaint();
          }
          email.get(email.size()-1).setLocation(email.get(email.size()-1).getX(),email.get(email.size()-1).getY()-10);
          name.get(name.size()-1).setLocation(name.get(name.size()-1).getX(),name.get(name.size()-1).getY()-10);
          repaint();
        }
      });
      addItem();
      numberofguest++;
      repaint();

    });
  }
  void checker(JButton j)
  {

  }//  static class delete extends JButton implements ActionListener {
//    final int toBeDelete = numberofguest;
//    @Override
//    public void actionPerformed(ActionEvent e) {
//      del.remove(toBeDelete-1);
//      name.remove(toBeDelete-1);
//      email.remove(toBeDelete-1);
//      repaint();
//      System.out.println(numberofguest);
//      numberofguest--;
//    }
//  }

  void addItem() {
    add(name.get(numberofguest));
    add(email.get(numberofguest));
    add(del.get(numberofguest - 1));
  }
}

