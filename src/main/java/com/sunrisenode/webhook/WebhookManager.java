package com.sunrisenode.webhook;

import ch.njol.skript.Skript;
import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;

import java.util.HashMap;

public class WebhookManager {
    public static HashMap<String, WebhookClient> webhooks = new HashMap<>();

    public void registerWebhooks(String id, String input) {
        if (webhooks.containsKey(id)) {
            Skript.error("Webhook with id " + id + " already exists.");
            return;
        }
        WebhookClientBuilder builder = new WebhookClientBuilder(input);

        builder.setThreadFactory(job -> {
            Thread thread = new Thread(job);
            thread.setName("Webhooks-" + id);
            thread.setDaemon(true);
            return thread;
        });
        builder.setWait(true);
        WebhookClient client = builder.build();
        webhooks.put(id, client);
    }

    // get webhook client from hashmap
    public WebhookClient getWebhook(String id) {
        if (!webhooks.containsKey(id)) {
            Skript.error("Webhook with id " + id + " does not exist. Please register it first.");
            return null;
        }
        return webhooks.get(id);
    }

}
