package BongoyeEngine;

import java.io.File;

public class Scene {
    public String name;
    public gameObject[] gameObjects;

    public Scene(File s) {
        sceneManager.activeScene = s.getName();
        name = sceneManager.activeScene;
    }
}