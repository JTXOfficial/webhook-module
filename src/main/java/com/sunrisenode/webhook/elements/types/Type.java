package com.sunrisenode.webhook.elements.types;

import ch.njol.skript.registrations.Comparators;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import com.sunrisenode.webhook.utils.WebhookComparator;
import info.itsthesky.disky.api.DiSkyType;
import net.dv8tion.jda.api.entities.Webhook;

public class Type {
    public static class WebhookComparators {
        static {
            Comparators.registerComparator(Webhook.class, Webhook.class, new WebhookComparator<>());
        }

    }

    static {
        new DiSkyType<>(
                WebhookMessageBuilder.class,
                "webhookmessagebuilder",
                "webhookmessagebuilders?",
                webhookMessageBuilder -> webhookMessageBuilder.toString(),
                null,
                false
        ).register();
    }
}
