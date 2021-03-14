package responsemodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetBookResponseForAuthor {
    @JsonProperty("book_name")
    public String bookName;
    public String isbn;
    public String aisle;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }


}
