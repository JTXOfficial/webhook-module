package com.sunrisenode.webhook.elements.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import com.sunrisenode.webhook.elements.scope.ScopeWebhookMessage;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprName extends SimplePropertyExpression<Object, String> {

    public static void load() {
        register(ExprName.class, String.class,
                "discord name",
                "webhookmessagebuilder"
        );
    }

    @Nullable
    @Override
    public String convert(Object entity) {
        String finalName = null;
        try {
            finalName = (String) entity.getClass().getDeclaredMethod("getName").invoke(entity);
        } catch (final Exception ignored) { }
        if (finalName == null) {
            if (entity instanceof WebhookMessageBuilder) finalName = ((WebhookMessageBuilder) entity).build().getContent();
        }
        return finalName;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    protected String getPropertyName() {
        return "discord name";
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
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        if (delta == null || delta.length == 0) return;
        if (mode == Changer.ChangeMode.SET) {
            for (Object entity : getExpr().getArray(e)) {
                if (entity instanceof WebhookMessageBuilder) {
                    WebhookMessageBuilder webhook = (WebhookMessageBuilder) entity;
                    webhook.setUsername(delta[0].toString());
                    ScopeWebhookMessage.lastBuilder = webhook;
                    return;
                }
            }
        }
    }
}
