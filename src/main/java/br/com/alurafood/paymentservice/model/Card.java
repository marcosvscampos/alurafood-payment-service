package br.com.alurafood.paymentservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

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

    @OneToOne(mappedBy = "card")
    private Payment payment;

}
