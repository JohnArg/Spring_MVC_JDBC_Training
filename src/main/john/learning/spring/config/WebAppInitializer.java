package john.learning.spring.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		//Register our web configuration
		context.register(WebConfig.class);
		
		//Create and Register the dispatcher servlet
		DispatcherServlet dispatcher = new DispatcherServlet(context);
		ServletRegistration.Dynamic dispatcherConfig = servletContext.addServlet("dispatcher", dispatcher);
		dispatcherConfig.setLoadOnStartup(1);
		dispatcherConfig.addMapping("/");
		dispatcherConfig.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		
	}

}
