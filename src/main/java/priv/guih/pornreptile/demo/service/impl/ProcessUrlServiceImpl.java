package priv.guih.pornreptile.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import priv.guih.pornreptile.demo.service.ProcessUrlService;
import priv.guih.pornreptile.demo.util.ProcessUrl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author Guih
 */

@Service
public class ProcessUrlServiceImpl implements ProcessUrlService {

    /**
     * url处理工具类
     */
    private final ProcessUrl processUrl;

    public ProcessUrlServiceImpl(ProcessUrl processUrl) {
        this.processUrl = processUrl;
    }

    @Override
    public Map<String,String> getVideoUrl(String url) throws Exception {
        return processUrl.getAllVideo(url);
    }
}
