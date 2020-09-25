package net.lz1998.mirai.plugin;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class CQPluginConfig implements ApplicationContextAware {

    public List<CQPlugin> cqPlugins = new LinkedList<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String,CQPlugin> map =applicationContext.getBeansOfType(CQPlugin.class);
        this.cqPlugins= new LinkedList<CQPlugin>(map.values());
        this.cqPlugins.forEach(CQPlugin::setSort);
        this.cqPlugins.sort(new Comparator<CQPlugin>() {
            @Override
            public int compare(CQPlugin o1, CQPlugin o2) {
                return o1.getSort() - o2.getSort();
            }
        });
    }
}
