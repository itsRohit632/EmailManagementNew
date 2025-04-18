package com.projectmanagement.dto;

public class ResumeScreeningDTO {
    private String email;
    private String technology;
    private String screenedBy;
    private String feedback;
    private Integer communication;
    private Integer fluency;
    private Integer technicalKnowledge;
    private Integer confidence;

    // Getters and setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTechnology() { return technology; }
    public void setTechnology(String technology) { this.technology = technology; }

    public String getScreenedBy() { return screenedBy; }
    public void setScreenedBy(String screenedBy) { this.screenedBy = screenedBy; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public int getCommunication() { return communication; }
    public void setCommunication(int communication) { this.communication = communication; }
	/**
	 * @return the fluency
	 */
	public Integer getFluency() {
		return fluency;
	}
	/**
	 * @param fluency the fluency to set
	 */
	public void setFluency(Integer fluency) {
		this.fluency = fluency;
	}
	/**
	 * @return the technicalKnowledge
	 */
	public Integer getTechnicalKnowledge() {
		return technicalKnowledge;
	}
	/**
	 * @param technicalKnowledge the technicalKnowledge to set
	 */
	public void setTechnicalKnowledge(Integer technicalKnowledge) {
		this.technicalKnowledge = technicalKnowledge;
	}
	/**
	 * @return the confidence
	 */
	public Integer getConfidence() {
		return confidence;
	}
	/**
	 * @param confidence the confidence to set
	 */
	public void setConfidence(Integer confidence) {
		this.confidence = confidence;
	}
	/**
	 * @param communication the communication to set
	 */
	public void setCommunication(Integer communication) {
		this.communication = communication;
	}

}
