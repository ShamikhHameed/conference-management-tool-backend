package com.nsss.conferencemanagementtoolbackend.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Document(collection = "ResearchPaperDetails")
public class ResearchPaperDetails {

    private String id;
    private String name;
    private String title;
    private String researchArea;
    private Date publishedDate;
    private String country;
    private String author;
    private List<String> contributors;
    private List<String> contributorTitle;
    private boolean approvalStatus;


    public ResearchPaperDetails() {
    }

    public ResearchPaperDetails(String name, String title, String researchArea, Date publishedDate, String country, String author, List<String> contributors, List<String> contributorTitle, boolean approvalStatus) {
        this.name=name;
        this.title=title;
        this.researchArea=researchArea;
        this.publishedDate=publishedDate;
        this.country=country;
        this.author=author;
        this.contributors=contributors;
        this.contributorTitle=contributorTitle;
    }


    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getResearchArea() { return researchArea; }

    public void setResearchArea(String researchArea) { this.researchArea = researchArea; }

    public Date getPublishedDate() { return publishedDate; }

    public void setPublishedDate(Date publishedDate) { this.publishedDate = publishedDate; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public List<String> getContributors() { return contributors; }

    public void setContributors(List<String> contributors) { this.contributors = contributors; }

    public List<String> getContributorTitle() { return contributorTitle; }

    public void setContributorTitle(List<String> contributorTitle) { this.contributorTitle = contributorTitle; }

    public boolean isApprovalStatus() { return approvalStatus; }

    public void setApprovalStatus(boolean approvalStatus) { this.approvalStatus = approvalStatus; }

}
