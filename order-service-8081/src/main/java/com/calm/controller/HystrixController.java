package com.calm.controller;

import com.calm.common.model.ResultData;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 *
 * @Author calm
 */

@RestController
@RequestMapping("/hystrix")
@RequiredArgsConstructor
// -orderController中也存在断路器的测试
@Tag(name = "服务熔断测试")
@Slf4j
public class HystrixController {

    /**
     * 服务熔断，和服务降级感觉区别不大呢 都是那个配置，主要区别就是服务降级是一次性的，服务熔断是有开启 关闭 半开相关条件的
     * 1. 当满足一定的阀值的时候（默认10秒内超过20个请求次数）
     * 2. 当失败率达到一定的时候（默认10秒内超过50%的请求失败）
     * 3. 到达以上阀值，断路器将会开启
     * 4. 当开启的时候，所有请求都不会进行转发
     * 5. 一段时间之后（默认是5秒），这个时候断路器是半开状态，会让其中一个请求进行转发。如果成功，断路器会关闭，若失败，继续开启。重复4和5
     */
    @HystrixCommand(fallbackMethod = "circuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),  // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),  // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),  // 失败率达到多少后跳闸
    })
    @Operation(summary = "服务熔断通过@HystrixCommand实现")
    @GetMapping("/circuitBreaker/{num}")
    public ResultData<String> circuitBreaker(
            @Parameter(name = "num", description = "数值", required = true) @PathVariable("num") Long num) {
        if (num < 0) {
            throw new RuntimeException("服务熔断测试 num 不能为负数");
        } else {
            return ResultData.success();
        }
    }

    private ResultData<String> circuitBreakerFallback(@PathVariable("num") Long num) {
        return ResultData.error("服务熔断测试 num 不能为负数，请稍后再试num: " + num);
    }
}
