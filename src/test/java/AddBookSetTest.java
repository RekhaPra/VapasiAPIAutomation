import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import responsemodel.AddBookResponse;
import responsemodel.GetBookResponseForAuthor;

public class AddBookSetTest extends BaseTest{

        @Test
        public void addBooks() {
            RestAssured.baseURI = "http://216.10.245.166";

            AddBookResponse response = addBookResponse("Bangle1","2021","2223","March");
            Assert.assertEquals(response.getMsg(), "successfully added", "Add Book message incorrect");

            response = addBookResponse("Bangle2", "2425", "2627", "March");
            Assert.assertEquals(response.getMsg(), "successfully added", "Add Book message incorrect");

            response = addBookResponse("Bangle3", "2829", "3031", "March");
            Assert.assertEquals(response.getMsg(), "successfully added", "Add Book message incorrect");

            System.out.println("Added 3 books with author as March");

            GetBookResponseForAuthor[] books = getBookResponseForAuthorName("March");
            Assert.assertEquals(books[0].getBookName(), "Bangle1", "Get Book message incorrect");
            Assert.assertEquals(books[1].getBookName(), "Bangle2", "Get Book message incorrect");
            Assert.assertEquals(books[2].getBookName(), "Bangle3", "Get Book message incorrect");

            System.out.println("Number of books returned for author March is = " + books.length);
            for (int i = 0; i < books.length; i++) System.out.println(books[i].getBookName());
            String id = books[0].getIsbn().concat(books[0].getAisle());

            Response deleteResponse = deleteBook(id);
            String deleteOutput = deleteResponse.asString().substring(8, 36);
            Assert.assertEquals(deleteOutput, "book is successfully deleted", "Delete Book message incorrect");
            System.out.println("Book with id " + id + " successfully deleted");

            GetBookResponseForAuthor[] book = getBookResponseForAuthorName("March");
            Assert.assertEquals(book[0].getBookName(), "Bangle2", "Get Book message incorrect");
            Assert.assertEquals(book[1].getBookName(), "Bangle3", "Get Book message incorrect");
            System.out.println("Number of books returned for author March is = " + book.length);
            for (int i = 0; i < book.length; i++)
                System.out.println("Book details : book" + (i + 1) + " " + book[i].getBookName());

        }


        /**@Test public void bookByAuthorName(){
        RestAssured.baseURI = "http://216.10.245.166";
        Response response = given().queryParam("AuthorName","REKPRA")
        .header("Content-Type","application/json")
        .when().get("/Library/GetBook.php").then()
        .statusCode(200).extract().response();
        GetBookResponseForAuthor[] books = response.as(GetBookResponseForAuthor[].class);
        Assert.assertEquals(books[0].getBookName(),"Book1","Get Book message incorrect");
        Assert.assertEquals(books[1].getBookName(),"Book2","Get Book message incorrect");
        Assert.assertEquals(books[2].getBookName(),"Book3","Get Book message incorrect");
        System.out.println("Number of books returned for author REKPRA is = "+ books.length);
        for (int i=0;i<books.length;i++) System.out.println(books[i].getBookName());
        String id = books[0].getIsbn().concat(books[0].getAisle());
        System.out.println(id);
        Response deleteResponse = deleteBook(id);
        String deleteOutput = deleteResponse.asString().substring(8,36);
        Assert.assertEquals(deleteOutput, "book is successfully deleted", "Delete Book message incorrect");
        System.out.println("Book with id "+ id + " successfully deleted");
        }**/

    }

