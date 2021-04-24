package Homework.pierwsza_czesc_zadania;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
public class JsonplaceholderBeautyWithThenGETTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "posts";

    // GET do kolekcji postów
    @Test
    public void jsonplaceholderReadAllPosts(){

        Response response = given()
                .when()
                .get(BASE_URL + "/" + POSTS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<Object> titles = json.getList("title");
        titles.stream()
                .map(x -> "title " + titles.indexOf(x) + " = " + x)
                .forEach(System.out::println);
        assertEquals(100, titles.size());
    }

    // GET do jednego posta
    @Test
    public void jsonplaceholderReadOnePost(){
        Response response = given()
                .when()
                .get(BASE_URL + "/" + POSTS + "/3")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("ea molestias quasi exercitationem repellat qui ipsa sit aut", json.get("title"));
        assertEquals("et iusto sed quo iure" +
                "\nvoluptatem occaecati omnis eligendi aut ad" +
                "\nvoluptatem doloribus vel accusantium quis pariatur" +
                "\nmolestiae porro eius odio et labore et velit aut", json.get("body"));

    }

    // PATH VARIABLE
    @Test
    public void jsonplaceholderReadPostUserWithPathVariable() {
        Response response = given()
                .pathParam("postId", 3)
                .when()
                .get(BASE_URL + "/" + POSTS + "/{postId}")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.asString());

        JsonPath json = response.jsonPath();
        assertEquals("ea molestias quasi exercitationem repellat qui ipsa sit aut", json.get("title"));
        assertEquals("et iusto sed quo iure" +
                "\nvoluptatem occaecati omnis eligendi aut ad" +
                "\nvoluptatem doloribus vel accusantium quis pariatur" +
                "\nmolestiae porro eius odio et labore et velit aut", json.get("body"));
    }

    // QUERY PARAMS
    @Test
    public void jsonplaceholderReadOnePostWithQueryParamByTitle() {
        Response response = given()
                .queryParam("title", "ea molestias quasi exercitationem repellat qui ipsa sit aut")
                .when()
                .get(BASE_URL + "/" + POSTS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals(3, json.getList("id").get(0));
        assertEquals("et iusto sed quo iure" +
                "\nvoluptatem occaecati omnis eligendi aut ad" +
                "\nvoluptatem doloribus vel accusantium quis pariatur" +
                "\nmolestiae porro eius odio et labore et velit aut", json.getList("body").get(0));
    }

    @Test
    public void jsonplaceholderReadOnePostWithQueryParamById() {
        Response response = given()
                .queryParam("id", 3)
                .when()
                .get(BASE_URL + "/" + POSTS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("ea molestias quasi exercitationem repellat qui ipsa sit aut", json.getList("title").get(0));
        assertEquals("et iusto sed quo iure" +
                "\nvoluptatem occaecati omnis eligendi aut ad" +
                "\nvoluptatem doloribus vel accusantium quis pariatur" +
                "\nmolestiae porro eius odio et labore et velit aut", json.getList("body").get(0));
    }
}
