package com.glodon.job.core.biz.client;

import com.glodon.job.core.biz.AdminBiz;
import com.glodon.job.core.biz.model.HandleCallbackParam;
import com.glodon.job.core.biz.model.RegistryParam;
import com.glodon.job.core.biz.model.ReturnT;
import com.glodon.job.core.util.GlodonJobRemotingUtil;

import java.util.List;

/**
 * admin api test
 *
 */
public class AdminBizClient implements AdminBiz {

    public AdminBizClient() {
    }
    public AdminBizClient(String addressUrl, String accessToken) {
        this.addressUrl = addressUrl;
        this.accessToken = accessToken;

        // valid
        if (!this.addressUrl.endsWith("/")) {
            this.addressUrl = this.addressUrl + "/";
        }
    }

    private String addressUrl ;
    private String accessToken;


    @Override
    public ReturnT<String> callback(List<HandleCallbackParam> callbackParamList) {
        return GlodonJobRemotingUtil.postBody(addressUrl+"api/callback", accessToken, callbackParamList, 3);
    }

    @Override
    public ReturnT<String> registry(RegistryParam registryParam) {
        return GlodonJobRemotingUtil.postBody(addressUrl + "api/registry", accessToken, registryParam, 3);
    }

    @Override
    public ReturnT<String> registryRemove(RegistryParam registryParam) {
        return GlodonJobRemotingUtil.postBody(addressUrl + "api/registryRemove", accessToken, registryParam, 3);
    }
}
