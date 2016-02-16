package net.atos.mobile.roadshow.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import net.atos.mobile.roadshow.util.Configuration;


/**
 * Servlet context listener to create a directory on the web server containing
 * sample resources (images, audio, video) - will be replaced when admin/file
 * upload is complete
 * 
 * @author a170632
 *
 */
public class ResourceServletContextListener implements
		ServletContextListener {

	private final static Logger log = Logger
			.getLogger(ResourceServletContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		log.debug("contextInitialized");

		String resourcesPath = System.getenv(Configuration.ENV_DATA_DIR_OPENSHIFT);

		if (resourcesPath == null || "".equals(resourcesPath)) {
			log.info("Not running on OpenShift. Falling back to local resource path");

			resourcesPath = System.getenv(Configuration.ENV_DATA_DIR_LOCAL);
		}

		log.debug("resourcesPath = " + resourcesPath);
		
		event.getServletContext().setAttribute(Configuration.ATT_DATA_DIR, resourcesPath);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		log.debug("contextDestroyed");

	}


}