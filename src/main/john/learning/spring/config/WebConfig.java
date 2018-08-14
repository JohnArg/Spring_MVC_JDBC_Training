package john.learning.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
public class WebConfig implements WebMvcConfigurer{
	private static final String RESOLVER_PREFIX = "/WEB-INF/Views/";
	private static final String RESOLVER_SUFFIX = ".jsp";
	
	//define a view resolver
	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(RESOLVER_PREFIX);
		resolver.setSuffix(RESOLVER_SUFFIX);
		return resolver;
	}
	
	//define static page view controllers
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName(ViewNames.HOME_PAGE);
	}
	
	//define static resources handlers
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/styles/**").addResourceLocations("static_files/Css/");
		registry.addResourceHandler("/scripts/**").addResourceLocations("static_files/Scripts/");
	}
}
