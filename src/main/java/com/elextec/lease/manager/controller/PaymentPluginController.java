package com.elextec.lease.manager.controller;

import com.alibaba.fastjson.JSONObject;
import com.elextec.framework.BaseController;
import com.elextec.framework.common.constants.RunningResult;
import com.elextec.framework.common.response.MessageResponse;
import com.elextec.framework.utils.WzStringUtil;
import com.elextec.lease.manager.service.PaymentPluginService;
import com.elextec.persist.model.mybatis.PaymentPlugin;
import jodd.util.net.URLDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/PaymentPluginController")
public class PaymentPluginController extends BaseController {

    /* 日志 */
    private final Logger logger = LoggerFactory.getLogger(PaymentPluginController.class);

    @Autowired
    private PaymentPluginService paymentPluginService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public MessageResponse list(@RequestBody String param){
        if (WzStringUtil.isBlank(param)) {
            logger.error("参数不能为空");
            return new MessageResponse(RunningResult.NO_PARAM);
        }
        String paramStr = URLDecoder.decode(param,"utf-8");
        Map<String,Object> map = JSONObject.parseObject(paramStr,Map.class);
        if (map.size() == 0) {
            logger.error("参数解析错误");
            return new MessageResponse(RunningResult.PARAM_ANALYZE_ERROR);
        }
        return new MessageResponse(RunningResult.SUCCESS,paymentPluginService.list(map));
    }

    @RequestMapping(value = "/addone",method = RequestMethod.POST)
    public MessageResponse addone(@RequestBody String param){
        if (WzStringUtil.isBlank(param)) {
            logger.error("参数不能为空");
            return new MessageResponse(RunningResult.NO_PARAM);
        }
        String paramStr = URLDecoder.decode(param,"utf-8");
        PaymentPlugin paymentPlugin = JSONObject.parseObject(paramStr,PaymentPlugin.class);
        if (WzStringUtil.isBlank(paymentPlugin.getId())) {
            logger.error("参数解析错误");
            return new MessageResponse(RunningResult.PARAM_ANALYZE_ERROR.code(),"添加参数不能为空");
        }
        paymentPluginService.insterPaymentPlugin(paymentPlugin);
        return new MessageResponse(RunningResult.SUCCESS);
    }

    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public MessageResponse modify(@RequestBody String param){
        if (WzStringUtil.isBlank(param)) {
            logger.error("参数不能为空");
            return new MessageResponse(RunningResult.NO_PARAM);
        }
        String paramStr = URLDecoder.decode(param,"utf-8");
        PaymentPlugin paymentPlugin = JSONObject.parseObject(paramStr,PaymentPlugin.class);
        if (WzStringUtil.isBlank(paymentPlugin.getId())) {
            logger.error("参数解析错误");
            return new MessageResponse(RunningResult.PARAM_ANALYZE_ERROR);
        }
        paymentPluginService.updatePaymentPlugin(paymentPlugin);
        return new MessageResponse(RunningResult.SUCCESS);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public MessageResponse delete(@RequestBody String param){
        return new MessageResponse(RunningResult.SUCCESS);
    }

    @RequestMapping(value = "/getByPK",method = RequestMethod.POST)
    public MessageResponse getByPK(@RequestBody String param){
        return new MessageResponse(RunningResult.SUCCESS);
    }


}
