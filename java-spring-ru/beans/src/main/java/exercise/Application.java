package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;


// BEGIN
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @Bean
    @Scope("prototype")
    public Daytime getShowTime() {
        int curHour = LocalDateTime.now().getHour();
        if (curHour >= 6 && curHour < 22) {
            return new Day();
        }
        return new Night();
    }
    // END
}