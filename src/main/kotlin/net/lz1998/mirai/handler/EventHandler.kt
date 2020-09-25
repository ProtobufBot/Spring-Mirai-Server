package net.lz1998.mirai.handler

import com.google.protobuf.Message
import net.lz1998.bot.BotPlugin
import net.lz1998.mirai.bot.Bot
import net.lz1998.mirai.plugin.CQPluginConfig
import onebot.OnebotEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EventHandler {
    @Autowired
    lateinit var cqPluginConfig: CQPluginConfig


    fun handlePrivateMessageEvent(bot: Bot, event: OnebotEvent.PrivateMessageEvent) {
        cqPluginConfig.miraiPlugins.forEach {
            if (it.onPrivateMessage(bot, event) == BotPlugin.MESSAGE_BLOCK) {
                return
            }
        }
    }

    fun handleGroupMessageEvent(bot: Bot, event: OnebotEvent.GroupMessageEvent) {
        cqPluginConfig.miraiPlugins.forEach {
            if (it.onGroupMessage(bot, event) == BotPlugin.MESSAGE_BLOCK) {
                return
            }
        }
    }

    fun handleGroupDecreaseNoticeEvent(bot: Bot, event: OnebotEvent.GroupDecreaseNoticeEvent) {
        cqPluginConfig.miraiPlugins.forEach {
            if (it.onGroupDecreaseNotice(bot, event) == BotPlugin.MESSAGE_BLOCK) {
                return
            }
        }
    }

    fun handleGroupIncreaseNoticeEvent(bot: Bot, event: OnebotEvent.GroupIncreaseNoticeEvent) {
        cqPluginConfig.miraiPlugins.forEach {
            if (it.onGroupIncreaseNotice(bot, event) == BotPlugin.MESSAGE_BLOCK) {
                return
            }
        }
    }

    fun handleUnknown(event: Message?) {

    }
}