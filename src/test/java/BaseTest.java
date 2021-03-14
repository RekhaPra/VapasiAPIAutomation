import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import requestmodel.AddBookRequest;
import responsemodel.AddBookResponse;
import responsemodel.GetBookResponseForAuthor;

import static io.restassured.RestAssured.given;

public class BaseTest {

    public AddBookResponse addBookResponse(String bookName, String isbn, String aisle, String authorName){
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setName(bookName);
        addBookRequest.setIsbn(isbn);
        addBookRequest.setAisle(aisle);
        addBookRequest.setAuthor(authorName);
        Response responseAddBook = given().header("Content-Type","application/json")
                .body(addBookRequest)
                .when().post("/Library/Addbook.php").then()
                .statusCode(200).extract().response();
        AddBookResponse addBookResponse = responseAddBook.body().as(AddBookResponse.class);
        return addBookResponse;
    }

    public Response getBookResponseForID(String id, int StatusCode){
        Response getBookResponse = given().queryParam("ID",id)
                .header("Content-Type","application/json")
                .when().get("/Library/GetBook.php").then()
                .statusCode(StatusCode).extract().response();
        return getBookResponse;
    }

    public GetBookResponseForAuthor[] getBookResponseForAuthorName(String name){
        RestAssured.baseURI = "http://216.10.245.166";
        Response getBookResponse = given().queryParam("AuthorName",name)
                .header("Content-Type","application/json")
                .when().get("/Library/GetBook.php").then()
                .statusCode(200).extract().response();
        GetBookResponseForAuthor[] books = getBookResponse.as(GetBookResponseForAuthor[].class);
        return books;
    }

    public Response deleteBook(String id){
        JSONObject requestParamsDelete = new JSONObject();
        requestParamsDelete.put("ID",id);

        Response deleteResponse = given().header("Content-Type","application/json")
                .body(requestParamsDelete.toJSONString())
                .when().post("/Library/DeleteBook.php")
                .then()
                .statusCode(200).extract().response();
        return deleteResponse;
    }
}
