package com.elextec.lease.manager.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private AlipayClient alipayClient;//支付宝请求sdk客户端

    @Value("${localsetting.download-payment-icon}")
    private String downloadPaymentIcon;//支付logo路径

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
     * 支付请求
     * @param httpResponse
     * @return
     */
//    @GetMapping("/app}")
//    public String app(HttpServletResponse httpResponse) {
//        JSONObject data = new JSONObject();
//        data.put("out_trade_no", "201701010000001234"); //商户订单号
//        data.put("product_code", "QUICK_MSECURITY_PAY"); //产品码, APP支付 QUICK_MSECURITY_PAY, PC支付 FAST_INSTANT_TRADE_PAY, 移动H5支付 QUICK_WAP_PAY
//        data.put("total_amount", "0.01"); //订单金额
//        data.put("subject", "测试订单"); //订单标题
//
//        //APP支付
//        AlipayTradeAppPayRequest alipayTradeAppPayRequest = new AlipayTradeAppPayRequest();
//
//        //PC支付
//        //AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
//
//        //移动H5支付
//        //AlipayTradeWapPayRequest alipayTradeWapPayRequest = new AlipayTradeWapPayRequest();
//
//        alipayTradeAppPayRequest.setNotifyUrl("http://demo/pay/alipay/notify/1"); //异步通知地址
//
//        alipayTradeAppPayRequest.setBizContent(data.toJSONString()); //业务参数
//
//        return alipayClient.sdkExecute(alipayTradeAppPayRequest).getBody();
//
//    }

//    @PostMapping("/notify")
//    public String notify(HttpServletRequest request) {
//        if (!notify(request.getParameterMap())) {
//            //这里处理验签失败
//        }
//        request.getParameter("trade_no");//获取请求参数中的商户订单号
//        return "success";
//    }


}
