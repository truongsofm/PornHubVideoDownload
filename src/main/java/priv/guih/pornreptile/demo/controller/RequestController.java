package priv.guih.pornreptile.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import priv.guih.pornreptile.demo.service.impl.ProcessUrlServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author Guih
 */

@Controller
public class RequestController {

    private final ProcessUrlServiceImpl processUrlService;

    public RequestController(ProcessUrlServiceImpl processUrlService) {
        this.processUrlService = processUrlService;
    }

    @PostMapping("/url")
    public String requestUrl(@RequestParam @NotBlank String url, HttpServletRequest request) throws Exception {

        Map<String, String> videoUrl = processUrlService.getVideoUrl(url);
        request.setAttribute("videos", videoUrl);
        return "videos";
    }

}
