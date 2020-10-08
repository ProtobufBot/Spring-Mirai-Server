package net.lz1998.pbbot.plugin;

import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import net.lz1998.pbbot.utils.Msg;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class DemoPlugin extends BotPlugin {

    @Override
    public int onPrivateMessage(@NotNull Bot bot, @NotNull OnebotEvent.PrivateMessageEvent event) {
        long userId = event.getUserId();
        Msg msg = Msg.builder()
                .face(1)
                .text("hello1")
                .text("hello2")
                .image("https://www.baidu.com/img/flexible/logo/pc/result@2.png");
        bot.sendPrivateMsg(userId, msg, false);
        return MESSAGE_BLOCK;
    }
}
