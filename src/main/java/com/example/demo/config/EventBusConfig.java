package com.example.demo.config;

import com.google.common.eventbus.AsyncEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 事件总线配置
 *
 * @author stream
 */
@Configuration
public class EventBusConfig {

	@Bean
	public AsyncEventBus asyncEventBus() {
		int corePoolSize = Runtime.getRuntime().availableProcessors();
		int maxPoolSize = 100;
		int keepAliveTime = 60 * 10;

		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(5000);
		RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
		ThreadFactory factory = new ThreadFactory() {
			private final AtomicInteger integer = new AtomicInteger(1);

			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r, "EventBus-Thread - " + integer.getAndIncrement());
			}
		};

		return new AsyncEventBus(new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue, factory, handler));
	}

}
