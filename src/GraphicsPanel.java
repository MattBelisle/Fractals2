/**
 * Created by Matt on 10/24/2015.
 */

import org.apache.commons.math3.complex.Complex;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

class GraphicsPanel extends JPanel {

    public GraphicsPanel() {
        // Method of JComponent
        setBackground(Color.black);
        setPreferredSize(new Dimension(Globals.width, Globals.height));
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("painting");
        //super.paintComponent(g);
        //dragon(g);
        //Derivatives.getEquation();
        //Mandelbrot_Julia(g, false);
        Man_Julia_gradient(g);
        //Mandelbrot_Julia_Coloured(g,false);
    }

    /**
     * creates a mandelbrot and julia set COLOURED
     *
     * @param g-  graphics
     * @param Man - true is mandelbrot and false is julia set
     */
    public void Mandelbrot_Julia_Coloured(Graphics g, Boolean Man) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        setBackground(Color.white);
        //System.out.println(epsilon);
        double ComplexX = Globals.complexX;
        // so the array is only calculated ONCE per zoom should speed up subsequent paints
        if (Globals.CalcDone == false) {
            for (int column = 0; column < Globals.width; column++) {
                ComplexX += Globals.epsilonX;
                double ComplexY = Globals.complexY;
                //System.out.println(column);
                //System.out.println(ComplexX);
                for (int row = 0; row < Globals.height; row++) {
                    ComplexY -= Globals.epsilonY;
                    int iterations;
                    Complex C;
                    if (Man == true) {
                        C = new Complex(ComplexX, ComplexY);
                    } else {
                        //C = new Complex(0.2, -0.4);
                        //C=new Complex(-0.4,0.6);
                        //C = new Complex(0.285,0.01);
                        //C = new Complex(-0.40,0.65);
                        //C = new Complex(-0.52,0.57);
                        //C = new Complex(0.321,0.456);
                        //C = new Complex(-0.8,0.156);
                        C = new Complex(-0.75, 0.0);
                    }

                    iterations = Calculations.Mandelbrot_Set(ComplexX, ComplexY, C);
                    Globals.IterationCount[column][row] = iterations;
                }
            }
            Globals.CalcDone = true;

        }
        if (Globals.CalcDone == true) {
            for (int column = 0; column < Globals.width; column++) {
                System.out.println(column);
                for (int row = 0; row < Globals.height; row++) {
                    int iterations = Globals.IterationCount[column][row];
                    Colouring.Escape_TimeV2(iterations);
                    Color colour = new Color(Globals.red, Globals.green, Globals.blue);
                    g2d.setColor(colour);
                    g2d.fill(new Rectangle2D.Double(column, row, 1, 1));
                    Globals.red = 0;
                    Globals.green = 0;
                    Globals.blue = 0;
                }
            }
        }
    }

    /**
     * where the making of a uncoloured mandelbrot or julia set is made
     *
     * @param g   - graphics
     * @param Man - Whether its man or julia false is Julia true is Man
     */
    public void Mandelbrot_Julia(Graphics g, Boolean Man) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        setBackground(Color.white);
        //System.out.println(epsilon);
        double ComplexX = Globals.complexX;
        // so the array is only calculated ONCE per zoom should speed up subsequent paints
        if (Globals.CalcDone == false) {
            for (int column = 0; column < Globals.width; column++) {
                ComplexX += Globals.epsilonX;
                double ComplexY = Globals.complexY;
                for (int row = 0; row < Globals.height; row++) {
                    ComplexY -= Globals.epsilonY;
                    int Check;
                    Complex C;
                    if (Man == true) {
                        C = new Complex(ComplexX, ComplexY);
                    } else {
                        C = new Complex(0.2, -0.4);
                    }
                    Check = Calculations.Mandelbrot_Set_Black_And_White(ComplexX, ComplexY, C);
                    Globals.IterationCount[column][row] = Check;
                }
            }
            Globals.CalcDone = true;
        }
        if (Globals.CalcDone == true) {
            for (int column = 0; column < Globals.width; column++) {
                for (int row = 0; row < Globals.height; row++) {
                    g2d.setColor(Color.BLACK);
                    if (Globals.IterationCount[column][row] == 1) {
                        g2d.fill(new Rectangle2D.Double(column, row, 1, 1));
                    } else {
                        g2d.setColor(Color.WHITE);
                        g2d.fill(new Rectangle2D.Double(column, row, 1, 1));
                    }
                }
            }
        }

    }

    /**
     * WIP Where a dragon fractal is set up perameters
     *
     * @param g
     */
    public void dragon(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        //0=north,1=east,2=south,3=west
        Globals.xLoc = getWidth() / 2;
        Globals.yLoc = getHeight() / 2;
        for (int c = 1; c < 250000; c++) {
            System.out.println(c);
            Colouring.Dragon(c);
            g2d.setColor(Color.BLUE);
            g2d.drawRect(Globals.xLoc, Globals.yLoc, 1, 1);
        }
    }

    /**
     * WIP to make a newton fractal gets equation derives it and uses newtons
     * method to create a fractal
     *
     * @param g -graphics
     */
    public void Newton(Graphics g) {
        Derivatives.getEquation();
        /*  * A generalization of Newton's iteration is

         z_{n+1}=z_n- a \frac{p(z_n)}{p'(z_n)}

         where a is any complex number.[1] The special choice a=1 corresponds to the Newton fractal.
         The fixed points of this map are stable when a lies inside the disk of radius 1 centered at 1. When a is outside this
         disk, the fixed points are locally unstable, however the map still exhibits a fractal structure in the sense of Julia
         set. If p is a polynomial of degree n, then the sequence z_n is bounded provided that a is inside a disk of radius n
         centered at n.*/
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        setBackground(Color.white);
        //System.out.println(epsilon);
        double ComplexX = Globals.complexX;
        //the A in the equation
        int A = 1;
        for (int column = 0; column < getHeight(); column++) {
            ComplexX += Globals.epsilonX;
            double ComplexY = Globals.complexY;
            // System.out.println(column);
            //System.out.println(ComplexX);
            for (int row = 0; row < getWidth(); row++) {
                ComplexY -= Globals.epsilonY;
                int iterations = 0;
                Colouring.Newton(ComplexX, ComplexY, A);
                Color colour = new Color(Globals.red, Globals.green, Globals.blue);
                g2d.setColor(colour);
                g2d.fill(new Rectangle2D.Double(column, row, 1, 1));
                Globals.red = 0;
                Globals.green = 0;
                Globals.blue = 0;
            }
        }   //System.out.println(ComplexY);
    }

    /**
     * where the zooming work takes place
     *
     * @param n - if its in or out n=1 is in n=2 is out
     */
    public void ZoomandEnhance(int n) {
        //find a new centre point based on where you clicked
        Globals.complexY = Globals.complexY - (Globals.epsilonY * Globals.MouseY);
        Globals.complexX = Globals.complexX + (Globals.epsilonX * Globals.MouseX);
        Globals.epsilonX *= Globals.zoom;
        Globals.epsilonY *= Globals.zoom;
        if (n == 1) {
            Globals.zoom *= 4;
        } else if (n == 2) {
            Globals.zoom /= 4;
        }
        Globals.epsilonX /= Globals.zoom;
        Globals.epsilonY /= Globals.zoom;
        //applies that centre point to create a new box
        //System.out.println(Globals.epsilon);
        Globals.complexX = Globals.complexX - (0.5 * Globals.epsilonX * (Globals.width));

        Globals.complexY = Globals.complexY + (0.5 * Globals.epsilonY * (Globals.width));
        //  System.out.println(Globals.complexX+" "+Globals.complexY);
        Globals.CalcDone = false;
        repaint();
    }

    /**
     * creates concentric circles with gradient color
     *
     * @param g -graphics component
     */
    public void Circles(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //just making sure everything looks smooth gotten tips from web to do this (anti aliasing)
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        int width = getWidth();
        int height = getHeight();

        double count = 0;
        for (double i = 0; i < 2000; i++) {
            Color colour = new Color(Globals.red, Globals.green, Globals.blue);
            g2d.setColor(colour);
            //creates an ellipse (Circle) that moves in total down and to the right
            //the same as it shrinks on one of its variables, creating concentric circles
            Ellipse2D.Double circle = new Ellipse2D.Double(0 + count, 0 + count, height - i, width - i);
            //draw
            g2d.draw(circle);
            //adds 1 half the amount of i
            System.out.println(i);
            count += 0.5;
            if (i < 254) {
                Globals.blue++;
                //green++;
                //red++;
            } else if (i >= 253 && i < 507) {
                Globals.red++;
                //blue++;
                //green++;
            } else if (i >= 506 && i < 760) {
                Globals.green++;
                //red++;
                //blue++;
            } else {

            }
        }
    }

    public void gradient(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //just making sure everything looks smooth gotten tips from web to do this (anti aliasing)
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        int width = getWidth();
        int height = getHeight();
        double percent;
        int finalRed;
        int finalGreen;
        int finalBlue;
        for (double c = 1.0; c < 255.0; c += 1.0) {
            Globals.red = 255;
            Globals.blue = 0;
            Globals.green = 0;
            Globals.red2 = 0;
            Globals.green2 = 255;
            Globals.blue2 = 0;
            percent = (c / 255.0);
            System.out.println(percent);
            finalRed = (int) ((int) ((Globals.red) * (1 - percent)) + ((Globals.red2) * (percent)));
            finalBlue = (int) ((int) ((Globals.blue) * (1 - percent)) + ((Globals.blue2) * (percent)));
            finalGreen = (int) ((int) ((Globals.green) * (1 - percent)) + ((Globals.green2) * (percent)));
            Color colour = new Color(finalRed, finalGreen, finalBlue);
            g2d.setColor(colour);
            g2d.drawLine(0, (int) c, Globals.width, (int) c);
        }
    }

    public void Man_Julia_gradient(Graphics g) {
        //rgb is for colouring
        int rgb = 0;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        setBackground(Color.white);
        //System.out.println(epsilon);
        double ComplexX = Globals.complexX;
        if (Globals.CalcDone == false) {
            for (int column = 0; column < Globals.width; column++) {
                ComplexX += Globals.epsilonX;
                double ComplexY = Globals.complexY;

                for (int row = 0; row < Globals.height; row++) {
                    ComplexY -= Globals.epsilonY;
                    int iterations;
                    Complex C;
                    C = new Complex(-0.8,0.156);
                    //C = new Complex(ComplexX, ComplexY);
                    double percent;
                    percent = Calculations.gradient(ComplexX, ComplexY, C);
                    if (percent != -1) {
                        //this line of code was gotten from http://stackoverflow.com/questions/369438/smooth-spectrum-for-mandelbrot-set-rendering
                        rgb = Color.HSBtoRGB((float) (1.2f + 10*percent), 0.9f, 0.3f);

                    } else {
                        rgb = Color.HSBtoRGB((0.0f), 0.0f, 1.0f);
                    }
                    Globals.IterationCount[column][row] = rgb;
                }
            }
        }
        Globals.CalcDone = true;
        if (Globals.CalcDone == true) {
            for (int column = 0; column < Globals.width; column++) {
                for (int row = 0; row < Globals.height; row++) {
                    rgb = Globals.IterationCount[column][row];
                    Color color = new Color(rgb);
                    g2d.setColor(color);
                    g2d.fill(new Rectangle2D.Double(column, row, 1, 1));
                }
            }
        }
    }
}