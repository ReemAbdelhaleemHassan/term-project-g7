package GUI;

import java.util.Date;

public class BorrowedBookInfo {
    int isbn;
    int user_id;
    Date borrowDate;
    Date returnDate;
    public BorrowedBookInfo(){
        this.isbn = 0;
        this.user_id = 0;
        this.borrowDate=null;
        this.returnDate=null;
    }
    public BorrowedBookInfo(int isbn, int user_id, Date borrowDate, Date returnDate){
        this.isbn = isbn;
        this.user_id = user_id;
        this.borrowDate=borrowDate;
        this.returnDate=returnDate;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
