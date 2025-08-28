package IF_ELSE;
import java.util.*;
public class onlineShopping {

    static int grocery(int n){

        Scanner scn= new Scanner(System.in);
        String [] arr = { "fruits " , "veg" , " drinks" , "beverage"};
        for( String val : arr){
            System.out.print(val + " ");
        }
        System.out.print(" \nchoose according to the index : ");

        int option1 = scn.nextInt();

        if( option1 == 1){
            n += 250;
        } else if (option1 == 2) {
            n += 300;
        } else if (option1 == 3) {
            n += 500;
        } else if (option1 == 4) {
            n += 600;
        }
        else{
            System.out.println("invalid !");
            grocery(n);
        }
         return n;
    }

    static int clothes(int n){
        Scanner scn= new Scanner(System.in);
        String [] arr = { "T- shirt" , " Pants" , " Paryt-wear" , " Shoes"};
        for( String val : arr){
            System.out.print(val + " ");
        }
        System.out.print(" \nchoose according to the index : ");

        int option1 = scn.nextInt();

        if( option1 == 1){
            n += 550;
        } else if (option1 == 2) {
            n += 1000;
        } else if (option1 == 3) {
            n += 1500;
        } else if (option1 == 4) {
            n += 600;
        }
        else{
            System.out.println("invalid !");
            clothes(n);
        }

        return n;

    }

    static int accesories( int n){
        Scanner scn= new Scanner(System.in);
        String [] arr = { "Phones " , "Watches" , " Cables" , "Electric-Materials"};
        for( String val : arr){
            System.out.print(val + " ");
        }
        System.out.print(" \nchoose according to the index : ");

        int option1 = scn.nextInt();

        if( option1 == 1){
            n += 1250;
        } else if (option1 == 2) {
            n += 600;
        } else if (option1 == 3) {
            n += 700;
        } else if (option1 == 4) {
            n += 600;
        }
        else{
            System.out.println("invalid !");
            accesories(n);
        }

        return n;
    }

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        Boolean ask = true;
        int wallet = 0;
        int balance = 0;
        while(ask){

            System.out.println("\nEnter option: 1.grocery 2.clothes 3.accesories 4.Exit");
            int option = scn.nextInt();

            if(option == 1){
                balance+=grocery(wallet);
            }
            else if(option == 2){
                balance+=clothes(wallet);
            }
            else if(option == 3){
                balance+=accesories(wallet);
            }
            else if(option == 4){

                if ( balance == 0){
                    System.out.println(" :: buy At least on resource :: ");
                }
                else {
                    System.out.println("total balance :" + balance);
                    if( balance > 5000){
                        System.out.println("after discout :"+ (balance-1000));
                    }
                    ask = false;
                    balance = 0;
                }
            }
            else{
                System.out.println("Invalid Option !!");
            }
        }

    }
}
