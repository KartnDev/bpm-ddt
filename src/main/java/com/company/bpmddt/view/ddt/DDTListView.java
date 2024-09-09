package com.company.bpmddt.view.ddt;

import com.company.bpmddt.entity.DDT;
import com.company.bpmddt.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "dDTs", layout = MainView.class)
@ViewController("DDT.list")
@ViewDescriptor("ddt-list-view.xml")
@LookupComponent("dDTsDataGrid")
@DialogMode(width = "64em")
public class DDTListView extends StandardListView<DDT> {
}