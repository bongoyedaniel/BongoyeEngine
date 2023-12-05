package BongoyeEngine;

import javax.swing.JLabel;

import BongoyeEngine.UI.window;
import BongoyeEngine.UI.window.newPanel;

public class Debug {
    public static newPanel p =window.debugPanel;

    public static void Log(String message)
    {
       p.add(new JLabel(message));
    }

}
