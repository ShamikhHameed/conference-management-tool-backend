package com.nsss.conferencemanagementtoolbackend.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "workshop")
public class WorkshopFile {
    private String id;
    private String name;
    private String type;
    private byte[] data;

    private String user;
    private boolean approvalStatus;

    public WorkshopFile() {
    }

/*    public WorkshopFile(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }*/

    public WorkshopFile(String name, String type, byte[] data, String user, boolean approvalStatus) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.user = user;
        this.approvalStatus = approvalStatus;
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
}
