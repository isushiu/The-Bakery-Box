//hasanatun
public class Customer
{
    private String name;
    private int membership; //1-member, 2- nonmember
    private Address homeAdress;
    private String phoneNum;
    private String services;
    private String paymentMethod;
    
    /**default constructor*/
    public Customer()
    {
        name =null;
        membership =0;
        services=null;
        homeAdress=null;
        phoneNum=null;
        paymentMethod=null;
        
    }
    
    /**normal constructor*/
    public Customer(String name, int membership,String services, Address homeAdress, String phoneNum,String paymentMethod)
    {
        this.name=name;
        this.membership=membership;
        this.services=services;
        this.homeAdress=homeAdress;
        this.phoneNum=phoneNum;
        this.paymentMethod=paymentMethod;
    }
    
    /**mutator method for each variables*/
    public void setName(String N)
    {
        name=N;
    }
    
    public void setMembership(int M)
    {
        membership = M;
    }
    
    public void sethomeAddress(Address HA)
    {
        homeAdress=HA;
    }
    
    public void setphoneNum(String pN)
    {
        phoneNum=pN;
    }
    
    public void setServices(String S)
    {
        services=S;
    }
    
    public void setpaymentMethod(String pM)
    {
        paymentMethod=pM;
    }
    
    /**accessor method*/
    public String getName()
    {
        return name;
    }
    
    public int getMembership()
    {
        return membership;
    }
    
    public Address gethomeAdress()
    {
        return homeAdress;
    }
    
    public String getphoneNum()
    {
        return phoneNum;
    }
    
    public String getServices()
    {
        return services;
    }
    
    public String getpaymentMethod()
    {
        return paymentMethod;
    }
    
    /**toString method*/
    public String toString()
    {
        return "\n\t CUSTOMER NAME                                    : " + name + 
               "\n\t SERVICES [DELIVERY | PICKUP]                     : " + services+
                homeAdress.toString() +
               "\n\t PHONE NUMBER                                     : " + phoneNum + 
               "\n\t PAYMENT METHOD [CASH | QRSCAN | TRANSFER]        : " + paymentMethod;
    }
}