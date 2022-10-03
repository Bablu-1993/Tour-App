package com.fmsfrontend.ltctours.modelClass;

public class ReviewModel {
    String review_id;
    String review;
    String user_id;
    String review_status;

    public ReviewModel(String review_id, String review, String user_id, String review_status) {
        this.review_id = review_id;
        this.review = review;
        this.user_id = user_id;
        this.review_status = review_status;
    }

    public String getReview_id() {
        return review_id;
    }

    public void setReview_id(String review_id) {
        this.review_id = review_id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getReview_status() {
        return review_status;
    }

    public void setReview_status(String review_status) {
        this.review_status = review_status;
    }
}
