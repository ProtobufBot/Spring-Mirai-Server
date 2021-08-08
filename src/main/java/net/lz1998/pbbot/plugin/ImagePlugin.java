package net.lz1998.pbbot.plugin;

import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import net.lz1998.pbbot.utils.Msg;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ImagePlugin extends BotPlugin {
    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {
        long groupId = event.getGroupId(); // 群号
        long userId = event.getUserId(); // 发送者QQ
        String rawMsg = event.getRawMessage(); // 文本消息

        if ("生成图片".equals(rawMsg)) {
            // controller/ImageController.java
            // getImage(Long qq) 生成图片
            Msg.builder()
                    .image("http://localhost:8081/getImage?qq=" + userId)
                    .sendToGroup(bot, groupId);
        }

        return MESSAGE_IGNORE;
    }
}
