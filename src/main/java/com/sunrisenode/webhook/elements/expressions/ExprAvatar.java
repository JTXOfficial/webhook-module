package com.sunrisenode.webhook.elements.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import com.sunrisenode.webhook.elements.scope.ScopeWebhookMessage;
import net.dv8tion.jda.api.entities.Webhook;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Avatar of webhook")
@Description("Set webhook avatar")
@Examples("set avatar of last webhook to \"https://i.imgur.com/YQQQQQq.png\"")
public class ExprAvatar extends SimplePropertyExpression<Object, String> {

    public static void load() {
        System.out.println("Registering ...");
        register(ExprAvatar.class, String.class,
                "[discord] avatar",
                "webhookmessagebuilder");
        }

    @Nullable
    @Override
    public String convert(Object entity) {
        return ((Webhook) entity).getDefaultUser().getAvatarUrl();
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    protected String getPropertyName() {
        return "avatar";
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
                    webhook.setAvatarUrl(delta[0].toString());
                    ScopeWebhookMessage.lastBuilder = webhook;
                    return;
                }
            }
        }
    }
}
