package org.xcc;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.xcc.DTOs.CampaignVinDto;
import org.xcc.DTOs.CreateCampaignDto;
import org.xcc.DTOs.CreateVinDto;
import org.xcc.entities.Campaign;
import org.xcc.entities.Vin;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/backend")
public class XccBackend {

    @ConfigProperty(name = "my.test.property")
    String testProperty;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hello")
    public String hello() {
        return testProperty;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/MsgQueue")
    public String msgQueue() {
        return InMemoryQueue.getInstance().toString() + " first element: " + InMemoryQueue.getInstance().peek();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/createCampaign")
    public Response createCampaign(CreateCampaignDto dto) {
        Campaign campaign = new Campaign();
        campaign.message = dto.message;
        campaign.campaignType = dto.campaignType;
        campaign.startDate = dto.startDate;
        campaign.endDate = dto.endDate;
        campaign.description = dto.description;
        campaign.persist();
        return Response.status(Response.Status.CREATED).entity(campaign).build();
    }


    @POST
    @Path("/addVinToCampaign")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addVinToCampaign(@QueryParam("vin") String vinString, @QueryParam("campaignId") Long campaignId) {
        // Fetch the Campaign from the database
        Campaign campaign = Campaign.findById(campaignId);

        // Check if the Campaign exists
        if (campaign == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Fetch the Vin from the database using the vin string
        Vin vin = Vin.find("vin", vinString).firstResult();

        // Check if the Vin exists
        if (vin == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Add the Vin to the Campaign's vins list
        campaign.vins.add(vin);

        // Persist the changes
        campaign.persist();

        // Put the Campaign and Vin onto the in-memory queue
        CampaignVinDto campaignVin = new CampaignVinDto(campaign,vin);
        InMemoryQueue.getInstance().put(campaignVin);
        System.out.println("put vin on MemoryQueue: "  + InMemoryQueue.getInstance().peek());

        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/createVin")
    public Response createVin(CreateVinDto dto) {
        Vin vin = new Vin();
        vin.vin = dto.vin;
        vin.persist();
        return Response.status(Response.Status.CREATED).entity(vin).build();
    }

    @DELETE
    @Path("/campaigns/{id}")
    @Transactional
    public Response deleteCampaign(@PathParam("id") Long id) {
        Campaign campaign = Campaign.findById(id);
        if (campaign == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        campaign.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
