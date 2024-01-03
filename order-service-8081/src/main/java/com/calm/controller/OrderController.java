package com.calm.controller;

import com.calm.common.model.ResultData;
import com.calm.constants.ServiceUrl;
import com.calm.dto.PaymentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Tag(name = "OrderController-订单")
public class OrderController {
    private final RestTemplate restTemplate;

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
}