package org.xcc.entities;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Campaign extends PanacheEntity{

    public String message;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vin_campaigns",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "vin_id"))
    public List<Vin> vins;

    @Enumerated(EnumType.STRING)
    public CampaignType campaignType;

    public Timestamp startDate;
    public Timestamp endDate;

    @Column(length = 1000)
    public String description;

    public enum CampaignType {
        RECALL,
        SERVICE_INFORMATION,
        PRODUCT_INFORMATION,
        CONTRACT_INFORMATION,
        RSU
    }
}
