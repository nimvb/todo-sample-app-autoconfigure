package com.nimvb.app.autoconfigure.todospringbootautoconfigure;

import com.nimvb.app.repository.SimpleRepository;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(SimpleRepository.class)
@AutoConfigureAfter(DatabaseAutoConfiguration.class)
@ComponentScan("com.nimvb.app.repository")
public class RepositoryAutoConfiguration {

}
