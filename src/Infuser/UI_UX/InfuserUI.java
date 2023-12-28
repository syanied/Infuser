package Infuser.UI_UX;

import Infuser.UI_UX.Custom_UI.ScrollBarUI;
import Infuser.Utils.DataFileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main interface of the Application.
 *
 * @author Syanide.
 */

public class InfuserUI implements MouseListener {

  private static JFrame infuserInterface;
  private static JLabel closeLabel;
  private final JSeparator headerSeparator;
  private static JPanel draggablePanel;
  private static JLabel appNameLabel;
  private static JTextArea promptArea;
  private static JTextField textField;
  private final JLayeredPane panel;
  private static JButton settingsButton;
  private final JScrollPane scrollPane;
  private static JLabel minimizeLabel;
  private static JButton donateButton;
  private static JLabel infoButton;
  private Path path;

    public InfuserUI() {

        infuserInterface = new JFrame("Infuser "+ "x1.0");
        closeLabel = new JLabel("X");
        headerSeparator = new JSeparator();
        appNameLabel = new JLabel("INFUSER "+ "x1.0");
        promptArea = new JTextArea("");
        textField = new JTextField("");
        settingsButton = new JButton("⚙");
        scrollPane = new JScrollPane(promptArea);
        draggablePanel = new JPanel();
        minimizeLabel = new JLabel("_");
        donateButton = new JButton("😍");
        infoButton = new JLabel("❓");
        panel = new JLayeredPane();
    }

    public void initComponents() throws IOException {

        path = Paths.get("data.ins");

        JComponent[] components = {headerSeparator, appNameLabel, closeLabel, settingsButton, textField, minimizeLabel, donateButton, infoButton, draggablePanel};
        for(JComponent i : components){
            i.setBackground(new Color(Integer.parseInt(Files.readAllLines(path).get(1)), true));
            i.setForeground(new Color(Integer.parseInt(Files.readAllLines(path).get(3)), true));
            panel.add(i);
            i.addMouseListener(this);
        }

        appNameLabel.setFont(new Font("Orbitron", Font.BOLD, 10));
        closeLabel.setForeground(new Color(Integer.parseInt(Files.readAllLines(path).get(4)), true));
        JLabel[] labels = {closeLabel, minimizeLabel};
        for(JLabel l : labels) {
            l.setFont(new Font("Calibri", Font.BOLD, 11));
            l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        minimizeLabel.setForeground(new Color(Integer.parseInt(Files.readAllLines(path).get(5)), true));
        JButton[] buttons = {settingsButton, donateButton};
        for(JButton b : buttons) {
            b.setFont(new Font("", Font.BOLD, 18));
            b.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            b.setBorderPainted(false);
            b.setFocusPainted(false);
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        draggablePanel.addMouseMotionListener(new MouseMotionListener() {
            int x, y;

            @Override
            public void mouseDragged(MouseEvent e) {

                int xCords = e.getXOnScreen();
                int yCords = e.getYOnScreen();

                infuserInterface.setLocation(new Point(xCords - x, yCords - y));
            }

            @Override
            public void mouseMoved(MouseEvent e) {

                x = e.getX();
                y = e.getY();
            }
        });

        headerSeparator.setForeground(Color.BLACK);

        textField.setBackground(new Color(Integer.parseInt(Files.readAllLines(path).get(6)), true));
        textField.setForeground(new Color(Integer.parseInt(Files.readAllLines(path).get(9)), true));
        textField.setSelectionColor(new Color(Integer.parseInt(Files.readAllLines(path).get(9)), true));
        donateButton.setFont(new Font("", Font.BOLD, 15));
        infoButton.setFont(new Font("", Font.BOLD, 15));
        infoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        textField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        textField.setForeground(new Color(Integer.parseInt(Files.readAllLines(path).get(3)), true));
        promptArea.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        promptArea.setBackground(new Color(Integer.parseInt(Files.readAllLines(path).get(6)), true));
        promptArea.setForeground(new Color(Integer.parseInt(Files.readAllLines(path).get(7)), true));
        promptArea.setSelectionColor(new Color(Integer.parseInt(Files.readAllLines(path).get(7)), true));
        scrollPane.setBackground(new Color(Integer.parseInt(Files.readAllLines(path).get(6)), true));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black, 0));
        scrollPane.getVerticalScrollBar().setUI(ScrollBarUI.basicScrollBarUI());

        promptArea.setLineWrap(true);
        promptArea.setAutoscrolls(true);
        promptArea.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        promptArea.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        textField.setBounds(23, 440, 880, 50);
        headerSeparator.setBounds(1, 15, 1000, 25);
        appNameLabel.setBounds(2, -2, 100, 25);
        closeLabel.setBounds(919, -2, 10, 25);
        settingsButton.setBounds(-12, 491, 50, 30);
        scrollPane.setBounds(23, 30, 880, 400);
        minimizeLabel.setBounds(903, -5, 10, 25);
        donateButton.setBounds(889, 491, 50, 30);
        infoButton.setBounds(907, 24, 40, 30);
        draggablePanel.setBounds(2, -2, 1000, 15);
        panel.setBounds(0,0,929, 521);

        panel.add(scrollPane);
        infuserInterface.add(panel);

        infuserInterface.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        infuserInterface.setLayout (null);
        infuserInterface.setSize(929, 521);
        infuserInterface.setUndecorated(true);
        infuserInterface.setShape(new RoundRectangle2D.Double(-1, -1, 929, 521, 15, 15));
        infuserInterface.getContentPane().setBackground(new Color(Integer.parseInt(Files.readAllLines(path).get(1)), true));
        infuserInterface.setFocusable(true);
        infuserInterface.setResizable(false);
        infuserInterface.setLocationRelativeTo(null);
        infuserInterface.setVisible(true);
    }

    public static JTextArea getPromptArea() {
        return promptArea;
    }

    public static JTextField getTextField() {
        return textField;
    }

    static final SettingsUI ui;

    static {
        try {
            ui = new SettingsUI(null, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setInfuserInterfaceColor(Color color) throws IOException {

        if(!(color == null)) {

            infuserInterface.getContentPane().setBackground(color);
            draggablePanel.setBackground(color);
            settingsButton.setBackground(color);
            donateButton.setBackground(color);

            draggablePanel.repaint();
            settingsButton.repaint();
            donateButton.repaint();
            infuserInterface.repaint();

            DataFileUtil.writeToDataFile(String.valueOf(color.getRGB()), 1);
        }
    }

    public static void setInfuserInterfaceTextColor(Color color) throws IOException {

        if(!(color == null)) {

            appNameLabel.setForeground(color);
            settingsButton.setForeground(color);
            donateButton.setForeground(color);
            infoButton.setForeground(color);

            ui.changeSettingsMenuTextColor(color);

            settingsButton.repaint();
            donateButton.repaint();
            infoButton.repaint();
            appNameLabel.repaint();

            DataFileUtil.writeToDataFile(String.valueOf(color.getRGB()), 3);

        }
    }

    public static void setPromptTextColor(Color color) throws IOException {

        if(!(color == null)) {

            promptArea.setForeground(color);
            promptArea.setSelectionColor(color);
            promptArea.repaint();

            DataFileUtil.writeToDataFile(String.valueOf(color.getRGB()), 7);

        }
    }

    public static void setPromptTextFieldBackgroundColor(Color color) throws IOException {

        if(!(color == null)) {

            promptArea.setBackground(color);
            textField.setBackground(color);

            promptArea.repaint();
            textField.repaint();

            DataFileUtil.writeToDataFile(String.valueOf(color.getRGB()), 6);
        }
    }

    public static void setScrollBarForeground(Color color) throws IOException {

        if(!(color == null)) {

            if(ScrollBarUI.getScrollBar() != null) {

                ScrollBarUI.getScrollBar().setForeground(color);
                ScrollBarUI.getScrollBar().repaint();

                DataFileUtil.writeToDataFile(String.valueOf(color.getRGB()), 8);
            }
        }
    }

    public static void setTextFieldTextColor(Color color) throws IOException {

        if(!(color == null)) {

            textField.setForeground(color);
            textField.setSelectedTextColor(color);
            textField.repaint();

            DataFileUtil.writeToDataFile(String.valueOf(color.getRGB()), 9);
        }
    }

    public static void setCloseButtonColor(Color color) throws IOException {

        if(!(color == null)) {

            closeLabel.setForeground(color);
            closeLabel.repaint();

            DataFileUtil.writeToDataFile(String.valueOf(color.getRGB()), 4);
        }
    }

    public static void setMinimizeButtonColor(Color color) throws IOException {

        if(!(color == null)) {

            minimizeLabel.setForeground(color);
            minimizeLabel.repaint();

            DataFileUtil.writeToDataFile(String.valueOf(color.getRGB()), 5);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        Object source = e.getSource();

        if (closeLabel.equals(source)) {
            System.exit(0);

        } else if(minimizeLabel.equals(source)) {

            infuserInterface.setState(JFrame.ICONIFIED);

        } else if (infoButton.equals(source)) {

            getPromptArea().append("-------------------------------------------------------------------------------------------------------------------------------------------------"+"\n");
            getPromptArea().append("This is a preview of Infuser application version x1.0"+"\n");
            getPromptArea().append("(© Copyright 2023, Syanide, All rights reserved)"+"\n");

            getPromptArea().append("""

                    [INFO] Infuser x1.0 is accessed in form of a Modular system using Plugins.
                    """+"\n");
            getPromptArea().append("""
                    [INFO] You can find these Plugins here [https://discord.gg/d9rFtgkBXK]
                     or you can make one of your own plugin in Java using the application API. [access it in the Discord server]
                    """+"\n");
            getPromptArea().append("""
                    [INFO] Infuser x1.0 does not have a controlled environment such as a Sandbox
                     to limit the usage of the Plugin used during application runtime.
                    """+"\n");
            getPromptArea().append("[*] Use this application at your own risk and discretion."+"\n");
            getPromptArea().append("-------------------------------------------------------------------------------------------------------------------------------------------------"+"\n");


        } else if (settingsButton.equals(source)) {

            try {
                new SettingsUI(panel, settingsButton).showSettingsMenu();
                infuserInterface.repaint();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } else if(donateButton.equals(source)) {
            try {
                Desktop.getDesktop().browse(new URI("https://paypal.me/ShubhamK228"));
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    private Thread donateButtonThread;
    private Thread settingsButtonThread;
    private Thread infoButtonThread;

    @Override
    public void mouseEntered(MouseEvent e) {

        if(e.getSource() == infoButton) {

            infoButtonThread = new Thread(() -> {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    return;
                }

                final JPopupMenu infoButtonPopUp = new JPopupMenu();
                final JLabel label = new JLabel("Info [ℹ]");

                infoButtonPopUp.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                try {
                    infoButtonPopUp.setBackground(new Color(Integer.parseInt(Files.readAllLines(path).get(6)), true));
                    label.setForeground(new Color(Integer.parseInt(Files.readAllLines(path).get(3)), true));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                infoButtonPopUp.add(label);
                infoButtonPopUp.show(panel, infoButton.getX() - 10, infoButton.getY() - 20);

            });
            infoButtonThread.start();

        }

        if(e.getSource() == settingsButton) {

            settingsButtonThread = new Thread(() -> {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    return;
                }

                final JPopupMenu settingsPopUp = new JPopupMenu();
                final JLabel label = new JLabel("Settings");

                settingsPopUp.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                try {
                    settingsPopUp.setBackground(new Color(Integer.parseInt(Files.readAllLines(path).get(6)), true));
                    label.setForeground(new Color(Integer.parseInt(Files.readAllLines(path).get(3)), true));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                settingsPopUp.add(label);
                settingsPopUp.show(panel, settingsButton.getX() + 10, settingsButton.getY() - 20);

            });
            settingsButtonThread.start();

        }

        if(e.getSource() == donateButton) {

             donateButtonThread = new Thread(() -> {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    return;
                }

                final JPopupMenu donatePopUp = new JPopupMenu();
                final JLabel label = new JLabel("Support me!");

                donatePopUp.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                 try {
                     donatePopUp.setBackground(new Color(Integer.parseInt(Files.readAllLines(path).get(6)), true));
                     label.setForeground(new Color(Integer.parseInt(Files.readAllLines(path).get(3)), true));
                 } catch (IOException ex) {
                     throw new RuntimeException(ex);
                 }

                donatePopUp.add(label);
                donatePopUp.show(panel, donateButton.getX() - 10, donateButton.getY() - 20);

            });
            donateButtonThread.start();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        if(e.getSource() == donateButton) {

            donateButtonThread.interrupt();
        }

        if(e.getSource() == settingsButton) {

            settingsButtonThread.interrupt();
        }

        if(e.getSource() == infoButton) {

            infoButtonThread.interrupt();
        }
    }
}

