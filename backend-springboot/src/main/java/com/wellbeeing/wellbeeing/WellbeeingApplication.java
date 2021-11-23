package com.wellbeeing.wellbeeing;

import com.wellbeeing.wellbeeing.util.ActiveUserInterceptor;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication
public class WellbeeingApplication implements WebMvcConfigurer {

	final ActiveUserInterceptor activeUserInterceptor;

	public WellbeeingApplication(ActiveUserInterceptor activeUserInterceptor) {
		this.activeUserInterceptor = activeUserInterceptor;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new SpecificationArgumentResolver());
		argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(activeUserInterceptor);
	}

	public static void main(String[] args) {
		SpringApplication.run(WellbeeingApplication.class, args);
	}

/*	@Bean
	CommandLineRunner init(@Qualifier("fileService") FileService fileService,
						   @Qualifier("exerciseService")ExerciseService exerciseService) {
		return (args) -> {
			exerciseService.initiateExercises();
//			fileService.deleteAll();
//			fileService.init();
		};
	}*/

}
