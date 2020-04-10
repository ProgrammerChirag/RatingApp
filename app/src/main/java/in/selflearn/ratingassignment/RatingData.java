package in.selflearn.ratingassignment;

public class RatingData {
    String Date;
    String Time;
    double rating;

    public RatingData(){}

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public RatingData(String date, String time, double rating) {
        Date = date;
        Time = time;
        this.rating = rating;
    }


}
