package john.learning.spring.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import john.learning.spring.util.ViewNames;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages="john.leanring.config")
@PropertySources({ @PropertySource("classpath:src/resources/EBookLib.properties")})
public class WebConfig implements WebMvcConfigurer{
	private static final String RESOLVER_PREFIX = "/WEB-INF/Views/";
	private static final String RESOLVER_SUFFIX = ".jsp";
	@Autowired
	private Environment env; //this will contain all the properties from our properties sources
	
	//define a view resolver
	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(RESOLVER_PREFIX);
		resolver.setSuffix(RESOLVER_SUFFIX);
		return resolver;
	}
	
	//define a datasource for mysql connection
	@Bean(name = "datasource")
	public BasicDataSource getDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(env.getProperty("jdbc.driver"));
		ds.setUsername(env.getProperty("jdbc.username"));
		ds.setPassword(env.getProperty("jdbc.password"));
		ds.setUrl(env.getProperty("jdbc.url"));
		return ds;
	}
	
	//define static page view controllers
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName(ViewNames.HOME_PAGE);
	}
	
	//define static resources handlers
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/styles/**").addResourceLocations("classpath:/Css/");
		registry.addResourceHandler("/scripts/**").addResourceLocations("classpath:/Scripts/");
	}
}
