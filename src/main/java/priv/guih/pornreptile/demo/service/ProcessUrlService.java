package priv.guih.pornreptile.demo.service;

import org.springframework.http.HttpEntity;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

/**
 * @author Guih
 */
public interface ProcessUrlService {

    /**
     * 传入URL，响应不同清晰度的视频地址
     *
     * @param url 传入的url
     * @return HttpEntity
     * @throws Exception 异常
     */
    Map<String, String> getVideoUrl(String url) throws Exception;

}
