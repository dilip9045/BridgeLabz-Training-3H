package OOPS.Encapsulation;
import java.util.Scanner;

public class BankAccountMain {

    public static void main(String[] args) {

        System.out.println("============= Create Your Account ==============");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        System.out.print("Enter Balance: ");
        int balance = sc.nextInt();

        System.out.print("Set Account PIN: ");
        int pin = sc.nextInt();

        BankAccount bank = new BankAccount(accNo, balance, pin);

        System.out.println("1. deposit");
        System.out.println("2. Credit");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                bank.deposit();
                break;
            case 2:
                bank.credit();
                break;
            default:
                System.out.println("You made a invalid choice");
        }

        System.out.println("1. Account Number \n2. Balance \n3. Pin");
        int result = sc.nextInt();
        switch(result){
            case 1:
                System.out.println(bank.getAccountNumber());
                break;
            case 2:
                System.out.println(bank.getBalance());
                break;
            case 3:
                System.out.println(bank.getPin());
                break;
            default:
                System.out.println("You made a invalid choice");
        }
    }

}
