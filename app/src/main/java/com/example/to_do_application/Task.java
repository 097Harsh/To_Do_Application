package com.example.to_do_application;

public class Task {
    private String id;
    private String name;
    private String details;
    private String status;

    public Task() {
        // Default constructor required for Firebase
    }

    public Task(String id, String name, String details, String status) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.status = status;
    }



    // Getters and setters
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
