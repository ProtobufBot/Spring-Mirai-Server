package net.lz1998.pbbot.plugin;

import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import onebot.OnebotBase;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HelloPlugin extends BotPlugin {
    @Override
    public int onPrivateMessage(@NotNull Bot bot, @NotNull OnebotEvent.PrivateMessageEvent event) {
        // 这里展示了event消息链的用法
        List<OnebotBase.Message> messageChain = event.getMessageList();
        if (messageChain.size() > 0) {
            OnebotBase.Message message = messageChain.get(0);
            if (message.getType().equals("text")) {
                String text = message.getDataMap().get("text");
                if ("hello".equals(text)) {
                    bot.sendPrivateMsg(event.getUserId(), "hi", false);
                }
            }
        }
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {
        // 这里展示了RawMessage的用法（纯String）
        long groupId = event.getGroupId();
        String text = event.getRawMessage();
        if ("hello".equals(text)) {
            bot.sendGroupMsg(groupId, "hi", false);
        }
        return MESSAGE_BLOCK;
    }
}
