package com.wellbeeing.wellbeeing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class WellbeeingApplication /*implements WebMvcConfigurer*/ {

/*	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new SpecificationArgumentResolver());
		argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
	}*/
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
