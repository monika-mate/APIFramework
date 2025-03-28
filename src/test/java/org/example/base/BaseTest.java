package org.example.base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.asserts.AssertActions;
import org.example.endpoints.APIConstant;
import org.example.modules.PayloadManager;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public Response response;
    public ValidatableResponse validatableResponse;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;

    @BeforeTest
    public void setup(){
         payloadManager = new PayloadManager();
         assertActions = new AssertActions();

         requestSpecification = RestAssured.given()
                 .baseUri(APIConstant.BASE_URL)
                 .contentType(ContentType.JSON)
                 .log().all();

    }

    public String getToken(){
        requestSpecification = RestAssured
                .given()
                .baseUri(APIConstant.BASE_URL)
                .basePath(APIConstant.Auth_url);

        String payload = payloadManager.setAuthPayload();

        response  = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();

        String token = payloadManager.getTokenFromJSON(response.asString());

        return token;
    }

}
