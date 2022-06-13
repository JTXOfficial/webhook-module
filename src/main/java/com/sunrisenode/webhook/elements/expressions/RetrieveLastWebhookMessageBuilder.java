package com.sunrisenode.webhook.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import com.sunrisenode.webhook.elements.effect.MultipleWebhookMessage;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;


@Name("Retrieve Last Webhook Message Builder")
@Description("Returns the last webhook message builder.")
public class RetrieveLastWebhookMessageBuilder extends SimpleExpression<WebhookMessageBuilder> {

    public static void load() {
        System.out.println("Registering ...");

        //register expression
        Skript.registerExpression(
                RetrieveLastWebhookMessageBuilder.class, WebhookMessageBuilder.class, ExpressionType.SIMPLE, "retrieve last webhook message builder");
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Nullable
    @Override
    protected WebhookMessageBuilder[] get(Event e) {
        return new WebhookMessageBuilder[]{MultipleWebhookMessage.lastBuilder};
    }

    @Override
    public Class<? extends WebhookMessageBuilder> getReturnType() {
        return WebhookMessageBuilder.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "the last webhook message builder";
    }
}
