package src.main.java.gui;
public class BookInfo {
    int isbn;
    String book_name;
    String genre;
    String author;
    String publisher;
    int quantity;
    public BookInfo(){
        this.isbn = 0;
        this.book_name = "";
        this.genre = "";
        this.author ="";
        this.publisher = "";
        this.quantity = 0;

    }
    public BookInfo(int isbn, String book_name,String genre, String author, String publisher, int quantity) {
        this.isbn = isbn;
        this.book_name = book_name;
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;

    }


    public int getIsbn() {
        return isbn;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

