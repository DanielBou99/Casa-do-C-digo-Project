package br.com.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/* Configurações primárias do Spring */

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {SecurityConfiguration.class, AppWebConfiguration.class, JPAConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {};
		/*Spring conhecer as classes config*/
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"}; /* Qual URL queremos que o Spring mapeie? */
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setForceEncoding(true);
		encodingFilter.setEncoding("UTF-8");
		
		return new Filter[] {encodingFilter, new OpenEntityManagerInViewFilter()};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {/* Metodo para o arquivo ser enviado ao servidor*/
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
	    super.onStartup(servletContext);
	    servletContext.addListener(new RequestContextListener());
	    servletContext.setInitParameter("spring.profiles.active", "dev");
	}
	
}
