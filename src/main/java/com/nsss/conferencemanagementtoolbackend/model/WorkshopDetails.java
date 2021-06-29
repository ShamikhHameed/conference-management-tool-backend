package com.nsss.conferencemanagementtoolbackend.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "workshopDetails")
public class WorkshopDetails {

    private String id;
    private String title;
    private String time;
    private String place;
    private Date startDate;
    private int noOfDays;
    private List<String> speakers;
    private List<String> speakerInstitutes;
    private boolean approvalStatus;

    public WorkshopDetails() {
    }

    public WorkshopDetails(String title, String time,String place, Date startDate, int noOfDays, List<String> speakers, List<String> speakerInstitutes, boolean approvalStatus) {
        this.title=title;
        this.time=time;
        this.place=place;
        this.startDate=startDate;
        this.noOfDays=noOfDays;
        this.speakers=speakers;
        this.speakerInstitutes=speakerInstitutes;
    }


    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public String getPlace() { return place; }

    public void setPlace(String place) { this.place = place; }

    public Date getStartDate() { return startDate; }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public int getNoOfDays() { return noOfDays; }

    public void setNoOfDays(int noOfDays) { this.noOfDays = noOfDays; }

    public List<String> getSpeakers() { return speakers; }

    public void setSpeakers(List<String> speakers) { this.speakers = speakers; }

    public List<String> getSpeakerInstitutes() { return speakerInstitutes; }

    public void setSpeakerInstitutes(List<String> speakerInstitutes) { this.speakerInstitutes = speakerInstitutes; }

    public boolean isApprovalStatus() { return approvalStatus; }

    public void setApprovalStatus(boolean approvalStatus) { this.approvalStatus = approvalStatus; }







}