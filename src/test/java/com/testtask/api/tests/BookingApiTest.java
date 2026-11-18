package com.testtask.api.tests;

import com.testtask.api.models.Booking;
import com.testtask.api.models.BookingResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.testtask.api.providers.BookingDataProvider;

public class BookingApiTest extends BaseApiTest {

    @Test(dataProvider = "validBookingData", dataProviderClass = BookingDataProvider.class)
    public void testCreateAndVerifyBookingFlow(Booking expectedBooking) {
        
        Response responsePost = bookingClient.createBooking(expectedBooking);
        
        Assert.assertEquals(responsePost.getStatusCode(), 200, "POST status code mismatch");
        BookingResponse postResponse = responsePost.as(BookingResponse.class);

        int bookingId = postResponse.getBookingid();
        Assert.assertTrue(bookingId > 0, "Booking ID should be positive");

        Response responseGet = bookingClient.getBookingById(bookingId);
        
        Assert.assertEquals(responseGet.getStatusCode(), 200, "GET status code mismatch");
        Booking actualBooking = responseGet.as(Booking.class);

        Assert.assertEquals(actualBooking.getFirstname(), expectedBooking.getFirstname(), "First name mismatch");
        Assert.assertEquals(actualBooking.getLastname(), expectedBooking.getLastname(), "Last name mismatch");
        Assert.assertEquals(actualBooking.getTotalprice(), expectedBooking.getTotalprice(), "Total price mismatch");
        Assert.assertEquals(actualBooking.isDepositpaid(), expectedBooking.isDepositpaid(), "Deposit paid status mismatch");
        Assert.assertEquals(actualBooking.getBookingdates().getCheckin(), expectedBooking.getBookingdates().getCheckin(), "Checkin mismatch");
        Assert.assertEquals(actualBooking.getBookingdates().getCheckout(), expectedBooking.getBookingdates().getCheckout(), "Checkout mismatch");
    }
}