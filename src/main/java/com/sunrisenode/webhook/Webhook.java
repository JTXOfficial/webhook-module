package com.sunrisenode.webhook;

import ch.njol.skript.SkriptAddon;
import info.itsthesky.disky.DiSky;
import info.itsthesky.disky.api.modules.DiSkyModule;
import lombok.Getter;

import java.io.File;


public class Webhook extends DiSkyModule {

    @Getter private static Webhook instance;
    @Getter private static WebhookManager webhookManager;

    public Webhook(String name, String author, String version, File moduleJar) {
        super(name, author, version, moduleJar);
    }

    @Override
    public void init(DiSky diSky, SkriptAddon skriptAddon) {
        instance = this;
        webhookManager = new WebhookManager();

        try {
            loadClasses("com.sunrisenode.webhook.elements");
        } catch (Exception e) {
            diSky.getLogger().severe("Failed to load webhooksender elements: ");
            e.printStackTrace();
            return;
        }
    }
}
