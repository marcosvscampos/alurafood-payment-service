package br.com.alurafood.paymentservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @NotNull
    @Positive
    @Column(name = "payment_value")
    private BigDecimal value;

    @NotBlank
    @Size(max = 100)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(max = 19)
    @Column(name = "number")
    private String number;

    @NotBlank
    @Size(max = 7)
    @Column(name = "expiration")
    private String expiration;

    @NotBlank
    @Size(min = 3, max = 3)
    @Column(name = "code")
    private String code;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @NotNull
    @Column(name = "order_id")
    private String orderId;

    @NotNull
    @Column(name = "payment_method_id")
    private String paymentMethodId;
}
