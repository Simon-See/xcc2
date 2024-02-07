package org.xcc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Vin extends PanacheEntity {

    public String vin;

    @ManyToMany(mappedBy = "vins")
    public List<Campaign> campaigns;
}