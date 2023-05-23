package com.calebematos.askfood.domain.event;

import com.calebematos.askfood.domain.model.Ordering;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConfirmedOrderEvent {

    private Ordering ordering;

}
