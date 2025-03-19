package org.example.tests.Integration.Curd;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.Utils.PropertyReader;
import org.example.base.BaseTest;
import org.example.endpoints.APIConstant;
import org.example.pojos.BookingResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestVerifyCreateBookingPost01 extends BaseTest {

    @Owner("Monika")
    @Link(name = "Link to TC", url  = "") //link your test case and provide  jira url here
    @Issue("JIRA RBT-4")   // mention jira issue
    @Description("Verify that POST Request is working fine")
    @Test
    public void Create_Booking_Test(){

        requestSpecification
                .basePath(APIConstant.Create_Update_Booking_Url);

        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingString()).post();

        validatableResponse = response.then().log().all();
                          validatableResponse.statusCode( 200);

                          validatableResponse.body("booking.firstname", Matchers.equalTo("Monika"));

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotEmpty();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Monika");


        assertActions.verifyStatusCode(response, 200);
    }
}
