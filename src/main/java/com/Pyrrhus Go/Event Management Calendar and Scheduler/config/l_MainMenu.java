import com.mysql.cj.jdbc.admin.MiniAdmin;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class l_MainMenu extends JPanel {
  JButton accountInfo = new JButton("ACCOUNT"),home = new JButton("HOME");

  public l_MainMenu(){
    setSize(100,550);
    setOpaque(true);
    setBackground(Color.black);
    setLayout(null);
    setProperties();
    add(MainFrame.logout);
    add(accountInfo);
    add(home);
    add(MainFrame.logout);
    setBorder(new LineBorder(Color.black));
  }
  public void setProperties(){
    MainFrame.logout.setLocation(0,500);
    MainFrame.logout.setFont(new Font("Aptos", Font.PLAIN, 12));
    MainFrame.logout.setBackground(Color.black);
//    MainFrame.logout.setOpaque(true);
    MainFrame.logout.setForeground(Color.white);
//    MainFrame.logout.setContentAreaFilled(false);
    MainFrame.logout.setFocusable(false);
    MainFrame.logout.setBorderPainted(false);
    MainFrame.logout.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseExited(MouseEvent e) {
        MainFrame.logout.setBackground(Color.black);
        MainFrame.logout.setForeground(Color.white);
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        MainFrame.logout.setBackground(Color.white);
        MainFrame.logout.setForeground(Color.black);
      }
    });

//    accountInfo.setOpaque(true);
    accountInfo.setBackground(Color.black);
    accountInfo.setForeground(Color.white);
    accountInfo.setFont(new Font("Aptos", Font.PLAIN, 12));
    accountInfo.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseExited(MouseEvent e) {
        accountInfo.setBackground(Color.black);
        accountInfo.setForeground(Color.white);
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        accountInfo.setBackground(Color.white);
        accountInfo.setForeground(Color.black);
      }
    });
    accountInfo.setFocusable(false);
//    accountInfo.setContentAreaFilled(false);
    accountInfo.setBorderPainted(false);
    accountInfo.setSize(100,40);
    accountInfo.setLocation(0,40);
    accountInfo.addActionListener(e ->{
      MainFrame.mainMenu.setVisible(false);
      MainFrame.r_mainMenu.setVisible(false);
      MainFrame.jp.setVisible(true);
//      JScrollBar js = new JScrollBar();
//      js.setValue(0);
      MainFrame.jScrollPane.getHorizontalScrollBar().setValue(0);
      MainFrame.jScrollPane.getVerticalScrollBar().setValue(0);
      MainFrame.jScrollPane.repaint();
    });

    home.setSize(100,40);
    home.setLocation(0,0);
    home.setFont(new Font("Aptos", Font.PLAIN, 12));
    home.addActionListener(e ->{
      MainFrame.mainMenu.setVisible(true);
      MainFrame.r_mainMenu.setVisible(true);
      MainFrame.jp.setVisible(false);
      repaint();
    }
    );
    home.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseExited(MouseEvent e) {
        home.setBackground(Color.black);
        home.setForeground(Color.white);
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        home.setBackground(Color.white);
        home.setForeground(Color.black);
      }
    });
//    home.setContentAreaFilled(false);
    home.setBorderPainted(false);
    home.setFocusable(false);
//    home.setOpaque(true);
    home.setForeground(Color.white);
    home.setBackground(Color.black);
  }
}
