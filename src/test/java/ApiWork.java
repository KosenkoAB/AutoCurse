import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class ApiWork {

    @Test
    @Tag("api")
    void rest (){
        given()
                .baseUri("http://localhost:8080")
                .queryParam("page", 0)
                .queryParam("size", 10)
                .auth()
                .basic("admin","secret123")
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods",notNullValue());
    }


    private RequestSpecification asd = new RequestSpecBuilder()
            .setBaseUri("http://localhost:8080")
            .log(LogDetail.ALL)
            .build();

    @Test
    @Tag("api")
    void spec (){
        Response response = (Response) given()
                .spec(asd)
                .queryParam("page", 0)   // сервер ожидает query-параметры ?page=..&size=..
                .queryParam("size", 10)
                .auth()
                .basic("admin","secret123")
                .contentType(ContentType.JSON)
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods",notNullValue());
    }




//    void rest (){
//        Response response = given()
//                .spec(asd)
//                .queryParam("size", 0)
//                .get("/goods/list")
//                    .then()
//                    .log().all()
//                    .statusCode(200);
//
//
//    }



}
