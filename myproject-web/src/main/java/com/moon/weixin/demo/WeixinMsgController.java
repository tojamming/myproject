package com.moon.weixin.demo;

import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.jfinal.MsgController;
import com.jfinal.weixin.sdk.msg.in.*;
import com.jfinal.weixin.sdk.msg.in.event.*;
import com.jfinal.weixin.sdk.msg.in.speech_recognition.InSpeechRecognitionResults;
import com.jfinal.weixin.sdk.msg.out.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ���� DemoController ��YourJFinalConfig ��ע��·�ɣ�
 * �����ú�weixin���������ĵ� URL �� token ��ʹ URL ָ���
 * DemoController �̳��Ը��� WeixinController �� index
 * ��������ֱ�����п�Ч�����ڴ˻���֮���޸���صķ������ɽ���ʵ����Ŀ����
 */
public class WeixinMsgController extends MsgController {

    static Logger logger = LoggerFactory.getLogger(WeixinMsgController.class);
    private static final String helpStr = "\t���� help �ɻ�ð���������\"��Ƶ\" �ɻ�ȡ��Ƶ�̳̣����� \"��Ů\" �ɿ���Ů������ music �������� ���������ſɿ�JFinal�°汾��Ϣ�����ںŹ��ܳ���������";

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
     * ʵ�ָ���鷽���������ı���Ϣ
     * �������и�����Ϣ�еĲ�ͬ�ı����ݷֱ������˲�ͬ����Ӧ��ͬʱҲ��Ϊ�˲��� jfinal weixin sdk�Ļ������ܣ�
     *     �������������� OutTextMsg��OutNewsMsg��OutMusicMsg �������͵�OutMsg��
     *     �������͵���Ϣ�������ķ����н��в���
     */
    protected void processInTextMsg(InTextMsg inTextMsg) {
        String msgContent = inTextMsg.getContent().trim();
        // ������ʾ
        if ("help".equalsIgnoreCase(msgContent) || "����".equals(msgContent)) {
            OutTextMsg outMsg = new OutTextMsg(inTextMsg);
            outMsg.setContent(helpStr);
            render(outMsg);
        }
        // ͼ����Ϣ����
        else if ("news".equalsIgnoreCase(msgContent) || "����".equalsIgnoreCase(msgContent)) {
            OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
            outMsg.addNews("�ҵ�΢�Ź��ں�javenlife", "Jfinal����΢�ż�������","https://mmbiz.qlogo.cn/mmbiz/ibHRiaZ9MRcUosol56OtHjVibWTK9opiaxsYTQHXuRwoib8YobOfqCbykp3ZSaEk8czAqdkAARU0OdKDtv34F5evFIQ/0?wx_fmt=jpeg", "http://mp.weixin.qq.com/s?__biz=MzA4MDA2OTA0Mg==&mid=208184833&idx=1&sn=d9e615e45902c3c72db6c24b65c4af3e#rd");
            outMsg.addNews("�ҵĲ��͡��ǻ��ƶ��ռǡ�", "���ھͼ��� JFinal ���ٿ������磬��ʡ����ʱ��ȥ��Ů����ɽ��ˮ ^_^", "https://mmbiz.qlogo.cn/mmbiz/ibHRiaZ9MRcUosol56OtHjVibWTK9opiaxsY9tPDricojmV5xxuLJyibZJXMAdNOx1qbZFcic9SvsPF2fTUnSc9oQB1IQ/0?wx_fmt=jpeg","http://mp.weixin.qq.com/s?__biz=MzA4MDA2OTA0Mg==&mid=208413033&idx=1&sn=06e816e1b2905c46c9a81df0ac0b3bad#rd");
            render(outMsg);
        }
        // ������Ϣ����
        else if ("music".equalsIgnoreCase(msgContent) || "����".equals(msgContent)) {
            OutMusicMsg outMsg = new OutMusicMsg(inTextMsg);
            outMsg.setTitle("When The Stars Go Blue-Venke Knutson");
            outMsg.setDescription("������ WIFI �������������ʹ�����");
            outMsg.setMusicUrl("http://www.jfinal.com/When_The_Stars_Go_Blue-Venke_Knutson.mp3");
            outMsg.setHqMusicUrl("http://www.jfinal.com/When_The_Stars_Go_Blue-Venke_Knutson.mp3");
            outMsg.setFuncFlag(true);
            render(outMsg);
        }
        else if ("��Ů".equalsIgnoreCase(msgContent)) {
            OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
            outMsg.addNews(
                    "javenlife ���������",
                    "javenlife ��������ඣ�����ֻ����Ů ^_^",
                    "https://mmbiz.qlogo.cn/mmbiz/ibHRiaZ9MRcUosol56OtHjVibWTK9opiaxsYTQHXuRwoib8YobOfqCbykp3ZSaEk8czAqdkAARU0OdKDtv34F5evFIQ/0?wx_fmt=jpeg",
                    "http://mp.weixin.qq.com/s?__biz=MzA4MDA2OTA0Mg==&mid=207820985&idx=1&sn=4ef9361e68495fc3ba1d2f7f2bca0511#rd");


            render(outMsg);
        }
        // �����ı���Ϣֱ�ӷ���ԭֵ + ������ʾ
        else {
            renderOutTextMsg("\t�ı���Ϣ�ѳɹ����գ�����Ϊ�� " + inTextMsg.getContent() + "\n\n" + helpStr);
        }
    }

    /**
     * ʵ�ָ���鷽��������ͼƬ��Ϣ
     */
    protected void processInImageMsg(InImageMsg inImageMsg) {
        OutImageMsg outMsg = new OutImageMsg(inImageMsg);
        // ���շ�������ͼƬ�ٷ���ȥ
        outMsg.setMediaId(inImageMsg.getMediaId());
        render(outMsg);
    }

    /**
     * ʵ�ָ���鷽��������������Ϣ
     */
    protected void processInVoiceMsg(InVoiceMsg inVoiceMsg) {
        OutVoiceMsg outMsg = new OutVoiceMsg(inVoiceMsg);
        // ���շ������������ٷ���ȥ
        outMsg.setMediaId(inVoiceMsg.getMediaId());
        render(outMsg);
    }

    /**
     * ʵ�ָ���鷽����������Ƶ��Ϣ
     */
    protected void processInVideoMsg(InVideoMsg inVideoMsg) {
        /* ��Ѷ api �� bug���޷��ظ���Ƶ��Ϣ����ʱ�ظ��ı���Ϣ�������
        OutVideoMsg outMsg = new OutVideoMsg(inVideoMsg);
        outMsg.setTitle("OutVideoMsg ����");
        outMsg.setDescription("�ոշ�������Ƶ�ٷ���ȥ");
        // ���շ���������Ƶ�ٷ���ȥ��������֤������Ѷ�ٷ��� api �� bug���� api bug ȴ��������
        outMsg.setMediaId(inVideoMsg.getMediaId());
        render(outMsg);
        */
        OutTextMsg outMsg = new OutTextMsg(inVideoMsg);
        outMsg.setContent("\t��Ƶ��Ϣ�ѳɹ����գ�����Ƶ�� mediaId Ϊ: " + inVideoMsg.getMediaId());
        render(outMsg);
    }

    @Override
    protected void processInShortVideoMsg(InShortVideoMsg inShortVideoMsg)
    {
        OutTextMsg outMsg = new OutTextMsg(inShortVideoMsg);
        outMsg.setContent("\t��Ƶ��Ϣ�ѳɹ����գ�����Ƶ�� mediaId Ϊ: " + inShortVideoMsg.getMediaId());
        render(outMsg);
    }

    /**
     * ʵ�ָ���鷽���������ַλ����Ϣ
     */
    protected void processInLocationMsg(InLocationMsg inLocationMsg) {
        OutTextMsg outMsg = new OutTextMsg(inLocationMsg);
        outMsg.setContent("���յ�����λ����Ϣ:" +
                "\nlocation_X = " + inLocationMsg.getLocation_X() +
                "\nlocation_Y = " + inLocationMsg.getLocation_Y() +
                "\nscale = " + inLocationMsg.getScale() +
                "\nlabel = " + inLocationMsg.getLabel());
        render(outMsg);
    }

    /**
     * ʵ�ָ���鷽��������������Ϣ
     * �ر�ע�⣺����ʱ��Ҫ�����ҵ��ղ��е������ղع���ͼ����Ϣ��ֱ�ӷ������ӵ�ַ�ᵱ���ı���Ϣ������
     */
    protected void processInLinkMsg(InLinkMsg inLinkMsg) {
        OutNewsMsg outMsg = new OutNewsMsg(inLinkMsg);
        outMsg.addNews("������Ϣ�ѳɹ�����", "����ʹ��ͼ����Ϣ�ķ�ʽ���ظ��㣬������ʹ���ı���ʽ���ء����ͼ����Ϣ����ת�����ӵ�ַҳ�棬�ǲ��Ǻܺ��� :)" , "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq1ibBkhSA1BibMuMxLuHIvUfiaGsK7CC4kIzeh178IYSHbYQ5eg9tVxgEcbegAu22Qhwgl5IhZFWWXUw/0", inLinkMsg.getUrl());
        render(outMsg);
    }

    @Override
    protected void processInCustomEvent(InCustomEvent inCustomEvent)
    {
        System.out.println("processInCustomEvent() �������Գɹ�");
    }

    /**
     * ʵ�ָ���鷽���������ע/ȡ����ע��Ϣ
     */
    protected void processInFollowEvent(InFollowEvent inFollowEvent) {
        OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
        outMsg.setContent("��л��ע JFinal Weixin ���ٿ�������ţ�Ϊ����Լ����ʱ�䣬ȥ�����ˡ����˺����� :) \n\n\n " + helpStr);
        // ���Ϊȡ����ע�¼������޷����յ����ص���Ϣ
        render(outMsg);
    }

    /**
     * ʵ�ָ���鷽��������ɨ���������ά���¼�
     */
    protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
        OutTextMsg outMsg = new OutTextMsg(inQrCodeEvent);
        outMsg.setContent("processInQrCodeEvent() �������Գɹ�");
        render(outMsg);
    }

    /**
     * ʵ�ָ���鷽���������ϱ�����λ���¼�
     */
    protected void processInLocationEvent(InLocationEvent inLocationEvent) {
        OutTextMsg outMsg = new OutTextMsg(inLocationEvent);
        outMsg.setContent("processInLocationEvent() �������Գɹ�");
        render(outMsg);
    }

    @Override
    protected void processInMassEvent(InMassEvent inMassEvent)
    {
        System.out.println("processInMassEvent() �������Գɹ�");
    }

    /**
     * ʵ�ָ���鷽���������Զ���˵��¼�
     */
    protected void processInMenuEvent(InMenuEvent inMenuEvent) {
        renderOutTextMsg("processInMenuEvent() �������Գɹ�");
    }

    /**
     * ʵ�ָ���鷽���������������ʶ����
     */
    protected void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults) {
        renderOutTextMsg("����ʶ������ " + inSpeechRecognitionResults.getRecognition());
    }

    // ������յ���ģ����Ϣ�Ƿ��ʹ�ɹ�֪ͨ�¼�
    protected void processInTemplateMsgEvent(InTemplateMsgEvent inTemplateMsgEvent) {
        String status = inTemplateMsgEvent.getStatus();
        renderOutTextMsg("ģ����Ϣ�Ƿ���ճɹ���" + status);
    }

    @Override
    protected void processInShakearoundUserShakeEvent(InShakearoundUserShakeEvent inShakearoundUserShakeEvent) {

    }

    @Override
    protected void processInVerifySuccessEvent(InVerifySuccessEvent inVerifySuccessEvent) {

    }

    @Override
    protected void processInVerifyFailEvent(InVerifyFailEvent inVerifyFailEvent) {

    }

    @Override
    protected void processInPoiCheckNotifyEvent(InPoiCheckNotifyEvent inPoiCheckNotifyEvent) {

    }

    @Override
    protected void processInWifiEvent(InWifiEvent inWifiEvent) {

    }

    @Override
    protected void processInUserViewCardEvent(InUserViewCardEvent inUserViewCardEvent) {

    }

    @Override
    protected void processInSubmitMemberCardEvent(InSubmitMemberCardEvent inSubmitMemberCardEvent) {

    }

    @Override
    protected void processInUpdateMemberCardEvent(InUpdateMemberCardEvent inUpdateMemberCardEvent) {

    }

    @Override
    protected void processInUserPayFromCardEvent(InUserPayFromCardEvent inUserPayFromCardEvent) {

    }

    @Override
    protected void processInMerChantOrderEvent(InMerChantOrderEvent inMerChantOrderEvent) {

    }

    @Override
    protected void processIsNotDefinedEvent(InNotDefinedEvent inNotDefinedEvent) {

    }

    @Override
    protected void processIsNotDefinedMsg(InNotDefinedMsg inNotDefinedMsg) {

    }


}
