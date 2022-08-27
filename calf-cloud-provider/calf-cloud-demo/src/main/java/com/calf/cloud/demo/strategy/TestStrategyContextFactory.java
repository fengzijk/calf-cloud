package com.calf.cloud.demo.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class TestStrategyContextFactory {
    @Autowired
    private Map<String, StrategyTestService> contextFactory = new ConcurrentHashMap<>(8);
    @Autowired
    private TestServiceaImpl testServicea;

    public StrategyTestService get(String prefix) {
        StrategyTestService searchService = this.contextFactory.get(prefix.toUpperCase() + "Impl");
        if (Objects.isNull(searchService)) {
            log.info("没有匹配 使用默认策略");
            return testServicea;
        }

        return searchService;
    }
}
