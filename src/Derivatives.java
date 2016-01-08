/**
 * Created by Matt on 10/27/2015.
 */
public class Derivatives {
    public static void getEquation(){
//
        boolean degreeCheck=false;
        String degreeDigits="";
        String constantDigits="";
       int degreeCount=0;
       int constantCount=0;
        boolean neg=false;
        for(int i=0;i< Globals.equation.length();i++){
            if(Globals.equation.charAt(i)!='x'){
                Globals.termCount++;
            }
            if(degreeCheck) {

                if(neg){
                    //get a char for the num
                    char degreeDigit = Globals.equation.charAt(i);
                    //get the char in string
                    degreeDigits = degreeDigits + degreeDigit;
                    Globals.Degree[degreeCount] = Integer.parseInt(degreeDigits)*(-1);
                } else if(!neg&&Character.isDigit(Globals.equation.charAt(i))) {
                    //get a char for the num
                    char degreeDigit = Globals.equation.charAt(i);
                    //get the char in string
                    degreeDigits = degreeDigits + degreeDigit;
                    Globals.Degree[degreeCount] = Integer.parseInt(degreeDigits);
                }
                if( Globals.equation.charAt(i)=='-'){
                    neg=true;
                }
                if (i != Globals.equation.length() - 1) {
                    if (!Character.isDigit(Globals.equation.charAt(i + 1))) {
                        degreeCheck = false;
                        degreeCount++;
                        constantCount++;
                        degreeDigits = "";
                        constantDigits = "";
                        neg=false;
                       // System.out.println("Degree neg");
                    }
                }
            }
            if(Globals.equation.charAt(i)=='^'){
                degreeCheck=true;
            }
            else if(degreeCheck==false &&Character.isDigit(Globals.equation.charAt(i)) &&Globals.equation.charAt(i+1)!='+'&& Globals.equation.charAt(i+1)!='-' ){
                //get a char for the num
                char constantDigit=Globals.equation.charAt(i);
                //System.out.println(constantDigit);
                //get the char in string
                constantDigits=constantDigits+constantDigit;
                if(i!=0) {
                    if (Globals.equation.charAt(i - 1) == '-' && neg == false) {
                        Globals.Constant[constantCount] = Integer.parseInt(constantDigits) * (-1);
                        neg = true;
                       // System.out.println("constant neg on");
                    }
                }
                //tell me if its negative or not please
                if(neg==true){
                    Globals.Constant[constantCount] = Integer.parseInt(constantDigits)*(-1);
                }
                else{
                    Globals.Constant[constantCount] = Integer.parseInt(constantDigits);
                }
                if(!Character.isDigit(Globals.equation.charAt(i+1))){
                    neg=false;
                   // System.out.println(neg);
                }
            }
        }
        //System.out.println(Globals.Constant[0]+"x^"+Globals.Degree[0] + " + " + Globals.Constant[1]+"x^"+Globals.Degree[1]);
        basicDerivative();
    }
public static void basicDerivative(){
for(int i=0;i<Globals.Constant.length;i++){
    Globals.DerivedConstant[i]=Globals.Constant[i]*Globals.Degree[i];
    if (Globals.Degree[2] != 0) {
        Globals.DerivedDegree[i]=Globals.Degree[i]-1;
    }

}
    System.out.println(Globals.Constant[0]+"x^"+Globals.Degree[0] + " + " + Globals.Constant[1]+"x^"+Globals.Degree[1]);
    System.out.println(Globals.DerivedConstant[0]+"x^"+Globals.DerivedDegree[0] + " + " + Globals.DerivedConstant[1]+"x^"+Globals.DerivedDegree[1]);
}
}
