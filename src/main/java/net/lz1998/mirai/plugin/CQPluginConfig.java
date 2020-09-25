package net.lz1998.mirai.plugin;

import java.util.Comparator;
import java.util.List;

import net.lz1998.bot.BotPlugin;
import net.lz1998.mirai.boot.MiraiProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class CQPluginConfig implements ApplicationContextAware {

    @Autowired
    public MiraiProperties miraiProperties;
    @Autowired
    public List<? extends BotPlugin> miraiPlugins ;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        miraiPlugins.sort(Comparator.comparingInt(o -> miraiProperties.getPluginList().indexOf(o.getClass())));
    }
}
