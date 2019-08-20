package com.ops.sunwou.wx;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ops.util.XMLUtil;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @author onepieces
 */
public class WXpayUtil {

    private static final String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //private static final String mch_id = "1450755702";
    private static final String device_info = "WEB";
    //private static final String key = "medusa18058505737medusa180585057"; //medusa18058505737medusa180585057
    private static String sign = "";
    private static final String sign_type = "MD5";
    private static final String fee_type = "CNY";
    private static String spbill_create_ip = "";
    private static String goods_tag = "WXG";
    //private static final String notify_url = "https://www.sunwou.com/frame";//回调地址
    private static final String trade_type = "JSAPI";
    private static String limit_pay = "no_credit";
    private static final Gson gson = new Gson();

    public static Map<String, NotifyImple> notifyimple = new HashMap<>();

    /**
     * @param appid        小程序appid
     * @param mch_id       商户号
     * @param key          支付密钥
     * @param body         商品描述  格式  *-*
     * @param out_trade_no 商户订单好
     * @param total_fee    总金额以分为单位
     * @param openid
     * @param addr         客户端ip
     * @param attach       自定义附加字段
     * @param notify
     * @return
     */
    public static Object payrequest(String appid, String mch_id, String key, String body, String out_trade_no, String total_fee,
                                    String openid, String addr, JsonObject attach, String notifyUrl, NotifyImple notify) {
        String nonce_str = getUUID();
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", appid);
        params.put("mch_id", mch_id);
        params.put("nonce_str", nonce_str);
        params.put("body", body);
        params.put("out_trade_no", out_trade_no);
        params.put("total_fee", total_fee);
        params.put("spbill_create_ip", addr);
        params.put("notify_url", notifyUrl);
        params.put("trade_type", trade_type);
        params.put("openid", openid);
        params.put("attach", attach.toString());
        params = PayUtil.paraFilter(params);
        String rs1 = PayUtil.createLinkString(params);
        String rs2 = "&key=" + key;
        sign = PayUtil.sign(rs1, rs2, "utf-8").toUpperCase();
        params.put("sign", sign);
        PaymentEntity pay = new PaymentEntity(params);
        if (openid.contains("__")) {
            pay.setOpenid(pay.getOpenid().replace("__", "o@p"));
        }
        String respXml = MessageUtil.messageToXML(pay);
        respXml = respXml.replace("__", "_");
        if (openid.contains("__")) {
            respXml = respXml.replace("o@p", "__");
        }
        String result = PayUtil.httpRequest(url, "POST", respXml);
        Map<String, String> map = XMLUtil.parseXML2map(result);
        // 返回信息
        String return_code = map.get("return_code");//返回状态码
        String return_msg = map.get("return_msg");//返回信息
        if (return_code.equals("SUCCESS")) {
            if (!notifyimple.containsKey(attach.get("callback").getAsString())) {
                notifyimple.put(attach.get("callback").getAsString(), notify);
            }
            // 业务结果
            String nonceStr = UUIDHexGenerator.generate();
            map.put("nonceStr", nonceStr);
            Long timeStamp = System.currentTimeMillis() / 1000;
            map.put("time", timeStamp + "");
            String stringSignTemp = "appId=" + appid + "&nonceStr=" + nonceStr + "&package=prepay_id=" + map.get("prepay_id") + "&signType=MD5&timeStamp=" + timeStamp;
            //再次签名
            String paySign = PayUtil.sign(stringSignTemp, "&key=" + key, "utf-8").toUpperCase();
            map.put("paySign", paySign);
            map.put("total_fee", total_fee);
        }
        return map;
    }

    /**
     * 对字符串md5加密
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        try {
            // 生成�??个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()�??后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就�??8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash�??
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }


    /**
     * 生成32位编码
     *
     * @return string
     */
    private static String getUUID() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }


    /**
     * 回调方法封装
     *
     * @throws IOException
     */
    public static void notify(HttpServletRequest req, HttpServletResponse rep) throws IOException {
        NotifyImple notify = null;
        Map<String, String> map = null;
        try {
            map = XMLUtil.parseXML(req);
        } catch (SAXException e) {
            e.printStackTrace();
        }
        // 返回状态码
        String return_code = map.get("return_code");
        // 返回信息
        String return_msg = map.get("return_msg");
        // 业务结果,判断交易是否成功
        String result_code = map.get("result_code");
        //out_trade_no用户订单?
      /*String out_trade_no=map.get("out_trade_no");
        String openid=map.get("openid"); 
        String total_fee=map.get("total_fee");*/
        String attach = map.get("attach");
        JsonObject json = gson.fromJson(attach, JsonObject.class);
        if ((notify = WXpayUtil.notifyimple.get(json.get("callback").getAsString())) != null) {
            if (return_code == "SUCCESS" || return_code.equals("SUCCESS")) {
                //处理业务逻辑
                if (result_code.equals("SUCCESS")) {
                    if (notify.notifcation(map)) {
                        PrintWriter out = rep.getWriter();
                        out.print(setXML("SUCCESS", "SUCCESS"));
                        out.flush();
                        out.close();
                    }
                }
            }
        } else {
            //Util.outLog("回调失败  未找到实现方法   订单号："+map.get("out_trade_no"));
        }
    }


    public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code>"
                + "<return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
    }


}
