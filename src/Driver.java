import java.util.*;
import java.io.*;


public class Driver extends Bank{

    public static void Menu(){
        System.out.println("Bank System Menu: \n");

        System.out.println("1) Add a new Account");
        System.out.println("2) Display All Acounts");
        System.out.println("3) SearchAccount");
        System.out.println("4) Withdraw Amount");
        System.out.println("5) Transfer Amount to Another Account");
        System.out.println("6) Exit the System");
    }

    public static void saveData(ArrayList<Bank> temp){
        try{
            FileOutputStream writer = new FileOutputStream("objects.txt");
            ObjectOutputStream Owriter = new ObjectOutputStream(writer);

            for(int i = 0; i < temp.size(); i++){
                Owriter.writeObject(temp.get(i));
            }

            Owriter.close();
            writer.close();
        }
        catch(IOException exc){}
    }

    public static void LoadData(ArrayList<Bank> temp){
        try{
            FileInputStream reader = new FileInputStream("objects.txt");
            ObjectInputStream Oreader = new ObjectInputStream(reader);


            Bank extra = (Bank)Oreader.readObject();
            while(extra != null)
            {
                temp.add(extra);
                extra = (Bank)Oreader.readObject();
            }

            Oreader.close();
            reader.close();
        }
        catch(IOException exc){System.out.println("IOException in LoadData method!");}
        catch(ClassNotFoundException exc){System.out.println("ClassNotFoundException in LoadData method!");}
    }

    public static void main(String args[]){

        //Using this only so that i can call functions
        Bank temp = new Bank();


        ArrayList<Bank> banklist = new ArrayList<Bank>();

        LoadData(banklist);

        Scanner inp = new Scanner(System.in);
        int choice = 0;

        while(true){
            Menu();

            System.out.println("What would you like to do: ");
            choice = inp.nextInt();

            switch(choice){
                case 1:{
                    try{
                        Bank extra = temp.addNewRecord(banklist);
                        banklist.add(extra);
                    }
                    catch(NullPointerException exc){
                        System.out.println("You entered wrong data");
                    }
                    break;
                }
                case 2:{
                    for(int i = 0; i < banklist.size(); i++){
                        banklist.get(i).Display();
                    }
                    break;
                }
                case 3:{
                    try{
                        System.out.println("Enter the Account Number: ");
                        long data = inp.nextLong();
                        int id = temp.searchAccount(banklist,data);
                        banklist.get(id).Display();
                    }
                    catch(Exception exc){
                        System.out.println("You entered wrong data");
                    }

                    break;
                }
                case 4:{
                    temp.withdraw(banklist);
                    break;
                }
                case 5:{
                    temp.transfer(banklist);
                    break;
                }
                case 6:{
                    saveData(banklist);
                    System.exit(0);
                }
            }
        }

    }

}