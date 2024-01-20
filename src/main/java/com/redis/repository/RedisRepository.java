package com.redis.repository;


import com.redis.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class RedisRepository {

    public static final String HASH_KEY = "Product";

    @Autowired
    private RedisTemplate redisTemplate;

    public Product saveProduct(Product product) {
        redisTemplate.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }

    public List<Product> findAll() {
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public Product findById(String id) {
        log.info("DB call for id {} ",id);
        return (Product) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    public String deleteById(String id) {
        redisTemplate.opsForHash().delete(HASH_KEY, id);
        return "Product Removed!";
    }

    public Product updateProduct(Product product, String id) {

        log.info("making db call to update product id {} ",id);
        Product p = (Product) redisTemplate.opsForHash().get(HASH_KEY, id);


        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setQuantity(product.getQuantity());

        redisTemplate.opsForHash().put(HASH_KEY, p.getId(), p);

        return p;

    }
}
