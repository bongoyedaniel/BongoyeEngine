package BongoyeEngine;

import java.util.Timer;
import java.util.TimerTask;

public class Animation {

    public static void Play(gameObject obj,Vector3 from,Vector3 to,int time)
    {
        Timer t = new Timer("hello");
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                obj.transform.position.x =  obj.transform.position.x  - 0.006f;
            }
            
        }, 0, time);
    }

    ///add rotation and stuff
}
