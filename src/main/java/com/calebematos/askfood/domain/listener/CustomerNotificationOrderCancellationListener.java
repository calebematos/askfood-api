package com.calebematos.askfood.domain.listener;

import com.calebematos.askfood.domain.event.CanceledOrderEvent;
import com.calebematos.askfood.domain.model.Ordering;
import com.calebematos.askfood.domain.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class CustomerNotificationOrderCancellationListener {

    private final SendEmailService emailService;

    @TransactionalEventListener
    public void whenConfirmingOrder(CanceledOrderEvent event) {
        Ordering ordering = event.getOrdering();
        var message = SendEmailService.Message.builder()
                .subject(ordering.getRestaurant().getName() + "Order confirmed")
                .body("canceled-order.html")
                .variable("order", ordering)
                .recipient(ordering.getClient().getEmail())
                .build();

        emailService.sendEmail(message);
    }

}
