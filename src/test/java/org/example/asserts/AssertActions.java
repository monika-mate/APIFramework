package org.example.asserts;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

public class AssertActions {


    public void verifyResponseBody(String actual, String expected, String description){
        assertEquals(actual, expected, description);
    }

    public void verifyResponseBody(int actual, int expected, String description){
        assertEquals(actual, expected, description);
    }

    public void verifyStatusCode(Response response, Integer expected){
        assertEquals(response.getStatusCode(), expected);
    }

}
