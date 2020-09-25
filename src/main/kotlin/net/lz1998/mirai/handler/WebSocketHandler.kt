package net.lz1998.mirai.handler

import com.google.protobuf.util.JsonFormat
import net.lz1998.mirai.bot.BotFactory
import net.lz1998.mirai.bot.MiraiBot
import net.lz1998.mirai.boot.EventProperties
import onebot.OnebotFrame
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.socket.*
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class WebSocketHandler(
        eventProperties: EventProperties,
        var botFactory: BotFactory
) : TextWebSocketHandler() {
    val botMap = mutableMapOf<Long, MiraiBot>()

    val sessionMap = mutableMapOf<Long, WebSocketSession>()

    val jsonFormatParser: JsonFormat.Parser = JsonFormat.parser().ignoringUnknownFields()

    var executor: ExecutorService = ThreadPoolExecutor(eventProperties.corePoolSize, eventProperties.maxPoolSize, eventProperties.keepAliveTime, TimeUnit.MILLISECONDS, ArrayBlockingQueue(eventProperties.workQueueSize));

    @Autowired
    lateinit var frameHandler: FrameHandler

    override fun afterConnectionEstablished(session: WebSocketSession) {
        val xSelfId = session.handshakeHeaders["x-self-id"]?.get(0)?.toLong() ?: 0L
        if (xSelfId == 0L) {
            session.close()
            return
        }
        sessionMap[xSelfId] = session
        println("$xSelfId connected")
        botMap[xSelfId] = botFactory.createBot(xSelfId, session)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        val xSelfId = session.handshakeHeaders["x-self-id"]?.get(0)?.toLong() ?: 0L
        if (xSelfId == 0L) {
            return
        }
        sessionMap.remove(xSelfId, session)
        println("$xSelfId disconnected")
        botMap.remove(xSelfId)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val frameBuilder = OnebotFrame.Frame.newBuilder()
        jsonFormatParser.merge(message.payload, frameBuilder)
        val frame = frameBuilder.build()
        session.sendMessage(PingMessage())
        executor.execute {
            frameHandler.handleFrame(frame)
        }
    }

    override fun handleBinaryMessage(session: WebSocketSession, message: BinaryMessage) {
        val frame = OnebotFrame.Frame.parseFrom(message.payload)
        session.sendMessage(PingMessage())
        executor.execute {
            frameHandler.handleFrame(frame)
        }
    }


}