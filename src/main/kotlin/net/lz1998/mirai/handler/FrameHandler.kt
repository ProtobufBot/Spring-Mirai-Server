package net.lz1998.mirai.handler

import net.lz1998.mirai.bot.ApiSender
import net.lz1998.mirai.bot.MiraiBot
import onebot.OnebotFrame
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FrameHandler {

    @Autowired
    lateinit var eventHandler: EventHandler

    @Autowired
    lateinit var apiSender: ApiSender

    @Autowired
    lateinit var webSocketHandler: WebSocketHandler

    fun handleFrame(frame: OnebotFrame.Frame) {
        val bot: MiraiBot = webSocketHandler.botMap[frame.botId] ?: return
        when (frame.messageType) {
            OnebotFrame.Frame.MessageType.PrivateMessageEvent -> eventHandler.handlePrivateMessageEvent(bot, frame.privateMessageEvent)
            OnebotFrame.Frame.MessageType.GroupMessageEvent -> eventHandler.handleGroupMessageEvent(bot, frame.groupMessageEvent)
            OnebotFrame.Frame.MessageType.GroupUploadNoticeEvent -> eventHandler.handleUnknown(null)
            OnebotFrame.Frame.MessageType.GroupAdminNoticeEvent -> eventHandler.handleUnknown(null)
            OnebotFrame.Frame.MessageType.GroupDecreaseNoticeEvent -> eventHandler.handleGroupDecreaseNoticeEvent(bot, frame.groupDecreaseNoticeEvent)
            OnebotFrame.Frame.MessageType.GroupIncreaseNoticeEvent -> eventHandler.handleGroupIncreaseNoticeEvent(bot, frame.groupIncreaseNoticeEvent)
            OnebotFrame.Frame.MessageType.GroupBanNoticeEvent -> eventHandler.handleUnknown(null)
            OnebotFrame.Frame.MessageType.FriendAddNoticeEvent -> eventHandler.handleUnknown(null)
            OnebotFrame.Frame.MessageType.GroupRecallNoticeEvent -> eventHandler.handleUnknown(null)
            OnebotFrame.Frame.MessageType.FriendRecallNoticeEvent -> eventHandler.handleUnknown(null)
            OnebotFrame.Frame.MessageType.FriendRequestEvent -> eventHandler.handleUnknown(null)
            OnebotFrame.Frame.MessageType.GroupRequestEvent -> eventHandler.handleUnknown(null)

            else -> apiSender.echoFutureMap[frame.echo]?.complete(frame)
        }

    }
}