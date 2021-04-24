package Homework.pierwsza_czesc_zadania;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class JsonplaceholderGETTest {

    // GET do kolekcji postów
    @Test
    public void jsonplaceholderReadAllPosts(){

        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts");

        Assertions.assertEquals(200, response.statusCode());

        JsonPath json = response.jsonPath();
        List<Object> titles = json.getList("title");
        titles.stream()
                .map(x -> "title " + titles.indexOf(x) + " = " + x)
                .forEach(System.out::println);

        Assertions.assertEquals(100, titles.size());
    }

    // GET do jednego posta
    @Test
    public void jsonplaceholderReadOnePost(){
        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/3");

        Assertions.assertEquals(200, response.statusCode());

        JsonPath json = response.jsonPath();
        Assertions.assertEquals("ea molestias quasi exercitationem repellat qui ipsa sit aut", json.get("title"));
        Assertions.assertEquals("et iusto sed quo iure" +
                "\nvoluptatem occaecati omnis eligendi aut ad" +
                "\nvoluptatem doloribus vel accusantium quis pariatur" +
                "\nmolestiae porro eius odio et labore et velit aut", json.get("body"));

        System.out.println(response.asString());
    }

    // PATH VARIABLE
    @Test
    public void jsonplaceholderReadPostUserWithPathVariable() {
        Response response = given()
                .pathParam("postId", 3)
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/{postId}");

        System.out.println(response.asString());

        JsonPath json = response.jsonPath();
        Assertions.assertEquals("ea molestias quasi exercitationem repellat qui ipsa sit aut", json.get("title"));
        Assertions.assertEquals("et iusto sed quo iure" +
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
                .get("https://jsonplaceholder.typicode.com/posts");

        Assertions.assertEquals(200, response.getStatusCode());

        JsonPath json = response.jsonPath();
        Assertions.assertEquals(3, json.getList("id").get(0));
        Assertions.assertEquals("et iusto sed quo iure" +
                "\nvoluptatem occaecati omnis eligendi aut ad" +
                "\nvoluptatem doloribus vel accusantium quis pariatur" +
                "\nmolestiae porro eius odio et labore et velit aut", json.getList("body").get(0));
    }
    @Test
    public void jsonplaceholderReadOnePostWithQueryParamById() {
        Response response = given()
                .queryParam("id", 3)
                .when()
                .get("https://jsonplaceholder.typicode.com/posts");

        Assertions.assertEquals(200, response.getStatusCode());

        JsonPath json = response.jsonPath();
        Assertions.assertEquals("ea molestias quasi exercitationem repellat qui ipsa sit aut", json.getList("title").get(0));
        Assertions.assertEquals("et iusto sed quo iure" +
                "\nvoluptatem occaecati omnis eligendi aut ad" +
                "\nvoluptatem doloribus vel accusantium quis pariatur" +
                "\nmolestiae porro eius odio et labore et velit aut", json.getList("body").get(0));
    }
    }
