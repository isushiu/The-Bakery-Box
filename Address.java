//afina 
public class Address
{
    private String street;
    private String city;
    private String state;
    
    //default
    public Address()
    {
        street = null;
        city = null;
        state = null;
    }
    
    //normal dalam uml takda
    public Address(String street, String city, String state)
    {
        this.street = street ;
        this.city = city;
        this.state = state;
    }
    
    //accessor
    public String getStreet()
    {
        return street;
    }
    
    public String getCity()
    {
        return city;
    }
    
    public String getState()
    {
        return state;
    }
    
    //mutator
    public void setStreet(String street)
    {
        this.street = street;
    }
    
    public void setCity(String c)
    {
        city = c;
    }
    
    public void setState(String s)
    {
        state = s;
    }
    
    //toString dalam uml takda
    public String toString()
    {
        return 
        "\n\t HOME ADDRESS                                     : "+ street + ", " + city + ", " + state ;
    }
    
}