package com.company.bpmddt.view.transportorder;

import com.company.bpmddt.entity.DDT;
import com.company.bpmddt.entity.TransportOrder;
import com.company.bpmddt.form.api.AcceptsTaskFormView;
import com.company.bpmddt.view.ddt.DDTDetailView;
import com.company.bpmddt.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.bpm.service.BpmTaskService;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionPropertyContainer;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.util.OperationResult;
import io.jmix.flowui.view.*;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Route(value = "transportOrders/:id", layout = MainView.class)
@ViewController("TransportOrder.detail")
@ViewDescriptor("transport-order-detail-view.xml")
@EditedEntityContainer("transportOrderDc")
@DialogMode(width = "50em")
public class TransportOrderDetailView extends StandardDetailView<TransportOrder> implements AcceptsTaskFormView {
    @Autowired
    private DialogWindows dialogWindows;
    @ViewComponent
    private DataContext dataContext;
    @ViewComponent
    private DataGrid<DDT> dDTsDataGrid;
    @ViewComponent
    private CollectionPropertyContainer<DDT> documentsDc;
    private Task task;
    @Autowired
    private BpmTaskService bpmTaskService;

    @Subscribe("dDTsDataGrid.create")
    public void onDDTsDataGridCreate(final ActionPerformedEvent event) {
        dialogWindows.detail(dDTsDataGrid)
                .withViewClass(DDTDetailView.class)
                .newEntity()
                .withParentDataContext(dataContext)
                .open();

    }

    @Subscribe("dDTsDataGrid.edit")
    public void onDDTsDataGridEdit(final ActionPerformedEvent event) {
        dialogWindows.detail(dDTsDataGrid)
                .withViewClass(DDTDetailView.class)
                .editEntity(Objects.requireNonNull(dDTsDataGrid.getSingleSelectedItem()))
                .withParentDataContext(dataContext)
                .open();
    }

    @Subscribe("dDTsDataGrid.remove")
    public void onDDTsDataGridRemove(final ActionPerformedEvent event) {
        documentsDc.getMutableItems().remove(dDTsDataGrid.getSingleSelectedItem());
        dataContext.remove(Objects.requireNonNull(dDTsDataGrid.getSingleSelectedItem()));
    }

    @Install(to = "saveAction", subject = "nextStepSupplier")
    private OperationResult saveActionNextStepSupplier() {
        bpmTaskService.complete(task.getId());
        return OperationResult.success();
    }


    @Override
    public void setTask(Task task) {
        this.task = task;
    }
}