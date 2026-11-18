package com.testtask.api.clients;

import com.testtask.api.models.Booking;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookingClient {

    private final RequestSpecification requestSpec;
    private static final String BOOKING_ENDPOINT = "/booking";
    private static final String BOOKING_ID_ENDPOINT = "/booking/{id}";

    public BookingClient(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    public Response createBooking(Booking bookingBody) {
        return RestAssured.given()
                .spec(requestSpec)
                .body(bookingBody)
                .when()
                .post(BOOKING_ENDPOINT);
    }

    public Response getBookingById(int id) {
        return RestAssured.given()
                .spec(requestSpec)
                .pathParam("id", id)
                .when()
                .get(BOOKING_ID_ENDPOINT);
    }
}