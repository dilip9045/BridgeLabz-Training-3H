import java.util.*;

// ---------------- ABSTRACT ACCOUNT ----------------
abstract class Account {
    private final String accountNumber; // immutable
    protected double balance;
    protected String owner;

    public Account(String accountNumber, double balance, String owner) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
    }

    public final String getAccountNumber() { // final ensures immutability
        return accountNumber;
    }

    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public abstract void displayAccountType();
}

// ---------------- SUBCLASSES ----------------
class SavingsAccount extends Account {
    public SavingsAccount(String accNo, double bal, String owner) {
        super(accNo, bal, owner);
    }

    @Override
    public void displayAccountType() {
        System.out.println("Savings Account - " + getAccountNumber());
    }
}

class CurrentAccount extends Account {
    public CurrentAccount(String accNo, double bal, String owner) {
        super(accNo, bal, owner);
    }

    @Override
    public void displayAccountType() {
        System.out.println("Current Account - " + getAccountNumber());
    }
}

// ---------------- CUSTOMER (Aggregation) ----------------
class Customer {
    private String name;
    private List<Account> accounts = new ArrayList<>();

    public Customer(String name) { this.name = name; }

    public void addAccount(Account acc) { accounts.add(acc); }

    public List<Account> getAccounts() { return accounts; }

    public String getName() { return name; }
}

// ---------------- LOAN OPERATIONS INTERFACE ----------------
interface LoanOperations {
    void approveLoan();
    double calculateEMI();
    void closeLoan();
}

// ---------------- REPAYMENT SCHEDULE (Composition) ----------------
class RepaymentSchedule {
    private int tenureMonths;
    private double emiAmount;

    public RepaymentSchedule(int tenureMonths, double emiAmount) {
        this.tenureMonths = tenureMonths;
        this.emiAmount = emiAmount;
    }

    public int getTenureMonths() { return tenureMonths; }
    public double getEmiAmount() { return emiAmount; }
}

// ---------------- BASE LOAN ----------------
abstract class Loan implements LoanOperations {
    protected static double interestRate = 0.08; // static for all loans
    protected Customer customer;   // Association
    protected double principal;
    protected int tenureMonths;
    protected RepaymentSchedule repaymentSchedule; // Composition
    protected boolean active = true;

    public Loan(Customer customer, double principal, int tenureMonths) {
        this.customer = customer;
        this.principal = principal;
        this.tenureMonths = tenureMonths;
    }

    @Override
    public void approveLoan() {
        System.out.println("Loan approved for " + customer.getName());
    }

    @Override
    public void closeLoan() {
        active = false;
        System.out.println("Loan closed for " + customer.getName());
    }

    public RepaymentSchedule getRepaymentSchedule() {
        return repaymentSchedule;
    }

    // Common EMI simulator
    public void autoDebitEMI() {
        double emi = repaymentSchedule.getEmiAmount();
        for (Account acc : customer.getAccounts()) {
            if (acc.withdraw(emi)) {
                System.out.println("EMI of " + emi + " debited from " + acc.getAccountNumber());
                return;
            }
        }
        System.out.println("❌ Insufficient balance in all accounts for " + customer.getName());
    }
}

// ---------------- POLYMORPHISM: DIFFERENT LOAN TYPES ----------------
class HomeLoan extends Loan {
    public HomeLoan(Customer customer, double principal, int tenureMonths) {
        super(customer, principal, tenureMonths);
        repaymentSchedule = new RepaymentSchedule(tenureMonths, calculateEMI());
    }

    @Override
    public double calculateEMI() {
        // Lower interest rate for home loan
        double rate = interestRate - 0.02;
        return (principal * rate * Math.pow(1 + rate, tenureMonths)) /
               (Math.pow(1 + rate, tenureMonths) - 1);
    }
}

class CarLoan extends Loan {
    public CarLoan(Customer customer, double principal, int tenureMonths) {
        super(customer, principal, tenureMonths);
        repaymentSchedule = new RepaymentSchedule(tenureMonths, calculateEMI());
    }

    @Override
    public double calculateEMI() {
        double rate = interestRate;
        return (principal * rate * Math.pow(1 + rate, tenureMonths)) /
               (Math.pow(1 + rate, tenureMonths) - 1);
    }
}

class PersonalLoan extends Loan {
    public PersonalLoan(Customer customer, double principal, int tenureMonths) {
        super(customer, principal, tenureMonths);
        repaymentSchedule = new RepaymentSchedule(tenureMonths, calculateEMI());
    }

    @Override
    public double calculateEMI() {
        // Higher rate for personal loan
        double rate = interestRate + 0.03;
        return (principal * rate * Math.pow(1 + rate, tenureMonths)) /
               (Math.pow(1 + rate, tenureMonths) - 1);
    }
}

// ---------------- TESTING ----------------
public class BankLoanSystem {
    public static void main(String[] args) {
        Customer c1 = new Customer("Amit");
        Account acc1 = new SavingsAccount("S001", 20000, "Amit");
        Account acc2 = new CurrentAccount("C001", 5000, "Amit");
        c1.addAccount(acc1);
        c1.addAccount(acc2);

        Loan homeLoan = new HomeLoan(c1, 1000000, 120); // 10 years
        homeLoan.approveLoan();

        // Simulate 3 months auto-debit
        for (int i = 1; i <= 3; i++) {
            System.out.println("\nMonth " + i + " EMI debit:");
            homeLoan.autoDebitEMI();
        }
    }
}
