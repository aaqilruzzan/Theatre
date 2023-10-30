public class Person {

    // encapsulating class by setting attributes to private and only allow external access to it indirectly via public methods
    private String name;
    private String surname;
    private String email;

    public Person(String name,String surname,String email){
        this.name=name;
        this.surname=surname;
        this.email=email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }
}
