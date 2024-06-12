import java.io.*;

public class AccountManager {
    private int maxAccounts;
    private int currentAccountNum;
    private Account[] accounts;

    private int lc[], rc[];
    public AccountManager(int maxAccounts){
        this.maxAccounts = maxAccounts;
        accounts = new Account[maxAccounts];
        currentAccountNum = 0;

        //bst
        lc = new int[maxAccounts];
        rc = new int[maxAccounts];
    }
    //helper add method bbst
    private void add(int cur, Account ac) {
        if (accounts[cur] == null) {
            accounts[cur] = ac;
            return;
        }
        if (accounts[cur].compareToName(ac) < 0) {
            if (lc[cur] == 0) lc[cur] = ++currentAccountNum;
            add(lc[cur], ac);
        } else {
            if (rc[cur] == 0) rc[cur] = ++currentAccountNum;
            add(rc[cur], ac);
        }
    }
    public boolean addCustomer(String name, String password){
        if(searchAccount(name)== null && currentAccountNum < maxAccounts){
            add(0, new Customer(name, password));
            return true;
        }
        return false;
    }
    public boolean addEmployee(String employeeKey, String name, String password){
        if(employeeKey.equals(Employee.EMPLOYEE_KEY) && searchAccount(name)== null && currentAccountNum < maxAccounts){
            add(0, new Employee(name, password));
            return true;
        }
        return false;
    }
    //bbst helper query
    private int search(int cur, String name) {
        if (name.equals(accounts[cur].getName())) {
            return cur;
        }
        if (name.compareTo(accounts[cur].getName()) < 0) {
            if (lc[cur] == 0) return -1;
            search(lc[cur], name);
        } else {
            if (rc[cur] == 0) return -1;
            search(rc[cur], name);
        }
        return -1;
    }

    //
    public Account searchAccount(String name){
        int i = search(0, name);
        if (i == -1) return null;
        return accounts[i];
    }     
    public int searchAccount(Account acc){
        return search(0, acc.getName());
    } 
    public void deleteAccount(Account delete){
        
    }
    public boolean loadFromFile(String fileName){
        int type;
        String name;
        String password;
        double balance;
        boolean membership;


        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            currentAccountNum = Integer.parseInt(reader.readLine());
            for (int i = 0; i < currentAccountNum; i++) {
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
                        Membership m;
                        switch (memtype) {
                            case 1:
                                m = new BronzeMember(day, month, year, (Customer)accounts[i]);
                                break;
                            case 2:
                                m = new SilverMember(day, month, year, (Customer)accounts[i]);
                                break;
                            default:
                                m = new GoldMember(day, month, year, (Customer)accounts[i]);
                                break;
                        }
                        ((Customer)accounts[i]).setMembership(m);
                    }
                }
                //Employee
                else{
                    accounts[i] = new Employee(name, password);
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
            
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
                writer.write("" + currentOrderNum);
                writer.newLine();

                // Save information of all orders to file
                for (int i = 0; i < currentOrderNum; i++) {
                    Account cur = accounts[i];
                    
                    if(cur instanceof Employee){
                        writer.write("1");
                        writer.newLine();
                        writer.write(cur.getName());
                        writer.newLine();                        
                        writer.write(cur.getPassword());
                        writer.newLine();                        
                        writer.write(""+cur.getBalance());
                        writer.newLine();
                    }
                    else{
                        writer.write("0");
                        writer.newLine();
                        writer.write(cur.getName());
                        writer.newLine();                        
                        writer.write(cur.getPassword());
                        writer.newLine();                        
                        writer.write(""+cur.getBalance());
                        writer.newLine();
                        Membership mbr = ((Customer)cur).getMembership();
                        if(mbr == null){
                            writer.write("false");
                            writer.newLine();
                        }
                        else{
                            writer.write("true");
                            writer.newLine();                            
                            writer.write(""+mbr.getIssueDay());
                            writer.newLine();                            
                            writer.write(""+mbr.getIssueMonth());
                            writer.newLine();                            
                            writer.write(""+mbr.getIssueYear());
                            writer.newLine();

                        }
                    }
                   
                }
                writer.close();

                return true;
            } 
            
            catch (IOException ioex) {
            }
         
        return false;
    }
}

