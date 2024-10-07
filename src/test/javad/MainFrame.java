package javad;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainFrame extends JFrame {

  private AccountInfosPanel accountInfos;

  public MainFrame(String userName) {
    // Initialize JFrame
    setSize(1000, 600);
    setLayout(null);
    setProperties();
    setLocationRelativeTo(null);

    // Initialize and add other components
    JLabel l_mainMenu = new JLabel("Main Menu");
    l_mainMenu.setSize(183, 50); // Set size for layout purposes
    add(l_mainMenu);
    l_mainMenu.setLocation(0, 0);

    JPanel mainMenu = new JPanel();
    mainMenu.setBorder(new LineBorder(Color.black));
    mainMenu.setSize(483, 600); // Set size for layout purposes
    add(mainMenu);
    mainMenu.setLocation(183, 0);

    JLabel r_mainMenu = new JLabel("Right Menu");
    r_mainMenu.setSize(334, 50); // Set size for layout purposes
    add(r_mainMenu);
    r_mainMenu.setLocation(666, 0);

    // Initialize accountInfos
    accountInfos = new AccountInfosPanel();
    accountInfos.setPreferredSize(new Dimension(800, 1000)); // Ensuring it is larger than JScrollPane

    // Create and configure JScrollPane
    JScrollPane jScrollPane = new JScrollPane(accountInfos);
    jScrollPane.setPreferredSize(new Dimension(800, 550));

    // Create a container panel and add the JScrollPane to it
    JPanel containerPanel = new JPanel();
    containerPanel.setBorder(new LineBorder(Color.black));
    containerPanel.setLayout(null); // Set null layout for absolute positioning
    containerPanel.setBounds(183, 0, 800, 550); // Set bounds for the container panel
    containerPanel.add(jScrollPane);
    jScrollPane.setBounds(0, 0, 800, 550); // Set bounds for the JScrollPane

    // Add container panel to the JFrame
    add(containerPanel);

    // Set current user method call (assuming it's defined)
    setCurrentUser(userName);

    // Make JFrame visible
    setVisible(true);
  }

  private void setProperties() {
    // Define setProperties() method (e.g., set JFrame properties, etc.)
  }

  private void setCurrentUser(String userName) {
    // Define setCurrentUser method
  }

  public static void main(String[] args) {
    // Create and show the main frame
    new MainFrame("exampleUser");
  }
}

class AccountInfosPanel extends JPanel {
  public AccountInfosPanel() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    for (int i = 0; i < 20; i++) {
      add(new JLabel("Account Info " + i));
    }
  }
}
