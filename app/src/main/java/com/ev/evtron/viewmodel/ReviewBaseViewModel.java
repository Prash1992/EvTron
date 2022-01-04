package com.ev.evtron.viewmodel;

public class ReviewBaseViewModel {
    public String bookingInternalId;
    public String rating;
    public String reviews;

    public String getBookingInternalId() {
        return bookingInternalId;
    }

    public void setBookingInternalId(String bookingInternalId) {
        this.bookingInternalId = bookingInternalId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }
}
