package net.lz1998.mirai.handler

import com.google.protobuf.Message
import net.lz1998.mirai.bot.CoolQ
import net.lz1998.mirai.plugin.CQPlugin
import onebot.OnebotEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EventHandler {
    @Autowired
    lateinit var pluginList: MutableList<CQPlugin>


    fun handlePrivateMessageEvent(bot: CoolQ, event: OnebotEvent.PrivateMessageEvent) {
        pluginList.forEach {
            if (it.onPrivateMessage(bot, event) == CQPlugin.MESSAGE_BLOCK) {
                return
            }
        }
    }

    fun handleGroupMessageEvent(bot: CoolQ, event: OnebotEvent.GroupMessageEvent) {
        pluginList.forEach {
            if (it.onGroupMessage(bot, event) == CQPlugin.MESSAGE_BLOCK) {
                return
            }
        }
    }

    fun handleGroupDecreaseNoticeEvent(bot: CoolQ, event: OnebotEvent.GroupDecreaseNoticeEvent) {
        pluginList.forEach {
            if (it.onGroupDecreaseNotice(bot, event) == CQPlugin.MESSAGE_BLOCK) {
                return
            }
        }
    }

    fun handleGroupIncreaseNoticeEvent(bot: CoolQ, event: OnebotEvent.GroupIncreaseNoticeEvent) {
        pluginList.forEach {
            if (it.onGroupIncreaseNotice(bot, event) == CQPlugin.MESSAGE_BLOCK) {
                return
            }
        }
    }

    fun handleUnknown(event: Message?) {

    }
}