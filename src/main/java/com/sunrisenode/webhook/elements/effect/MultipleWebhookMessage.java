package com.sunrisenode.webhook.elements.effect;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import info.itsthesky.disky.api.skript.SpecificBotEffect;
import info.itsthesky.disky.core.Bot;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

@Name("Webhook Message Builder")
@Description("Webhook Message are allowed to have multiple txt content, and can be sent to multiple webhooks at once.")
public class MultipleWebhookMessage extends SpecificBotEffect {

    public static WebhookMessageBuilder lastBuilder;

    public static void load() {
        System.out.println("Registering ...");
        //register condition
        Skript.registerEffect(
                MultipleWebhookMessage.class, "make [new] [discord] webhook message");

    }


    @Override
    public void runEffect(@NotNull Event event, @NotNull Bot bot) {
        lastBuilder = new WebhookMessageBuilder();
    }

    @Override
    public boolean initEffect(Expression[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public String toString(@NotNull Event event, boolean b) {
        return "make new webhook message";
    }
}
