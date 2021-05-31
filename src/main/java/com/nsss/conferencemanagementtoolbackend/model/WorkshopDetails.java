package com.nsss.conferencemanagementtoolbackend.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "workshopDetails")
public class WorkshopDetails {

    private String id;
    private String name;
    private String institute;
    private Date startDate;
    private int noOfDays;
    private List<String> speakers;
    private List<String> speakerInstitute;


    private byte[] data;

    private boolean approvalStatus;

    public WorkshopDetails() {
    }

    public WorkshopDetails(String name, String institute, Date startDate, int noOfDays, List<String> speakers, List<String> speakerInstitute, boolean approvalStatus) {
        this.name=name;
        this.institute=institute;
        this.startDate=startDate;
        this.noOfDays=noOfDays;
        this.speakers=speakers;
        this.speakerInstitute=speakerInstitute;
    }


    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getInstitute() { return institute; }

    public void setInstitute(String institute) { this.institute = institute; }

    public Date getStartDate() { return startDate; }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public int getNoOfDays() { return noOfDays; }

    public void setNoOfDays(int noOfDays) { this.noOfDays = noOfDays; }

    public List<String> getSpeakers() { return speakers; }

    public void setSpeakers(List<String> speakers) { this.speakers = speakers; }

    public List<String> getSpeakerInstitute() { return speakerInstitute; }

    public void setSpeakerInstitute(List<String> speakerInstitute) { this.speakerInstitute = speakerInstitute; }

    public byte[] getData() { return data; }

    public void setData(byte[] data) { this.data = data; }

    public boolean isApprovalStatus() { return approvalStatus; }

    public void setApprovalStatus(boolean approvalStatus) { this.approvalStatus = approvalStatus; }







}