package springhibernatemysql;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springhibernatemysql.dao.DatabaseDao;

@SpringBootApplication
public class Springboot2HibernateMysqlJSPApplication {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Springboot2HibernateMysqlJSPApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.servlet.context-path", "/"));

        app.run(args);
    }


}
