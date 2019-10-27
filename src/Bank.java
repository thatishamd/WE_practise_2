import java.io.*;
import java.util.*;
class Bank implements Serializable{

    //Data Members
    public String name;
    public long accNo;
    public int pin;
    public long amount;


    //Constructors
    Bank(){}

    Bank(String name, long accNo, int pin, long amount){

        this.name = name;
        this.accNo = accNo;
        this.pin = pin;
        this.amount = amount;

    }


    //Getters n Setters
    public void setName(String name){
        this.name = name;
    }
    public void setAccNo(long accNo){
        this.accNo = accNo;
    }
    public void setPin(int pin){
        this.pin = pin;
    }
    public void setAmount(long amount){
        this.amount = amount;
    }

    public String getName(){
        return name;
    }
    public long getAccNo(){
        return accNo;
    }
    public int getPin(){
        return pin;
    }
    public long getAmount(){
        return amount;
    }

    //Utility Functions
    public void Display(){
        System.out.println("Owner Name: " + getName());
        System.out.println("Account No: " + getAccNo());
        System.out.println("Pin Code  : " + getPin());
        System.out.println("Amount	  : " + getAmount());
    }

    public int searchAccount(ArrayList<Bank> temp, long test){
        for(int i = 0; i < temp.size(); i++){
            if(temp.get(i).getAccNo() == test)
                return i;
        }
        return -1;
    }

    public boolean transfer(ArrayList<Bank> temp){
        Scanner inp = new Scanner(System.in);

        try{
            System.out.println("Enter the 8-Digit No of Account of your Account: ");
            long tempNo = inp.nextLong();
            System.out.println("Enter the amount to transfer: ");
            long tempAmount = inp.nextLong();
            System.out.println("Enter the 8-Digit of Account you want to transfer to: ");
            long tempNo2 = inp.nextLong();

            if(searchAccount(temp,tempNo) == -1)
                throw new Exception();
            if(searchAccount(temp,tempNo2) == -1)
                throw new Exception();

            Bank tempobj = temp.get(searchAccount(temp,tempNo));
            if(tempobj.getAmount() < tempAmount)
                throw new Exception();
            else{
                int id1 = searchAccount(temp,tempNo);
                int id2 = searchAccount(temp,tempNo2);
                //System.out.println(id2);
                long amountt = temp.get(id1).getAmount();
                long amountx = temp.get(id2).getAmount();
                System.out.println(amountx);
                temp.get(id1).setAmount(amountt - tempAmount);

                //System.out.println(amountx);
                temp.get(id2).setAmount(amountx + tempAmount);
                //System.out.println(temp.get(id2).getAmount());
            }

        }
        catch(NumberFormatException exc){
            System.out.println("Wrong Number Format Exception!");
            return false;
        }
        catch(Exception exc){
            System.out.println("Exception occured in finding the account!!!");
            return false;
        }
        return true;
    }

    public boolean withdraw(ArrayList<Bank> temp){
        Scanner inp = new Scanner(System.in);

        try{
            System.out.println("Enter the 8-Digit No of Account of your Account: ");
            long tempNo = inp.nextLong();
            System.out.println("Enter the amount to Withdraw: ");
            long tempAmount = inp.nextLong();

            if(searchAccount(temp,tempNo) == -1)
                throw new Exception();

            Bank tempobj = temp.get(searchAccount(temp,tempNo));
            if(tempobj.getAmount() < tempAmount)
                throw new Exception();
            else{
                int id = searchAccount(temp,tempNo);
                long amountt = temp.get(id).getAmount();
                temp.get(id).setAmount(amountt - tempAmount);
            }

        }
        catch(NumberFormatException exc){
            System.out.println("Wrong Number Format Exception!");
            return false;
        }
        catch(Exception exc){
            System.out.println("Exception occured in finding the account OR amount!!!");
            return false;
        }
        return true;
    }

    public Bank addNewRecord(ArrayList<Bank> temp){
        Bank obj = new Bank();
        Scanner inp = new Scanner(System.in);

        try{
            System.out.println("Enter the name of Account Holder: ");
            String tempName = inp.nextLine();

            System.out.println("Enter the 8-Digit Account number: ");
            long tempNo = inp.nextLong();

            System.out.println("Enter the Pin Code: ");
            int tempNo2 = inp.nextInt();

            if(searchAccount(temp,tempNo) == -1){
                obj.setName(tempName);
                obj.setAccNo(tempNo);
                obj.setPin(tempNo2);
                obj.setAmount(1000);
            }
            else{
                throw new Exception();
            }

        }
        catch(NumberFormatException exc){
            System.out.println("Wrong Number Format Exception!");
        }
        catch(Exception exc){
            System.out.println("Account Already Exists!");
            return null;
        }

        obj.setAmount(1000);

        return obj;
    }
}
