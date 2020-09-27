package net.lz1998.pbbot.plugin;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogPlugin extends BotPlugin {
    @Override
    public int onPrivateMessage(@NotNull Bot bot, @NotNull OnebotEvent.PrivateMessageEvent event) {
        log.info("收到私聊消息 QQ：{} 内容：{}", event.getUserId(), event.getRawMessage());
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {
        log.info("收到群消息 群号：{} QQ：{} 内容：{}", event.getGroupId(), event.getUserId(), event.getRawMessage());
        return MESSAGE_IGNORE;
    }
}
