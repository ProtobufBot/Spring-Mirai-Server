package net.lz1998.mirai.handler

import com.google.protobuf.util.JsonFormat
import net.lz1998.mirai.bot.BotFactory
import net.lz1998.mirai.bot.CoolQ
import net.lz1998.mirai.bot.EventProperties
import onebot.OnebotFrame
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.socket.*
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

@Component
class WebSocketHandler : TextWebSocketHandler() {
    val botMap = mutableMapOf<Long, CoolQ>()

    @Autowired
    lateinit var botFactory: BotFactory

    val sessionMap = mutableMapOf<Long, WebSocketSession>()

    val jsonFormatParser: JsonFormat.Parser = JsonFormat.parser().ignoringUnknownFields()


    val executor:ExecutorService = ThreadPoolExecutor(EventProperties.corePoolSize.toInt(),EventProperties.maxPoolSize.toInt(),EventProperties.keepAliveTime.toLong(),TimeUnit.MILLISECONDS
            ,ArrayBlockingQueue(EventProperties.workQueueSize.toInt()));

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