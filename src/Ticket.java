public class Ticket {
    // encapsulating class by setting attributes to private and only allow external access to it indirectly via public methods
    private int row;
    private int seat;
    private double price;
    private Person Person;

    public Ticket(int row,int seat,double price,Person Person){
        this.row=row;
        this.seat=seat;
        this.price=price;
        this.Person=Person;
    }

    public void print(){
        System.out.println("Name: "+this.Person.getName());
        System.out.println("Surname: "+this.Person.getSurname());
        System.out.println("Email: "+this.Person.getEmail());
        System.out.println("Row: "+this.row);
        System.out.println("Seat: "+this.seat);
        System.out.println("Price: "+this.price);
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }
}
