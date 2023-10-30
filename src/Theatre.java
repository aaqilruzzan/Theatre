import java.util.*;
import java.io.*;
public class Theatre {
    static int[] row1 = new int[12];
    static int[] row2 = new int[16];
    static int[] row3 = new int[20];
    static ArrayList<Ticket> ticketArrayList=new ArrayList<Ticket>();

    static void sort_tickets(){             // referenced from SD2 week8 sort search pdf,section - selection sort

            int minIndex;
            Ticket temp;
            for(int start = 0; start < ticketArrayList.size()-1; start++) {
                minIndex = start;
                for(int i = start+1; i <= ticketArrayList.size()-1; i++) {
                    if(ticketArrayList.get(i).getPrice() < ticketArrayList.get(minIndex).getPrice()) minIndex = i;
            }
                temp = ticketArrayList.get(start);
                ticketArrayList.set(start,ticketArrayList.get(minIndex));
                ticketArrayList.set(minIndex,temp);

        }
            for(Ticket t:ticketArrayList){
                t.print();
                System.out.println("\n");
            }

    }

    static String emailcheck() {    //to verify email format in buy ticket method
        Scanner input=new Scanner(System.in);
        String email= input.nextLine();
        while (true) {
            if (email.contains("@") && email.contains(".")) {
                break;
            } else{
                System.out.println("Email missing '@' or '.'  or both\nTry again");
                System.out.println("What is your email? ");
                email= input.nextLine();
            }
        } return email.toLowerCase();
    }

    static String namecheck(){   //to verify name,surname in buy ticket method
        Scanner input=new Scanner(System.in);
        String name= input.nextLine();
        while (true){
        if (name.matches("[a-zA-Z]+")) break; // referenced from https://stackoverflow.com/questions/7866320/how-to-match-letters-only-using-java-regex-matches-method
        else{
            System.out.println("Value should only contain letters\nTry again");
            System.out.println("Re enter your name/surname : ");
            name= input.nextLine();
        }
        }   return name;
    }
    static void load(){
        int count=0;
        ArrayList<Integer> rows=new ArrayList<>();
        try {
            File f=new File("theatre.txt");
            Scanner s = new Scanner(f) ;

            while (s.hasNextInt()){    // referenced from https:youtu.be/k9pf8KKPcwI
                rows.add(s.nextInt());
            }

        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();   // referenced from https://www.w3schools.com/
        }
        Integer[] array=rows.toArray(rows.toArray(new Integer[0]));

        for (int i = 0; i < row1.length; i++) {   // copying text file data to the row arrays
            row1[i] = array[i];
        }
        int x=0;
        for (int i = 12; i < 28; i++) {
            row2[x] = array[i];
            x+=1;
        }
        int y=0;
        for (int i = 28; i < array.length; i++) {
            row3[y] = array[i];
            y+=1;
        }
    }
    static void show_tickets_info(){
        int sum =0;
        for (Ticket t:ticketArrayList){
            t.print();
            sum+=t.getPrice();
            System.out.println("\n");
        }
        System.out.println("Total price is $ "+sum);
    }
    static void delobject(int row_num,int seat_num){ //taking in requested cancel seat row and seat numbers from cancel ticket method
                for(int x=0;x<ticketArrayList.size();x++){ // checking each object for the received row and seat numbers to remove it from the array
                    if(ticketArrayList.get(x).getRow()==row_num && ticketArrayList.get(x).getSeat()==seat_num){
                        ticketArrayList.remove(ticketArrayList.get(x));
                    }
                }
    }

    static void getdetails(int row_num,int seat_num,int price){
                   System.out.println("What is your name? ");
                   String name=namecheck();
                   System.out.println("What is your surname? ");
                   String surname=namecheck();
                   System.out.println("What is your email? ");
                   String email=emailcheck();
                   Person buyer=new Person(name,surname,email);
                   Ticket ticket=new Ticket(row_num,seat_num,price,buyer);
                   ticketArrayList.add(ticket);
    }

    static int[] checkrs() {
        int[]rs=new int[2];
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println(" Row number (1/2/3):");
            int row_num = 0;
            try {
                row_num = Integer.parseInt(input.nextLine());
            }
            catch (Exception e) {
                System.out.println("Incorrect value, Please only enter a number.");
                continue;
            }

            if (row_num < 1 || row_num > 3) {
                System.out.println("There is no such row\nTry again"); // verifying whether the row number is correct
                continue;
            }
            int seat_num = 0;
            while (true) {
                System.out.println(" Seat number (Row 1-12 seats\n Row2 2-16 seats\n Row3-20 seats):");

                try {
                    seat_num = Integer.parseInt(input.nextLine());
                } catch (Exception e) {
                    System.out.println("Incorrect value, Please only enter a number.");
                    continue;
                }
                break;
            }

            rs[0]=row_num;
            rs[1]=seat_num;
            int x;

            if(row_num==1) x=12;
            else if (row_num==2)
                x=16;
            else x=20;

            if (seat_num > x || seat_num < 1) { // checking whether seat number entered is correct based on the row
                System.out.println("There is no such seat in row " + row_num + "\nTry again");
            } else {
                break;
            }

        }return rs;

    }
/// Seat Prices set as- Row 1- 30, Row2- 20, Row 3- 10 all considered in $.
    static void buy_ticket(int row_num,int seat_num) {
        int rows[][]={row1,row2,row3};
        int rows_index=row_num-1;
       //  booking the requested seat if it's available

        if(rows[rows_index][seat_num-1]==0){
            int price= rows_index == 0 ? 30 : rows_index == 1 ? 20 : 10;
            getdetails(row_num,seat_num,price); // this method asks user information and creates and adds a new ticket to the ticket arraylist
                                                // this method is implemented for cleaner code
            System.out.println("Your Seat has been successfully booked");
            rows[rows_index][seat_num - 1] = 1;

        } else {
            System.out.println("Your Seat is unavailable. Try again"); // asking user to try again if the seat is already booked
        }

    }

        static void print_seating_area(){

        System.out.print(" ".repeat(4));System.out.print("***********\n\n");
        System.out.print(" ".repeat(4));System.out.print("*  STAGE  *\n\n");
        System.out.print(" ".repeat(4));System.out.print("***********\n\n");

            int mid_space=6;
            int start_space=4;
            int[][] rows={row1,row2,row3};
            for (int[] i:rows){
                System.out.print(" ".repeat(start_space));  // printing start space according to the pattern
                for (int x=0;x< i.length;x++){
                    if (x==mid_space) {System.out.print(" "); }    // printing mid space according to the pattern
                    if (i[x]==0) System.out.print("O");            // printing "O" if seat is available
                    else System.out.print("X");                    // printing "X" if seat is available
                }
                mid_space+=2;
                start_space-=2;
                System.out.println();
            }
        }

        static void cancel_ticket(int row_num,int seat_num){
            int rows[][]={row1,row2,row3};
            int rows_index=row_num-1;                                            //  cancelling the requested seat if it's booked

            if (rows[rows_index][seat_num - 1] == 1) {
                delobject(row_num,seat_num); // method removes relevant ticket object from the ticket arraylist
                                            // method is implemented for cleaner code
                System.out.println("Your Seat booking has been successfully cancelled");
                rows[rows_index][seat_num - 1] = 0;
            } else {
                System.out.println("This is an already available seat. Try again");//  asking user to try again if the seat is an available one
            }

    }
        static void show_available(){
        int[][]rows={row1,row2,row3};
        int row_no=1;
        for (int[]i:rows){
            System.out.print("Seats available in row "+row_no+": ");
            StringJoiner sj = new StringJoiner(", "); // referenced from https://www.w3schools.blog/java-8-stringjoiner-class
            for (int x=0;x< i.length;x++){
                if (i[x]==0){                                   //  printing seat number if it's array index corresponds
                    sj.add(Integer.toString(x + 1));                //  to 0 (available seat)
                }
            }
            System.out.println(sj + ".");
            row_no+=1;

        }
        }

        static void save(){
            try {
                File txtfile= new File("theatre.txt");
                txtfile.createNewFile();
                FileWriter writer= new FileWriter("theatre.txt");
                int[][]rows={row1,row2,row3};

                for (int[]i:rows){            // writing each array's information to the text file

                    for (int x:i){
                        writer.write(x+" ");
                    }
                    writer.write("\n");

                } writer.close();


            } catch (IOException e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }


        public static void main (String[]args ){
            Scanner input = new Scanner(System.in);
            int quit=1;
            System.out.println("Welcome to the New Theatre");
            System.out.println("-------------------------------------------------");
            System.out.println(" Please select an option:");
            System.out.print(" 1) Buy a ticket\n 2) Print seating area\n 3) Cancel ticket\n 4) List available seats\n " +
                    "5) Save to file\n 6) Load from file\n " +
                    "7) Print ticket information and total price\n 8) Sort tickets by price\n     0) Quit\n");
            System.out.println("-------------------------------------------------");


            while (quit==1) {
                System.out.println(" \nEnter option:");
                int option = 0;
                try {                                                   // validating option value
                    option = Integer.parseInt(input.nextLine());
                }catch (Exception e) {
                    System.out.println("Incorrect value, Please only enter a number.");
                    continue;
                }
                switch (option) {
                    case 1:
                        int rs[]=checkrs();
                        buy_ticket(rs[0],rs[1]);
                        break;
                    case 2:
                        print_seating_area();
                        break;
                    case 3:
                        int row_seat[]=checkrs();
                        cancel_ticket(row_seat[0],row_seat[1]);
                        break;
                    case 4:
                        show_available();
                        break;
                    case 5:
                        save();
                        break;
                    case 6:
                        load();
                        break;
                    case 7:
                        show_tickets_info();
                        break;
                    case 8:
                        sort_tickets();
                        break;
                    case 0:
                        quit=0;
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }
        }
    }