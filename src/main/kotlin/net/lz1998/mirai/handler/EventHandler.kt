package net.lz1998.mirai.handler

import com.google.protobuf.Message
import net.lz1998.mirai.bot.CoolQ
import net.lz1998.mirai.plugin.CQPlugin
import net.lz1998.mirai.plugin.CQPluginConfig
import onebot.OnebotEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EventHandler {
    @Autowired
    lateinit var cqPluginConfig: CQPluginConfig


    fun handlePrivateMessageEvent(bot: CoolQ, event: OnebotEvent.PrivateMessageEvent) {
        cqPluginConfig.cqPlugins.forEach {
            if (it.onPrivateMessage(bot, event) == CQPlugin.MESSAGE_BLOCK) {
                return
            }
        }
    }

    fun handleGroupMessageEvent(bot: CoolQ, event: OnebotEvent.GroupMessageEvent) {
        cqPluginConfig.cqPlugins.forEach {
            if (it.onGroupMessage(bot, event) == CQPlugin.MESSAGE_BLOCK) {
                return
            }
        }
    }

    fun handleGroupDecreaseNoticeEvent(bot: CoolQ, event: OnebotEvent.GroupDecreaseNoticeEvent) {
        cqPluginConfig.cqPlugins.forEach {
            if (it.onGroupDecreaseNotice(bot, event) == CQPlugin.MESSAGE_BLOCK) {
                return
            }
        }
    }

    fun handleGroupIncreaseNoticeEvent(bot: CoolQ, event: OnebotEvent.GroupIncreaseNoticeEvent) {
        cqPluginConfig.cqPlugins.forEach {
            if (it.onGroupIncreaseNotice(bot, event) == CQPlugin.MESSAGE_BLOCK) {
                return
            }
        }
    }

    fun handleUnknown(event: Message?) {

    }
}