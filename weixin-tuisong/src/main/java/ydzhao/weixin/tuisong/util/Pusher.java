package ydzhao.weixin.tuisong.util;

import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.text.ParseException;

/**
 *@ClassName Pusher
 *@Description TODO
 *@Author ydzhao
 *@Date 2022/8/2 16:03
 */
public class Pusher {

    public static void main(String[] args) throws ParseException {
        push();
    }
    /**
     * 测试号的appId和secret
     */
    private static String appId = "wxa42251a2daf2d8c1";
    private static String secret = "ddca744ae0972f41049ed43529e73668";
    //模版id
    private static String templateId = "y_uXYW5wVPSL784IQ4WqBTM-uZz5CnpzXMDtcSVdZUM";
//    private static String templateId = "RWfbg4vn2MuN46RuVmI2f-bGU9tCMSNZGS2wIA6ODAU";

    private static String marks[]={"oBOWZ5sgkPBliPCmwXQ8qlH4pHow","oBOWZ5obwRvjqMZkgwdEt9IbW8DU","oBOWZ5j0rmnNq5hPgaXFU4v2semw"};
//    private static String marks[]={"oBOWZ5obwRvjqMZkgwdEt9IbW8DU","oBOWZ5j0rmnNq5hPgaXFU4v2semw"};

    public static void push() throws ParseException {
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appId);
        wxStorage.setSecret(secret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        for (String mark : marks) {
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(mark)
                    .templateId(templateId)
                    //.url("https://30paotui.com/")//点击模版消息要访问的网址
                    .build();

            //3,如果是正式版发送模版消息，这里需要配置你的信息
            //        templateMessage.addData(new WxMpTemplateData("name", "value", "#FF00FF"));
            //                templateMessage.addData(new WxMpTemplateData(name2, value2, color2));
            //填写变量信息，比如天气之类的
            JSONObject todayWeather = Tianqi.getNanjiTianqi();

            templateMessage.addData(new WxMpTemplateData("riqi", todayWeather.getString("date") + "  " + todayWeather.getString("week"), "#00BFFF"));
            templateMessage.addData(new WxMpTemplateData("tianqi", todayWeather.getString("text_day"), "#00FFFF"));
            templateMessage.addData(new WxMpTemplateData("low", todayWeather.getString("low") + "", "#173177"));
            templateMessage.addData(new WxMpTemplateData("high", todayWeather.getString("high") + "", "#FF6347"));
//        templateMessage.addData(new WxMpTemplateData("windClass",todayWeather.getString("windClass") + "","#173177"));
//        templateMessage.addData(new WxMpTemplateData("windDir",todayWeather.getString("windDir")+ "","#FF6347" ));
            templateMessage.addData(new WxMpTemplateData("caihongpi", CaiHongPi.getCaiHongPi(), "#FF69B4"));
//        templateMessage.addData(new WxMpTemplateData("lianai",JiNianRi.getLianAi()+"","#FF1493"));
            templateMessage.addData(new WxMpTemplateData("Age", JiNianRi.getAge() + "", "#FFA500"));
            templateMessage.addData(new WxMpTemplateData("shengri", JiNianRi.getShengRi() + "", "#FFA500"));
            templateMessage.addData(new WxMpTemplateData("jinju", CaiHongPi.getJinJu() + "", "#C71585"));
//        templateMessage.addData(new WxMpTemplateData("linzhen",JiNianRi.getLinZhen()+"","#FF6347"));
            String beizhu = "";
//        if(JiNianRi.getJieHun() % 365 == 0){
//            beizhu = "今天是结婚纪念日！";
//        }
            if (JiNianRi.getLianAi() % 365 == 0) {
                beizhu = "今天是恋爱纪念日！";
            }
//        if(JiNianRi.getLinZhen() % 365 == 0){
//            beizhu = "今天是结婚纪念日！";
//        }
            templateMessage.addData(new WxMpTemplateData("beizhu", beizhu, "#FF0000"));


            try {
                System.out.println(templateMessage.toJson());
                System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
            } catch (Exception e) {
                System.out.println("推送失败：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
