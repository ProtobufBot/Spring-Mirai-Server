package net.lz1998.mirai

import net.lz1998.mirai.utils.toMessageList
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SpringMiraiServerApplicationTests {

    @Test
    fun contextLoads() {
        val result="123[CQ:at,qq=875543533]".toMessageList()
        println(result)
    }

}
