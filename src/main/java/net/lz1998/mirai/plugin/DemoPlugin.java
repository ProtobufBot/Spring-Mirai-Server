package net.lz1998.mirai.plugin;

import org.springframework.stereotype.Component;

@Component
public class DemoPlugin extends CQPlugin {


    @Override
    protected int setSortValue() {
        return 5;
    }
}
