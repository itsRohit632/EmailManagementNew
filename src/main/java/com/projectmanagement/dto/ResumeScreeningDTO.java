package com.projectmanagement.dto;

public class ResumeScreeningDTO {
    private String email;
    private String technology;
    private String screenedBy;
    private boolean isPassed;
    private String feedback;

    // ---------- Getters and Setters ----------

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTechnology() {
        return technology;
    }
    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getScreenedBy() {
        return screenedBy;
    }
    public void setScreenedBy(String screenedBy) {
        this.screenedBy = screenedBy;
    }

    public boolean isPassed() {
        return isPassed;
    }
    public void setPassed(boolean isPassed) {
        this.isPassed = isPassed;
    }

    public String getFeedback() {
        return feedback;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
