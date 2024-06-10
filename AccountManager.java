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
    public void deleteAccount(Account delete){

    }
    public boolean loadFromFile(String fileName){

    }
    public boolean saveToFile(String fileName){
        
    }
}

