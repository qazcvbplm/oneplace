package com.ops.sunwou.wx;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ops.sunwou.exception.MyException;
import com.ops.util.HttpUtil;
import com.ops.util.XMLUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WXUtil {
    private static String openidurl = "https://api.weixin.qq.com/sns/jscode2session?appid=";
    private static String tokenurl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";  //获取token的api
    private static String codeurl = "https://api.weixin.qq.com/wxa/getwxacode?access_token=";                      //获取小程序二维码api
    private static String msurl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";
    private static String query = "https://api.mch.weixin.qq.com/pay/orderquery";//查询对账单
    private static String token = "";
    private static long tokenTime = 0;
    private static final long tokenTimeRefreshTime = 7200 * 1000;
    private static Gson gson = new Gson();

    /**
     * 获取openid
     *
     * @param code 客户端获取code
     */
    public static String wxlogin(String appid, String secert, String code) {
        StringBuffer sb = new StringBuffer();
        String nurl = sb.append(openidurl).append(appid).append("&secret=").append(secert).append("&js_code=").
                append(code).append("&grant_type=authorization_code").toString();
        String RequestOpenidResult = HttpUtil.doGet(nurl);
        JsonObject RequestOpenidjson = gson.fromJson(RequestOpenidResult, JsonObject.class);
        if (RequestOpenidjson.get("errcode") == null) {
            // 正确获取openid
            return RequestOpenidjson.get("openid").getAsString();
        } else {
            throw new MyException(RequestOpenidjson.get("errmsg").getAsString());
        }
    }

    /**
     * 获取access_token
     *
     * @param appid
     * @param secret
     * @return
     */
    public static String getAccessToken(String appid, String secert) {
        if (token != "" || ((System.currentTimeMillis() - tokenTime) >= tokenTimeRefreshTime)) {
            String rs = HttpUtil.doGet(tokenurl + "&appid=" + appid + "&secret=" + secert);
            JsonObject json = gson.fromJson(rs, JsonObject.class);
            return json.get("access_token").getAsString();
        } else {
            return token;
        }
    }

    /**
     * 获取小程序二维码
     *
     * @param page 页面路径
     * @param path 二维码路径
     * @return
     */
    public static int getCode(String appid, String secert, String page, String path) {
        String rs = HttpUtil.doGet(tokenurl + "&appid=" + appid + "&secret=" + secert);
        JsonObject json = gson.fromJson(rs, JsonObject.class);
        String token = json.get("access_token").getAsString();
        JsonObject params = new JsonObject();
        params.addProperty("path", page);
        params.addProperty("width", 430);
        String u = codeurl + token;
        InputStream in = PayUtil.httpRequest2(u, "POST", params.toString());
        if (in == null) {
            return 0;
        }
        try {
            FileOutputStream fileout = new FileOutputStream(new File(path));
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = in.read(b)) != -1) {
                fileout.write(b, 0, len);
                fileout.flush();
            }
            fileout.close();
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 发送小程序服务通知模板
     * touser 用户openid
     * template_id 模板id
     * form_id  表单id  或者支付id
     * keyword 关键字 1,2,3,4,5,6,7.。。。
     */
    public static void snedM(Map<String, String> map) {
        //发送模板消息
        String access_token = getAccessToken(map.get("appid"), map.get("secert"));
        JsonObject output = new JsonObject();
        output.addProperty("touser", map.get("touser"));
        output.addProperty("template_id", map.get("template_id"));
        output.addProperty("form_id", map.get("form_id"));
        JsonObject data = new JsonObject();
        JsonObject keyword;
        int count = Integer.valueOf(map.get("keywordcount"));
        for (int i = 1; i < count; i++) {
            keyword = new JsonObject();
            keyword.addProperty("value", map.get("keyword" + i));
            keyword.addProperty("color", "#173177");
            data.add("keyword" + i, keyword);
        }
        output.add("data", data);
        String rs = PayUtil.httpRequest(msurl + access_token, "POST", output.toString());
        String a = "";
    }

    public static boolean checkFK(String appid, String mch_id, String key, String out_trade_no) {
        boolean rs = false;
        String nonce_str = UUID.randomUUID().toString().trim().replaceAll("-", "");
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", appid);
        params.put("mch_id", mch_id);
        params.put("out_trade_no", out_trade_no);
        params.put("nonce_str", nonce_str);
        params = PayUtil.paraFilter(params);
        String rs1 = PayUtil.createLinkString(params);
        String rs2 = "&key=" + key;
        String sign = PayUtil.sign(rs1, rs2, "utf-8").toUpperCase();
        params.put("sign", sign);
        QueryEntity q = new QueryEntity(appid, mch_id, out_trade_no, sign, nonce_str);
        String respXml = MessageUtil.messageToXML2(q);
        respXml = respXml.replace("__", "_");
        String result = PayUtil.httpRequest(query, "POST", respXml);
        Map<String, String> map = XMLUtil.parseXML2map(result);
        if (map.get("trade_state_desc") != null && map.get("trade_state_desc").equals("支付成功")) {
            rs = true;
        }
        return rs;
    }

}
