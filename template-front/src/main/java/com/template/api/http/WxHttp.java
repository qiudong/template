package com.template.api.http;

import com.alibaba.fastjson.JSON;
import com.template.api.common.HttpClient;
import com.template.api.http.entry.WxHttpResponse;

public class WxHttp {

    private static final String appid = "wxe779f0285da45097";
    private static final String secret = "d0bf84659ccc83872e8662195d555f94";

    /***
     * 获取access token
     * @return
     */
    public static String getAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        String result = HttpClient.doGet(url);
        return result;
    }


    /***
     * 发消息给用户
     * @param msg
     * @param msgType
     * @param openid
     * @return
     */
    public static String sendMsgToUsr(String msg, String msgType, String openid) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=18_0XCmZqMj-30q7O6AqNRx9ynbkemMEL5UE7Q1NC5pOsnPEEoGJZ_7XNBIGzK_7DTAfNrmW_wCGOraB7GGaNwYkM0r4G2t-fOyyrKnZt6itVbSpBZQfRyPWCLNw5wYNIfACAMBW";
        WxHttpResponse wxHttpResponse = new WxHttpResponse();
        WxHttpResponse.Content content = wxHttpResponse.new Content();
        content.setContent(msg);
        wxHttpResponse.setMsgtype(msgType);
        wxHttpResponse.setText(content);
        wxHttpResponse.setTouser(openid);

        String json = JSON.toJSON(wxHttpResponse).toString();
        String result = HttpClient.doPostJson(url, json);
        return result;
    }

}
