package com.pvlpech;

import com.pvlpech.controller.Controller;
import com.pvlpech.model.loader.FileLoader;
import com.pvlpech.model.loader.Loader;
import com.pvlpech.view.gui.SwingView;
import com.pvlpech.view.View;
import com.pvlpech.view.tui.ConsoleView;

public class Main {

    public static void main(String[] args) {
//        View view = new ConsoleView();
        View view = new SwingView();
        Loader loader = new FileLoader();
        Controller controller = new Controller(view, loader);

        view.addPropertyChangeListener(controller);
        loader.addPropertyChangeListener(view);

        view.init();
    }
}
