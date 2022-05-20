package src.main.java.gui;

public class Librarian extends User{
    public Librarian(){
        this.name="";
        this.age=0;
        this.phoneNumber=0;
        this.address="";
        this.city="";
        this.username="";
        this.password="";
    }
    public Librarian( String name, int age, int phoneNumber, String address, String city, String username, String password){
        this.name=name;
        this.age=age;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.city=city;
        this.username=username;
        this.password=password;

    }

}
