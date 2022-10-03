package com.fmsfrontend.ltctours.modelClass;

public class TourModel {
   String id;
   String heading;
   String ltc_days;
   String ltc_images;
   String ltc_pdf;
   String ltc_file;

    public TourModel(String id, String heading, String ltc_days, String ltc_images, String ltc_pdf, String ltc_file) {
        this.id = id;
        this.heading = heading;
        this.ltc_days = ltc_days;
        this.ltc_images = ltc_images;
        this.ltc_pdf = ltc_pdf;
        this.ltc_file = ltc_file;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getLtc_days() {
        return ltc_days;
    }

    public void setLtc_days(String ltc_days) {
        this.ltc_days = ltc_days;
    }

    public String getLtc_images() {
        return ltc_images;
    }

    public void setLtc_images(String ltc_images) {
        this.ltc_images = ltc_images;
    }

    public String getLtc_pdf() {
        return ltc_pdf;
    }

    public void setLtc_pdf(String ltc_pdf) {
        this.ltc_pdf = ltc_pdf;
    }

    public String getLtc_file() {
        return ltc_file;
    }

    public void setLtc_file(String ltc_file) {
        this.ltc_file = ltc_file;
    }
}
