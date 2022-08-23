package ydzhao.weixin.tuisong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ydzhao.weixin.tuisong.util.Pusher;

import java.text.ParseException;

/**
 *@ClassName MainStart
 *@Description TODO
 *@Author ydzhao
 *@Date 2022/8/2 15:44
 */

@SpringBootApplication
@EnableScheduling
public class MainStart {
    public static void main(String[] args) {
        SpringApplication.run(MainStart.class, args);
    }

    @Scheduled(cron = "0 30 8 * * ?")
    public void goodMorning() throws ParseException {
        Pusher.push();
    }
}
