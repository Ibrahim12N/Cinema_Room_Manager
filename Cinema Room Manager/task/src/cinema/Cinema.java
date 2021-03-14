package cinema;

import java.util.Scanner;

public class Cinema {
    static int purchasedTickets=0;
    static double purchasedTicketsPer=0;
    static Scanner scanner;
    static char [][] spaces =new char [0][0];
    static int seatNum=-1;
    static int rowsNum=-1;
    public static void main(String[] args) {
        scanner=new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows=scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats=scanner.nextInt();
        spaces =new char [rows][seats];
        int menuOp;
        int income;
        int sum=0;
        createSeatsArr(spaces,seats,rows,seatNum,rowsNum);
        while (true) {
            showTheMenu();
            menuOp = scanner.nextInt();
            switch (menuOp) {
            case 1:
                showSeats(spaces,seats, rows);
                break;
            case 2:
                income= ticketPrice(seats, rows, rowsNum);
                sum+=income;
                break;
            case 3:
                statistics(seats,rows,purchasedTickets,purchasedTicketsPer,sum);
                break;
            case 0:
                return;
            }
        }
    }

    public static boolean checkPurchase(char[][] spaces, int seats, int rows, int seatNum, int rowsNum) {
        boolean res=false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                    if (i == rowsNum - 1 && j == seatNum - 1&& spaces[i][j] == 'B'){
                        System.out.println("That ticket has already been purchased!");
                        res=false;
                    break;
                } else
                   res=true ;
            }
            if(!res)
            break;
        }
        return res;
    }
    public static char[][] createSeatsArr(char[][] spaces, int seats, int rows, int seatNum, int rowsNum) {
        for(int i=0;i<rows;i++) {
            for (int j = 0; j < seats; j++) {

                if (i == rowsNum - 1 && j == seatNum - 1) {
                    spaces[i][j] = 'B';
                    } else if (spaces[i][j]!='B'){
                    spaces[i][j] = 'S';
                }
            }
        }
        return spaces;
    }

    public static void statistics(int seats, int rows, int purchasedTickets,double purchasedTicketsPer, int sum) {
        System.out.println("Number of purchased tickets: "+purchasedTickets);
        int totalSeats=seats*rows;
        System.out.printf("Percentage: %.2f%% %n",purchasedTicketsPer*100);
        System.out.printf("Current income: $%d%n",sum);
        int totalIncome;
        if(totalSeats<=60)
            totalIncome=totalSeats*10;
        else
            totalIncome=((rows/2)*10+(rows-rows/2)*8)*seats;
        System.out.printf("Total income: $%d%n",totalIncome);
    }

    public static void showTheMenu() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static void showSeats(char [][] spaces, int seats, int rows) {
        System.out.print("Cinema:\n  ");
        for (int i = 1; i <= seats; i++)
            System.out.print(i + " ");

        for (int i = 0; i < rows; i++) {
            System.out.print("\n" + (i + 1) + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(spaces[i][j] + " ");
            }
        }
    }
    public static int ticketPrice (int seats, int rows, int rowsNum) {
        purchasedTickets++;
        purchasedTicketsPer= (double) purchasedTickets/(double)(seats*rows);
        boolean purchased=false;

        while(!purchased){
            boolean bonds=true;
            while (bonds) {
                System.out.println("Enter a row number:");
                rowsNum = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                seatNum = scanner.nextInt();
                if(rowsNum>rows || seatNum>seats){
                    bonds=true;
                    System.out.println("Wrong input!");}
                else
                    bonds=false;
            }
            purchased=checkPurchase(spaces,seats,rows,seatNum,rowsNum);
        }
        createSeatsArr(spaces,seats,rows,seatNum,rowsNum);

        int totalSeats = rows * seats;
        if (totalSeats <= 60){
            System.out.printf("Ticket price:$%d%n", 10);
        return 10;}
        else  {
           if (rowsNum<=rows/2){
               System.out.printf("Ticket price:$%d%n", 10);
           return 10;}
           else{
               System.out.printf("Ticket price:$%d%n", 8);
           return 8;}
        }
    }
}