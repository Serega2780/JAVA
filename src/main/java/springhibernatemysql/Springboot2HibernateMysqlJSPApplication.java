package springhibernatemysql;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springhibernatemysql.dao.DatabaseDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
public class Springboot2HibernateMysqlJSPApplication {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Springboot2HibernateMysqlJSPApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.servlet.context-path", "/"));

        app.run(args);
    }


}
