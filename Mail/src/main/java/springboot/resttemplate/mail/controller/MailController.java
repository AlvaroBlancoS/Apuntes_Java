package springboot.resttemplate.mail.controller;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import springboot.resttemplate.mail.service.MailService;

import com.commonbookstore.dto.MailDto;

@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
@Validated
public class MailController {

    private final MailService mailService;

    @GetMapping
    @Operation(summary = "Obtener todos los correos electrónicos", description = "Devuelve una lista de todos los correos electrónicos registrados, ordenados por ID ascendente o descendente")
    public List<MailDto> getAllMails(@RequestParam(defaultValue = "id,asc") String sort) {
        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams.length > 1 && "desc".equalsIgnoreCase(sortParams[1]) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sortObj = Sort.by(direction, sortParams[0]);
        return mailService.getAllMails(sortObj);
    }

    @GetMapping("/v2")
    @Operation(summary = "Obtener todos los correos electrónicos v2", description = "Devuelve una lista de todos los correos electrónicos registrados")
    public List<MailDto> getAllMailsv2() {
        return mailService.getAllMails();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener correo electrónico por ID", description = "Devuelve el correo electrónico correspondiente al ID proporcionado")
    public MailDto getMailById(@PathVariable UUID id) {
        return mailService.getMailById(id);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Obtener correo electrónico por nombre", description = "Devuelve el correo electrónico correspondiente al nombre proporcionado")
    public MailDto getMailByName(@PathVariable @Valid String name) {
        return mailService.getMailByName(name);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo correo electrónico", description = "Crea y guarda un nuevo correo electrónico en el sistema")
    public MailDto createMail(@Valid @RequestBody MailDto dto) {
        return mailService.createMail(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un correo electrónico", description = "Actualiza la información de un correo electrónico existente")
    public MailDto updateMail(@PathVariable UUID id, @Valid @RequestBody MailDto dto) {
        return mailService.updateMail(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar correo electrónico por ID", description = "Elimina el correo electrónico correspondiente al ID proporcionado")
    public void deleteMailById(@PathVariable UUID id) {
        mailService.deleteMailById(id);
    }

    @DeleteMapping("/name/{name}")
    @Operation(summary = "Eliminar correo electrónico por nombre", description = "Elimina el correo electrónico correspondiente al nombre proporcionado")
    public void deleteMailByName(@PathVariable @Valid String name) {
        mailService.deleteMailByName(name);
    }
}
