import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * spring boot启动类
 *
 * @author xuke
 * @create 2017-03-30 下午 13:55
 **/
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.homework"})
@PropertySource({"classpath:log4j.properties", "classpath:application.properties"})
//@PropertySource(value = "classpath:application.properties")
@ImportResource()
public class BootApplication {
    public static void main(String[] args) throws Exception {

        SpringApplication.run(BootApplication.class, args);
    }
}
