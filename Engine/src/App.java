import java.io.File;
import BongoyeEngine.Scene;
import BongoyeEngine.sceneManager;

public class App {
    public static void main(String[] args) {
      Scene s = new Scene(new File("E:\\GameEngine\\Engine\\src\\BongoyeEngine\\level.bng"));
      sceneManager.loadScene(s);
      //fileManager.openScene();
    }
}
