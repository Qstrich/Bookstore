import java.io.*;

public class AccountManager {
    private int maxAccounts;
    private int currentAccountNum;
    private Account[] accounts;
    public AccountManager(int maxAccounts){
        this.maxAccounts = maxAccounts;
        accounts = new Account[maxAccounts];
    }
    public boolean addCustomer(String name, String password){
        if(searchAccount(name)== null && currentAccountNum < maxAccounts){
            accounts[currentAccountNum] = new Customer(name, password);
            currentAccountNum++;
            return true;
        }
        return false;
    }
    public boolean addEmployee(String employeeKey, String name, String password){
        if(employeeKey.equals(Employee.EMPLOYEE_KEY) && searchAccount(name)== null && currentAccountNum < maxAccounts){
            accounts[currentAccountNum] = new Employee(name, password);
            currentAccountNum++;
            return true;
        }
        return false;
    }
    public void sortAccounts(){
          
    }
    public Account searchAccount(String name){

    }     
    
    public int searchAccount(Account acc){

    } 
    public void deleteAccount(Account delete){

    }
    public boolean loadFromFile(String fileName){
        
        int numAccounts;
        int type;
        String name;
        String password;
        double balance;
        boolean membership;


        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            numAccounts = Integer.parseInt(reader.readLine());
            for (int i = 0; i < numAccounts; i++) {
                // Read all information of the order from file
                type = Integer.parseInt(reader.readLine());
                name = reader.readLine();
                password = reader.readLine();
                balance = Double.parseDouble(reader.readLine());
                
                //Customer
                if(type == 0){
                    accounts[i] = new Customer(name, password);
                    membership = Boolean.parseBoolean(reader.readLine());
                    if(membership == true){
                        int memtype, day, month, year;    
                        memtype = Integer.parseInt(reader.readLine());
                        day = Integer.parseInt(reader.readLine());
                        month = Integer.parseInt(reader.readLine());
                        year = Integer.parseInt(reader.readLine());
                    }
                }
                //Employee
                else{


                }

            
            }
            reader.close();

            return true;
        }

        catch(IOException ioex) {
        }
    
        return false;
    }


    public boolean saveToFile(String fileName){
        
    }
}

