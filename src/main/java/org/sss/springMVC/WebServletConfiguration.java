package org.sss.springMVC;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.sss.springMVC.config.WebMvcConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebServletConfiguration implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletCxt) {
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(WebMvcConfig.class);
        ac.setServletContext(servletCxt);

        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletCxt.addServlet("dispatcher", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
