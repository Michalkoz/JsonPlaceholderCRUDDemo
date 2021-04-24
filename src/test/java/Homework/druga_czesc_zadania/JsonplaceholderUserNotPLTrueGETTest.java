package Homework.druga_czesc_zadania;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderUserNotPLTrueGETTest {

    @Test
    public void jsonplaceholderUserNotPLTrueGETTest() {

        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath jsonReceived = response.jsonPath();
        List<String> listOfAddressesOfEmail = jsonReceived.getList("email");

        // first method
        boolean isContainsDomainPL = listOfAddressesOfEmail.stream()
                .anyMatch(x -> x.endsWith("pl"));
        assertFalse(false, String.valueOf(isContainsDomainPL));

        // second method
        long numberOfDomainPL = listOfAddressesOfEmail.stream()
                .filter(x -> x.endsWith("pl"))
                .count();
        assertEquals(0, numberOfDomainPL);

        // third method using for loop
        List<String> plDomainList = new ArrayList<>();
        for (String element : listOfAddressesOfEmail) {
            if (element.endsWith("pl")) {
                plDomainList.add(element);
            }
        }
        assertEquals(0, plDomainList.size());
    }
}
