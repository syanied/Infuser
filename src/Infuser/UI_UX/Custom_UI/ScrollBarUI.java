package Infuser.UI_UX.Custom_UI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Basic ScrollBar UI implementation.
 *
 * @author Syanide.
 */

public class ScrollBarUI extends BasicScrollBarUI {
    private static JScrollBar sb;
    private static Path path;

    public static BasicScrollBarUI basicScrollBarUI() {

        path = Paths.get("data.ins");

        return new BasicScrollBarUI() {

           private final Dimension d = new Dimension();

           @Override
           protected JButton createDecreaseButton(int orientation) {
               return new JButton() {

                   @Override
                   public Dimension getPreferredSize() {
                       return d;
                   }
               };
           }

           @Override
           protected JButton createIncreaseButton(int orientation) {
               return new JButton() {

                   @Override
                   public Dimension getPreferredSize() {
                       return d;
                   }
               };
           }

           @Override
           protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
           }

           @Override
           protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
               Graphics2D g2 = (Graphics2D) g.create();
               g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
               Color color;
               sb = (JScrollBar) c;

               try {
                   sb.setBackground(new Color(Integer.parseInt(Files.readAllLines(path).get(6)), true));
                   sb.setBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt(Files.readAllLines(path).get(8)), true), -1));
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
               if (!sb.isEnabled() || r.width > r.height) {
                   return;
               } else if (isDragging) {
                   try {
                       color = new Color(Integer.parseInt(Files.readAllLines(path).get(8)), true);
                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }
               } else if (isThumbRollover()) {
                   try {
                       color = new Color(Integer.parseInt(Files.readAllLines(path).get(8)), true);
                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }
               } else {
                   try {
                       color = new Color(Integer.parseInt(Files.readAllLines(path).get(8)), true);
                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }
               }
               g2.setPaint(color);
               g2.fillRoundRect(r.x, r.y, r.width, r.height, 10, 10);
               try {
                   g2.setPaint(new Color(Integer.parseInt(Files.readAllLines(path).get(6)), true));
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
               g2.drawRoundRect(r.x, r.y, r.width, r.height, 10, 10);
               g2.dispose();
           }

           @Override
           protected void setThumbBounds(int x, int y, int width, int height) {
               super.setThumbBounds(x, y, width, height);
               scrollbar.repaint();
           }
       };
    }

    public static JScrollBar getScrollBar() {
        return sb;
    }
}
