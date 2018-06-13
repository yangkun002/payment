package com.elextec.lease.manager.controller;

import com.alibaba.fastjson.JSONObject;
import com.elextec.framework.common.constants.RunningResult;
import com.elextec.framework.common.response.MessageResponse;
import com.elextec.framework.utils.WzStringUtil;
import com.elextec.framework.utils.WzUniqueValUtil;
import com.elextec.lease.manager.service.DetailedService;
import com.elextec.persist.field.enums.BusinessType;
import com.elextec.persist.field.enums.PaymentType;
import com.elextec.persist.model.mybatis.Detailed;
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
@RequestMapping(value = "/DetailedController")
public class DetailedController {

    /* 日志 */
    private final Logger logger = LoggerFactory.getLogger(BusinessController.class);

    @Autowired
    private DetailedService detailedService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public MessageResponse list(@RequestBody String param){
        if (WzStringUtil.isBlank(param)) {
            logger.error("参数为空");
            return new MessageResponse(RunningResult.NO_PARAM);
        }
        String paramStr = URLDecoder.decode(param,"utf-8");
        Map<String,Object> map = JSONObject.parseObject(paramStr,Map.class);
        if (map.size() == 0) {
            logger.error("参数解析失败");
            return new MessageResponse(RunningResult.PARAM_ANALYZE_ERROR);
        }
        return new MessageResponse(RunningResult.SUCCESS,detailedService.list(map));
    }

    @RequestMapping(value = "/addone",method = RequestMethod.POST)
    public MessageResponse addone(@RequestBody String param){
        if (WzStringUtil.isBlank(param)) {
            logger.error("参数为空");
            return new MessageResponse(RunningResult.NO_PARAM);
        }
        String paramStr = URLDecoder.decode(param,"utf-8");
        Detailed detailed = JSONObject.parseObject(paramStr,Detailed.class);
        if (WzStringUtil.isBlank(detailed.getMchId())
                || detailed.getOrderTime() ==null
                || detailed.getPaymentTime() == null) {
            logger.error("参数解析失败");
            return new MessageResponse(RunningResult.PARAM_ANALYZE_ERROR);
        }
        if (!detailed.getType().toString().equals(PaymentType.ALIPAY_APPPAY.toString())
                || !detailed.getType().toString().equals(PaymentType.ALIPAY_BARCODEPAY.toString())
                || !detailed.getType().toString().equals(PaymentType.ALIPAY_HUABEIPAY.toString())
                || !detailed.getType().toString().equals(PaymentType.ALIPAY_MOBILEWEBPAY.toString())
                || !detailed.getType().toString().equals(PaymentType.ALIPAY_SCANNERPAY.toString())
                || !detailed.getType().toString().equals(PaymentType.ALIPAY_WEBSITEPAY.toString())
                || !detailed.getType().toString().equals(PaymentType.WEWHAT_APPPAY.toString())
                || !detailed.getType().toString().equals(PaymentType.WEWHAT_CARDPAY.toString())
                || !detailed.getType().toString().equals(PaymentType.WEWHAT_PUBLICNUMBERPAY.toString())
                || !detailed.getType().toString().equals(PaymentType.WEWHAT_SCANNERPAY.toString())
                || !detailed.getType().toString().equals(PaymentType.WEWHAT_WEPAY.toString())) {
            logger.error("交易参数有误");
            return new MessageResponse(RunningResult.PARAM_ANALYZE_ERROR.code(),"交易方式参数有误");
        }
        if (!detailed.getState().toString().equals(BusinessType.ALREADY_PAID.toString())
                || !detailed.getState().toString().equals(BusinessType.PAYMENT_UNDETERMINED.toString())
                || !detailed.getState().toString().equals(BusinessType.CLOSE_COMPLETE.toString())
                || !detailed.getState().toString().equals(BusinessType.CLOSE_UNDETERMINED.toString())
                || !detailed.getState().toString().equals(BusinessType.REFUND_UNDETERMINED.toString())
                || !detailed.getState().toString().equals(BusinessType.REFUND_COMPLETE.toString())
                || !detailed.getState().toString().equals(BusinessType.REVOKE_UNDETERMINED.toString())
                || !detailed.getState().toString().equals(BusinessType.REVOKE_COMPLETE.toString())) {
            logger.error("交易参数有误");
            return new MessageResponse(RunningResult.PARAM_ANALYZE_ERROR.code(),"交易状态参数有误");
        }
        detailed.setId(WzUniqueValUtil.makeUUID());
        detailedService.insterDetailed(detailed);
        return new MessageResponse(RunningResult.SUCCESS);
    }

    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public MessageResponse modify(@RequestBody String param){
        if (WzStringUtil.isBlank(param)) {
            logger.error("参数为空");
            return new MessageResponse(RunningResult.NO_PARAM);
        }
        String paramStr = URLDecoder.decode(param,"utf-8");
        Detailed detailed = JSONObject.parseObject(paramStr,Detailed.class);
        if (WzStringUtil.isBlank(detailed.getId())) {
            logger.error("参数解析失败");
            return new MessageResponse(RunningResult.PARAM_ANALYZE_ERROR.code(),"参数id不能为空");
        }
        detailedService.updateDetailed(detailed);
        return new MessageResponse(RunningResult.SUCCESS);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public MessageResponse delete(@RequestBody String param){
        if (WzStringUtil.isBlank(param)) {
            logger.error("参数为空");
            return new MessageResponse(RunningResult.NO_PARAM);
        }
        String paramStr = URLDecoder.decode(param,"utf-8");
        Detailed detailed = JSONObject.parseObject(paramStr,Detailed.class);
        if (WzStringUtil.isBlank(detailed.getId())) {
            logger.error("参数解析失败");
            return new MessageResponse(RunningResult.PARAM_ANALYZE_ERROR.code(),"参数id不能为空");
        }
        detailedService.deleteDetailed(detailed);
        return new MessageResponse(RunningResult.SUCCESS);
    }

    @RequestMapping(value = "/getByPK",method = RequestMethod.POST)
    public MessageResponse getByPK(@RequestBody String param){
        if (WzStringUtil.isBlank(param)) {
            logger.error("参数为空");
            return new MessageResponse(RunningResult.NO_PARAM);
        }
        String paramStr = URLDecoder.decode(param,"utf-8");
        String id = JSONObject.parseObject(paramStr,String.class);
        if (WzStringUtil.isBlank(id)) {
            logger.error("参数解析失败");
            return new MessageResponse(RunningResult.PARAM_ANALYZE_ERROR.code(),"参数id不能为空");
        }
        detailedService.getByPrimaryKey(id);
        return new MessageResponse(RunningResult.SUCCESS);
    }

}
