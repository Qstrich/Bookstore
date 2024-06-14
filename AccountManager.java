/*
 * Account.java
 * Jimmy Tao, Jason Medeiros 
 * June 8, 2024
 * A.Y. Jackson S.S.
 * 
 * The class contains the fields and methods for the class AccountManager
 */
import java.io.*;

public class AccountManager {
    //fields
    private int maxAccounts;
    private int currentAccountNum;
    private Account[] accounts;
    //bst helper arrays
    private int lc[], rc[];

    /*
     * AccountManager(int maxAccounts)
     * 
     * int maxAccounts - maximum number of accounts for the array
     * The constructor initialises all fields and sets the array according to the max size.
     */
    public AccountManager(int maxAccounts){
        this.maxAccounts = maxAccounts;
        accounts = new Account[maxAccounts];
        currentAccountNum = 0;

        //bst arrays (memory optimization)
        lc = new int[maxAccounts];
        rc = new int[maxAccounts];
    }
    
    /*
     * boolean addCustomer(String name, String password)
     * Return boolean - Returns the comparison of this name with the other name. 
     * 
     * The method compares 2 accounts by their name. 
     */
    public boolean addCustomer(String name, String password){
        if(searchAccount(name) == null && currentAccountNum < maxAccounts){
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

    /*
    *   add(int cur, Account ac)
    *   
    *   This method is a recursive method that traverses the binary tree until it has a place to add an item (O(n) worse case, O(logn) average)
    *   The binary tree is a template bst, with each node having two children, left smaller and right bigger
    */
    private void add(int cur, Account ac) {
        if (accounts[cur] == null) {
            accounts[cur] = ac;
            currentAccountNum++;
            return;
        }
        if (accounts[cur].compareToName(ac) < 0) {
            if (lc[cur] == 0) lc[cur] = currentAccountNum;
            add(lc[cur], ac);
        } else {
            if (rc[cur] == 0) rc[cur] = currentAccountNum;
            add(rc[cur], ac);
        }
    }

    /*
    *   int search(int cur, String name)
    *   returns - int (the index of the item to find)
    *   This method traverses the binary tree, looking for an element with the same usernmae
    */
    private int search(int cur, String name) {
        if (accounts[cur] == null) return -1;
        if (name.equals(accounts[cur].getName())) {
            return cur;
        } else if (accounts[cur].getName().compareTo(name) < 0) {
            if (lc[cur] != 0) return search(lc[cur], name);
        } else {
            if (rc[cur] != 0) return search(rc[cur], name);
        }
        return -1;
    }

    public Account searchAccount(String name){
        int i = search(0, name);
        if (i == -1) return null;
        return accounts[i];
    }     
    public int searchAccount(Account acc){
        return search(0, acc.getName());
    } 
    /* deleteAccount(Account ac)
     * This method deletes the specified account, traversing the children of the deleted node to find the next best node to replace it
     */
    public void deleteAccount(Account ac){
        currentAccountNum--;
        int cur = searchAccount(ac);
        if (cur == -1) return;
        if (lc[cur] == 0 && rc[cur] == 0) {
            accounts[cur] = null;
        }

        int left = getMaxLeft(cur);
        int right = getMinRight(cur); 
        
        if (left != cur && (accounts[left].getName().compareTo(accounts[right].getName()) > 0 || right == cur)) {
            accounts[cur] = accounts[left];
            accounts[left] = null;    
        } else if (right != cur) {
            accounts[cur] = accounts[right];
            accounts[right] = null;
        }
    }
    /* getMinRight(int cur)
     * Helper method for delete to find the next biggest element in the left subtree
     */
    public int getMinRight(int cur) {
        if (lc[cur] != 0) return getMinRight(lc[cur]);
        return cur;
    }
    /* getMinRight(int cur)
     * Helper method for delete to find the next biggest element in the right subtree
     */
    public int getMaxLeft(int cur) {
        if (rc[cur] != 0) return getMaxLeft(rc[cur]);
        return cur;
    }

    public boolean loadFromFile(String fileName){
        int type;
        String name;
        String password;
        double balance;
        boolean membership;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            int amt = Integer.parseInt(reader.readLine());
            for (int i = 0; i < amt; i++) {
                // Read all information of the order from file
                type = Integer.parseInt(reader.readLine());
                name = reader.readLine();
                password = reader.readLine();
                balance = Double.parseDouble(reader.readLine());
                
                //Customer
                if(type == 0){
                    addCustomer(name, password);
                    Account a = searchAccount(name);
                    a.setBalance(balance);
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
                                m = new BronzeMember(day, month, year, (Customer)a);
                                break;
                            case 2:
                                m = new SilverMember(day, month, year, (Customer)a);
                                break;
                            default:
                                m = new GoldMember(day, month, year, (Customer)a);
                                break;
                        }
                        ((Customer)a).setMembership(m);
                    }
                }
                //Employee
                else{
                    addEmployee(Employee.EMPLOYEE_KEY, name, password);
                    Account a = searchAccount(name);
                    a.setBalance(balance);
                }
            }
            reader.close();
            return true;
        } catch(IOException ioex) {
        }
    
        return false;
    }


    public boolean saveToFile(String fileName){
            
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
                writer.write("" + currentAccountNum);
                writer.newLine();

                // Save information of all orders to file
                for (int i = 0; i < currentAccountNum; i++) {
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

    public String toString() {
        String format = "\nCurrent number of accounts: " + currentAccountNum + "\nMaximum number of accounts: " + maxAccounts;
        for (int i = 0; i < currentAccountNum; i++) {
            format += "\n" + accounts[i] + "\n";
        }
        return format;
    }
}

