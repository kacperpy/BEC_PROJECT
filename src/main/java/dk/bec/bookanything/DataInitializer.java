package dk.bec.bookanything;

import dk.bec.bookanything.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final AddressRepository addressRepository;

    @EventListener
    public void atStart(ContextRefreshedEvent event) {




    }
}

