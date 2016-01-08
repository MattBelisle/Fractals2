import org.apache.commons.math3.complex.Complex;

/**
 * Created by Matt on 11/28/2015.
 */
public class Calculations {
    public static int Mandelbrot_Set(double rX, double iY, Complex C) {
        Complex z = new Complex(rX, iY);
        int iterations = 0;
        //System.out.println("z= " + z);
        while ((iterations < 200) && (z.abs() <= 2)) {
            z = (z.pow(2)).add(C.pow(1));
            iterations++;
        }
        return iterations;
    }
    /**
     *  Checks if a complex number is in the mandelbrot set

     the formula is z(new)=z(old)^2+C where c is a complex constant and z at
     0 = c therefore z is also a complex number and c does not actually need
     to be defined as it can just be original z Steps from: (CODE IS
     NOT) http://world.mathigon.org/ start with the plane of complex numbers.
     Every point on the plane is represented by a different number c, and we
     repeat the following steps for every single point: First we create an
     infinite sequence of numbers according to the following pattern: We start
     with 0. Every new number is the previous number squared, plus c. In
     mathematical notation, we have a sequence (zn), where zn+1 = zn2 + c. If
     this sequence of numbers always increases and tends to infinity (it
     diverges), we colour the point white. However if the sequence does not
     increase beyond a certain limit (if it is bounded), we colour the point
     black.
     * @param rX-Real value X
     * @param iY-imaginary value Y
     * @param C- c value for eqn 
     * @return -  check 0 means false, check 1 means true 1 = black 0 = white
     */
    public static int Mandelbrot_Set_Black_And_White(double rX, double iY, Complex C) {
        Complex z = new Complex(rX, iY);
        int Checker;
        int iterations = 200;
        while ((iterations >= 0) && (z.abs() <= 2)) {
            z = (z.pow(2)).add(C);
            iterations--;
        }
        if (z.abs() <= 2) {
            Checker = 1;
        }
        else{
            Checker=2;
        }
        return Checker;
    }
    public static double gradient(double rX, double iY,Complex C){
        double percent=0;
        Complex z = new Complex(rX, iY);
        int iterations = 0;
        //System.out.println("z= " + z);
        while ((iterations < 300) && (z.abs() <= 2)) {
            z = (z.pow(2)).add(C.pow(1));
            iterations++;
        }
        if(iterations!=300){
            percent=((iterations + 1) - (Math.log(Math.log(z.abs())))/Math.log(2));
            percent/=300;
            // System.out.println(percent);
            Colouring.Gradient(percent);
        }
        else if(iterations==300){
            percent=-1;
        }
        return percent;
    }
}
//