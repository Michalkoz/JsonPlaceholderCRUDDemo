package Homework.pierwsza_czesc_zadania;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderObjectJsonPUTPATCHTest {

    private static Faker faker;
    private String fakeTitle;
    private String fakeBody;

    @BeforeAll
    public static void beforeAll(){
        faker = new Faker();
    }

    @BeforeEach
    public void beforeEach(){
        fakeTitle = faker.book().title();
        fakeBody = faker.lorem().fixedString(50);
    }

    @Test
    public void jsonplaceholderUpdateUserPUTTest(){

        JSONObject post = new JSONObject();
        post.put("userId", 1);
        post.put("id", 1);
        post.put("title", fakeTitle);
        post.put("body", fakeBody);

        Response response = given()
                .contentType("application/json")
                .body(post.toString())
                .when()
                .put("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals(fakeTitle, json.get("title"));
        assertEquals(fakeBody, json.get("body"));
    }

    @Test
    public void jsonplaceholderUpdateUserPATCHTest(){

        JSONObject post = new JSONObject();
        post.put("title", fakeTitle);

        Response response = given()
                .contentType("application/json")
                .body(post.toString())
                .when()
                .patch("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals(fakeTitle, json.get("title"));

    }

}
