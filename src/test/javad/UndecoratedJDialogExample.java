import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UndecoratedJDialogExample {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }

  private static void createAndShowGUI() {
    JFrame frame = new JFrame("Main Frame");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);
    frame.setLocationRelativeTo(null);

    JButton showDialogButton = new JButton("Show Undecorated Dialog");
    showDialogButton.addActionListener(e -> showUndecoratedDialog(frame));

    frame.getContentPane().add(showDialogButton, BorderLayout.CENTER);
    frame.setVisible(true);
  }

  private static void showUndecoratedDialog(JFrame owner) {
    JDialog dialog = new JDialog(owner, "Undecorated Dialog", true);
    dialog.setUndecorated(true); // Remove the title bar (toolbar)

    JPanel panel = new JPanel();
    panel.setLayout(/*new BoxLayout(panel, BoxLayout.Y_AXIS)*/null);
    panel.setBorder(new LineBorder(Color.black));
    panel.add(new JLabel("This is an undecorated dialog."));
//    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(e -> dialog.dispose());
    panel.add(closeButton);

    dialog.getContentPane().add(panel);
    dialog.pack();
    dialog.setLocationRelativeTo(owner);
    dialog.setVisible(true);
  }
}
