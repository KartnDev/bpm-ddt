package com.company.bpmddt.form;

import com.company.bpmddt.entity.TransportOrder;
import com.company.bpmddt.form.api.AcceptProcessDefinition;
import com.company.bpmddt.form.api.AcceptsTaskFormView;
import com.company.bpmddt.view.transportorder.TransportOrderDetailView;
import io.jmix.bpmflowui.processform.viewcreator.ProcessFormViewCreator;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.Views;
import io.jmix.flowui.view.DialogWindow;
import io.jmix.flowui.view.View;
import io.jmix.flowui.view.builder.DetailWindowBuilder;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

@Component("archiva_DDTProcessFormCreator")
@Order(1)
public class DDTProcessFormCreator implements ProcessFormViewCreator {
    @Autowired
    private DialogWindows dialogWindows;

    @Override
    public String isApplicableFor() {
        return "custom";
    }

    @Nullable
    @Override
    public DialogWindow<?> createStartProcessView(CreationContext creationContext) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Nullable
    @Override
    public DialogWindow<?> createUserTaskView(CreationContext creationContext) {
        return dialogWindows.detail(creationContext.getOrigin(), TransportOrder.class)
                .withViewClass(TransportOrderDetailView.class)
                .newEntity()
                .withViewConfigurer(view -> {
                    if (view instanceof AcceptsTaskFormView acceptsTaskFormView) {
                        acceptsTaskFormView.setTask(creationContext.getTask());
                    }
                })
                .build();
    }


}
