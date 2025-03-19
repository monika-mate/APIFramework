package org.example.tests.Integration.Curd;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.example.base.BaseTest;
import org.example.endpoints.APIConstant;
import org.example.pojos.Booking;
import org.example.pojos.BookingResponse;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class testIntegrationFlow extends BaseTest {



    @Test(groups = "integration", priority = 1)
    @Description("Verify Booking is created")
    public void testCreateBooking(ITestContext  iTestContext){

        requestSpecification
                .basePath(APIConstant.Create_Update_Booking_Url);

        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingString()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode( 200);
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());

    }

    @Test(groups = "integration", priority = 2)
    public void verifyBookingId(ITestContext iTestContext){
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathGet = APIConstant.Create_Update_Booking_Url + "/" +bookingid;
        System.out.println(basePathGet);

        requestSpecification.basePath(basePathGet);
        response = RestAssured.given(requestSpecification)
                   .when().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("Monika");

    }

    @Test(groups = "integration", priority = 3)
    public void updateBookingById(ITestContext iTestContext){

        String token = getToken();
        iTestContext.setAttribute("token", token);
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathPUT = APIConstant.Create_Update_Booking_Url + "/" + bookingid;
        System.out.println(basePathPUT);

        requestSpecification.basePath(basePathPUT);
        response = RestAssured.given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.fullUpdatedPayloadAsstring()).put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        assertThat(booking.getFirstname()).isNotBlank().isNotNull();
        assertThat(booking.getFirstname()).isEqualTo("Saksham");
        assertThat(booking.getLastname()).isEqualTo("Tiwari");

    }

    @Test(groups = "integration", priority = 4)
    public void testDeleteBookingById(ITestContext iTestContext){
        String token = (String) iTestContext.getAttribute("token");
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathDelete = APIConstant.Create_Update_Booking_Url + "/" +bookingid;
        requestSpecification.basePath(basePathDelete).cookie("token", token);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);
    }
}
