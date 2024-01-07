package com.calm.controller;

import com.calm.common.model.ResultData;
import com.calm.dto.PaymentDTO;
import com.calm.entity.Payment;
import com.calm.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 支付信息表 前端控制器
 * </p>
 *
 * @author calm
 * @since 2024-01-02
 */
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Tag(name = "PaymentController-订单支付")
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Operation(summary = "insert payment")
    @PostMapping("/insert")
    public ResultData<Integer> insert(@RequestBody @Valid PaymentDTO paymentDTO) {
        return ResultData.success(paymentService.insertSelective(paymentDTO));
    }

    @Operation(summary = "select(by id)")
    @GetMapping("/{id}")
    public ResultData<Payment> select(
            @Parameter(name = "id", description = "主键", required = true)
            @PathVariable("id") Long id) {
        log.info("调用{}", serverPort);
        return ResultData.success(paymentService.selectByPrimaryKey(id));
    }

    @Operation(summary = "select port")
    @GetMapping("/port")
    public ResultData<String> port() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        // 写一个除数为0的错误
        int num = 10 / 0;
        return ResultData.success(serverPort);
    }
}
