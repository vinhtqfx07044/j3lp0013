package vn.edu.funix.j3lp0011;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories; // Thêm dòng này

@SpringBootApplication
@EnableJpaRepositories
public class J3lp0011Application {

    public static void main(String[] args) {
        SpringApplication.run(J3lp0011Application.class, args);
    }

}
