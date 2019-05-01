package capacita;

import capacita.security.JWTAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    Environment environment;

    //-------------  CORS PARAMETERS ------------
    @Value("${cors.allowed.mapping.path}")
    private String corsAllowedMappingPath;

    @Value("${cors.allowed.origins}")
    private String corsAllowedOrigins;



    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
       return new WebMvcConfigurerAdapter() {
          @Override
          public void addCorsMappings(CorsRegistry registry) {

             registry.addMapping(corsAllowedMappingPath)
                     .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                     .allowedOrigins(corsAllowedOrigins)
                     .allowedHeaders("*");
          }
       };
    }

    @Override
    public void run(String... args) throws Exception {

        String ip = InetAddress.getLocalHost().getHostAddress();
        String hostName = InetAddress.getLocalHost().getCanonicalHostName();
        String port = environment.getProperty("local.server.port");

        LOGGER.info("Aplicacion iniciada en {}", ip+":"+port);

        System.out.println("===============================================");
        System.out.println(String.format("Aplicación iniciada en:  %s:%s ", ip, port));
        System.out.println("===============================================");


    }
}

