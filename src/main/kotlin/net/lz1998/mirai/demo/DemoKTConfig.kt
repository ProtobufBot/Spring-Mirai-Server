package net.lz1998.mirai.demo

import net.lz1998.mirai.plugin.CQPlugin
import org.springframework.stereotype.Component

@Component
class DemoKTConfig : CQPlugin() {
    override fun setSortValue(): Int {
        return 10;
    }

}