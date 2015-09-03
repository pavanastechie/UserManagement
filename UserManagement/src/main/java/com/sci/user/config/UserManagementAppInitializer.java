package com.sci.user.config;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class UserManagementAppInitializer implements WebApplicationInitializer {

	private static final Logger LOGGER = Logger.getLogger(UserManagementAppInitializer.class);

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		LOGGER.info("Start UserManagementAppInitializer : onStartup");

		
		
		  // Create the root appcontext
		   AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		   // since we registered RootConfig instead of passing it to the constructor
		   rootContext.refresh(); 
		 
		   // Manage the lifecycle of the root appcontext
		   servletContext.addListener(new ContextLoaderListener(rootContext));
		   servletContext.setInitParameter("defaultHtmlEscape", "true");
		 
		   // now the config for the Dispatcher servlet
		   AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
		   mvcContext.register(ApplicationContextConfig.class);
		 
		   // The main Spring MVC servlet.
		   ServletRegistration.Dynamic appServlet = servletContext.addServlet(
		      "appServlet", new DispatcherServlet(mvcContext));
				appServlet.setLoadOnStartup(1);
		   Set<String> mappingConflicts = appServlet.addMapping("/");
		 
		   if (!mappingConflicts.isEmpty()) {
		      for (String s : mappingConflicts) {
		         LOGGER.error("Mapping conflict: " + s);
		      }
		      throw new IllegalStateException(
		         "'appServlet' cannot be mapped to '/' under Tomcat versions <= 7.0.14");
		   }
		   
		   
		LOGGER.info("End UserManagementAppInitializer : onStartup");
	}

}