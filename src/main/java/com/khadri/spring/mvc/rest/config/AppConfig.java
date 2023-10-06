package com.khadri.spring.mvc.rest.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.khadri.spring.mvc.rest" })
@PropertySource("classpath:DB.properties")
public class AppConfig {

	@Bean
	public MappingJackson2HttpMessageConverter httpMessageConverter() {
		return new MappingJackson2HttpMessageConverter();
	}

	@Bean
	public Jaxb2RootElementHttpMessageConverter jaxb2RootElementHttpMessageConverter() {
		return new Jaxb2RootElementHttpMessageConverter();
	}

	@Bean
	public RequestMappingHandlerAdapter handlerAdapter(MappingJackson2HttpMessageConverter converter1,
			Jaxb2RootElementHttpMessageConverter converter2) {
		RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
		adapter.setMessageConverters(List.of(converter1, converter2));
		return adapter;
	}

	@Bean
	public DbProperties dbProperties() {
		return new DbProperties();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DbProperties dbProperties) {

		DriverManagerDataSource dataSource = new DriverManagerDataSource(dbProperties.getUrl(),
				dbProperties.getUserName(), dbProperties.getPassword());

		dataSource.setDriverClassName(dbProperties.getDriverClass());

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		return jdbcTemplate;
	}

}
