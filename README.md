# Webhook
The webhook DiSky module, to send webhooks.

# Installation

Suggested VPS & Minecraft hosting: [SunriseNode](https://sunrisenode.com)

![](https://sunrisenode.com/assets/img/logos/sunrisenode-ban.gif)

1. Download DiSky, put it in the `/plugins/` folder
2. Download Webhook, put it in the `/plugins/DiSky/modules/` folder
3. Restart your server

# Webhook Example
First register the webhook.

```applescript
register webhook "name" using url "URL".
```

> You can't have multiple webhooks with the same name!

You are now able to manage the webhook using the name specified.

Here's an example of it being used taking the event-user avatar and event-member nick name.

```applescript
make new webhook message:
    set discord name of webhook to discord nickname of event-member
    set avatar of webhook to avatar of event-user
    set message content of webhook to "Hello!"
```

You can after send the webhook effect doing

```applescript
make webhook "name" send message last webhook message builder
```




