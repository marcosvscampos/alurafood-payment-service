package br.com.alurafood.paymentservice.service;

import br.com.alurafood.paymentservice.client.OrderClient;
import br.com.alurafood.paymentservice.dto.PaymentDTO;
import br.com.alurafood.paymentservice.exception.NotFoundException;
import br.com.alurafood.paymentservice.model.Payment;
import br.com.alurafood.paymentservice.model.Status;
import br.com.alurafood.paymentservice.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentService {

    private OrderClient client;

    private PaymentRepository repository;

    private ModelMapper mapper;

    public Page<PaymentDTO> getAll(Pageable paging){
        return repository.findAll(paging)
                .map(p -> mapper.map(p, PaymentDTO.class));
    }

    public PaymentDTO getById(String id) {
        Payment payment = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pagamento ID " + id +  " não encontrado"));

        return mapper.map(payment, PaymentDTO.class);
    }

    public PaymentDTO create(PaymentDTO request){
        Payment payment = mapper.map(request, Payment.class);
        payment.setStatus(Status.CREATED);
        repository.save(payment);

        return mapper.map(payment, PaymentDTO.class);
    }

    public PaymentDTO update(String id, PaymentDTO request) {
        Payment payment = mapper.map(request, Payment.class);
        payment.setId(id);
        payment = repository.save(payment);
        return mapper.map(payment, PaymentDTO.class);
    }

    public void delete(String id){
        repository.deleteById(id);
    }

    public void confirmPayment(String id){
        Optional<Payment> payment = repository.findById(id);

        if (payment.isEmpty()) {
            throw new EntityNotFoundException();
        }

        payment.get().setStatus(Status.CONFIRMED);
        repository.save(payment.get());
        client.updateOrderStatus(payment.get().getOrderId());
    }

    public void updateStatus(String id) {
        Optional<Payment> payment = repository.findById(id);

        if (payment.isEmpty()) {
            throw new EntityNotFoundException();
        }

        payment.get().setStatus(Status.CONFIRMED_INTEGRATION_PENDING);
        repository.save(payment.get());
    }
}
