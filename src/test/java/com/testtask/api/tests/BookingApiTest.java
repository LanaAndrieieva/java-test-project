package com.testtask.api.tests;

import com.testtask.api.models.Booking;
import com.testtask.api.models.BookingResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.testtask.api.providers.BookingDataProvider;
import io.qameta.allure.*;

@Epic("Booking API")
@Feature("Bookings")
public class BookingApiTest extends BaseApiTest {

    @Test(groups = {"api"}, 
    dataProvider = "validBookingData", 
    dataProviderClass = BookingDataProvider.class,
    description = "Create a booking and verify it via GET")
    public void testCreateAndVerifyBookingFlow(Booking expectedBooking) {
        
        Response responsePost = bookingClient.createBooking(expectedBooking);
        
        Assert.assertEquals(responsePost.getStatusCode(), 200, "POST status code mismatch");
        BookingResponse postResponse = responsePost.as(BookingResponse.class);

        int bookingId = postResponse.getBookingid();
        Assert.assertTrue(bookingId > 0, "Booking ID should be positive");

        Response responseGet = bookingClient.getBookingById(bookingId);
        
        Assert.assertEquals(responseGet.getStatusCode(), 200, "GET status code mismatch");
        Booking actualBooking = responseGet.as(Booking.class);
        verifyBooking(actualBooking, expectedBooking);

    }

    @Step("Verify booking data")
    private void verifyBooking(Booking actual, Booking expected) {
        Assert.assertEquals(actual.getFirstname(), expected.getFirstname(), "First name mismatch");
        Assert.assertEquals(actual.getLastname(), expected.getLastname(), "Last name mismatch");
        Assert.assertEquals(actual.getTotalprice(), expected.getTotalprice(), "Total price mismatch");
        Assert.assertEquals(actual.isDepositpaid(), expected.isDepositpaid(), "Deposit paid mismatch");
        Assert.assertEquals(actual.getBookingdates().getCheckin(), expected.getBookingdates().getCheckin(), "Checkin mismatch");
        Assert.assertEquals(actual.getBookingdates().getCheckout(), expected.getBookingdates().getCheckout(), "Checkout mismatch");
    }
}