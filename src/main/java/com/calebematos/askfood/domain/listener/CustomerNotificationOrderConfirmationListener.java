package com.calebematos.askfood.domain.listener;

import com.calebematos.askfood.domain.event.ConfirmedOrderEvent;
import com.calebematos.askfood.domain.model.Ordering;
import com.calebematos.askfood.domain.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class CustomerNotificationOrderConfirmationListener {

    private final SendEmailService emailService;

    @TransactionalEventListener
    public void whenConfirmingOrder(ConfirmedOrderEvent event) {
        Ordering ordering = event.getOrdering();
        var message = SendEmailService.Message.builder()
                .subject(ordering.getRestaurant().getName() + "Order confirmed")
                .body("confirmed-order.html")
                .variable("order", ordering)
                .recipient(ordering.getClient().getEmail())
                .build();

        emailService.sendEmail(message);
    }

}
