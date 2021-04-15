package cn.deepdraw.training.framework.dubbo.spring;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;

/**
 * Dubbo Config
 * @author huangjiancheng
 * @Date 2021-04-14
 */
@Configuration
@EnableDubbo(scanBasePackages = "cn.deepdraw.training")
public class DubboConfig {
}