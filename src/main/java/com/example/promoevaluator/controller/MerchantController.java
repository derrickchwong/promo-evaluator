package com.example.promoevaluator.controller;

import java.util.UUID;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.promoevaluator.model.Campaign;
import com.example.promoevaluator.model.Customer;
import com.example.promoevaluator.model.Merchant;
import com.example.promoevaluator.model.Order;
import com.example.promoevaluator.model.Product;
import com.example.promoevaluator.model.ProductGroup;
import com.example.promoevaluator.repo.CampaignRepository;
import com.example.promoevaluator.repo.MerchantRepository;
import com.example.promoevaluator.repo.ProductGroupRepository;
import com.example.promoevaluator.repo.ProductRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class MerchantController {
    
    private final MerchantRepository merchantRepository;
    private final ProductGroupRepository productGroupRepository;
    private final ProductRepository productRepository;
    private final CampaignRepository campaignRepository;
    private final MongoTemplate mongoTemplate;

    @DeleteMapping("/")
    public ResponseEntity deleteAll(){
        mongoTemplate.dropCollection(Merchant.class);
        mongoTemplate.dropCollection(ProductGroup.class);
        mongoTemplate.dropCollection(Product.class);
        mongoTemplate.dropCollection(Campaign.class);
        mongoTemplate.dropCollection(Order.class);
        mongoTemplate.dropCollection(Customer.class);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/merchants")
    public ResponseEntity<Merchant> addMerchant(@RequestBody Merchant merchant){
        if(merchant.getId() == null) 
            merchant.setId(UUID.randomUUID().toString());
        merchantRepository.save(merchant);
        return ResponseEntity.ok(merchant);
    }

    @GetMapping("/merchants")
    public ResponseEntity<Iterable<Merchant>> getMerchants(){
        return ResponseEntity.ok(merchantRepository.findAll());
    }

    @GetMapping("/campaigns")
    public ResponseEntity<Iterable<Campaign>> getCampaigns(){
        return ResponseEntity.ok(campaignRepository.findAll());
    }

    @PostMapping("/merchants/{merchantId}/campaign")
    public ResponseEntity<Campaign> addCampaign(@PathVariable String merchantId, @RequestBody Campaign campaign){
        log.info("Adding campaign for merchant {}", merchantId);
        if(campaign.getId() == null) 
            campaign.setId(UUID.randomUUID().toString());
        
        Merchant merchant = merchantRepository.findById(merchantId).get();
        campaign.setMerchantId(merchantId);
        merchant.addCampaign(campaign);
        merchantRepository.save(merchant);
        campaignRepository.save(campaign);
        
        return ResponseEntity.ok(campaign);
    }

    @PostMapping("/merchants/{merchantId}/campaigns/{campaignId}/product-groups")
    public ResponseEntity addProductGroupToCampaign(@PathVariable String merchantId, @PathVariable String campaignId, @RequestParam String productGroupId, @RequestParam int amount){
        log.info("Adding product group {} to campaign {} for merchant {}", productGroupId, campaignId, merchantId);
        Campaign campaign = campaignRepository.findById(campaignId).get();
        campaign.addProductGroupAmount(productGroupId, amount);
        ProductGroup productGroup = productGroupRepository.findById(productGroupId).get();
        productGroup.addCampaign(campaign);
        campaignRepository.save(campaign);
        productGroupRepository.save(productGroup);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/merchants/{merchantId}/product-groups")
    public ResponseEntity<ProductGroup> addProductGroup(@PathVariable String merchantId, @RequestBody ProductGroup productGroup){
        log.info("Adding product group for merchant {}", merchantId);
        if(productGroup.getId() == null)
            productGroup.setId(UUID.randomUUID().toString());
        Merchant merchant = merchantRepository.findById(merchantId).get();
        merchant.addProductGroup(productGroup);
        merchantRepository.save(merchant);
        productGroupRepository.save(productGroup);
        return ResponseEntity.ok(productGroup);
    }

    @PostMapping("/product-groups/{productGroupId}/products")
    public ResponseEntity<Product> addProduct(@PathVariable String productGroupId, @RequestBody Product product){
        if(product.getId() == null)
            product.setId(UUID.randomUUID().toString());
        product.setProductGroupId(productGroupId);
        ProductGroup productGroup = productGroupRepository.findById(productGroupId).get();
        productGroup.addProduct(product);
        productGroupRepository.save(productGroup);
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/product-groups/{productGroupId}/products/{productId}")
    public ResponseEntity deleteProduct(@PathVariable String productGroupId, @PathVariable String productId){
        ProductGroup productGroup = productGroupRepository.findById(productGroupId).get();
        Product product = productRepository.findById(productId).get();
        productGroup.deleteProduct(product);
        productGroupRepository.save(productGroup);
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }


}
