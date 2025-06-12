package br.com.bank.controller.request;


import br.com.bank.model.Customer;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomerRequest {

    private final String email;
    private final String document;
}
