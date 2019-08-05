package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileRead {
    static String userFilePath = "src/storage/Users.txt";
    static String accBalFilePath = "src/storage/AccountBalance.txt";
    static String acctAccessPath = "src/storage/AccountAccess.txt";


    public static String getBalance(String acctNumber){
        String output = "error";
        try(BufferedReader br = new BufferedReader(new FileReader(accBalFilePath) ) ){
            String line = br.readLine();

            while(line != null){
                if (line.contains(acctNumber)) {
                    String[] temp = line.split(":");
                    output = temp[0] + " : $" + temp[1];
                    return output;
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

    //retrieve account's status
    public static String getAccountStatus(int acctNum) {
    	String output = "error";
    	try(BufferedReader br = new BufferedReader(new FileReader(accBalFilePath));){
    		
    		String line = br.readLine();
    		while(line != null) {
    			if(line.contains(Integer.toString(acctNum))) {
    				String strArr[] = line.split(":");
    				String status = strArr[1];
    				output = status;
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

    /*
        Returns the account line persisted in the AccountAccess file for verification of JointApplication
     */
    public static ArrayList<String> getAccount(String acctNum) {
        ArrayList<String> output = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(acctAccessPath) ) ){

            String line = br.readLine();
            while(line != null) {
                if(line.contains(acctNum)) {
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


    //reads all accounts in AccountAccess file
    public static ArrayList<String> getAllAccounts() {
    	ArrayList<String> output = new ArrayList<String>();
    	try(BufferedReader br = new BufferedReader(new FileReader(acctAccessPath))){
    		
    		String line = br.readLine();
    		while(line != null) {
    			output.add(line);
    			line = br.readLine();
    		}
    		
    	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return output;
    }
    
    //read users status 
    public static String getUserRole(String userName) {
    	String output = "";
    	try(BufferedReader br = new BufferedReader(new FileReader(userFilePath))) {
    		String line = br.readLine();
    		while(line != null) {
    			if(line.contains(userName)) {
    				String strArr[] = line.split(":");
    				String role = strArr[2];
    				output = role.replaceAll("\\s","");
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
    
    public static String getAccountBalance(String acctNum) {
    	String output = "";
    	try(BufferedReader br = new BufferedReader(new FileReader(accBalFilePath))) {
    		String line = br.readLine();
    		while(line != null) {
    			if(line.contains(acctNum)) {
    				String strArr[] = line.split(":");
    				String role = strArr[1];
    				output = role.replaceAll("\\s","");
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
