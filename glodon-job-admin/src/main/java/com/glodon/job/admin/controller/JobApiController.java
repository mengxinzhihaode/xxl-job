package com.glodon.job.admin.controller;

import com.glodon.job.admin.core.conf.GlodonJobAdminConfig;
import com.glodon.job.admin.core.exception.GlodonJobException;
import com.glodon.job.admin.core.util.JacksonUtil;
import com.glodon.job.admin.controller.annotation.PermissionLimit;
import com.glodon.job.core.biz.AdminBiz;
import com.glodon.job.core.biz.model.HandleCallbackParam;
import com.glodon.job.core.biz.model.RegistryParam;
import com.glodon.job.core.biz.model.ReturnT;
import com.glodon.job.core.util.XxlJobRemotingUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xuxueli on 17/5/10.
 */
@Controller
@RequestMapping("/api")
public class JobApiController {

    @Resource
    private AdminBiz adminBiz;


    // ---------------------- base ----------------------

    /**
     * valid access token
     */
    private void validAccessToken(HttpServletRequest request){
        if (GlodonJobAdminConfig.getAdminConfig().getAccessToken()!=null
                && GlodonJobAdminConfig.getAdminConfig().getAccessToken().trim().length()>0
                && !GlodonJobAdminConfig.getAdminConfig().getAccessToken().equals(request.getHeader(XxlJobRemotingUtil.XXL_RPC_ACCESS_TOKEN))) {
            throw new GlodonJobException("The access token is wrong.");
        }
    }

    /**
     * parse Param
     */
    private Object parseParam(String data, Class<?> parametrized, Class<?>... parameterClasses){
        Object param = null;
        try {
            if (parameterClasses != null) {
                param = JacksonUtil.readValue(data, parametrized, parameterClasses);
            } else {
                param = JacksonUtil.readValue(data, parametrized);
            }
        } catch (Exception e) { }
        if (param==null) {
            throw new GlodonJobException("The request data invalid.");
        }
        return param;
    }

    // ---------------------- admin biz ----------------------

    /**
     * callback
     *
     * @param data
     * @return
     */
    @RequestMapping("/callback")
    @ResponseBody
    @PermissionLimit(limit=false)
    public ReturnT<String> callback(HttpServletRequest request, @RequestBody(required = false) String data) {
        // valid
        validAccessToken(request);

        // param
        List<HandleCallbackParam> callbackParamList = (List<HandleCallbackParam>) parseParam(data, List.class, HandleCallbackParam.class);

        // invoke
        return adminBiz.callback(callbackParamList);
    }



    /**
     * registry
     *
     * @param data
     * @return
     */
    @RequestMapping("/registry")
    @ResponseBody
    @PermissionLimit(limit=false)
    public ReturnT<String> registry(HttpServletRequest request, @RequestBody(required = false) String data) {
        // valid
        validAccessToken(request);

        // param
        RegistryParam registryParam = (RegistryParam) parseParam(data, RegistryParam.class);

        // invoke
        return adminBiz.registry(registryParam);
    }

    /**
     * registry remove
     *
     * @param data
     * @return
     */
    @RequestMapping("/registryRemove")
    @ResponseBody
    @PermissionLimit(limit=false)
    public ReturnT<String> registryRemove(HttpServletRequest request, @RequestBody(required = false) String data) {
        // valid
        validAccessToken(request);

        // param
        RegistryParam registryParam = (RegistryParam) parseParam(data, RegistryParam.class);

        // invoke
        return adminBiz.registryRemove(registryParam);
    }

    // ---------------------- job biz ----------------------

}
