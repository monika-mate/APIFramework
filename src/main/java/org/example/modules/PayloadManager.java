package org.example.modules;

import com.google.gson.Gson;
import org.example.pojos.*;

public class PayloadManager {

    Gson gson;
    public String createPayloadBookingString(){
        Booking booking = new Booking();
        booking.setFirstname("Monika");
        booking.setLastname("Mate");
        booking.setTotalprice(1111);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2025-02-01");
        bookingdates.setCheckout("2025-02-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);
        gson = new Gson();
        String jsonStringPayload = gson.toJson(booking);


        return jsonStringPayload;
    }

    public BookingResponse bookingResponseJava(String responseString){
        gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;

    }

    public Booking getResponseFromJSON(String getResponse){
      Booking booking = gson.fromJson(getResponse, Booking.class);
      return booking;
    }

    public String setAuthPayload(){
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson = new Gson();
        String jsonPayloadString = gson.toJson(auth);
        System.out.println("Payload set to the " + jsonPayloadString);
        return jsonPayloadString;
    }


    public String getTokenFromJSON(String tokenResponse){
        gson = new Gson();
        TokenResponse tokenResponse1 = gson.fromJson(tokenResponse, TokenResponse.class);
        return  tokenResponse1.getToken();

    }

    public String fullUpdatedPayloadAsstring(){
        Booking booking = new Booking();
        booking.setFirstname("Saksham");
        booking.setLastname("Tiwari");
        booking.setTotalprice(1111);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2025-02-01");
        bookingdates.setCheckout("2025-02-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");


        return gson.toJson(booking);

    }

}
