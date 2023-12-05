package BongoyeEngine;

import BongoyeEngine.UI.window;

public class sceneManager {
    public static String activeScene;

    sceneManager()
    {

    }
    public static void loadScene(Scene scene)
    {
        new window(scene.name);
    }

}
