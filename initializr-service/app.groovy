package app

import io.spring.initializr.web.LegacyStsController
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurerSupport
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

import java.util.concurrent.Executor

@Grab('io.spring.initalizr:initializr:1.0.0.BUILD-SNAPSHOT')
@Grab('spring-boot-starter-redis')
class InitializerService
{
	@Bean
	@SuppressWarnings("deprecation")
	LegacyStsController legacyStsController()
	{
		new LegacyStsController()
	}

	@Configuration
	@EnableAsync
	static class AsyncConfiguration extends AsyncConfigurerSupport
	{
		@Override
		Executor getAsyncExecutor()
		{
			ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor()
			executor.setCorePoolSize(1)
			executor.setMaxPoolSize(5)
			executor.setThreadNamePrefix("initializr-")
			executor.initialize()
			executor
		}
	}
}
