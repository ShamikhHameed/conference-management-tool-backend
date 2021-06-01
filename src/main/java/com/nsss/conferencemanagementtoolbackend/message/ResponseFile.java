package com.nsss.conferencemanagementtoolbackend.message;

public class ResponseFile {
    private String name;
    private String url;
    private String type;
    private long size;

    private String user;
    private Boolean approvalStatus;

    private Boolean paymentStatus;

    public ResponseFile(String name, String url, String type, long size) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
    }

    public ResponseFile(String name, String url, String type, long size, String user) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.user = user;
    }

    public ResponseFile(String name, String url, String type, long size, String user, Boolean approvalStatus) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.user = user;
        this.approvalStatus = approvalStatus;
    }

    public ResponseFile(String name, String url, String type, long size, String user, Boolean approvalStatus, Boolean paymentStatus) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.user = user;
        this.approvalStatus = approvalStatus;
        this.paymentStatus = paymentStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Boolean getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
