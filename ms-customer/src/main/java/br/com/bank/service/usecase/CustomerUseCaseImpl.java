package br.com.bank.service.usecase;

import br.com.bank.model.Customer;
import br.com.bank.repository.CustomerRepository;
import br.com.bank.service.CustomerUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerUseCaseImpl implements CustomerUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public void createCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public String sendRequest(String document) {
        var customer = this.customerRepository.findByDocument(document);
        var request = customer.get().getRequests();
        customer.get().setRequests(request+1);
        this.customerRepository.saveAndFlush(customer.get());

        //External call
        return "Card requested successfully";
    }
}
