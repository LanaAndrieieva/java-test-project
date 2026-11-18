package com.testtask.api.clients;

import com.testtask.api.models.Booking;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.*;

public class BookingClient {

    private final RequestSpecification requestSpec;
    private static final String BOOKING_ENDPOINT = "/booking";
    private static final String BOOKING_ID_ENDPOINT = "/booking/{id}";

    public BookingClient(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    @Step("Create booking for {booking.firstname} {booking.lastname}")
    public Response createBooking(Booking bookingBody) {
        return RestAssured.given()
                .spec(requestSpec)
                .body(bookingBody)
                .when()
                .post(BOOKING_ENDPOINT);
    }

    @Step("Get booking by id {bookingId}")
    public Response getBookingById(int id) {
        return RestAssured.given()
                .spec(requestSpec)
                .pathParam("id", id)
                .when()
                .get(BOOKING_ID_ENDPOINT);
    }
}