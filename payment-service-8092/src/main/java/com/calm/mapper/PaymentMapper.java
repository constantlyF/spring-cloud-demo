package com.calm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.calm.entity.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author 15131
 * @description 针对表【payment(支付信息表)】的数据库操作Mapper
 * @createDate 2024-01-02 15:01:08
 * @Entity com.calm.entity.Payment
 */
public interface PaymentMapper extends BaseMapper<Payment> {
    /**
     * insert record to table selective
     *
     * @param param the record
     * @return insert count
     */
    int insertSelective(Payment param);

    /**
     * update record selective
     *
     * @param param the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Payment param);

    /**
     * 查询（根据ID）
     *
     * @param id id
     * @return Payment
     */
    Payment selectByPrimaryKey(@Param("id") Long id);

    /**
     * 删除（根据ID）
     *
     * @param id id
     * @return delete count
     */
    int deleteByPrimaryKey(@Param("id") Long id);
}




