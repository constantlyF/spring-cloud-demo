package com.calm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 支付信息表
 * </p>
 *
 * @author calm
 * @since 2024-01-02
 */
@Data
@Accessors(chain = true)
@Schema(name = "PaymentDTO", description = "支付信息表DTO")
public class PaymentDTO {

    @Schema(description = "流水号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "serial 为空")
    private String serial;
}