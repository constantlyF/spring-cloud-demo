package com.calm.service;

import com.calm.common.model.ResultData;
import com.calm.constants.ServiceUrl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import feign.hystrix.FallbackFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient(value = ServiceUrl.PAYMENT_SERVICE_NAME, fallback = PaymentFeignServiceImpl.class)
@FeignClient(value = ServiceUrl.PAYMENT_SERVICE_NAME, fallbackFactory = PaymentFeignServiceFallbackFactory.class, decode404 = true)
//@FeignClient(value = ServiceUrl.PAYMENT_SERVICE_NAME, fallbackFactory = PaymentFeignServiceFallbackFactory2.class, decode404 = true)
//@FeignClient(name = ServiceUrl.PAYMENT_SERVICE_NAME, fallback = PaymentFeignServiceImpl.class)
public interface PaymentFeignService {
    /**
     * 其实直接将对应的Controller copy过来去掉方法体和返回值即可
     * 路径记得加上Controller上的路径
     */
    @GetMapping("/payment/port")
    ResultData<String> port();
}

/**
 */
@Service
class PaymentFeignServiceImpl implements PaymentFeignService {
    @Override
    public ResultData<String> port() {
        return ResultData.error(String.format("%s->我是order-service-8081 hystrixPortFallbackMethod", "PaymentFeignServiceImpl"));
    }
}

@Service
@Slf4j
class PaymentFeignServiceFallbackFactory implements FallbackFactory<PaymentFeignService> {
    @Override
    public PaymentFeignService create(Throwable cause) {
        return () -> {
            log.error("调用payment port 异常", cause);
            return ResultData.error(String.format("%s->我是order-service-8081 hystrixPortFallbackMethod", "PaymentFeignServiceFallbackFactory"));
        };
    }
}

/**
 * 我定义了全局拦截器我直接打印一个错误信息然后抛出对应的异常也是可以的啊
 */
@Service
@Slf4j
class PaymentFeignServiceFallbackFactory2 implements FallbackFactory<PaymentFeignService> {
    @Override
    public PaymentFeignService create(Throwable cause) {
        return new PaymentFeignService() {
            @Override
            @SneakyThrows
            public ResultData<String> port() {
                log.error("调用payment port 异常", cause);
                throw cause;
            }
        };
    }
}