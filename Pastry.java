//iza
public abstract class Pastry
{
    private String type;
    private int quantity;

    // normal Constructor
    public Pastry(String type, int quantity) 
    {
        this.type = type;
        this.quantity = quantity;
    }

    // Accessor methods
    public int getQuantity() 
    {
        return quantity;
    }

    public String getType() 
    {
        return type;
    }

    // Mutator methods
    public void setQuantity(int quantity) 
    {
        this.quantity = quantity;
    }

    public void setType(String type) 
    {
        this.type = type;
    }

    /**processor method*/
    public double calcDiscount(double totalPrice, double discount)
    {
        double discountPrice = 0.0;
        discountPrice        = totalPrice * discount;
        
        return discountPrice;
    }
    
    //kalau ada error delete ni
    //***abstract method
    public abstract double total();
    
    // toString method
    public String toString()
    {
        return (
        "\n\t TYPE OF PASTRY                                                    : " + type + 
        "\n\n\t TOTAL ORDER PASTRY          (AFTER EDIT)                          : " + quantity);
    }
}
