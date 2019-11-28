package priv.guih.pornreptile.demo.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.ProxyHost;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Guih
 */
@Component
public class ProcessUrl {

    private String[] videoQualities = new String[]{"quality_240p", "quality_480p", "quality_720p", "quality_1080p"};

    public Map<String, String> getAllVideo(String url) throws Exception {
        Map<String, String> map = new HashMap<>(50);
        Map<String, String> uriDataMap = new HashMap<>(4);
        StringBuilder s = new StringBuilder();

        HttpClient client = new HttpClient();

//        client.getHostConfiguration().setProxy("127.0.0.1", 1080);
        client.getHttpConnectionManager().getParams()
                .setConnectionTimeout(30000);

        //使用GET方法
        HttpMethod method = new GetMethod(url);

        // 解决：Cookie rejected: violates RFC 2109: domain must start with a dot
        method.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);


        client.executeMethod(method);
        // 打印服务器返回的状态
        System.out.println("Status:" + method.getStatusLine());

        // 返回体
        String responseBody = method.getResponseBodyAsString();

        // 截取视频链接大概位置
        int firstStart = responseBody.indexOf("player_mp4_seek");
        int firstEnd = responseBody.indexOf("loadScriptVar.push");
        String firstString = responseBody.substring(firstStart, firstEnd);

        // 将字符串中的var转为空
        String noHaveVar = firstString.replaceAll("var", "");

        // 根据 ";" 分割为字符数组
        String[] split = noHaveVar.trim().split(";");

        for (int i = 0; i < split.length - 5; i++) {
            // 获取等号位置,将等号左右两边作为键值对
            int index = split[i].indexOf("=");

            String mapKey = split[i].substring(1, index);
            String mapValue = split[i].substring(index + 1);

            // 一些Value中是 ‘ “xxx”+“yyy” ’ 形式，去掉中间的加号。合在一起
            String mapValueEnd = mapValue.replace("\" + \"", "");
            map.put(mapKey, mapValueEnd);
        }

        // 获取240 480 720 1080视频地址，如果没有会报空指针异常
        for (String videoQuality : videoQualities) {
            try {
                // 从map中获取对应视频清晰度的value
                String videoValue = map.get(videoQuality);

                // 将获取到的value中的注释删去
                String removeComment = videoValue.replaceAll("/\\*\\s\\+\\s[A-Za-z0-9]{10,30}\\s\\+\\s\\*/", "");

                // 根据“ + ”来分割成数组
                String[] urlKeys = removeComment.split("\\s\\+\\s");

                // 遍历数组。去map找对应的value
                for (String value : urlKeys) {
                    String s1 = map.get(value);
                    if (s1 == null) {

                        // 因为第一行哪里有换行,所以第一个会为null
                        String tempValue = "\n\t\t " + value;

                        String s3 = map.get(tempValue);
                        if (s3 != null) {
                            s.append(s3);
                            continue;
                        }
                    }

                    s.append(s1);
                }

                // 去掉引号
                String videoUrlStr = s.toString().replaceAll("\"", "");

                int index = videoUrlStr.indexOf("null");

                // 清空用于下一次
                s.delete(0, s.length());

                if (index != -1) {
                    // 里面含有null
                    uriDataMap.put(videoQuality.substring(8), "解析此格式失败，请观看其他格式或重新解析！" +
                            "Parsing this format failed, watch another format or re-parse!");
                    continue;
                }

                uriDataMap.put(videoQuality.substring(8), videoUrlStr);

            } catch (NullPointerException e) {

                uriDataMap.put(videoQuality.substring(8), "此视频不存在 " + videoQuality.substring(8) + " 格式！"
                        + "This video does not exist " + videoQuality.substring(8) + " Format!");
            }
        }
        // 释放连接
        method.releaseConnection();

        return uriDataMap;
    }

}