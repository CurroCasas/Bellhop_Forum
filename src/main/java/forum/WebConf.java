package forum;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConf implements WebMvcConfigurer {
//public class WebConf extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(@NotNull CorsRegistry registry) {
		registry.addMapping("/**");
	}

}
