package com.moon.weixin.demo;

import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.*;
import com.jfinal.weixin.sdk.jfinal.ApiController;

/**
 * Created by Administrator on 2016/12/4.
 */
public class WeixinApiController extends ApiController {

    public void index() {
        //render("/api/index.html");
        renderText("this my index");
    }

    /**
     * ���Ҫ֧�ֶ๫���˺ţ�ֻ��Ҫ�ڴ˷��ظ������ںŶ�Ӧ��  ApiConfig ���󼴿�
     * ����ͨ�������� url �йҲ�������̬�����ݿ��л�ȡ ApiConfig ����ֵ
     */
    public ApiConfig getApiConfig() {
        ApiConfig ac = new ApiConfig();

        // ����΢�� API ��س���
        ac.setToken(PropKit.get("token"));
        ac.setAppId(PropKit.get("appId"));
        ac.setAppSecret(PropKit.get("appSecret"));

        /**
         *  �Ƿ����Ϣ���м��ܣ���Ӧ��΢��ƽ̨����Ϣ�ӽ��ܷ�ʽ��
         *  1��true���м����ұ������� encodingAesKey
         *  2��false��������ģʽ��ͬʱҲ֧�ֻ��ģʽ
         */
        ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", false));
        ac.setEncodingAesKey(PropKit.get("encodingAesKey", "setting it in config file"));
        return ac;
    }

    /**
     * ��ȡ���ںŲ˵�
     */
    public void getMenu() {
        ApiResult apiResult = MenuApi.getMenu();
        if (apiResult.isSucceed())
            renderText(apiResult.getJson());
        else
            renderText(apiResult.getErrorMsg());
    }

    /**
     * �����˵�
     */
    public void createMenu()
    {
        String str = "{\n" +
                "    \"button\": [\n" +
                "        {\n" +
                "            \"name\": \"�������\",\n" +
                "            \"url\": \"http://m.bajie8.com/bajie/enter\",\n" +
                "            \"type\": \"view\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"��ȫ����\",\n" +
                "            \"key\": \"112\",\n" +
                "\t    \"type\": \"click\"\n" +
                "        },\n" +
                "        {\n" +
                "\t    \"name\": \"ʹ�ð���\",\n" +
                "\t    \"url\": \"http://m.bajie8.com/footer/cjwt\",\n" +
                "\t    \"type\": \"view\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        ApiResult apiResult = MenuApi.createMenu(str);
        if (apiResult.isSucceed())
            renderText(apiResult.getJson());
        else
            renderText(apiResult.getErrorMsg());
    }

    /**
     * ��ȡ���ںŹ�ע�û�
     */
    public void getFollowers()
    {
        ApiResult apiResult = UserApi.getFollows();
        renderText(apiResult.getJson());
    }

    /**
     * ��ȡ�û���Ϣ
     */
    public void getUserInfo()
    {
        ApiResult apiResult = UserApi.getUserInfo("ohbweuNYB_heu_buiBWZtwgi4xzU");
        renderText(apiResult.getJson());
    }

    /**
     * ����ģ����Ϣ
     */
    public void sendMsg()
    {
        String str = " {\n" +
                "           \"touser\":\"ohbweuNYB_heu_buiBWZtwgi4xzU\",\n" +
                "           \"template_id\":\"9SIa8ph1403NEM3qk3z9-go-p4kBMeh-HGepQZVdA7w\",\n" +
                "           \"url\":\"http://www.sina.com\",\n" +
                "           \"topcolor\":\"#FF0000\",\n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"��ϲ�㹺��ɹ���\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"ȥ�Ķ������ľƵ�����1����\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword2\":{\n" +
                "                       \"value\":\"1Ԫ\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"��ӭ�ٴι���\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        ApiResult apiResult = TemplateMsgApi.send(str);
        renderText(apiResult.getJson());
    }

    /**
     * ��ȡ������ά��
     */
    public void getQrcode()
    {
        String str = "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 123}}}";
        ApiResult apiResult = QrcodeApi.create(str);
        renderText(apiResult.getJson());

//        String str = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"123\"}}}";
//        ApiResult apiResult = QrcodeApi.create(str);
//        renderText(apiResult.getJson());
    }

    /**
     * ������ת�ɶ�����
     */
    public void getShorturl()
    {
        String str = "{\"action\":\"long2short\"," +
                "\"long_url\":\"http://wap.koudaitong.com/v2/showcase/goods?alias=128wi9shh&spm=h56083&redirect_count=1\"}";
        ApiResult apiResult = ShorturlApi.getShorturl(str);
        renderText(apiResult.getJson());
    }

    /**
     * ��ȡ�ͷ������¼
     */
    public void getRecord()
    {
        String str = "{\n" +
                "    \"endtime\" : 987654321,\n" +
                "    \"pageindex\" : 1,\n" +
                "    \"pagesize\" : 10,\n" +
                "    \"starttime\" : 123456789\n" +
                " }";
        ApiResult apiResult = CustomServiceApi.getRecord(str);
        renderText(apiResult.getJson());
    }

    /**
     * ��ȡ΢�ŷ�����IP��ַ
     */
    public void getCallbackIp()
    {
        ApiResult apiResult = CallbackIpApi.getCallbackIp();
        renderText(apiResult.getJson());
    }
}
