package com.sunrisenode.webhook.elements.effect;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.sunrisenode.webhook.Webhook;
import info.itsthesky.disky.api.skript.SpecificBotEffect;
import info.itsthesky.disky.core.Bot;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

@Name("Register webhooks")
@Description("Register webhooks client using a ID, and its url or token.")
@Examples("register webhook with name \"name\" and url \"https://discordapp.com/api/webhooks/123456789/abcdefghijklmnopqrstuvwxyz\"")
public class RegisterWebhook extends SpecificBotEffect {

    private Expression<String> exprName;
    private Expression<String> exprURL;

    public static void load() {
        System.out.println("Registering ...");
        Skript.registerEffect(
                RegisterWebhook.class,
                "register [new] [webhook] [(with name|named)] %string% (using|with) [the] [(url|token|id)] %string%");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void runEffect(@NotNull Event event, @NotNull Bot bot) {
        String name = exprName.getSingle(event);
        String url = exprURL.getSingle(event);
        if (name == null || url == null) return;
        Webhook.getWebhookManager().registerWebhooks(name, url);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean initEffect(Expression[] exprs, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        exprName = (Expression<String>) exprs[0];
        exprURL = (Expression<String>) exprs[1];
        return true;
    }

    @Override
    public String toString(@NotNull Event event, boolean b) {
        return "register webhook named " + exprName.getSingle(event) + " using url " + exprURL.getSingle(event);
    }
}
