package br.com.alurafood.paymentservice.dto;

import br.com.alurafood.paymentservice.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO implements Serializable {

    private String id;

    private BigDecimal value;

    private String name;

    private String number;

    private String expiration;

    private String code;

    private Status status;

    private String orderId;

    private String paymentMethodId;

}
