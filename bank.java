class BankAccount {
    private double balance;

    public BankAccount(double openingBalance) 
    {
        balance = openingBalance;
    }

    public void deposit(double amount) 
    {
        balance = balance + amount;
    }

    public void withdraw(double amount) 
    {
        balance = balance - amount;
    }

    public void display()
    {
        System.out.println("balance=" + balance);
    }
}

class BankApp
{
    public static void main(String[] args)
    {
        BankAccount bal = new BankAccount(100);

        System.out.println("Before transactions, ");
        bal.display();

        bal.deposit(74.35);
        bal.withdraw(20.00);

        System.out.println("After transactions, ");
        bal.display();
    }
}