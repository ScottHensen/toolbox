package supsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import supsvc.util.CORSFilter;

@SpringBootApplication
public class SupSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupSvcApplication.class, args);
	}
	
	//This bean will be used to filter servlet requests, so that we can handle CORS
	@Bean
	public FilterRegistrationBean commonsRequestLoggingFilter()
	{
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new CORSFilter());
		return registrationBean;
	}
}
