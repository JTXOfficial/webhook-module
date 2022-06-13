package com.sunrisenode.webhook.elements.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import com.sunrisenode.webhook.elements.effect.MultipleWebhookMessage;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class MessageContent extends SimplePropertyExpression<Object, String> {

    public static void load() {
        System.out.println("Registering ...");
        register(MessageContent.class, String.class, "message content", "message/webhookmessagebuilder");
    }


    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Nullable
    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(String.class);
        }
        return CollectionUtils.array();
    }

    @Override
    protected String getPropertyName() {
        return "message content";
    }

    @Nullable
    @Override
    public String convert(Object o) {
        if (o instanceof WebhookMessageBuilder) return ((WebhookMessageBuilder) o).build().getContent();
        return null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        if (delta == null || delta.length == 0 || delta[0] == null || !mode.equals(Changer.ChangeMode.SET)) return;
        String content = delta[0].toString();
        for (Object entity : getExpr().getAll(e)) {

            if (entity instanceof WebhookMessageBuilder) {
                WebhookMessageBuilder builder = (WebhookMessageBuilder) entity;
                builder.setContent(content);
                MultipleWebhookMessage.lastBuilder.setContent(content);
            }

        }
    }
}
