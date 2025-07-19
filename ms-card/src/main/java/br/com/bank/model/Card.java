package br.com.bank.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@Table(name = "tb_cards")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ENUM flag = ["MASTERCARD","VISA","ELO"];
    private String name;
    private BigDecimal cardLimit;
    private String document;
}