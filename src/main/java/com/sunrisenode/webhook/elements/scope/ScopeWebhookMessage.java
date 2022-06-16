package com.sunrisenode.webhook.elements.scope;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.*;
import ch.njol.util.Kleenean;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import com.sunrisenode.webhook.utils.EffectSection;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Webhook Message Builder")
@Description("Webhook Message are allowed to have multiple txt content, and can be sent to multiple webhooks at once.")
@Examples("make new webhook message:")
public class ScopeWebhookMessage extends EffectSection {

    public static WebhookMessageBuilder lastBuilder;

    public static void load() {
        System.out.println("Registering ...");
        //register condition
        Skript.registerCondition(
                ScopeWebhookMessage.class,
                "make [new] [discord] webhook message");

    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (checkIfCondition()) {
            return false;
        }
        if (!hasSection()) {
            return false;
        }
        loadSection(true);
        return true;
    }

    @Override
    protected void execute(Event e) {
        lastBuilder = new WebhookMessageBuilder();
        runSection(e);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "make new webhook message";
    }
}
