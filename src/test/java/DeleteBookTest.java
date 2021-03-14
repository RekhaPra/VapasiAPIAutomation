import com.fasterxml.jackson.annotation.JsonProperty;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import responsemodel.AddBookResponse;
import responsemodel.GetBookResponse;

import static io.restassured.RestAssured.given;

public class DeleteBookTest extends BaseTest{


    @Test
    public void deleteBook() {
        RestAssured.baseURI = "http://216.10.245.166";

        AddBookResponse bookResponse = addBookResponse("Hi","58","67","Rajee");
        Assert.assertEquals(bookResponse.getMsg(),"successfully added","Add Book message incorrect");

        Response getBookResponse = getBookResponseForID(bookResponse.getId(),200);
        GetBookResponse[] book = getBookResponse.as(GetBookResponse[].class);
        Assert.assertEquals(book[0].getAuthorName(),"Rajee","Get Book message incorrect");

        Response delResponse = deleteBook(bookResponse.getId());
        String deleteOutput = delResponse.asString().substring(8,36);
        Assert.assertEquals(deleteOutput, "book is successfully deleted", "Delete Book message incorrect");

        getBookResponse = getBookResponseForID(bookResponse.getId(),404);
        deleteOutput = getBookResponse.prettyPrint().substring(14,73);
        Assert.assertEquals(deleteOutput, "The book by requested bookid / author name does not exists!", "Get Book message incorrect");

       // addBookResponse = addBookResponse("Goal","45","56","Row");
       // Assert.assertEquals(addBookResponse.getMsg(),"successfully added","Add Book message incorrect");

        /**AddBookResponse addBookResponse  = addBookResponse("Fun Learning","12","34","GG");
        System.out.println(addBookResponse.getId());
        String bookDeleted = deleteBook(addBookResponse.getId()).asString().substring(8,36);
        Assert.assertEquals(bookDeleted,"book is successfully deleted");**/
    }

}
