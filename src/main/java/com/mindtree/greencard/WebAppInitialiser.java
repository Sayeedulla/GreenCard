package com.mindtree.greencard;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitialiser extends AbstractAnnotationConfigDispatcherServletInitializer
// implements WebApplicationInitializer
{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// servlet configuration
		//
		// enable mvc annotation to the application context
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(WebConfig.class);
		ctx.setServletContext(servletContext);

		servletContext.addListener(new ContextLoaderListener(ctx));

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcherServlet",
				new DispatcherServlet(ctx));

		dispatcher.setLoadOnStartup(1);
		// dispatcher.addMapping("*.html");
		// dispatcher.addMapping("*.pdf");
		// dispatcher.addMapping("*.json");

		// map all request as per mapping provided to dispatcher servlet.
		dispatcher.addMapping("/");
	}

	/**
	 * these three methods for dispatcher servlet and other mappings
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WebAppInitialiser.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
		// return null;
	}

}
