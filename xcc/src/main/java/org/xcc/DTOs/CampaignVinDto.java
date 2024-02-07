package org.xcc.DTOs;

import org.xcc.entities.Campaign;
import org.xcc.entities.Vin;

public class CampaignVinDto {


    public CreateCampaignDto campaign;
    public CreateVinDto vin;


    public CampaignVinDto(Campaign campaign_, Vin vin_) {
        campaign = new CreateCampaignDto(campaign_);
        vin = new CreateVinDto(vin_);
    }
}
