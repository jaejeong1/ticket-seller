package com.jj.ticketseller.repository;

import com.jj.ticketseller.domain.Member;
import com.jj.ticketseller.domain.Wait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class WaitRepository {
    private final RedisTemplate redisTemplate;

    private static final String KEY = "wait:%d";
    @Autowired
    public WaitRepository(RedisTemplate<?, ?> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public long increaseWaitNumber(long id) {
        String key = String.format(KEY, id);
        return redisTemplate.opsForValue().increment(key + ":wait_number", 1L);
    }

    public boolean addQueue(long id, Member member, long waitNumber) {
        String key = String.format(KEY, id);
        return redisTemplate.opsForZSet().add(key + ":queue", member, waitNumber);
    }
}
