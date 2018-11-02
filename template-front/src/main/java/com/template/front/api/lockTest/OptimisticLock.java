package com.template.front.api.lockTest;

import com.template.front.api.AbstractController;
import com.template.orm.mapper.TuserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: qiudong
 * @date: 2018/11/2
 * @version: V1.0
 * @review: qiudong /2018/11/2
 */
@Slf4j
@RestController
@RequestMapping("/optimisticLock")
public class OptimisticLock extends AbstractController {

    private static int loopnum = 0;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private TuserMapper mapper;
    @RequestMapping("/test1")
    public String test1() {
        int inventory = mapper.getInventory();
        redisTemplate.opsForValue().set("number",String.valueOf(inventory));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("inventory==={}",inventory);
        int number = Integer.parseInt(redisTemplate.opsForValue().get("number"));
        number = --number;
        if(number<=0) return null;
        int result = mapper.updateInventory(number);
        if(result>0){
            log.info("成功了");

        }
        return "dd";
    }
}
