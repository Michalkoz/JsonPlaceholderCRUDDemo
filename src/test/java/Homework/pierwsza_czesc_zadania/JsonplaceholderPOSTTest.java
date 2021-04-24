package Homework.pierwsza_czesc_zadania;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderPOSTTest {

    @Test
    public void jsonplaceholderCreateNewPost(){

        String jsonBody = "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 101,\n" +
                "    \"title\": \"stopierwszy Tytul\",\n" +
                "    \"body\": \"sto pierwsze Body\"\n" +
                "  }";
        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals("stopierwszy Tytul", json.get("title"));
        assertEquals("sto pierwsze Body", json.get("body"));
    }
}
