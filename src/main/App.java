/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import view.MenuView;

/**
 *
 * @author NATA
 */
public class App {

    private static MenuView menuView;

    public static MenuView getMenuView() {
        return menuView;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        menuView = new MenuView();
        menuView.setVisible(true);
    }

}