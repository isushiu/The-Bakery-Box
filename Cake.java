//iza
public class Cake extends Pastry 
{
    private int wholeCake;
    private int slices;
    private int flavours;

    // Normal Constructor
    public Cake( String type, int quantity, int wholeCake, int slices, int flavours) 
    {
        super(type,quantity);
        this.wholeCake  = wholeCake; //whole cake
        this.slices = slices; //slices
        this.flavours  = flavours;
    }

    // Accessor methods
    public int getFlavours() 
    {
        return flavours;
    }

    public int getwholeCake()
    {
        return wholeCake;
    }
    
    public int getslices()
    {
        return slices;
    }
    
    // Mutator methods
    public void setFlavor(int flavours) 
    {
        this.flavours = flavours;
    }

    public void setwholeCake(int wholeCake) 
    {
        this.wholeCake = wholeCake;
    }
    
    public void setslices(int slices)
    {
        this.slices = slices;
    }

    //processor to calculate total for all type of cake
    public double total()
    {
        double totalCake, pricewholeCake = 0.00, priceSlices = 0.00;
        //tukar flavour jadi cakeFlavours
        if ((flavours == 1) || (flavours == 3))
        {
            pricewholeCake = 20.00*wholeCake;
        }
        else if(flavours == 2)
        {
            pricewholeCake = 25.00*wholeCake;
        }
        
        priceSlices = 5.00*slices;
        totalCake = pricewholeCake + priceSlices;
        
        return totalCake;
    }
    
    //determine cake flavour by input user
    public String flavour(int cakeFlavours)
    {
        String flavour = null;
        
        if (cakeFlavours == 1)
        {
            flavour = "Strawberry Flavour";
        }
        else if (cakeFlavours == 3)
        {
            flavour = "Vanilla Flavour";
        }
        else
        {
            flavour = "Chocolate Flavour";
        }
        
        return flavour;
    }
    
    // toString method
    public String toString()
    {
        return (super.toString() + 
                "\n\t TOTAL ORDER FOR WHOLE CAKE                                        : " + wholeCake +
                "\n\t TOTAL ORDER FOR SLICES                                            : " + slices + 
                "\n\t CAKE FLAVOURS                                                     : " + flavour(flavours));
    }
}
