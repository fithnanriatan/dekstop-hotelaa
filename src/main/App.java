package main;

import view.MenuView;
import service.MasterService;
import service.impl.MasterServiceImpl;

public class App {

    public static MenuView menuView;
    public static MasterService masterService;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        masterService = new MasterServiceImpl();
        menuView = new MenuView();
        menuView.setVisible(true);
    }

}
