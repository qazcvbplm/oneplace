package com.ops.util;

import com.google.gson.JsonObject;

public class WxMapUtil {

    private final static String distanceUrl = "https://apis.map.qq.com/ws/distance/v1/?parameters?mode=walking&key=MJ3BZ-VA632-QHGUY-COFWL-U6LME-TGBQR";


    public static JsonObject getDistance(String from, String to) {
        StringBuilder sb = new StringBuilder();
        sb.append(distanceUrl).append("&from=").append(from).append("&to=").append(to);
        String rs = HttpUtil.doGet(sb.toString());
        return ResultUtil.gson.fromJson(rs, JsonObject.class);
    }
}
