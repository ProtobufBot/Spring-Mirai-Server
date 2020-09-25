package net.lz1998.mirai.plugin;

import net.lz1998.bot.BotPlugin;
import net.lz1998.mirai.bot.Bot;
import onebot.OnebotEvent;
import org.springframework.stereotype.Component;

@Component
public class DemoPlugin extends BotPlugin {
    public int onPrivateMessage(Bot cq, OnebotEvent.PrivateMessageEvent event) {
        return super.onPrivateMessage(cq, event);
    }
}
