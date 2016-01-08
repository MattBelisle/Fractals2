import org.apache.commons.math3.complex.Complex;

/**
 * Created by Matt on 10/24/2015.
 */
public class Colouring {
    public static void Gradient(double percent) {

        int finalRed;
        int finalGreen;
        int finalBlue;

        // System.out.println(percent);
        finalRed= (int) ((int) ((Globals.red)*(1.0-10*percent))+((Globals.red2)*(10*percent)));
        finalBlue= (int) ((int) ((Globals.blue)*(1.0-10*percent))+((Globals.blue2)*(10*percent)));
        finalGreen= (int) ((int) ((Globals.green)*(1.0-10*percent))+((Globals.green2)*(10*percent)));
        Globals.red3=finalRed;
        Globals.green3=finalGreen;
        Globals.blue3=finalBlue;
        //System.out.println( Globals.red3+" "+ Globals.green3+ " "+ Globals.blue3);
    }

//
    public static void Newton(double rX, double rY, int A) {
        Complex z = new Complex(rX, rY);
        Complex tempZ=z;
        int iterations = 0;
        while (z.abs() > 0.1) {
            for (int i = 0; i <= Globals.termCount; i++) {

                tempZ= tempZ.pow(Globals.Degree[i]);
                tempZ=tempZ.add(Globals.Constant[i]);

                tempZ=tempZ.divide(tempZ.pow(Globals.DerivedDegree[i]).add(Globals.DerivedConstant[i]));
            }
            z=z.subtract(tempZ);
            System.out.println(z);
            iterations++;
        }
        System.out.println(iterations);
    }
    public static void Dragon(int iteration){
        int TurnDir=0;
        while(iteration%2==0){
            iteration=iteration/=2;
        }
        TurnDir=iteration%4;
        //turn dir 1=R 3=L
        //0=north,1=east,2=south,3=west
        switch (Globals.Direction){
            //if facing north a left turn would subtract one and a right would be add one
            case 0:
                //goes right turn then left turn!!!
                if(TurnDir==3){
                    Globals.xLoc-=1;
                    Globals.Direction=1;
                }
                else{
                    Globals.xLoc+=1;
                    Globals.Direction=3;
                }
                break;
            //if facing east left turn is add 1 to y and right is minus one y
            case 1:
                if(TurnDir==3){
                    Globals.yLoc+=1;
                    Globals.Direction=2;
                }
                else{
                    Globals.yLoc-=1;
                    Globals.Direction=0;
                }
                break;
            // if facing south let turn is +1 y and right is -1 y
            case 2:
                if(TurnDir==3){
                    Globals.xLoc+=1;
                    Globals.Direction=3;
                }
                else{
                    Globals.xLoc-=1;
                    Globals.Direction=1;
                }
                break;
            // if facing west left turn is minus 1 x and right turn is +1 x
            case 3:
                if(TurnDir==3){
                    Globals.xLoc-=1;
                    Globals.Direction=0;
                }
                else{
                    Globals.xLoc+=1;
                    Globals.Direction=2;
                }
                break;
        }
    }
    public static void Escape_TimeV2(int iteration){
        switch (iteration) {
            case 1:
                Globals.green = 20;
                break;
            case 2:
                Globals.green = 30;
                break;
            case 3:
                Globals.green = 40;
                break;
            case 4:
                Globals.green = 50;
                break;
            case 5:
                Globals.green = 60;
                break;
            case 6:
                Globals.green = 70;
                break;
            case 7:
                Globals.green = 80;
                break;
            case 8:
                Globals.green = 90;
                break;
            case 9:
                Globals.green = 100;
                break;
            case 10:
                Globals.green = 110;
                break;
            case 20:
                Globals.red = 30;
                break;
            case 21:
                Globals.red = 40;
                break;
            case 22:
                Globals.red = 50;
                break;
            case 23:
                Globals.red = 60;
                break;
            case 24:
                Globals.red = 70;
                break;
            case 25:
                Globals.red = 80;
                break;
            case 26:
                Globals.red = 90;
                break;
            case 27:
                Globals.red = 100;
                break;
            case 28:
                Globals.red = 110;
                break;
            case 29:
                Globals.red = 120;
                break;
            case 30:
                Globals.blue = 30;
                break;
            case 31:
                Globals.blue = 40;
                break;
            case 32:
                Globals.blue = 50;
                break;
            case 33:
                Globals.blue = 60;
                break;
            case 34:
                Globals.blue = 70;
                break;
            case 35:
                Globals.blue = 80;
                break;
            case 36:
                Globals.blue = 90;
                break;
            case 37:
                Globals.blue = 100;
                break;
            case 38:
                Globals.blue = 110;
                break;
            case 39:
                Globals.blue = 120;
                break;
            case 200:
                Globals.red = 255;
                Globals.green = 255;
                Globals.blue = 255;
                break;
            default:
                Globals.red = iteration;
                break;
        }
    }
}