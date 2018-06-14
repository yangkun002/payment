package com.elextec.lease.manager.controller;

import com.alibaba.fastjson.JSONObject;
import com.elextec.framework.BaseController;
import com.elextec.framework.common.constants.RunningResult;
import com.elextec.framework.common.response.MessageResponse;
import com.elextec.framework.utils.WzStringUtil;
import com.elextec.lease.manager.service.MerchantService;
import com.elextec.persist.model.mybatis.Merchant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jodd.util.net.URLDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(value = "MerchantController", description = "商户API")
@RestController
@RequestMapping(value = "/MerchantController")
public class MerchantController extends BaseController {

    /* 日志 */
    private final Logger logger = LoggerFactory.getLogger(MerchantController.class);

    @Autowired
    private MerchantService merchantService;

    @ApiParam(required = false)
    @ApiOperation(value = "商户列表", notes = "展示所有商户",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
        return new MessageResponse(RunningResult.SUCCESS,merchantService.list(map));
    }

    @ApiOperation(value = "添加商户", notes = "增加商户信息",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/addone",method = RequestMethod.POST)
    public MessageResponse addone(@RequestBody String param){
        if (WzStringUtil.isBlank(param)) {
            logger.error("参数不能为空");
            return new MessageResponse(RunningResult.NO_PARAM);
        }
        String paramStr = URLDecoder.decode(param,"utf-8");
        Merchant merchant = JSONObject.parseObject(paramStr,Merchant.class);
        if (WzStringUtil.isBlank(merchant.getId())
                || WzStringUtil.isBlank(merchant.getName())
                || WzStringUtil.isBlank(merchant.getSign())) {
            logger.error("参数解析错误");
            return new MessageResponse(RunningResult.PARAM_ANALYZE_ERROR.code(),"添加参数不能为空");
        }
        merchantService.insterMerchant(merchant);
        return new MessageResponse(RunningResult.SUCCESS);
    }

    @ApiOperation(value = "修改商户信息", notes = "修改商户信息",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public MessageResponse modify(@RequestBody String param){
        if (WzStringUtil.isBlank(param)) {
            logger.error("参数不能为空");
            return new MessageResponse(RunningResult.NO_PARAM);
        }
        String paramStr = URLDecoder.decode(param,"utf-8");
        Merchant merchant = JSONObject.parseObject(paramStr,Merchant.class);
        if (WzStringUtil.isBlank(merchant.getId())
                || WzStringUtil.isBlank(merchant.getName())) {
            logger.error("参数解析错误");
            return new MessageResponse(RunningResult.PARAM_ANALYZE_ERROR);
        }
        merchantService.updateMerchant(merchant);
        return new MessageResponse(RunningResult.SUCCESS);
    }

    @ApiOperation(value = "删除商户", notes = "删除商户",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public MessageResponse delete(@RequestBody String param){
        return new MessageResponse(RunningResult.SUCCESS);
    }

    @ApiOperation(value = "根据id查询商户", notes = "查询商户",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/getByPK",method = RequestMethod.POST)
    public MessageResponse getByPK(@RequestBody String param){
        return new MessageResponse(RunningResult.SUCCESS);
    }

}
