package com.elextec.lease.manager.controller;

import com.elextec.framework.common.constants.RunningResult;
import com.elextec.framework.common.response.MessageResponse;
import com.elextec.framework.utils.WzFileUtil;
import com.elextec.lease.manager.model.PaymentParts;
import com.elextec.lease.manager.service.PaymentService;
import com.elextec.persist.model.mybatis.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 支付模块
 */
@RestController
@RequestMapping(value = "/payment/configuration")
public class PaymentController {

    /**日志*/
    private final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @Value("${localsetting.download-payment-icon}")
    private String downloadPaymentIcon;

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
            paymentParts.setIcon(downloadPaymentIcon + paymentList.get(i).getIcon() + WzFileUtil.EXT_JPG);
            paymentPartsList.add(paymentParts);
        }
        return new MessageResponse(RunningResult.SUCCESS,paymentPartsList);
    }

}
