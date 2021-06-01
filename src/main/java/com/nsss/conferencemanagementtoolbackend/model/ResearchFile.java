package com.nsss.conferencemanagementtoolbackend.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "research")
public class ResearchFile {
    private String id;
    private String name;
    private String type;
    private byte[] data;

    private String user;
    private boolean approvalStatus;

    private boolean paymentStatus;

    public ResearchFile() {
    }

    public ResearchFile(String name, String type, byte[] data, String user, boolean approvalStatus, boolean paymentStatus) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.user = user;
        this.approvalStatus = approvalStatus;
        this.paymentStatus = paymentStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
