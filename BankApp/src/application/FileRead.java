package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileRead {
    static String userFilePath = "src/storage/Users.txt";
    static String accBalFilePath = "src/storage/AccountBalance.txt";
    static String acctAccessPath = "src/storage/AccountAccess.txt";


    public static String getBalance(String acctNumber){
        String output = "error";
        try(BufferedReader br = new BufferedReader(new FileReader(acctAccessPath) ) ){
            String line = br.readLine();
            while(line != null){
                if (line.contains(acctNumber)) {
                    return line;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    //Retrieve all accounts that customer has access to
    public static ArrayList<String> getAccountsAsUser(String userName) {
        ArrayList<String> output = new ArrayList<String>();
        try(BufferedReader br = new BufferedReader(new FileReader(acctAccessPath) ) ){

            // Parse for accounts that are accessible by User
            String line = br.readLine();
            while(line != null) {
                if(line.contains(userName)) {
                    String[] acct = line.split(":");

                    // Do not show balance if pending or denied account
                    if (line.contains("PENDING") || line.contains("DENIED") ) {
                        line = acct[0] + " : " + acct[1];
                    } else{
                        line = getBalance(acct[0]);
                    }

                    output.add(line);
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }

}