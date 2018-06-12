package com.elextec.lease.manager.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.elextec.framework.common.constants.RunningResult;
import com.elextec.framework.common.response.MessageResponse;
import com.elextec.framework.utils.WzStringUtil;
import com.elextec.lease.manager.model.PaymentParts;
import com.elextec.lease.manager.service.PaymentService;
import com.elextec.persist.model.mybatis.Payment;
import jodd.util.net.URLDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 支付模块
 */
@RestController
@RequestMapping(value = "/pay/configuration")
public class PaymentController {

    /**日志*/
    private final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @Value("${localsetting.download-payment-icon}")
    private String downloadPaymentIcon;//支付logo路径

    @Value("${sdk.alipay.app-id}")
    private String appId;

    @Value("${sdk.alipay.private-key}")
    private String privateKey;

    @Value("${sdk.alipay.format}")
    private String format;

    @Value("${sdk.alipay.charset}")
    private String charset;

    @Value("${sdk.alipay.alipay-public-key}")
    private String alipayPublicKey;

    @Value("${sdk.alipay.sign-type}")
    private String signType;

    /**
     * 查询支付方式logo
     * @return 查询结果列表
     * <pre>
     *     {
     *         code:返回code,
     *         message:返回消息，
     *         respData:[
     *              {
     *                  icon:支付logo路径
     *              }，
     *              ......
     *         ]
     *     }
     * </pre>
     */
    @GetMapping(value = "/list")
    public MessageResponse list(){
        List<Payment> paymentList = paymentService.paymentList();
        List<PaymentParts> paymentPartsList = new ArrayList<PaymentParts>();
        for (int i = 0; i < paymentList.size(); i++) {
            PaymentParts paymentParts = new PaymentParts();
            paymentParts.setIcon(downloadPaymentIcon + paymentList.get(i).getIcon());
            paymentPartsList.add(paymentParts);
        }
        return new MessageResponse(RunningResult.SUCCESS,paymentPartsList);
    }

    /**
     * 增加支付方式
     * <pre>
     *     {
     *         name:支付名称,
     *         icon:logo文件名,
     *         appId:应用id,
     *         alipayPrivateKey:支付宝私钥,
     *         mchId:商家id,
     *         wechatPrivateKey:微信私钥
     *     }
     * </pre>
     * @param addParam 支付对象
     * @return
     * <pre>
     *     {
     *         code:返回Code,
     *         message:返回消息,
     *         respData:""
     *     }
     * </pre>
     */
    @PostMapping(value = "/addone")
    public MessageResponse addone(@RequestBody String addParam){
        if (WzStringUtil.isBlank(addParam)) {
            return new MessageResponse(RunningResult.NO_PARAM);
        }
        String paramStr = URLDecoder.decode(addParam,"utf-8");
        Payment payment = JSONObject.parseObject(paramStr,Payment.class);
        if (payment == null) {
            return new MessageResponse(RunningResult.PARAM_ANALYZE_ERROR);
        }
        if (WzStringUtil.isBlank(payment.getName())) {
            return new MessageResponse(RunningResult.PARAM_ANALYZE_ERROR.code(),"支付id和名称不能为空");
        }
        paymentService.insert(payment);
        return new MessageResponse(RunningResult.SUCCESS);
    }

    /**
     * 修改支付方式的收款商家
     * <pre>
     *     {
     *          id:支付id,
     *          appId:应用id,
     *          alipayPrivateKey:支付宝私钥,
     *          mchId:商户id,
     *          wechatPrivateKey:微信私钥
     *     }
     * </pre>
     * @param modifyParam 支付对象
     * @return
     * <pre>
     *     {
     *         code:返回code,
     *         message:返回消息，
     *         respData:""
     *     }
     * </pre>
     */
    @PostMapping(value = "/modify")
    public MessageResponse modify(@RequestBody String modifyParam){
        if (WzStringUtil.isBlank(modifyParam)) {
            return new MessageResponse(RunningResult.NO_PARAM);
        }
        String paramStr = URLDecoder.decode(modifyParam,"utf-8");
        Payment payment = JSONObject.parseObject(paramStr,Payment.class);
        if (payment == null) {
            return new MessageResponse(RunningResult.PARAM_ANALYZE_ERROR);
        }
        if (WzStringUtil.isBlank(payment.getId())) {
            return new MessageResponse(RunningResult.PARAM_ANALYZE_ERROR.code(),"支付id不能为空");
        }
        paymentService.updatePayment(payment);
        return new MessageResponse(RunningResult.SUCCESS);
    }

    /**
     * 外部商户APP唤起快捷SDK创建订单并支付
     * <pre>
     *     {
     *
     *     }
     * </pre>
     * @param appParam
     * @return
     * <pre>
     *     {
     *         code:返回code,
     *         message:返回消息，
     *         respData:[
     *              {
     *                   alipay_trade_app_pay_response:{
     *                      code: 网关返回码,
     *                      msg: 网关返回码描述,
     *                      out_trade_no: 商户网站唯一订单号,
     *                      trade_no: 该交易在支付宝系统中的交易流水号,
     *                      total_amount: 该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01],
     *                      seller_id:收款支付宝账号对应的支付宝唯一用户号,以2088开头的纯16位数字
     *                   },
     *                    sign:签名
     *              }
     *         ]
     *     }
     * </pre>
     */
    @PostMapping(value = "/appPayment")
    public MessageResponse appPayment(@RequestBody String appParam){
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",appId,privateKey,format,charset,alipayPublicKey,signType);
        //创建API对应的request
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //填充业务参数
        request.setBizContent("");
        AlipayTradeAppPayResponse response = null;
        try {
            response = alipayClient.pageExecute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            logger.info("app支付接口调用成功");
            return new MessageResponse(RunningResult.SUCCESS);
        } else {
            logger.error("app支付接口调用失败");
            return new MessageResponse(RunningResult.BAD_REQUEST);
        }
    }

    /**
     * 获取用户的芝麻分
     * @param SesameParam
     * @return
     */
    @PostMapping(value = "/selectSesameCredit")
    public MessageResponse selectSesameCredit(@RequestBody String SesameParam){
        return new MessageResponse(RunningResult.SUCCESS);
    }

}
