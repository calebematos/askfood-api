package com.calebematos.askfood.api.v1.mapper;

import com.calebematos.askfood.api.v1.model.FormPaymentModel;
import com.calebematos.askfood.api.v1.model.input.FormPaymentInput;
import com.calebematos.askfood.domain.model.FormPayment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class FormPaymentMapper {

    public abstract FormPaymentModel toModel(FormPayment formPayment);

    public abstract List<FormPaymentModel> toCollectionModel(Collection<FormPayment> formPayments);

    public abstract FormPayment toDomainObject(FormPaymentInput formPaymentInput);

    public abstract void copyToDomainObject(FormPaymentInput formPaymentInput, @MappingTarget FormPayment formPayment);
}
