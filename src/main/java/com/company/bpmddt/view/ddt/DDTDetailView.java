package com.company.bpmddt.view.ddt;

import com.company.bpmddt.entity.DDT;
import com.company.bpmddt.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "dDTs/:id", layout = MainView.class)
@ViewController("DDT.detail")
@ViewDescriptor("ddt-detail-view.xml")
@EditedEntityContainer("dDTDc")
public class DDTDetailView extends StandardDetailView<DDT> {


}