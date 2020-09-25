package net.lz1998.mirai.bot

import com.fasterxml.jackson.databind.util.LRUMap
import com.google.protobuf.Message
import kotlinx.coroutines.*
import onebot.OnebotApi
import onebot.OnebotFrame
import org.springframework.web.socket.BinaryMessage
import org.springframework.web.socket.WebSocketSession
import java.util.*

class ApiSender {
    val echoFutureMap = LRUMap<String, CompletableDeferred<OnebotFrame.Frame>>(128, 1024)

    fun callApi(session: WebSocketSession, botId: Long, apiReq: Message): Message? {
        val echo = UUID.randomUUID().toString()
        val futureResp = CompletableDeferred<OnebotFrame.Frame>()
        echoFutureMap.put(echo, futureResp)
        val frameBuilder = OnebotFrame.Frame.newBuilder()
        frameBuilder.echo = echo
        frameBuilder.botId = botId
        when (apiReq) {
           is OnebotApi.SendPrivateMsgReq ->{frameBuilder.sendPrivateMsgReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.SendPrivateMsgReq}
           is OnebotApi.SendGroupMsgReq ->{frameBuilder.sendGroupMsgReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.SendGroupMsgReq}
           is OnebotApi.SendMsgReq ->{frameBuilder.sendMsgReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.SendMsgReq}
           is OnebotApi.DeleteMsgReq ->{frameBuilder.deleteMsgReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.DeleteMsgReq}
           is OnebotApi.GetMsgReq ->{frameBuilder.getMsgReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.GetMsgReq}
           is OnebotApi.GetForwardMsgReq ->{frameBuilder.getForwardMsgReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.GetForwardMsgReq}
           is OnebotApi.SendLikeReq ->{frameBuilder.sendLikeReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.SendLikeReq}
           is OnebotApi.SetGroupKickReq ->{frameBuilder.setGroupKickReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.SetGroupKickReq}
           is OnebotApi.SetGroupBanReq ->{frameBuilder.setGroupBanReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.SetGroupBanReq}
           is OnebotApi.SetGroupAnonymousBanReq ->{frameBuilder.setGroupAnonymousBanReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.SetGroupAnonymousBanReq}
           is OnebotApi.SetGroupWholeBanReq ->{frameBuilder.setGroupWholeBanReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.SetGroupWholeBanReq}
           is OnebotApi.SetGroupAdminReq ->{frameBuilder.setGroupAdminReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.SetGroupAdminReq}
           is OnebotApi.SetGroupAnonymousReq ->{frameBuilder.setGroupAnonymousReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.SetGroupAnonymousReq}
           is OnebotApi.SetGroupCardReq ->{frameBuilder.setGroupCardReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.SetGroupCardReq}
           is OnebotApi.SetGroupNameReq ->{frameBuilder.setGroupNameReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.SetGroupNameReq}
           is OnebotApi.SetGroupLeaveReq ->{frameBuilder.setGroupLeaveReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.SetGroupLeaveReq}
           is OnebotApi.SetGroupSpecialTitleReq ->{frameBuilder.setGroupSpecialTitleReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.SetGroupSpecialTitleReq}
           is OnebotApi.SetFriendAddRequestReq ->{frameBuilder.setFriendAddRequestReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.SetFriendAddRequestReq}
           is OnebotApi.SetGroupAddRequestReq ->{frameBuilder.setGroupAddRequestReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.SetGroupAddRequestReq}
           is OnebotApi.GetLoginInfoReq ->{frameBuilder.getLoginInfoReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.GetLoginInfoReq}
           is OnebotApi.GetStrangerInfoReq ->{frameBuilder.getStrangerInfoReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.GetStrangerInfoReq}
           is OnebotApi.GetFriendListReq ->{frameBuilder.getFriendListReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.GetFriendListReq}
           is OnebotApi.GetGroupInfoReq ->{frameBuilder.getGroupInfoReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.GetGroupInfoReq}
           is OnebotApi.GetGroupListReq ->{frameBuilder.getGroupListReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.GetGroupListReq}
           is OnebotApi.GetGroupMemberInfoReq ->{frameBuilder.getGroupMemberInfoReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.GetGroupMemberInfoReq}
           is OnebotApi.GetGroupMemberListReq ->{frameBuilder.getGroupMemberListReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.GetGroupMemberListReq}
           is OnebotApi.GetGroupHonorInfoReq ->{frameBuilder.getGroupHonorInfoReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.GetGroupHonorInfoReq}
           is OnebotApi.GetCookiesReq ->{frameBuilder.getCookiesReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.GetCookiesReq}
           is OnebotApi.GetCsrfTokenReq ->{frameBuilder.getCsrfTokenReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.GetCsrfTokenReq}
           is OnebotApi.GetCredentialsReq ->{frameBuilder.getCredentialsReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.GetCredentialsReq}
           is OnebotApi.GetRecordReq ->{frameBuilder.getRecordReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.GetRecordReq}
           is OnebotApi.GetImageReq ->{frameBuilder.getImageReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.GetImageReq}
           is OnebotApi.CanSendImageReq ->{frameBuilder.canSendImageReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.CanSendImageReq}
           is OnebotApi.CanSendRecordReq ->{frameBuilder.canSendRecordReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.CanSendRecordReq}
           is OnebotApi.GetStatusReq ->{frameBuilder.getStatusReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.GetStatusReq}
           is OnebotApi.GetVersionInfoReq ->{frameBuilder.getVersionInfoReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.GetVersionInfoReq}
           is OnebotApi.SetRestartReq ->{frameBuilder.setRestartReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.SetRestartReq}
           is OnebotApi.CleanCacheReq ->{frameBuilder.cleanCacheReq = apiReq;frameBuilder.messageType = OnebotFrame.Frame.MessageType.CleanCacheReq}
            else->return null
       }
        frameBuilder.ok = true
        val reqFrame = frameBuilder.build()
        val msg=BinaryMessage(reqFrame.toByteArray())
        session.sendMessage(msg)
        val respFrame = runBlocking { futureResp.await() }
        return when (respFrame.messageType) {
            OnebotFrame.Frame.MessageType.SendPrivateMsgResp -> respFrame.sendPrivateMsgResp
            OnebotFrame.Frame.MessageType.SendGroupMsgResp -> respFrame.sendGroupMsgResp
            OnebotFrame.Frame.MessageType.SendMsgResp -> respFrame.sendMsgResp
            OnebotFrame.Frame.MessageType.DeleteMsgResp -> respFrame.deleteMsgResp
            OnebotFrame.Frame.MessageType.GetMsgResp -> respFrame.getMsgResp
            OnebotFrame.Frame.MessageType.GetForwardMsgResp -> respFrame.getForwardMsgResp
            OnebotFrame.Frame.MessageType.SendLikeResp -> respFrame.sendLikeResp
            OnebotFrame.Frame.MessageType.SetGroupKickResp -> respFrame.setGroupKickResp
            OnebotFrame.Frame.MessageType.SetGroupBanResp -> respFrame.setGroupBanResp
            OnebotFrame.Frame.MessageType.SetGroupAnonymousResp -> respFrame.setGroupAnonymousResp
            OnebotFrame.Frame.MessageType.SetGroupWholeBanResp -> respFrame.setGroupWholeBanResp
            OnebotFrame.Frame.MessageType.SetGroupAdminResp -> respFrame.setGroupAdminResp
            OnebotFrame.Frame.MessageType.SetGroupAnonymousBanResp -> respFrame.setGroupAnonymousBanResp
            OnebotFrame.Frame.MessageType.SetGroupCardResp -> respFrame.setGroupCardResp
            OnebotFrame.Frame.MessageType.SetGroupNameResp -> respFrame.setGroupNameResp
            OnebotFrame.Frame.MessageType.SetGroupLeaveResp -> respFrame.setGroupLeaveResp
            OnebotFrame.Frame.MessageType.SetGroupSpecialTitleResp -> respFrame.setGroupSpecialTitleResp
            OnebotFrame.Frame.MessageType.SetFriendAddRequestResp -> respFrame.setFriendAddRequestResp
            OnebotFrame.Frame.MessageType.SetGroupAddRequestResp -> respFrame.setGroupAddRequestResp
            OnebotFrame.Frame.MessageType.GetLoginInfoResp -> respFrame.getLoginInfoResp
            OnebotFrame.Frame.MessageType.GetStrangerInfoResp -> respFrame.getStrangerInfoResp
            OnebotFrame.Frame.MessageType.GetFriendListResp -> respFrame.getFriendListResp
            OnebotFrame.Frame.MessageType.GetGroupInfoResp -> respFrame.getGroupInfoResp
            OnebotFrame.Frame.MessageType.GetGroupListResp -> respFrame.getGroupListResp
            OnebotFrame.Frame.MessageType.GetGroupMemberInfoResp -> respFrame.getGroupMemberInfoResp
            OnebotFrame.Frame.MessageType.GetGroupMemberListResp -> respFrame.getGroupMemberListResp
            OnebotFrame.Frame.MessageType.GetGroupHonorInfoResp -> respFrame.getGroupHonorInfoResp
            OnebotFrame.Frame.MessageType.GetCookiesResp -> respFrame.getCookiesResp
            OnebotFrame.Frame.MessageType.GetCsrfTokenResp -> respFrame.getCsrfTokenResp
            OnebotFrame.Frame.MessageType.GetCredentialsResp -> respFrame.getCredentialsResp
            OnebotFrame.Frame.MessageType.GetRecordResp -> respFrame.getRecordResp
            OnebotFrame.Frame.MessageType.GetImageResp -> respFrame.getImageResp
            OnebotFrame.Frame.MessageType.CanSendImageResp -> respFrame.canSendImageResp
            OnebotFrame.Frame.MessageType.CanSendRecordResp -> respFrame.canSendRecordResp
            OnebotFrame.Frame.MessageType.GetStatusResp -> respFrame.getStatusResp
            OnebotFrame.Frame.MessageType.GetVersionInfoResp -> respFrame.getVersionInfoResp
            OnebotFrame.Frame.MessageType.SetRestartResp -> respFrame.setRestartResp
            OnebotFrame.Frame.MessageType.CleanCacheResp -> respFrame.cleanCacheResp
            else -> null
        }
    }

    fun sendPrivateMsg(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SendPrivateMsgReq) = callApi(session, botId, apiReq) as OnebotApi.SendPrivateMsgResp?

    fun sendGroupMsg(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SendGroupMsgReq) = callApi(session, botId, apiReq) as OnebotApi.SendGroupMsgResp?

    fun sendMsg(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SendMsgReq) = callApi(session, botId, apiReq) as OnebotApi.SendMsgResp?

    fun deleteMsg(session: WebSocketSession, botId: Long, apiReq: OnebotApi.DeleteMsgReq) = callApi(session, botId, apiReq) as OnebotApi.DeleteMsgResp?

    fun getMsg(session: WebSocketSession, botId: Long, apiReq: OnebotApi.GetMsgReq) = callApi(session, botId, apiReq) as OnebotApi.GetMsgResp?

    fun getForwardMsg(session: WebSocketSession, botId: Long, apiReq: OnebotApi.GetForwardMsgReq) = callApi(session, botId, apiReq) as OnebotApi.GetForwardMsgResp?

    fun sendLike(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SendLikeReq) = callApi(session, botId, apiReq) as OnebotApi.SendLikeResp?

    fun setGroupKick(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SetGroupKickReq) = callApi(session, botId, apiReq) as OnebotApi.SetGroupKickResp?

    fun setGroupBan(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SetGroupBanReq) = callApi(session, botId, apiReq) as OnebotApi.SetGroupBanResp?

    fun setGroupAnonymousBan(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SetGroupAnonymousBanReq) = callApi(session, botId, apiReq) as OnebotApi.SetGroupAnonymousBanResp?

    fun setGroupWholeBan(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SetGroupWholeBanReq) = callApi(session, botId, apiReq) as OnebotApi.SetGroupWholeBanResp?

    fun setGroupAdmin(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SetGroupAdminReq) = callApi(session, botId, apiReq) as OnebotApi.SetGroupAdminResp?

    fun setGroupAnonymous(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SetGroupAnonymousReq) = callApi(session, botId, apiReq) as OnebotApi.SetGroupAnonymousResp?

    fun setGroupCard(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SetGroupCardReq) = callApi(session, botId, apiReq) as OnebotApi.SetGroupCardResp?

    fun setGroupName(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SetGroupNameReq) = callApi(session, botId, apiReq) as OnebotApi.SetGroupNameResp?

    fun setGroupLeave(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SetGroupLeaveReq) = callApi(session, botId, apiReq) as OnebotApi.SetGroupLeaveResp?

    fun setGroupSpecialTitle(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SetGroupSpecialTitleReq) = callApi(session, botId, apiReq) as OnebotApi.SetGroupSpecialTitleResp?

    fun setFriendAddRequest(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SetFriendAddRequestReq) = callApi(session, botId, apiReq) as OnebotApi.SetFriendAddRequestResp?

    fun setFriendAdd(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SetFriendAddRequestReq) = callApi(session, botId, apiReq) as OnebotApi.SetFriendAddRequestResp?

    fun setGroupAddRequest(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SetGroupAddRequestReq) = callApi(session, botId, apiReq) as OnebotApi.SetGroupAddRequestResp?

    fun setGroupAdd(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SetGroupAddRequestReq) = callApi(session, botId, apiReq) as OnebotApi.SetGroupAddRequestResp?

    fun getLoginInfo(session: WebSocketSession, botId: Long, apiReq: OnebotApi.GetLoginInfoReq) = callApi(session, botId, apiReq) as OnebotApi.GetLoginInfoResp?

    fun getStrangerInfo(session: WebSocketSession, botId: Long, apiReq: OnebotApi.GetStrangerInfoReq) = callApi(session, botId, apiReq) as OnebotApi.GetStrangerInfoResp?

    fun getFriendList(session: WebSocketSession, botId: Long, apiReq: OnebotApi.GetFriendListReq) = callApi(session, botId, apiReq) as OnebotApi.GetFriendListResp?

    fun getGroupInfo(session: WebSocketSession, botId: Long, apiReq: OnebotApi.GetGroupInfoReq) = callApi(session, botId, apiReq) as OnebotApi.GetGroupInfoResp?

    fun getGroupList(session: WebSocketSession, botId: Long, apiReq: OnebotApi.GetGroupListReq) = callApi(session, botId, apiReq) as OnebotApi.GetGroupListResp?

    fun getGroupMemberInfo(session: WebSocketSession, botId: Long, apiReq: OnebotApi.GetGroupMemberInfoReq) = callApi(session, botId, apiReq) as OnebotApi.GetGroupMemberInfoResp?

    fun getGroupMemberList(session: WebSocketSession, botId: Long, apiReq: OnebotApi.GetGroupMemberListReq) = callApi(session, botId, apiReq) as OnebotApi.GetGroupMemberListResp?

    fun getGroupHonorInfo(session: WebSocketSession, botId: Long, apiReq: OnebotApi.GetGroupHonorInfoReq) = callApi(session, botId, apiReq) as OnebotApi.GetGroupHonorInfoResp?

    fun getCookies(session: WebSocketSession, botId: Long, apiReq: OnebotApi.GetCookiesReq) = callApi(session, botId, apiReq) as OnebotApi.GetCookiesResp?

    fun getCsrfToken(session: WebSocketSession, botId: Long, apiReq: OnebotApi.GetCsrfTokenReq) = callApi(session, botId, apiReq) as OnebotApi.GetCsrfTokenResp?

    fun getCredentials(session: WebSocketSession, botId: Long, apiReq: OnebotApi.GetCredentialsReq) = callApi(session, botId, apiReq) as OnebotApi.GetCredentialsResp?

    fun getRecord(session: WebSocketSession, botId: Long, apiReq: OnebotApi.GetRecordReq) = callApi(session, botId, apiReq) as OnebotApi.GetRecordResp?

    fun getImage(session: WebSocketSession, botId: Long, apiReq: OnebotApi.GetImageReq) = callApi(session, botId, apiReq) as OnebotApi.GetImageResp?

    fun canSendImage(session: WebSocketSession, botId: Long, apiReq: OnebotApi.CanSendImageReq) = callApi(session, botId, apiReq) as OnebotApi.CanSendImageResp?

    fun canSendRecord(session: WebSocketSession, botId: Long, apiReq: OnebotApi.CanSendRecordReq) = callApi(session, botId, apiReq) as OnebotApi.CanSendRecordResp?

    fun getStatus(session: WebSocketSession, botId: Long, apiReq: OnebotApi.GetStatusReq) = callApi(session, botId, apiReq) as OnebotApi.GetStatusResp?

    fun getVersionInfo(session: WebSocketSession, botId: Long, apiReq: OnebotApi.GetVersionInfoReq) = callApi(session, botId, apiReq) as OnebotApi.GetVersionInfoResp?

    fun setRestart(session: WebSocketSession, botId: Long, apiReq: OnebotApi.SetRestartReq) = callApi(session, botId, apiReq) as OnebotApi.SetRestartResp?

    fun cleanCache(session: WebSocketSession, botId: Long, apiReq: OnebotApi.CleanCacheReq) = callApi(session, botId, apiReq) as OnebotApi.CleanCacheResp?
}