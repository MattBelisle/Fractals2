import java.awt.Color;

/**
 * Created by Matt on 10/24/2015.
 */
public class Globals {
    //this just defines global ints that can be accessed anywhere within the program.
    //these are to dco with zooming
    public static int MouseX = 0;
    public static int MouseY = 0;
    public static double zoom = 1;
    //these are for colouring 
    public static int red = 0;
    public static int green = 250;
    public static int blue = 0;
    //these three are for gradient colouring
    public static int red2 = 250;
    public static int green2 = 0;
    public static int blue2 = 0;
    //these are for determining the colour after the gradient code
    public static int red3 = 0;
    public static int green3 = 0;
    public static int blue3 = 0;
    //these are for drawing
    public static double complexX = -2.0;
    public static double complexY = 1.5;
    public static int width = 1920;
    public static int height=1080;
    public static double epsilonX = 1.0 / (width / 4.0) / zoom;
    public static double epsilonY = 1.0 / (height / 3.0) / zoom;
    //for equations
    public static String equation = "-2x^2+5x^1+5";
    public static int[] Constant = new int[50];
    public static int[] Degree = new int[50];
    public static int[] DerivedConstant = new int[50];
    public static int[] DerivedDegree = new int[50];
    public static int termCount = -1;
    //for dragon
    public static int yLoc = width/2;
    public static int xLoc=height/2;
    //0=north,1=east,2=south,3=west
    public static int Direction=0;
    //for array changing so painting should take less time when not zooming, still will take time obviously double one is for gradient
    public static boolean CalcDone = false;
    public static int[][] IterationCount= new int[10000][10000];
}