import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class BakeryBoxSystem
{
    public static void main(String [] args)
    {
        try
        {
            Scanner input = new Scanner (System.in);
            input.useDelimiter("\n");
            
            DecimalFormat df = new DecimalFormat("0.00");
            
            //declaration of variables
            String name, phoneNum, services, paymentMethod, couponCode, type, bunFilling, customerType, editConfirmation;
            String street, city, state; //declare variables for address
            String output = null;
            
            int option, i = 0,    quantity = 0, chocFilling = 0, strawberryFilling = 0, bunCount = 0, cakeCount = 0 ;
            int wholeCake = 0,    slices=0,     cakeFlavours=0 ,editOrder, editPastry, sumCake = 0, sumBun = 0;
            double prices = 0.00, discount = 0.00, charge   = 0.00, totalBun = 0, totalCake=0, sum = 0.00, totalPastry = 0.00;
            
            //declaration of arrays
            Pastry  [] pastryOrder        = new Pastry  [20];
            Customer[] customerDetails    = new Customer[20];
            int     [] quantityEdit       = new int     [20] ; //to store edit that customer make, it will be shown at receipt
            
            //input read file
            FileReader fr     = new FileReader("CustomerDetails.txt");
            BufferedReader br = new BufferedReader(fr);
            String inData     = null;
            
            while((inData = br.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(inData, ";");
                
                name = st.nextToken();
                option = Integer.parseInt(st.nextToken());//1-Member, 2-Nonmember
                
                services = st.nextToken();
                street   = st.nextToken(); //for delivery address
                city     = st.nextToken();
                state    = st.nextToken();
                
                phoneNum      = st.nextToken();
                paymentMethod = st.nextToken(); //Cash / Transfer / QrScan
                
                Address homeAddress = new Address(street,city, state);
                
                customerDetails [i] = new Customer(name, option, services, homeAddress, phoneNum, paymentMethod);
                
                //Pastry Order Section
                type = st.nextToken(); //Bun or Cake
                if(type.equalsIgnoreCase("Bun"))
                {
                    chocFilling       = Integer.parseInt(st.nextToken()); //quantity of chocFilling Bun
                    strawberryFilling = Integer.parseInt(st.nextToken()); //quantity of strawberryFilling Bun
                    
                    pastryOrder [i]   = new Bun(type,(chocFilling + strawberryFilling),chocFilling,strawberryFilling);
                }
                else if (type.equalsIgnoreCase("Cake"))
                {
                    wholeCake       = Integer.parseInt(st.nextToken());
                    slices          = Integer.parseInt(st.nextToken()); 
                    cakeFlavours    = Integer.parseInt(st.nextToken()); //1-Strawberry 2- Chocholate 3-Vanilla
                    
                    pastryOrder [i] = new Cake(type,(wholeCake + slices),wholeCake,slices,cakeFlavours);
                }
                
                /***EDIT ORDER*/
                editConfirmation = st.nextToken(); // "Yes" if want to delete order.
                while (editConfirmation.equalsIgnoreCase("Yes"))
                {
                    //pastryType
                    editPastry = Integer.parseInt(st.nextToken()); //1-chocFilling 2-strawberryFilling 3-wholeCake 4-slices 
                    
                    if(pastryOrder[i] instanceof Bun)
                    {
                        quantityEdit[i] = Integer.parseInt(st.nextToken()); //enter quantity you want to delete
                        Bun tempBun     = (Bun) pastryOrder[i];
                        
                        if(editPastry == 1)
                        {
                            chocFilling = tempBun.getchocFilling() - quantityEdit[i];
                                
                            //rewrite data with new quantity
                            tempBun.setchocFilling(chocFilling);
                            
                            //set new quantity with the new chocFilling value
                            quantity    = chocFilling + strawberryFilling;
                            tempBun.setQuantity(quantity);
                        }
                        else if (editPastry == 2)
                        {
                            strawberryFilling = tempBun.getstrawberryFilling() - quantityEdit[i];
                                
                            //rewrite data with new quantity
                            tempBun.setstrawberryFilling(strawberryFilling);
                            
                            //set new quantity with the new strawberryFilling value
                            quantity          = chocFilling + strawberryFilling;
                            tempBun.setQuantity(quantity);
                        }
                        else {System.out.println("\n**The type of Pastry that you want to delete is not in our database.");}
                    }
                    else if (pastryOrder[i] instanceof Cake)
                    {
                        quantityEdit[i] = Integer.parseInt(st.nextToken()); //enter quantity you want to delete
                        Cake tempCake   = (Cake) pastryOrder[i];
                        if(editPastry == 3)
                        {
                            wholeCake   = tempCake.getwholeCake() - quantityEdit[i];
                                    
                            //rewrite data with new quantity
                            tempCake.setwholeCake(wholeCake);
                            
                            //set new quantity with the new wholeCake value
                            quantity    = wholeCake + slices;
                            tempCake.setQuantity(quantity);
                        }
                        else if(editPastry == 4)
                        {
                            slices      = tempCake.getslices() - quantityEdit[i];
                                
                            //rewrite data with new quantity
                            tempCake.setslices(slices);
                            
                            //set new quantity with the new slices value
                            quantity    = wholeCake + slices;
                            tempCake.setQuantity(quantity);
                        }
                        else {System.out.println("\n**The type of Pastry that you want to delete is not in our database.");}
                    }
                    editConfirmation = st.nextToken();
                }
                i++;
            }
              
            //APPLIED SORT FUNCTION
            //ASKING IF THEY WANT THE CUSTOMER RECEIPT IN ASC ORDER 
            String sort = JOptionPane.showInputDialog(null,"Do you want to sort the receipt in ascending order based on customer name [YES | NO]","BAKERYBOX SYSTEM", JOptionPane.INFORMATION_MESSAGE);
            
            if (sort.equalsIgnoreCase("YES"))
            {
                Customer temp;
                for ( i = 0; i < customerDetails.length; i++) {
                    for (int j = i + 1; j < customerDetails.length; j++) {
                        // to compare one string with other strings
                        if (customerDetails[i].getName().compareTo(customerDetails[j].getName()) > 0) 
                        {
                            // swapping
                            temp = customerDetails[i];
                            customerDetails[i] = customerDetails[j];
                            customerDetails[j] = temp;
                        }
                    }
                }
            }

            //display total bun order, total cake order, total prices, discount, totalOrder(discount tax include)
            for(int x = 0; x<pastryOrder.length;x++)
            {
                //GET total for each types of pastry
                if(pastryOrder[x] instanceof Bun)
                {
                    Bun tempBun     = (Bun) pastryOrder[x];
        
                    int bunOri = tempBun.getQuantity() + quantityEdit[x];
                    
                    output   = "\n\t EDIT ORDER                                               DELETE   " + quantityEdit[x] + " BUN." +
                                "\n\n\t TOTAL ORDER PASTRY          (BEFORE EDIT)                         : " + bunOri +
                                tempBun.toString();
                                
                               
                    sumBun = sumBun + tempBun.getQuantity(); //to display at daily report for staff
                }
                else if (pastryOrder[x] instanceof Cake)
                {
                    Cake tempCake   = (Cake) pastryOrder[x];

                    int cakeOri = tempCake.getQuantity() + quantityEdit[x];
                    
                    output    = "\t EDIT ORDER                                               DELETE " + quantityEdit[x] + " CAKE." +
                                "\n\n\t TOTAL ORDER PASTRY          (BEFORE EDIT)                         : " + cakeOri 
                                + tempCake.toString();
                    
                    sumCake = sumCake + tempCake.getQuantity(); //to display at daily report for staff
                }
                
                //Determine discount based on type of customer
                if (customerDetails[x].getMembership() == 1)
                {
                    customerType = "Members";
                    discount     = 0.06;
                }
                else
                {
                   customerType = "Non-Members";
                   discount     = 0.00; 
                }
                
                //calculate additional price
                if (customerDetails[x].getServices().equalsIgnoreCase("DELIVERY"))
                {
                    charge = 5; //delivery charge
                }

    
                totalPastry = pastryOrder[x].total(); //calculate total price for cake
                double totalDisc   = pastryOrder[x].calcDiscount(totalPastry,discount);
                double tax         = (totalPastry - totalDisc)*0.06;
                double totalOrder  = totalPastry - totalDisc + tax + charge; //incl tax, discount
                
                
                //RECEIPT FOR CUSTOMER
                System.out.println("\n\n\t\t\t##### WELCOME TO BAKERYBOX SYSTEM #####");
                System.out.println("\n\t\t\t\tCUSTOMER RECEIPT");
                System.out.println("==================================================================================================");
                System.out.println("\n\t Order # " + (x+1) + "\t\t\t\t FOR : "+ customerDetails[x].getName());
            
                System.out.println("\n--------------------------------------------------------------------------------------------------");
            
                System.out.println(customerDetails[x].toString()); //called toString from customer class
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println(output);
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println("\n\t TOTAL ORDER                                                     : RM " + df.format(totalPastry));
                System.out.println("\t TOTAL DISCOUNT                                                  : RM " + df.format(totalDisc));
                System.out.println("\t TOTAL TAX                                                       : RM " + df.format(tax));
                System.out.println("\t DELIVERY CHARGE                                                 : RM " + df.format(charge));
            
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println("\t TOTAL PRICES                                                    : RM " + df.format(totalOrder));
            
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println("\t\t\t\tTHANK YOU FOR BUYING!");
    
                totalBun     = 0;
                totalCake    = 0;
                totalPastry  = 0;
                charge       = 0;
                
                sum          = sum +  totalOrder;
            }
        
            
            //PROCESS SEARCHING FOR CUSTOMER WITH HIGHEST AMOUNT OF ORDERS
            int highest = 0;
            for(int j = 0; j<pastryOrder.length;j++)
            {
                if(pastryOrder[j].getQuantity()> pastryOrder[highest].getQuantity())
                {
                    highest = j;
                }
            }

            //DISPLAY DAILY REPORT FOR STAFF WITH CUSTOMER NAME THAT HAVE HIGHEST AMOUNT OF ORDER
            System.out.println("\n\n==================================================================================================");
            System.out.println("\t\t\t\t  DAILY REPORT FOR STAFF RECEIPT");
            System.out.println("==================================================================================================");
            System.out.println("\n\t TOTAL CAKE SOLD                                                 : " + sumCake);
            System.out.println("\t TOTAL BUN SOLD                                                  : " + sumBun);
            System.out.println("\n--------------------------------------------------------------------------------------------------");
            System.out.println("\t TOTAL PRICES                                                    : RM " + df.format(sum));
            System.out.println("\t CUSTOMER "+customerDetails[highest].getName() + " WITH HIGHEST COUNT OF ORDERS "+ pastryOrder[highest].getQuantity() 
                                + " " + pastryOrder[highest].getType()+ " ODERS.");
            System.out.println("--------------------------------------------------------------------------------------------------");
            
            /***output file = customer who choose delivery, the info will be stored*/
            sumCake = 0; sumBun = 0; highest = 0; // reset the values because need to find in customer that choose service delivery
            FileWriter fw = new FileWriter("DeliveryCustomer.txt");
            PrintWriter pw = new PrintWriter(fw); 
            pw.println("\n\t\t\tDETAILS FOR DELIVERY CUSTOMER");
            pw.println("==================================================================================================");
            for(int j = 0; j<customerDetails.length;j++)
            {
                if(pastryOrder[j] instanceof Bun)
                {
                    Bun tempBun     = (Bun) pastryOrder[j];
                    int bunOri = tempBun.getQuantity() + quantityEdit[j];
                    
                    output   = "\n\t EDIT ORDER                                               DELETE   " + quantityEdit[j] + " BUN." +
                                "\n\n\t TOTAL ORDER PASTRY          (BEFORE EDIT)                         : " + bunOri +
                                tempBun.toString();
                                
                    sumBun = sumBun + tempBun.getQuantity(); //to display at daily report for staff
                }
                else if (pastryOrder[j] instanceof Cake)
                {
                    Cake tempCake   = (Cake) pastryOrder[j];
                    int cakeOri = tempCake.getQuantity() + quantityEdit[j];
                    
                    output    = "\n\t EDIT ORDER                                               DELETE " + quantityEdit[j] + " CAKE." +
                                "\n\n\t TOTAL ORDER PASTRY          (BEFORE EDIT)                         : " + cakeOri 
                                + tempCake.toString();
                                
                    sumCake = sumCake + tempCake.getQuantity(); //to display at daily report for staff
                }
                if (customerDetails[j].getServices().equalsIgnoreCase("DELIVERY"))
                {   
                    pw.println(
                    "\n\n\t Order # " + (j+1) + "\t\t\t\t FOR : "+ customerDetails[j].getName() 
                    + "\n==================================================================================================" +
                    customerDetails[j].toString() + "\n"
                    +"\n--------------------------------------------------------------------------------------------------" + output +
                    pastryOrder[j].toString() + "\n--------------------------------------------------------------------------------------------------" );
                }
                //highest lowest
                if(pastryOrder[j].getQuantity()> pastryOrder[highest].getQuantity())
                {
                    highest = j;
                }
            }
            pw.println("==================================================================================================");
            pw.println("\n\t\t\t\t DAILY REPORT FOR STAFF RECEIPT");
            pw.println("==================================================================================================");
            pw.println("\n\t TOTAL CAKE SOLD                                                 : " + sumCake);
            pw.println("\t TOTAL BUN SOLD                                                  : " + sumBun);
            pw.println("\n--------------------------------------------------------------------------------------------------");
            pw.println("\t TOTAL PRICES                                                    : RM " + df.format(sum));
            pw.println("\t CUSTOMER "+customerDetails[highest].getName() + " WITH HIGHEST COUNT OF ORDERS "+ pastryOrder[highest].getQuantity() 
                                + " " + pastryOrder[highest].getType()+ " ODERS.");
            pw.println("--------------------------------------------------------------------------------------------------");
            
            //FIND AND DISPLAY CUSTOMER DETAILS BASED ON NAME
            //APPLIED THE SEARCH FUNCTION
            String confirmation = JOptionPane.showInputDialog(null,"DO YOU WANT TO USE THE SEARCH SYSTEM [YES|NO]","BAKERYBOX SYSTEM SEARCH SYSTEM", JOptionPane.QUESTION_MESSAGE);
            while(confirmation.equalsIgnoreCase("YES"))
            {
                String output2 = null;
                String search = JOptionPane.showInputDialog(null,"Enter name of customer that you want to search");
                boolean found = false;
                for(int j = 0; j<customerDetails.length;j++)
                {
                    if(pastryOrder[j] instanceof Bun)
                    {
                        Bun tempBun     = (Bun) pastryOrder[j];
                        int bunOri = tempBun.getQuantity() + quantityEdit[j];
                    
                        output2   = "\n\t EDIT ORDER                                               DELETE   " + quantityEdit[j] + " BUN." +
                                    "\n\n\t TOTAL ORDER PASTRY          (BEFORE EDIT)                         : " + bunOri +
                                    tempBun.toString();
                    }
                    else if (pastryOrder[j] instanceof Cake)
                    {
                        Cake tempCake   = (Cake) pastryOrder[j];
                        int cakeOri = tempCake.getQuantity() + quantityEdit[j];
                        
                        output2    = "\n\t EDIT ORDER                                               DELETE " + quantityEdit[j] + " CAKE." +
                                    "\n\n\t TOTAL ORDER PASTRY          (BEFORE EDIT)                         : " + cakeOri 
                                    + tempCake.toString();
                    }
                    if(customerDetails[j].getName().equalsIgnoreCase(search))
                    {
                        found = true;
                        output = "\n\t\t\t\tCUSTOMER RECEIPT" +
                        "\n==================================================================================================" + 
                        "\n\t Order # " + (j+1) + "\t\t\t\t FOR : "+ customerDetails[j].getName() +
                        "\n--------------------------------------------------------------------------------------------------" +
                        "\n" + customerDetails[j].toString() +
                        "\n--------------------------------------------------------------------------------------------------"+
                        "\n" + output2 + 
                        "\n--------------------------------------------------------------------------------------------------";
                    }
                }
                if(found)
                {
                    System.out.println("\n\n\t\t##### BAKERYBOX SYSTEM SEARCH SYSTEM #####");
                    System.out.println(output);
                    confirmation = "NO";
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Sorry the name you enter is not in our database.");
                    confirmation = JOptionPane.showInputDialog(null,"DO YOU WANT TO USE THE SEARCH SYSTEM [YES|NO]","BAKERYBOX SYSTEM SEARCH SYSTEM", JOptionPane.QUESTION_MESSAGE);
                }
            }
            pw.close();
            br.close();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println(fnfe.getMessage());
        }
        catch(IOException io )
        {
            System.out.println(io.getMessage());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}