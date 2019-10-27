import java.io.*;

public class serializeTest implements Serializable{
    public serializeTest(){}
    public static void main(String[] args) {
       try{
         ObjectInputStream reader = new ObjectInputStream(new FileInputStream("new.txt"));

         serializeTest a = new serializeTest();
         int count = 1;

        reader.readObject();

         reader.close();
       }
       catch (Exception exc){System.out.println("lol");}
    }
}
