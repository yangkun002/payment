package com.elextec.framework.filter;

import com.alibaba.fastjson.JSONObject;
import com.elextec.framework.common.constants.RunningResult;
import com.elextec.framework.common.constants.WzConstants;
import com.elextec.framework.common.response.MessageResponse;
import com.elextec.framework.plugins.redis.RedisClient;
import com.elextec.framework.utils.WzHttpUtil;
import com.elextec.framework.utils.WzStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 请求Filter.
 * @author wangtao
 */
@Order(Integer.MAX_VALUE)
@WebFilter(filterName = "VisitFilter", urlPatterns = "/*")
public class VisitFilter implements Filter {


    /** 日志. */
    private static final Logger logger = LoggerFactory.getLogger(VisitFilter.class);

    @Value("${localsetting.platform-type}")
    private String platformType;

    @Value("${localsetting.nofilters}")
    private String nofilters;

    @Value("${localsetting.white-flag}")
    private String whiteFlag;

    @Value("${localsetting.white-url}")
    private String whiteUrl;

    @Value("${localsetting.black-flag}")
    private String blackFlag;

    @Value("${localsetting.black-url}")
    private String blackUrl;

    @Autowired
    private RedisClient redisClient;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("===访问过滤器初始化===");
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {

        // 请求
        WzHttpServletRequestWrapper req = new WzHttpServletRequestWrapper((HttpServletRequest) request);
        // 响应
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setCharacterEncoding("UTF-8");
        // 请求URL
        String url = req.getRequestURI();
        // 请求方法
        String method = req.getMethod();
        // 请求IP
        String ipStr = WzHttpUtil.getClientIP(req);
        // Body参数
        String bodyParam = req.getBodyStr();

        logger.info("===[" + ipStr + "]请求:" + url + "[" + method + "]开始===");
        logger.info("===请求参数:" + bodyParam);

        if (WzStringUtil.isBlank(ipStr)) {
            blackFlag = "false";
            whiteFlag = "false";
        }
        // 黑名单验证
        if (WzStringUtil.isNotBlank(blackFlag) && "true".equals(blackFlag.toLowerCase())) {
            if (WzStringUtil.isNotBlank(blackUrl)) {
                String[] blackIps = blackUrl.split(",");
                for (int i = 0; i < blackIps.length; i++) {
                    if (ipStr.equalsIgnoreCase(blackIps[i])) {
                        logger.info("===[" + ipStr + "]请求:" + url + "[" + method + "]异常结束，原因[来访IP在黑名单中，拒绝访问]===");
                        MessageResponse errInfo = new MessageResponse(RunningResult.FORBIDDEN);
                        resp.getWriter().write(JSONObject.toJSONString(errInfo));
                        return;
                    }
                }
            }
        }
        // 白名单验证
        if (WzStringUtil.isNotBlank(whiteFlag) && "true".equals(whiteFlag.toLowerCase())) {
            boolean isOk = false;
            if (WzStringUtil.isBlank(whiteUrl)) {
                logger.info("===[" + ipStr + "]请求:" + url + "[" + method + "]异常结束，原因[需验证白名单但白名单为空，拒绝访问]===");
                MessageResponse errInfo = new MessageResponse(RunningResult.FORBIDDEN);
                resp.getWriter().write(JSONObject.toJSONString(errInfo));
                return;
            } else {
                String[] whiteIps = whiteUrl.split(",");
                for (int i = 0; i < whiteIps.length; i++) {
                    if (ipStr.equalsIgnoreCase(whiteIps[i])) {
                        isOk = true;
                        break;
                    }
                }
                if (!isOk) {
                    logger.info("===[" + ipStr + "]请求:" + url + "[" + method + "]异常结束，原因[来访IP不在白名单中，拒绝访问]===");
                    MessageResponse errInfo = new MessageResponse(RunningResult.FORBIDDEN);
                    resp.getWriter().write(JSONObject.toJSONString(errInfo));
                    return;
                }
            }
        }
        // 无需验证请求过滤
        if (WzStringUtil.isNotBlank(nofilters)) {
            String[] noFilterUrls = nofilters.split(",");
            for (int i = 0; i < noFilterUrls.length; i++) {
                if (-1 < url.indexOf(noFilterUrls[i])) {
                    chain.doFilter(req, resp);
                    logger.info("===[" + ipStr + "]请求:" + url + "[" + method + "]正常结束===");
                    return;
                }
            }
        }
        // 平台可访问验证
        if (WzStringUtil.isBlank(platformType)) {
            platformType = "mobile";
        }
        if ("all".equals(platformType.toLowerCase())) {
            // 所有接口均可访问
        }
        chain.doFilter(req, resp);
        logger.info("===[" + ipStr + "]请求:" + url + "[" + method + "]正常结束===");
    }

    @Override
    public void destroy() {
        logger.info("===访问过滤器销毁===");
    }

}
