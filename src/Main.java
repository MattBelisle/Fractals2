/**
 * Created by Matt on 10/24/2015.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
        public static void main(String[] args) {
            JFrame jf = new JFrame("Mandelbrot");
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            final GraphicsPanel f = new GraphicsPanel();
            jf.setContentPane(f);
            jf.pack();
            jf.setVisible(true);
            jf.setBackground(Color.white);
            jf.setMinimumSize(new Dimension(1920, 1080));
            jf.setResizable(false);
            jf.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    Globals.MouseX = e.getX();
                    Globals.MouseY = e.getY();
                    System.out.println(Globals.MouseX + " " + Globals.MouseY);
                    if(e.getButton() == MouseEvent.BUTTON1&& e.getX()<=900&& e.getY()<=900)
                    {
                        f.ZoomandEnhance(1);
                    }
                    else if(e.getButton() == MouseEvent.BUTTON3&& e.getX()<=900&& e.getY()<=900)
                    {
                        f.ZoomandEnhance(2);
                    }
                }
            });

        }
    }
