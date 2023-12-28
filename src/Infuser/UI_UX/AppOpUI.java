package Infuser.UI_UX;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

/**
 * Represents the App opening UI.
 *
 * @author Syanide.
 */

public class AppOpUI {

    private final JFrame openingUI = new JFrame();
    private final JProgressBar bar = new JProgressBar();
    private final JLabel loadingLabel = new JLabel("starting up...");

    public AppOpUI() {

        JLabel applicationNameLabel = new JLabel("INFUSER");
        applicationNameLabel.setOpaque(false);
        applicationNameLabel.setBounds (62, 35, 300, 63);
        applicationNameLabel.setFont(new Font("Orbitron", Font.BOLD, 55));
        applicationNameLabel.setForeground(new Color(253, 253, 253, 255));

        loadingLabel.setBounds(3, 101, 100, 25);
        loadingLabel.setForeground(new Color(253, 253, 253, 255));

        JLabel versionLabel = new JLabel("x1.0");
        versionLabel.setOpaque(false);
        versionLabel.setBounds (285, 85, 100, 25);
        versionLabel.setFont(new Font("Orbitron", Font.BOLD, 25));
        versionLabel.setForeground(getThemeColor());

        bar.setValue(0);
        bar.setOpaque(false);
        bar.setStringPainted(false);
        bar.setForeground(Objects.equals(getThemeColor(), new Color(73, 220, 179, 255)) ? new Color(40, 204, 86, 255) : getThemeColor());
        bar.setBackground(new Color(54, 54, 54, 255));
        bar.setBounds(1, 124, 600, 10);

        openingUI.getContentPane().add(applicationNameLabel);
        openingUI.getContentPane().add(versionLabel);
        openingUI.getContentPane().add(bar);
        openingUI.getContentPane().add (loadingLabel);

        openingUI.setLayout (null);
        openingUI.setSize(411, 130);
        openingUI.setUndecorated(true);
        openingUI.setBackground(new Color(1.0f,1.0f,1.0f,0.1f));
        openingUI.setFocusable(true);
        openingUI.setResizable(false);
        openingUI.setLocationRelativeTo(null);
        openingUI.setVisible(true);

        progressBar();
    }

    private Color getThemeColor() {

        Color themeColor;

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new URL("https://pastebin.com/raw/5ryBGe73").openStream()
            ));

            themeColor = Color.decode(reader.readLine());

        }catch (Exception e) {

            themeColor = new Color(73, 220, 179, 255);
        }
        
        return themeColor;
    }

    private void progressBar() {

        int counter = 0;
        while (counter < 150){
            bar.setValue(counter);
            try{
                Thread.sleep(16);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            counter++;
            if(counter == 100) {
                loadingLabel.setText("finalizing...");
                loadingLabel.setForeground(Objects.equals(getThemeColor(), new Color(73, 220, 179, 255)) ? new Color(58, 255, 164, 255) : getThemeColor());
            }
        }
        openingUI.dispose();
    }
}
