package net.lz1998.bot;

import net.lz1998.mirai.bot.Bot;
import onebot.OnebotEvent;


public abstract class BotPlugin {
    public static final int MESSAGE_BLOCK = 1;
    public static final int MESSAGE_IGNORE = 0;
    /**
     * 收到私聊消息时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onPrivateMessage(Bot bot, OnebotEvent.PrivateMessageEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 收到群消息时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupMessage(Bot bot, OnebotEvent.GroupMessageEvent event) {
        return MESSAGE_IGNORE;
    }


    /**
     * 群内有文件上传时调用此方法
     * 仅群文件上传表现为事件，好友发送文件在 酷Q 中没有独立的事件，而是直接表现为好友消息，请注意在编写业务逻辑时进行判断。
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupUploadNotice(Bot bot, OnebotEvent.GroupUploadNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 群管理员变动时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupAdminNotice(Bot bot, OnebotEvent.GroupAdminNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 群成员减少时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupDecreaseNotice(Bot bot, OnebotEvent.GroupDecreaseNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 群成员增加时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupIncreaseNotice(Bot bot, OnebotEvent.GroupIncreaseNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 群禁言时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupBanNotice(Bot bot, OnebotEvent.GroupBanNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 好友添加时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onFriendAddNotice(Bot bot, OnebotEvent.FriendAddNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 加好友请求时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onFriendRequest(Bot bot, OnebotEvent.FriendRequestEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 加群请求/邀请时调用此方法
     *
     * @param bot    机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupRequest(Bot bot, OnebotEvent.GroupRequestEvent event) {
        return MESSAGE_IGNORE;
    }

}