package com.calm.service;

import com.calm.common.model.ResultData;
import com.calm.constants.ServiceUrl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient(value = ServiceUrl.PAYMENT_SERVICE_NAME)
public interface PaymentFeignService {
    /**
     * 其实直接将对应的Controller copy过来去掉方法体和返回值即可
     * 路径记得加上Controller上的路径
     */
    @GetMapping("/payment/port")
    ResultData<String> port();
}
