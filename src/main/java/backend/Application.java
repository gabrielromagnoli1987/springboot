package backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
    	
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

    }
    
    
    /* CORS: https://spring.io/guides/gs/rest-service-cors/#initial
    También se puede configurar con annotations a nivel método o clase en los Controllers
    @CrossOrigin(origins = "http://localhost:8000") */
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override	
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/cars").allowedOrigins("http://localhost:8000");
            }
        };
    }

}
