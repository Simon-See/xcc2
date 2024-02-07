package org.xcc.DTOs;

import org.xcc.entities.Campaign;

import java.sql.Timestamp;

public class CreateCampaignDto {
    public String message;
    public Campaign.CampaignType campaignType;
    public Timestamp startDate;
    public Timestamp endDate;
    public String description;

    public CreateCampaignDto(Campaign campaign_) {
        this.message = campaign_.message;
        this. campaignType = campaign_.campaignType;
        this.startDate = campaign_.startDate;
        this.endDate = campaign_.endDate;
        this.description = campaign_.description;
    }

    public String getMessage() {
        return message;
    }

    public Campaign.CampaignType getCampaignType() {
        return campaignType;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }
}