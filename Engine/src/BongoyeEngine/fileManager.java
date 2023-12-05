package BongoyeEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class fileManager {
    public static void init() {
        for(int i=0 ;i < 2 ;i++)
        {
            mainRenderer.object[i] = new gameObject("hello", "b");
            mainRenderer.object[i].transform.position.x = i+1;
            mainRenderer.object[i].transform.position.y = i+2;
        }
    }
    public static void openScene() {
       File f = new File("E:\\GameEngine\\Engine\\src\\BongoyeEngine\\level.bng");

        try {
            if (f.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f));

                String a;

                while ((a=br.readLine()) != null) {
                    Debug.Log(a);
                    //float p = Float.parseFloat(a);
                }

                br.close();
            }
        } catch (Exception e) {
            Debug.Log(e.getMessage());
        }
    }
}
