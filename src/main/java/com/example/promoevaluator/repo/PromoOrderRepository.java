package com.example.promoevaluator.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.promoevaluator.model.PromoOrder;
import com.example.promoevaluator.model.PromoOrderId;

public interface PromoOrderRepository extends MongoRepository<PromoOrder, PromoOrderId> {
    
    @Query("{ 'orders.ecmPromoNo' : ?0 }")
    List<PromoOrder> findAllByEcmPromoNo(String ecmPromoNo);

    @Query("{ 'orders.dataFlag' : ?0 }")
    List<PromoOrder> findAllByDataflag(String dataflag);
}
