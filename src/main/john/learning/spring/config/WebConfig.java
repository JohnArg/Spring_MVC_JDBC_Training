package john.learning.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

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
	
	//define static view controllers
}
