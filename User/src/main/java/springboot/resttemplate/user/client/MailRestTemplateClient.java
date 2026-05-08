package springboot.resttemplate.user.client;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.commonbookstore.dto.MailDto;

@Component
public class MailRestTemplateClient {

    private static final String MAIL_BASE_URL = "http://localhost:8080/api/mail";

    private final RestTemplate restTemplate;

    public MailRestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MailDto getMailById(UUID id) {
        try {
            return restTemplate.getForObject(MAIL_BASE_URL + "/" + id, MailDto.class);
        } catch (HttpClientErrorException.NotFound ex) {
            return null;
        }
    }

    public MailDto getMailByName(String name) {
        try {
            return restTemplate.getForObject(MAIL_BASE_URL + "/name/" + name, MailDto.class);
        } catch (HttpClientErrorException.NotFound ex) {
            return null;
        }
    }
}
