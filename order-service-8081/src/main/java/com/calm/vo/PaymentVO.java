package com.calm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Schema(name = "PaymentVO", description = "支付信息表VO")
public class PaymentVO{

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "流水号")
    private String serial;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}