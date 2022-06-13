package com.sunrisenode.webhook.elements.types;

import ch.njol.skript.classes.Comparator;
import ch.njol.skript.registrations.Comparators;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import com.sunrisenode.webhook.utils.WebhookComparator;
import info.itsthesky.disky.api.DiSkyType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Webhook;

public class Type {
    public static class WebhookComparators {
        static {

            Comparators.registerComparator(Webhook.class, Webhook.class, new WebhookComparator<>());


            /*
             * Custom entities which need a precise comparator
             */
            Comparators.registerComparator(JDA.class, JDA.class, new Comparator<>() {
                @Override
                public Comparator.Relation compare(JDA jda, JDA jda2) {
                    if (jda.getSelfUser().getId().equals(jda2.getSelfUser().getId()))
                        return Relation.EQUAL;
                    return Relation.NOT_EQUAL;
                }

                @Override
                public boolean supportsOrdering() {
                    return false;
                }
            });
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
