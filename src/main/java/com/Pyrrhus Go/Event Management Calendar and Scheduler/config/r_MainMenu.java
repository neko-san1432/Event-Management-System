import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

//event info
public class r_MainMenu extends JPanel {
  static JLabel date = new JLabel("--/--/----");
  String[] dateToBook;
  static JButton book = new JButton("BOOK");
  static JDialog booking = new JDialog();
  static Cashier c;
  static JScrollPane jsp = new JScrollPane(c);
  public r_MainMenu(){
    setProperties();
    setLayout(null);
    setBackground(Color.white);
    setOpaque(true);
    setSize(160,550);
    setBorder(BorderFactory.createMatteBorder(0,1,0,0,Color.black));
    add(date);
    add(book);
  }
  public void setProperties(){
    date.setSize(100,40);
    date.setLocation(45,75);
    date.setHorizontalAlignment(JLabel.CENTER);
    date.setVerticalAlignment(JLabel.CENTER);
    date.setFont(new Font("Aptos", Font.BOLD, 14));
    for (int i = 0; i < MainMenu.venues.length;i++){
      MainMenu.venues[i].setSize(160,20);
      MainMenu.venues[i].setLocation(20,150+(i*20));
      MainMenu.venues[i].setFont(new Font("Aptos", Font.PLAIN, 11));
      MainMenu.venues[i].setVisible(false);
      add(MainMenu.venues[i]);
    }
    book.setVisible(false);
    book.setBorder(new LineBorder(Color.black, 3));
    book.setForeground(Color.black);
    book.setBackground(Color.white);
    book.setLocation(50,380);
    book.setSize(70,40);
    book.setFont(new Font("Aptos", Font.BOLD, 14));
    book.addActionListener(e->{
      dateToBook = date.getText().split("/");
      JLabel b = new JLabel("BOOKING");
      b.setSize(100,100);
      b.setLocation(217,0);
      booking.setSize(new Dimension(535,550));
      c = new Cashier(MainMenu.availableVenues);
      jsp = new JScrollPane(c,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      jsp.setSize(460,400);
      jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      jsp.setLocation(35,75);
      booking.setLayout(null);
      booking.add(jsp);
      booking.add(Cashier.exit);
      booking.getContentPane().setBackground(Color.black);
      booking.setMixingCutoutShape(new RoundRectangle2D.Double(0,0, booking.getWidth(),booking.getHeight(),20,20));
      booking.setLocationRelativeTo(MainFrame.mainMenu);
      c.setLocation(50,75);
      b.setFont(new Font("Aptos", Font.PLAIN, 20));
      b.setForeground(Color.WHITE);
      booking.add(b);
      booking.setModal(true);

      booking.setUndecorated(true);
      booking.setVisible(true);
    });
    book.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        book.setBorder(new LineBorder(Color.black, 3));
        book.setForeground(Color.white);
        book.setBackground(Color.black);
      }
      @Override
      public void mouseExited(MouseEvent e) {
        book.setBorder(new LineBorder(Color.black, 3));
        book.setForeground(Color.black);
        book.setBackground(Color.white);
      }
    });
  }

}
