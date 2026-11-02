
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class ApiWork {

    @Test
    @Tag("api")
    void rest() {
        given()
                .baseUri("http://localhost:8080")
                .queryParam("page", 0)
                .queryParam("size", 10)
                .auth()
                .basic("admin", "secret123")
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods", notNullValue());
    }

    private RequestSpecification asd = new RequestSpecBuilder()
            .setBaseUri("http://localhost:8080")
            .setAuth(basic("admin", "secret123"))
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Test
    @Tag("api")
    void spec() {
        given()
                .spec(asd)
                .queryParam("page", 0)
                .queryParam("size", 10)
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods", notNullValue());
    }


    public record Item(String name, Double price) {
    }

    //Для второго теста переменные цены и наименования
    String name = "RasBerry";
    Double price = 8.2;

    Item2 newItem = new Item2(name, price);

    @Test
    @Tag("api")
    void add() {
        given()
                .spec(asd)
                .contentType(ContentType.JSON)
                .body(newItem)
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200);

        given()
                .spec(asd)
                .queryParam("page", 0)
                .queryParam("size", 10)
                .contentType(ContentType.JSON)
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods", notNullValue());


    }


    public record Item2(String name, Double price) {
    }

    //Для второго теста переменные цены и наименования
    String name2 = "Пиво";
    Double price2 = 7.2;

    Item2 newItem2 = new Item2(name2, price2);

    @Test
    @Tag("api")
    void addAssert() {
        given()
                .spec(asd)
                .contentType(ContentType.JSON)
                .body(newItem2)
                .post("/goods/add");

        Response response = given()
                .spec(asd)
                .queryParam("page", 0)
                .queryParam("size", 10)
                .contentType(ContentType.JSON)
                .get("/goods/list");

        Assertions.assertThat(response.statusCode())
                .as("Код ответа должен быть 200, 201")
                .isBetween(200, 201);

        Assertions.assertThat(response.jsonPath().getList("goods.name", String.class))
                .as("товары с именем Тест присутствует")
                .contains(name2);

        int beerId = response.jsonPath().getInt("goods.find { it.name == '" + name2 + "' }.id");

        given()
                .spec(asd)
                .pathParam("id", beerId)
                .when()
                .delete("/goods/{id}")
                .then()
                .log().all()
                .statusCode(200);


    }


    private RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri("http://localhost:8080")
//            .setAuth(basic("admin","secret123"))
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Test
    @Tag("api")
    void postAdd400() {
        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(newItem2)
                .post("/goods/add");


        Assertions.assertThat(response.statusCode())
                .as("Код ответа должен быть 400, 401")
                .isBetween(400, 401);

    }

    @Test
    @Tag("api")
    void getGoodsId200() {
        given()
                .spec(asd)
                .contentType(ContentType.JSON)
                .body(newItem2)
                .post("/goods/add");

        Response response = given()
                .spec(asd)
                .queryParam("page", 0)
                .queryParam("size", 10)
                .contentType(ContentType.JSON)
                .get("/goods/list");

        int uid = response.jsonPath().getInt("goods.find { it.name == '" + name2 + "' }.id");

        given()
                .spec(asd)
                .contentType(ContentType.JSON)
                .queryParam("id", uid)
                .get("/goods/");

        Assertions.assertThat(response.statusCode())
                .as("Код ответа должен быть 200, подставь id из void spec")
                .isEqualTo(200);

        given()
                .spec(asd)
                .pathParam("id", uid)
                .when()
                .delete("/goods/{id}")
                .then()
                .log().all()
                .statusCode(200);

    }

    @Test
    @Tag("api")
    void getGoodsId404() {
        Response response = given()
                .spec(asd)
                .contentType(ContentType.JSON)
                .queryParam("id", 1000)
                .get("/goods/");

        Assertions.assertThat(response.statusCode())
                .as("Код ответа должен быть 404")
                .isEqualTo(404);
    }


    @Test
    @Tag("api")
    void delete200() {
        given()
                .spec(asd)
                .contentType(ContentType.JSON)
                .body(newItem2)
                .post("/goods/add");

        Response response = given()
                .spec(asd)
                .queryParam("page", 0)
                .queryParam("size", 10)
                .contentType(ContentType.JSON)
                .get("/goods/list");

        int beerId = response.jsonPath().getInt("goods.find { it.name == '" + name2 + "' }.id");

        given()
                .spec(asd)
                .pathParam("id", beerId)
                .when()
                .delete("/goods/{id}")
                .then()
                .log().all()
                .statusCode(200);

        Assertions.assertThat(response.statusCode())
                .as("Код ответа должен быть 200")
                .isEqualTo(200);
    }

    @Test
    @Tag("api")
    void deleteId404() {
        Response response = given()
                .spec(asd)
                .contentType(ContentType.JSON)
                .queryParam("id", 1000)
                .delete("/goods/");

        Assertions.assertThat(response.statusCode())
                .as("Код ответа должен быть 404")
                .isEqualTo(404);

    }




    public record Item3(String name, Double price) {
    }
    String name3 = "Пивас";
    Double price3 = 2.3;

    Item3 newItem3 = new Item3(name3, price3);

    @Test
    @Tag("api")
    void patch200() {
        given()
                .spec(asd)
                .contentType(ContentType.JSON)
                .body(newItem2)
                .post("/goods/add");

        Response response = given()
                .spec(asd)
                .queryParam("page", 0)
                .queryParam("size", 10)
                .contentType(ContentType.JSON)
                .get("/goods/list");

        int beerId = response.jsonPath().getInt("goods.find { it.name == '" + name2 + "' }.id");

        Response response2 =  given()
                .spec(asd)
                .pathParam("id", beerId)
                .body(newItem3)
                .when()
                .patch("/goods/{id}");

        Assertions.assertThat(response2.statusCode())
                .as("Код ответа должен быть 200,204")
                .isBetween(200,204);

        Assertions.assertThat(response2.jsonPath().getString("name"))
                .as("Имя должно обновиться на Пивас")
                .isEqualTo(name3);

        Assertions.assertThat(response2.jsonPath().getDouble("price"))
                .as("Цена должна обновиться")
                .isEqualTo(price3);

        given()
                .spec(asd)
                .pathParam("id", beerId)
                .when()
                .delete("/goods/{id}")
                .then()
                .log().all()
                .statusCode(200);

    }



    @Test
    @Tag("api")
    void patch400() {
        given()
                .spec(asd)
                .contentType(ContentType.JSON)
                .body(newItem2)
                .post("/goods/add");

        Response response = given()
                .spec(asd)
                .queryParam("page", 0)
                .queryParam("size", 10)
                .contentType(ContentType.JSON)
                .get("/goods/list");

        int beerId = response.jsonPath().getInt("goods.find { it.name == '" + name2 + "' }.id");

        Response response2 =  given()
                .spec(spec)
                .pathParam("id", beerId)
                .body(newItem3)
                .when()
                .patch("/goods/{id}");

        Assertions.assertThat(response2.statusCode())
                .as("Код ответа должен быть 401")
                .isEqualTo(401);

        given()
                .spec(asd)
                .pathParam("id", beerId)
                .when()
                .delete("/goods/{id}")
                .then()
                .log().all()
                .statusCode(200);

    }

    @Test
    @Tag("api")
    void patch404() {

        Response response2 =  given()
                .spec(asd)
                .pathParam("id", 123)
                .body(newItem3)
                .when()
                .patch("/goods/{id}");

        Assertions.assertThat(response2.statusCode())
                .as("Код ответа должен быть 404")
                .isEqualTo(404);

    }


}