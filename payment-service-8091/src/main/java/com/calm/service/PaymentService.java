package com.calm.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.calm.dto.PaymentDTO;
import com.calm.entity.Payment;
import com.calm.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 15131
 * @description 针对表【payment(支付信息表)】的数据库操作Service实现
 * @createDate 2024-01-02 15:01:08
 */
@Service
@RequiredArgsConstructor
public class PaymentService extends ServiceImpl<PaymentMapper, Payment> {
    private final PaymentMapper paymentMapper;

    public int insertSelective(PaymentDTO payment) {
        return paymentMapper.insertSelective(payment.toEntity());
    }

    public Payment selectByPrimaryKey(Long id) {
        return paymentMapper.selectByPrimaryKey(id);
    }
}




