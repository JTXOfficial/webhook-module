package com.sunrisenode.webhook.elements.effect;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import com.sunrisenode.webhook.Webhook;
import info.itsthesky.disky.api.skript.SpecificBotEffect;
import info.itsthesky.disky.core.Bot;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

@Name("Send Webhook")
@Description("Send a webhook to a specific channel.")
@Examples("make the webhook \"name\" send message \"Hello\"")
public class SendWebhook extends SpecificBotEffect {

    public static void load() {
        System.out.println("Registering ...");
        Skript.registerEffect(
                SendWebhook.class,
                "make [the] [webhook] [(named|with name)] %string% send [the] [(message|text|webhook message)] %string/webhookmessagebuilder/embedbuilder%");
    }

    private Expression<String> exprName;
    private Expression<Object> exprMessage;

    @Override
    @SuppressWarnings("unchecked")
    public void runEffect(Event event, Bot bot) {
        String name = exprName.getSingle(event);
        Object message = exprMessage.getSingle(event);

        if (name == null || message == null) return;

        WebhookClient client = Webhook.getWebhookManager().getWebhook(name);

        if (client == null || client.isShutdown()) {
            Skript.error("Webhook " + name + " is not registered or has shutdown.");
            return;
        }

        if (message instanceof String) {
            client.send(message.toString());
        } else if (message instanceof WebhookMessageBuilder) {
            client.send(((WebhookMessageBuilder) message).build());
            System.out.println("w");
        } else if (message instanceof EmbedBuilder) {
            client.send(WebhookEmbedBuilder.fromJDA(((EmbedBuilder) message).build()).build());
        } else {
            Skript.error("Invalid message type.");
        }
    }

    @Override
    public boolean initEffect(Expression[] exprs, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        exprName = (Expression<String>) exprs[0];
        exprMessage = (Expression<Object>) exprs[1];
        return true;
    }

    @Override
    public String toString(@NotNull Event event, boolean b) {
        return "make the webhook " + exprName.toString(event, b) + " send the message " + exprMessage.toString(event, b);
    }
}
