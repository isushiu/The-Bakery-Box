//IZA
public class Bun extends Pastry 
{
    private int chocFilling; //quantity of chocFilling bun
    private int strawberryFilling;

    // normal Constructor
    public Bun(String type, int quantity, int chocFilling, int strawberryFilling)
    {
        super(type, quantity);
        this.chocFilling = chocFilling;
        this.strawberryFilling = strawberryFilling;
    }

    // Accessor method
    public int getchocFilling()
    {
        return chocFilling;
    }

    public int getstrawberryFilling()
    {
        return strawberryFilling;
    }
    
    // Mutator method
    public void setchocFilling(int cF)
    {
        chocFilling = cF;
    }
    
    public void setstrawberryFilling(int sF)
    {
        strawberryFilling = sF;
    }
    
    //Processor int chocFilling, int strawberryFilling
    public double total() //calculate total price for both bun
    {
        double prices = (chocFilling*1.50) + (strawberryFilling*1.00);
    
        return prices;
    }
    
    
    // toString method
    public String toString() 
    {
        return (super.toString() + 
                "\n\t TOTAL ORDER FOR CHOCOLATE BUN                                     : " + chocFilling + 
                "\n\t TOTAL ORDER FOR STRAWBERRY BUN                                    : " + strawberryFilling);
    }
}
