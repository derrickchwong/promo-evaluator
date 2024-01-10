package com.example.promoevaluator.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.promoevaluator.model.Order;
import com.example.promoevaluator.model.PromoOrder;
import com.example.promoevaluator.model.PromoOrderId;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class PromoOrderRepositoryTest {

    @Autowired
    private PromoOrderRepository promoOrderRepository;

    @Test
    public void testQueries() {
        // Create a PromoOrder
        PromoOrder promoOrder = new PromoOrder();
        promoOrder.setId(new PromoOrderId("pid0001", "cid0001"));
        
        // Create an Order
        Order order = new Order();
        order.setEcmPromoNo("ecm00001");
        order.setCustNo("cid0001");
        order.setDataFlag("1");
        
        // Add the Order to the PromoOrder
        promoOrder.setOrders(List.of(order));

        // Save the PromoOrder
        promoOrderRepository.save(promoOrder);

        
        assertTrue(promoOrderRepository.findById(new PromoOrderId("pid0001", "cid0001")).isPresent());
        assertEquals(1, promoOrderRepository.count());
        List<PromoOrder> promoOrders = promoOrderRepository.findAll();
        assertEquals(1, promoOrders.size());
        assertEquals(promoOrder, promoOrders.get(0));
        

        List<PromoOrder> pos = promoOrderRepository.findAllByDataflag("1");

        // Assert that the list of Orders contains the Order that was created
        assertEquals(1, pos.size());
        assertEquals(promoOrder, pos.get(0));

        List<PromoOrder> pos2 = promoOrderRepository.findAllByEcmPromoNo("ecm00001");
        assertEquals(1, pos2.size());
        assertEquals(promoOrder, pos2.get(0));
        
    }
}

