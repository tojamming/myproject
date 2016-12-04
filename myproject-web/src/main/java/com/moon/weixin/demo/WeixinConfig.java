package com.moon.weixin.demo;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.ApiConfigKit;

/**
 * Created by Administrator on 2016/12/4.
 */
public class WeixinConfig extends JFinalConfig {
    /**
     * ����������������ļ����ڣ������ȼ��ظ����ã�������ؿ������������ļ�
     * @param pro �������������ļ�
     * @param dev �������������ļ�
     */
    public void loadProp(String pro, String dev) {
        try {
            PropKit.use(pro);
        }
        catch (Exception e) {
            PropKit.use(dev);
        }
    }

    public void configConstant(Constants me) {
        loadProp("a_little_config_pro.txt", "a_little_config.txt");
        me.setDevMode(PropKit.getBoolean("devMode", false));

        // ApiConfigKit ��Ϊ����ģʽ�����ڿ����׶�������󽻻��� xml �� json ����
        ApiConfigKit.setDevMode(me.getDevMode());
    }

    public void configRoute(Routes me) {
        me.add("/msg", WeixinMsgController.class);
        me.add("/api", WeixinApiController.class, "/api");
    }

    public void configPlugin(Plugins me) {
        // C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
        // me.add(c3p0Plugin);

        // EhCachePlugin ecp = new EhCachePlugin();
        // me.add(ecp);
    }

    public void configInterceptor(Interceptors me) {

    }

    public void configHandler(Handlers me) {

    }

    public static void main(String[] args) {
        JFinal.start("webapp", 80, "/", 5);
    }
}
