
package com.dlinkddns.atulsaurabh.erpecosystem.plugin;

import com.dlinkddns.atulsaurabh.erpecosystem.custom.ErpMenuBar;
import java.util.EventObject;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

/**
 *
 * @author Suyojan
 */
public class PlugEvent extends EventObject{
    private Object source;
    private Object container;

    public PlugEvent(Object source,Object container) {
        super(source);
        this.source=source;
        this.container = container;
    }
    
    public void handleEvent()
    {
        if(source instanceof MenuBar)
        {
            Platform.runLater(() -> {
                ErpMenuBar menuBar = (ErpMenuBar) source;
                Menu menu = (Menu) container;
                menu.show();
                ObservableList<Menu> menus = menuBar.getMenus();
                Menu modules = menus.stream().filter((Menu t) -> {
                    return t.getText().equals("Modules");
                }).findFirst().get();

                modules.getItems().add(menu);
            });

            
        }
    }
    
    
    
}
