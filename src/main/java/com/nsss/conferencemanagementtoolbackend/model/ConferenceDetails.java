package com.nsss.conferencemanagementtoolbackend.model;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Document(collection = "conferenceDetails")
public class ConferenceDetails {

    private String id;
    private String name;
    private String institute;
    private Date startDate;
    private int noOfDays;
    private List<String> speakers;
    private List<String> speakerInstitutes;
    private boolean approvalStatus;

    public ConferenceDetails() {
    }

    public ConferenceDetails(String name, String institute, Date startDate, int noOfDays, List<String> speakers, List<String> speakerInstitutes, boolean approvalStatus) {
        this.name=name;
        this.institute=institute;
        this.startDate=startDate;
        this.noOfDays=noOfDays;
        this.speakers=speakers;
        this.speakerInstitutes=speakerInstitutes;
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

    public List<String> getSpeakerInstitutes() { return speakerInstitutes; }

    public void setSpeakerInstitutes(List<String> speakerInstitutes) { this.speakerInstitutes = speakerInstitutes; }

    public boolean isApprovalStatus() { return approvalStatus; }

    public void setApprovalStatus(boolean approvalStatus) { this.approvalStatus = approvalStatus; }

}
