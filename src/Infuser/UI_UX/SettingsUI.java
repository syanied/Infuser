package Infuser.UI_UX;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Settings Menu.
 *
 * @author Syanide.
 */

public class SettingsUI extends JPopupMenu implements ActionListener {

    private final JMenuItem frameBackgroundColor;
    private final JMenuItem frameTextColor;
    private final JMenuItem promptBackgroundColor;
    private final JMenuItem promptTextColor;
    private final JMenuItem promptScrollBarColor;
    private final JMenuItem InputAreaTextColor;
    private final JMenuItem closeButtonColor;
    private final JMenuItem minimizeButtonColor;
    private final JLayeredPane frame;
    private final JButton button;

    public SettingsUI(@Nullable JLayeredPane frame, JButton button) throws IOException {

        this.frame = frame;
        this.button = button;

        Path path = Paths.get("data.ins");
        JMenuItem[] frameMenuItem, promptMenuItem, cbButtonsMenuItem, menuItems;

        setBackground(new Color(Integer.parseInt(Files.readAllLines(path).get(6)), true));

        JLabel settingsLabel = new JLabel(" Settings");
        JLabel dummyLabel = new JLabel(" ");
        dummyLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 5));
        settingsLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        settingsLabel.setForeground(new Color(Integer.parseInt(Files.readAllLines(path).get(3)), true));
        add(settingsLabel);
        add(dummyLabel);

        frameBackgroundColor = new JMenuItem("Frame Color");
        frameTextColor = new JMenuItem("Frame Text Color");

        frameMenuItem = new JMenuItem[]{frameBackgroundColor, frameTextColor};

        for(JMenuItem item : frameMenuItem) {
            add(item);
            item.addActionListener(this);
        }
        JSeparator separator = new JSeparator();
        separator.setBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt(Files.readAllLines(path).get(2)), true), 1));
        add(separator);


        promptBackgroundColor = new JMenuItem("Input/Prompt Area Color");
        promptTextColor = new JMenuItem("Prompt Area Text Color");
        promptScrollBarColor = new JMenuItem("Prompt Area ScrollBar Color");
        InputAreaTextColor = new JMenuItem("Input Area Text Color");

        promptMenuItem = new JMenuItem[]{promptBackgroundColor, promptTextColor, InputAreaTextColor, promptScrollBarColor};

        for(JMenuItem item : promptMenuItem) {
            add(item);
            item.addActionListener(this);
        }
        JSeparator separator2 = new JSeparator();
        separator2.setBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt(Files.readAllLines(path).get(2)), true), 1));
        add(separator2);


        closeButtonColor = new JMenuItem("Close Button Color");
        minimizeButtonColor = new JMenuItem("Minimize Button Color");

        cbButtonsMenuItem = new JMenuItem[]{closeButtonColor, minimizeButtonColor};

        for(JMenuItem item : cbButtonsMenuItem) {
            add(item);
            item.addActionListener(this);
        }

        menuItems = new JMenuItem[]{frameBackgroundColor, frameTextColor, promptBackgroundColor,
                promptTextColor, closeButtonColor, minimizeButtonColor, promptScrollBarColor, InputAreaTextColor};

        for(JMenuItem item : menuItems) {
            item.setBackground(new Color(Integer.parseInt(Files.readAllLines(path).get(6)), true));
            item.setForeground(new Color(Integer.parseInt(Files.readAllLines(path).get(3)), true));
            item.setBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt(Files.readAllLines(path).get(6)), true), 2));
        }

        JLabel dummyLabel2 = new JLabel(" ");
        JLabel versionLabel = new JLabel("   Build Version: x1.0 [BETA]");
        versionLabel.setForeground(new Color(Integer.parseInt(Files.readAllLines(path).get(3)), true));
        versionLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 8));
        dummyLabel2.setFont(new Font("Yu Gothic UI", Font.BOLD, 5));
        add(dummyLabel2);
        add(versionLabel);

        setBorder(BorderFactory.createLineBorder(Color.black, 1));

    }

    public void showSettingsMenu() {
        show(frame, button.getX() + 35, button.getY() - 222);
    }

    public void changeSettingsMenuTextColor(Color color) {

        setForeground(color);
        repaint();
    }


    @Override
    public void actionPerformed(@NotNull ActionEvent e) {

        Color color;

        if(e.getSource() == frameBackgroundColor) {

            try {
                color = JColorChooser.showDialog(null, "Change background Color of the frame.", Color.CYAN);
                InfuserUI.setInfuserInterfaceColor(color);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == frameTextColor) {

            try {
                color = JColorChooser.showDialog(frameTextColor, "Change frame text Color.", Color.CYAN);
                InfuserUI.setInfuserInterfaceTextColor(color);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        if(e.getSource() == promptBackgroundColor) {

            try {
                color = JColorChooser.showDialog(promptBackgroundColor, "Change Prompt/TextField Background Color.", Color.CYAN);
                InfuserUI.setPromptTextFieldBackgroundColor(color);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        if(e.getSource() == promptTextColor) {

            try {
                color = JColorChooser.showDialog(promptBackgroundColor, "Change Prompt area text Color.", Color.CYAN);
                InfuserUI.setPromptTextColor(color);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        if(e.getSource() == promptScrollBarColor) {

            try {
                color = JColorChooser.showDialog(promptBackgroundColor, "Change Prompt ScrollBar Color.", Color.CYAN);
                InfuserUI.setScrollBarForeground(color);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        if(e.getSource() == InputAreaTextColor) {

            try {
                color = JColorChooser.showDialog(promptBackgroundColor, "Change Input area text Color.", Color.CYAN);
                InfuserUI.setTextFieldTextColor(color);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        if(e.getSource() == closeButtonColor) {

            try {
                color = JColorChooser.showDialog(promptBackgroundColor, "Change Close Button Color.", Color.CYAN);
                InfuserUI.setCloseButtonColor(color);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        if(e.getSource() == minimizeButtonColor) {

            try {
                color = JColorChooser.showDialog(promptBackgroundColor, "Change Minimize Button Color.", Color.CYAN);
                InfuserUI.setMinimizeButtonColor(color);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
