package Homework.pierwsza_czesc_zadania;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderRawJsonPUTPATCHTest {


    @Test
    public void jsonplaceholderUpdateUserPUTTest(){

        String jsonBody = "{\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 1,\n" +
                "    \"title\": \"pierwszy PUTowy Tytul\",\n" +
                "    \"body\": \"pierwsze PUTowe Body\"\n" +
                "  }";

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .put("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals( "pierwszy PUTowy Tytul", json.get("title"));
        assertEquals("pierwsze PUTowe Body", json.get("body"));
    }

    @Test
    public void jsonplaceholderUpdateUserPATCHTest(){

        String jsonBody = "  {\n" +
                "    \"title\": \"pierwszy PATCHowy Tytul\"\n" +
                "  }";

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .patch("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals( "pierwszy PATCHowy Tytul", json.get("title"));

    }

}
