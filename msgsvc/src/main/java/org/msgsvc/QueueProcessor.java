package org.msgsvc;



import org.example.HUDDisplay;
import org.xcc.DTOs.CampaignVinDto;
import org.xcc.InMemoryQueue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QueueProcessor {
    private static final String VIN_TO_DISPLAY = "12345678901234567";
    private final ScheduledExecutorService executor;
    private final HUDDisplay displayModule ;

    public QueueProcessor() {
        this.displayModule = new HUDDisplay();
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void start() {
        executor.scheduleAtFixedRate(this::processQueue, 0, 1, TimeUnit.SECONDS);
    }

    private void processQueue()  {
        InMemoryQueue queue = InMemoryQueue.getInstance();
        CampaignVinDto campaignVin = queue.peek();
        System.out.println("campaignVin:  " + campaignVin);
        if (campaignVin != null && campaignVin.campaign.getStartDate().getTime() <= System.currentTimeMillis()) {
            System.out.println("found campaign: " + campaignVin);
            try {
                campaignVin = queue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (VIN_TO_DISPLAY.equals(campaignVin.vin.getVin())) {
                displayModule.displayCampaign(campaignVin);
            }
        }
        else
            System.out.println("nothing there in queue " + InMemoryQueue.getInstance().toString());
    }

    public static void main(String[] args) {
        new QueueProcessor().start();
    }
}