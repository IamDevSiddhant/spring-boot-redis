package com.redis.controller;


import com.redis.entity.Product;
import com.redis.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@EnableCaching
public class RedisController {

    @Autowired
    private RedisRepository redisRepository;

    @PostMapping("/save")
    public Product addProduct(@RequestBody Product product){
        return redisRepository.saveProduct(product);
    }

    @GetMapping("/findall")
    public List<Product> getall(){
        return redisRepository.findAll();
    }

    @GetMapping("/find/{id}")
    @Cacheable(key = "#id",value = "Product",unless = "#result.price>100000")
    public Product getById(@PathVariable String id){
        return redisRepository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(key = "#id",value = "Product")
    public String deleteById(@PathVariable String id){
        return redisRepository.deleteById(id);
    }

    @PutMapping("/update/{id}")
    @Cacheable(value = "Product",key = "#id")
    public Product update(@RequestBody Product product,@PathVariable String id){
        return redisRepository.updateProduct(product,id);
    }

}
