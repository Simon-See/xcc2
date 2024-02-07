package org.xcc;

import org.xcc.DTOs.CampaignVinDto;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class InMemoryQueue {
    private static InMemoryQueue instance;
    private PriorityBlockingQueue<CampaignVinDto> queue;

    private InMemoryQueue() {
        Comparator<CampaignVinDto> comparator = Comparator.comparing(c -> c.campaign.getStartDate());
        queue = new PriorityBlockingQueue<>(1000, comparator);
    }

    public static synchronized InMemoryQueue getInstance() {
        if (instance == null) {
            instance = new InMemoryQueue();
        }
        return instance;
    }

    public void put(CampaignVinDto campaignVin) {
        queue.put(campaignVin);
    }

    public CampaignVinDto take() throws InterruptedException {
        return queue.take();
    }

    public CampaignVinDto peek() {
        return queue.peek();
    }
}