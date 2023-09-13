package br.com.alurafood.paymentservice.controller;

import br.com.alurafood.paymentservice.dto.PaymentDTO;
import br.com.alurafood.paymentservice.service.PaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/payments")
@AllArgsConstructor
public class PaymentController {

    private PaymentService service;

    @GetMapping
    public ResponseEntity<Page<PaymentDTO>> getAll(@PageableDefault(size = 10) Pageable paging) {
        return ResponseEntity.ok(service.getAll(paging));
    }

    @GetMapping(value = "/{paymentId}")
    public ResponseEntity<PaymentDTO> getById(@PathVariable(name = "paymentId") @NotNull String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> create(@RequestBody @Valid PaymentDTO request, UriComponentsBuilder uriBuilder) {
        PaymentDTO response = service.create(request);
        URI paymentAddress = uriBuilder.path("/payments/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(paymentAddress).body(response);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PaymentDTO> update(@PathVariable @NotNull String id, @RequestBody @Valid PaymentDTO request){
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/confirm")
    @CircuitBreaker(name = "confirmPayment", fallbackMethod = "confirmedPaymentWithPendingIntegration")
    public void confirmPayment(@PathVariable @NotNull String id){
        service.confirmPayment(id);
    }

    public void confirmedPaymentWithPendingIntegration(String id, Exception e){
        service.updateStatus(id);
    }
}
