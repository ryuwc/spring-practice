package jpabook.jpashop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RedisController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("/redis")
    public ResponseEntity<?> addRedisKey() {
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        // 키: a, 값: apple
        vop.set("a", "apple");

        // 키: b, 해쉬 키: banana, 값: 바나나, 해쉬 키: orange, 값: 오렌지
        vop.set("b", "banana");
        vop.set("b:banana", "바나나");
        vop.set("b:orange", "오렌지");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/redis")
    public ResponseEntity<?> getRedisKey() {
        ValueOperations<String, String> vop = redisTemplate.opsForValue();

        // 키: a, 값: apple
        String value = vop.get("a");

        // 키: b, 해쉬 키: banana, 값: 바나나, 해쉬 키: orange, 값: 오렌지
        String value2 = vop.get("b");
        String value3 = vop.get("b:banana");
        String value4 = vop.get("b:orange");

        List<String> list = List.of(value, value2, value3, value4);
        return new ResponseEntity<>(list, HttpStatus.OK);

    }
}
