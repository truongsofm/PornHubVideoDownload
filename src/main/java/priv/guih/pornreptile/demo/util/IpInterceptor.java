package priv.guih.pornreptile.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Guih
 */
@Component
@Slf4j
public class IpInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getParameter("url");
        String ipAddr = IpUtil.getIpAddr(request);
        String requestURI = request.getRequestURI();

        log.info("requestURI:" + requestURI);
        log.info("requestProcessUrl:" + url);
        log.info("requestIpAddr:" + ipAddr);

        return true;
    }

}
