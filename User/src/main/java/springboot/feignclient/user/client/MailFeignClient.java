package springboot.feignclient.user.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import springboot.feignclient.user.dto.MailDto;

@FeignClient(name = "mail-service", url = "http://localhost:8080")
public interface MailFeignClient {

    @GetMapping("/api/mail/{id}")
    MailDto getMailById(@PathVariable("id") UUID id);

    @GetMapping("/api/mail/name/{name}")
    MailDto getMailByName(@PathVariable("name") String name);

}