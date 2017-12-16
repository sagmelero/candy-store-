/*
CS-182
Sam Melero
11/11/17
Project 3
 */
package dessertitemlab;
import static dessertitemlab.Candy.tax;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
enum DessertType { CANDY , COOKIE, ICECREAM , SUNDAE , UNKNOWN }; //enum values 
/**
 *
 * @author samme
 */
//Main super class
abstract class DessertItem {
    //siper class instance varriable
    private String name;
    static double totalTax = 0;
    static double totalDessertCost = 0; 
    
    //Constructors for super class
    public DessertItem(String nm){
        this.name = nm;
    }
    public DessertItem(){
        this.name = " ";
    }
    
    //mutators for super class
    public void setName(String nameSet){
        this.name = nameSet;
    }
    public String getName(){
        return this.name;
    }
    public void setTotalDessertCost(double dessert){
        this.totalDessertCost += dessert;
    }
    public double getTotalDessertCost(){
        return this.totalDessertCost;
    }
    public void setTotalDessertTax(double taxes){
        this.totalTax += taxes;
    }
    public double getTotalDessertTax(){
        return this.totalTax;
    }
    public abstract double getWholeTotal(); //for inheirited classes to define
    public abstract void setWholeTotal();   //for inheirited classes to define
    
    public String toString(){
        return String.format( "%s", getName() );
    }
   
}

class Candy extends DessertItem{
    //subclass instance variables
    double weight;
    double pricePerPound;
    static double candyCost = 0;     //stores candy's entire total
    static double candyTotalTax = 0; //stores all of candy's tax
    static double tax = .07;
    
    //mutators
    public void setWeight(double lbs){
        this.weight = lbs;
    }
    public void setPrice(double prc){
        this.pricePerPound = prc;
    }
    public double getWeight(){
        return this.weight;
    }
    public double getPrice(){
        return this.pricePerPound;
    }
    
    //for storing the total tax amount and the single amount
    public double getSingleTaxAmount(){
        double taxAmount = ( tax * (getPrice() * getWeight()) );
        return taxAmount;
    }
    public void setWholeTaxAmount(double tx){
        this.candyTotalTax += tx;
    }
    public double getWholeTaxAmount(){
        return this.candyTotalTax;
    }
    
    //storing candy's entire total vs single total
    public double getSingleTotal(){
        return ( (getWeight() * getPrice()) + getSingleTaxAmount() );
    }
    public void setWholeTotal(){
        this.candyCost += getSingleTotal();
    }
    public double getWholeTotal(){
        return this.candyCost;
    }
    
    //constructors
    public Candy (String nm, double itemWeight, double pricePerlb){
        super(nm);
        this.weight = itemWeight;
        this.pricePerPound = pricePerlb;
    }   
    public Candy(String nm){
        this(nm, 0, 0);
    }
    public Candy(){
        this(" ", 0, 0);
    }
    
    public String toString(){
        String output = super.toString();
        output += String.format(" %.2f lbs @ %.2f Price per pound    $%.2f \n [Tax: $%.2f] \n",
                                    getWeight(), getPrice(),  getSingleTotal(), getSingleTaxAmount() );
        return output;
    }
    
}

class NoItemsEnteredException extends Exception{
        public NoItemsEnteredException (){
            super();
        }
}

class Cookie extends DessertItem{
    
    //instance variables
    int itemCount;
    double pricePerDozen;
    static double cookieCost = 0;       //for Cookies total tax and costs
    static double cookieTotalTax = 0;   //entired tax
    static double tax = .07;            //for calculating each individual tax amount
    
    //mutators
    public void setDozenPrice(double prc){
        this.pricePerDozen = prc;
    }
    public void setCount(int count){
        this.itemCount = count;
    }
    public double getDozenPrice(){
        return this.pricePerDozen;
    }
    public int getCount(){
        return this.itemCount;
    }
    
    //whole vs single tax storing
    public double getSingleTaxAmount(){
        double taxAmount = ( tax * (getCount() * (getDozenPrice()/12) ) );
        return taxAmount;
    }
    public void setWholeTaxAmount(double tx){
        this.cookieTotalTax += tx;
    }
    public double getWholeTaxAmount(){
        return this.cookieTotalTax;
    }
   
    //whole vs single totals
    public double getSingleTotal(){
        return (  ((getDozenPrice()/12) * getCount()) + getSingleTaxAmount() );
    }
    public double getWholeTotal(){
        return this.cookieCost ;
    }
    public void setWholeTotal(){
        this.cookieCost += getSingleTotal();
    }
   
    
    //constructors
    public Cookie(String nm, int cnt, double ppd){
        super(nm);
        this.itemCount = cnt;
        this.pricePerDozen = ppd; 
    }
    public Cookie(String nm){
        this(nm, 0, 0);
    }
    public Cookie(){
        this(" ", 0, 0);
    }
    
    public String toString(){
        String output = super.toString();
        output += String.format(" %d @ %.2f per dozen     $%.2f \n [Tax: $%.2f] \n",
                                getCount(), getDozenPrice(), getSingleTotal(), getSingleTaxAmount() );
        return output;
    }
    
}

class IceCream extends DessertItem{
    double cost;
    static double iceCreamCost = 0;
    
    //mutators
    public void setCost(double cost){
        this.cost = cost;
    }
    public double getCost(){
        return this.cost;
    }
    public void setWholeTotal(){
        this.iceCreamCost += getCost();
    }
    public double getWholeTotal(){
        return this.iceCreamCost;
    }
    public void setTotalDessert(){
        super.totalDessertCost += getCost();
    }    
    //constructors
    public IceCream(String nm, double iceCreamCost){
        super(nm);
        this.cost = iceCreamCost;
    }
    public IceCream(String nm){
        this(nm, 0);
    }
    public IceCream(){
        this(" ", 0);
    }
     
    public String toString(){
        String output = super.toString();
        output += String.format("                           $%.2f\n", getCost() );
        return output;
    }
}

class Sundae extends IceCream{
    String toppingName;
    double toppingPrice;
    static double sundaeCost;
    
    //mutators
    public void setTopName(String topName){
        this.toppingName = topName;
    }
    public void setTopPrice(double topPrice){
        this.toppingPrice = topPrice;
    }
    public String getTopName(){
        return this.toppingName;
    }
    public double getTopPrice(){
        return this.toppingPrice;
    }
    
    //constructors
    public Sundae (String nm, double iceCreamCost, String topName, double topPrice){
        super(nm, iceCreamCost);
        this.toppingName = topName;
        this.toppingPrice = topPrice;
    }
    public Sundae (String nm, double iceCreamCost){
        this(nm, iceCreamCost, " ", 0);
    }
    public Sundae(){
        this(" ", 0, " ", 0 );
    }
    
    //single(for one item) vs whole( all specific types items ) totals
    public void setWholeTotal(){
        this.sundaeCost += getSingleTotal();
    }
    public double getWholeTotal(){
      return this.sundaeCost;
    }
    public double getSingleTotal(){
        return ( getTopPrice() + super.getCost() );
    }
    
    public String toString(){
        String output = super.toString();
        output += String.format("\n 1 scoop(s) @ $%.2f per scoop \n 1 topping[%s] @ $%.2f        $%.2f\n",
                                super.getCost(), getTopName(), getTopPrice(), getSingleTotal() );
        return output;
    }
    
}



public class DessertShop {
  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception, NoItemsEnteredException {
        final int MAX_AMOUNT = 5;
        String      outputFileName = " ";    //entered name for the printWriter
        PrintWriter outputFile = null; //sets where the file will be output
        Scanner input = new Scanner(System.in); 
        DessertType dessertType;       //objects for each class
        DessertItem dessertItem;
        String inputString;            // for all input strings
        double inputDouble;            // for all input doubles
        int inputInt;                  // for all input ints
        
        //setting the output text file
        try{
          System.out.println("Input the file you would like to write to."); 
          outputFileName = input.nextLine();                                                           
          outputFile = new PrintWriter( outputFileName );
          }
        catch(Exception FileNotFound){
              System.err.println(": This is not a proper file name");
              System.exit(0);
          }
       
       Candy[] candy = new Candy[MAX_AMOUNT]; 
       Cookie[] cookie = new Cookie[MAX_AMOUNT];
       IceCream[] icecream = new IceCream[MAX_AMOUNT];       //setting max array  and their objects
       Sundae[] sundae = new Sundae[MAX_AMOUNT];
       int candyCount = 0, cookieCount = 0, iceCreamCount = 0, sundaeCount = 0; //counters for each array
       int x = 1; //for do while loop
     
       do{    //do while loop for at least one occurance
       try{   //try catch block inside the loop so it will occur each time
       System.out.println( "Desset type? (Cookie, Candy, Ice cream, or Sundae): " ); 
       String typeOfDessert = input.nextLine(); 
       dessertType = getType( typeOfDessert );
       
       switch ( dessertType ){
           case CANDY: 
              if (candyCount < MAX_AMOUNT){ 
                  
                  candy[candyCount] = new Candy();
                  
                  System.out.println( "Enter the name of the dessert: " );  //set Candy names and other variables
                  inputString = input.nextLine();
                  candy[candyCount].setName( inputString );
                  
                  System.out.println( "Enter the price per pound of the dessert: " );
                  inputDouble = input.nextDouble();
                  candy[candyCount].setPrice(inputDouble);
                  
                  System.out.println( "Enter the weight of the dessert: " );
                  inputDouble = input.nextDouble();
                  candy[candyCount].setWeight(inputDouble);
                  
                  candy[candyCount].setTotalDessertTax( candy[candyCount].getSingleTaxAmount() ); //set all other variables such as totals and item totals
                  candy[candyCount].setWholeTaxAmount( candy[candyCount].getSingleTaxAmount() );
                  candy[candyCount].setWholeTotal();
                  candy[candyCount].setTotalDessertCost( candy[candyCount].getSingleTotal() );
                  
                  candyCount++;  
              }
              else{
                  System.out.println("You have reached max amount of Candy. Try a new item.");
              }
           break;
           
           case COOKIE:
               if (cookieCount < MAX_AMOUNT){;
                  
                  cookie[cookieCount] = new Cookie();
               
                  System.out.println( "Enter the name of the Cookie: " ); 
                  inputString = input.nextLine();
                  cookie[cookieCount].setName( inputString );
                  
                  System.out.println( "Enter the price per dozen: " );
                  inputDouble = input.nextDouble();
                  cookie[cookieCount].setDozenPrice (inputDouble);
                  
                  System.out.println( "Enter the quantity purchased: " );
                  inputInt = input.nextInt();
                  cookie[cookieCount].setCount( inputInt );
                                                                         //set all other variables such as totals and item totals
                  cookie[cookieCount].setTotalDessertTax( cookie[cookieCount].getSingleTaxAmount() );
                  cookie[cookieCount].setWholeTaxAmount( cookie[cookieCount].getSingleTaxAmount() );
                  cookie[cookieCount].setWholeTotal();
                  cookie[cookieCount].setTotalDessertCost( cookie[cookieCount].getSingleTotal() );
                  cookieCount++; 
               }
               else{
                  System.out.println("You have reached max amount of Cookies. Try a new item.");
              }  
           break;
           
           case ICECREAM:
               if (iceCreamCount < MAX_AMOUNT){
                   
                   icecream[iceCreamCount] = new IceCream();
                   
                   System.out.println( "Enter the name of the ice cream: " );
                  inputString = input.nextLine();
                  icecream[iceCreamCount].setName(inputString);     
                  
                  System.out.println( "Enter the price: " );
                  inputDouble = input.nextDouble();
                  icecream[iceCreamCount].setCost (inputDouble);
                  
                  icecream[iceCreamCount].setWholeTotal();        //set all other variables such as totals and item totals
                  icecream[iceCreamCount].setTotalDessertCost( icecream[iceCreamCount].getCost() );
                  iceCreamCount++;
               }
               else{
                  System.out.println("You have reached max amount of Ice cream. Try a new item.");
              }
               break;
               
           case SUNDAE:
               if (sundaeCount < MAX_AMOUNT){
                   
                   sundae[sundaeCount] = new Sundae();
                   
                   System.out.println( "Enter the name of the Ice cream used: " );
                  inputString = input.nextLine();
                  sundae[sundaeCount].setName(inputString);   
                  
                  System.out.println( "Enter the ice cream price: " );
                  inputDouble = input.nextDouble();
                  sundae[sundaeCount].setCost( inputDouble );
                  
                  if ( input.hasNextLine())
                     input.nextLine();      //reset keyboard
                  
                  System.out.println( "Enter the name of the topping: " );
                  inputString = input.nextLine();
                  sundae[sundaeCount].setTopName(inputString);
                  
                  System.out.println( "Enter the topping's price: " );
                  inputDouble = input.nextDouble();
                  sundae[sundaeCount].setTopPrice( inputDouble );
                  
                  sundae[sundaeCount].setWholeTotal();          //set all other variables such as totals and item totals
                  sundae[sundaeCount].setTotalDessertCost( sundae[sundaeCount].getSingleTotal() );
                  sundaeCount++;
               }
               else{
                  System.out.println("You have reached max amount of Candy. Try a new item.");
              }          
           break;
               
           case UNKNOWN:
               x = 2;    //allows the while loop to close
               int total =  candyCount + cookieCount + iceCreamCount + sundaeCount; 
               if(total == 0){
                   throw new NoItemsEnteredException();
                }
                    
               System.out.println( "Your summary has been written to: " + outputFileName );   //output each item that was entered to the file
               
               
               for ( int lp=0; lp <= candyCount && candy[lp] != null ; lp++ )
               {
                  outputFile.println( candy[lp].toString() );
               }
               for ( int lp=0; lp <= cookieCount && cookie[lp] != null ; lp++ )
               {
                  outputFile.println( cookie[lp].toString() );
               }
               for ( int lp=0; lp <= iceCreamCount && icecream[lp] != null ; lp++ )
               {
                  outputFile.println (  icecream[lp].toString() );
               }
               for ( int lp=0; lp <= sundaeCount && sundae[lp] != null ; lp++ )
               {
                  outputFile.println( sundae[lp].toString() );
               }
                                                                
               //output a formatted total that resembles a true receipt to the file
              outputFile.printf("\nCandy     [%d]     Cost: $ %5.2f     Tax: $ %.2f",
                                candyCount, candy[candyCount].candyCost, candy[candyCount].candyTotalTax );
              outputFile.printf("\nCookie    [%d]     Cost: $ %5.2f     Tax: $ %.2f",
                                cookieCount, cookie[cookieCount].cookieCost, cookie[cookieCount].cookieTotalTax );
              outputFile.printf("\nIce cream [%d]     Cost: $ %5.2f",
                                iceCreamCount, icecream[iceCreamCount].iceCreamCost );
              outputFile.printf("\nSundae    [%d]     Cost: $ %5.2f",
                                sundaeCount, sundae[sundaeCount].sundaeCost);
              outputFile.println("\n         -----           -------         -------\n");
              
              outputFile.printf( "Subtotals [%d]           $ %5.2f           $ %.2f\n", 
                                (candyCount + cookieCount + iceCreamCount + sundaeCount), 
                                candy[2].totalDessertCost , candy[2].totalTax );        //doesnt matter which we call, variables are static
              
               
            break;           
       
       }
       }
       //each possible error in order of occurance
       catch(NoItemsEnteredException e){
           System.err.println( e + ": Sorry, we need more values" ); //for now items entered exception
       }
       catch(Exception exception){
           System.err.println( exception + ": Sorry, that is an invalid input, please try again." ); //allows the user to input invalid data and reset
       }
       
        if ( input.hasNextLine())
                     input.nextLine(); //reset keyboard
        }while(x != 2);   //close do-while once no item was entered
       
       outputFile.close();  //close output file
       System.exit(0);
       }
    
    public static int ItemsEntered(int total) throws NoItemsEnteredException { //check if there are items entered
        if (total == 0){
            throw new NoItemsEnteredException();
        }
        else
            return total;
    }
    

      
       
    static DessertType getType (String input) //enum method 
    
    {
            if ( input.equalsIgnoreCase( "Candy" ) )
            {
                return DessertType.CANDY;
            }    
            else if ( input.equalsIgnoreCase( "Cookie" ) )
            {
                return DessertType.COOKIE;
            }   
            else if ( input.equalsIgnoreCase( "Ice cream" ) )
            {
                return DessertType.ICECREAM;
            }
            else if ( input.equalsIgnoreCase ( "Sundae" ) )
            {
                return DessertType.SUNDAE;
            }
            else 
            {
                return DessertType.UNKNOWN;
            }
    }
    
}
