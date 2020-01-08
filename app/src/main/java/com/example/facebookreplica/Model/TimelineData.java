package com.example.facebookreplica.Model;

public class TimelineData {
    private String fullname;

    private String status;

    private String time;

    private String timelineimage;

    public TimelineData(String fullname, String status, String time, String timelineimage) {
        this.fullname = fullname;
        this.status = status;
        this.time = time;
        this.timelineimage = timelineimage;
    }

//    public TimelineData(String fullname, String status, String time) {
//        this.fullname = fullname;
//        this.status = status;
//        this.time = time;
//    }

    public String getFullname() {
        return fullname;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public String getTimelineimage() {
        return timelineimage;
    }
}
