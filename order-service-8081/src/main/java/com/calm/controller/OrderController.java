package com.calm.controller;

import com.calm.common.model.ResultData;
import com.calm.constants.ServiceUrl;
import com.calm.dto.PaymentDTO;
import com.calm.service.PaymentFeignService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Tag(name = "OrderController-订单")
@Slf4j
public class OrderController {
    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;
    private final PaymentFeignService paymentFeignService;

    @GetMapping("/insertPayment")
    public ResultData insertPayment(PaymentDTO paymentDTO) {
        return restTemplate.postForObject(ServiceUrl.PAYMENT_SERVICE + "/payment/insert", paymentDTO, ResultData.class);
    }

    @Operation(summary = "select(by id)")
    @GetMapping("/payment/{id}")
    public ResultData selectPaymentById(
            @Parameter(name = "id", description = "主键", required = true) @PathVariable("id") Long id) {
        return restTemplate.getForObject(ServiceUrl.PAYMENT_SERVICE + "/payment/" + id, ResultData.class);
    }


    @Operation(summary = "get eureka client instances")
    @GetMapping("getInstances")
    public ResultData<List<ServiceInstance>> getInstances() {
        List<ServiceInstance> instances = discoveryClient.getInstances(ServiceUrl.PAYMENT_SERVICE_NAME);
        instances.forEach(item -> log.info(item.toString()));
        return ResultData.success(instances);
    }

    @Operation(summary = "feign payment port")
    @GetMapping("feign/port")
    public ResultData<String> port() {
      return paymentFeignService.port();
    }
}