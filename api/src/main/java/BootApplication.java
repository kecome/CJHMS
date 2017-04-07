/**
 * Created by Administrator on 2017/3/30 0030.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * spring boot启动类
 *
 * @author
 * @create 2017-03-30 下午 13:55
 **/
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.homework"})
public class BootApplication {
    public static void main(String[] args) throws Exception {

        SpringApplication.run(BootApplication.class, args);
    }
}
