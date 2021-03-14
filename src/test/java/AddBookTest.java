import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import requestmodel.AddBookRequest;
import responsemodel.AddBookResponse;
import responsemodel.GetBookResponse;

import static io.restassured.RestAssured.given;


public class AddBookTest extends BaseTest {
    @Test
    public void addBookAndValidateWithID() {
        RestAssured.baseURI = "http://216.10.245.166";
        AddBookResponse addBookResponse = addBookResponse("Photoshop", "909", "101", "Rekhi");
        Response getBookResponse = getBookResponseForID(addBookResponse.getId(),200);
        GetBookResponse[] book = getBookResponse.as(GetBookResponse[].class);
        Assert.assertEquals(book[0].getAuthorName(), "Rekhi", "Get Book message incorrect");
    }

    @Test
    public void verifyBookAlreadyExists() {
        RestAssured.baseURI = "http://216.10.245.166";
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setName("Photoshop");
        addBookRequest.setIsbn("909");
        addBookRequest.setAisle("101");
        addBookRequest.setAuthor("Rekhi");
        Response responseAddBook = given().header("Content-Type", "application/json")
                .body(addBookRequest)
                .when().post("/Library/Addbook.php").then()
                .statusCode(404).extract().response();
        String bookResponseAlreadyExists = responseAddBook.asString().substring(8, 69);
        Assert.assertEquals(bookResponseAlreadyExists, "Add Book operation failed, looks like the book already exists");
    }

}
