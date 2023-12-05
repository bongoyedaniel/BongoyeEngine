package BongoyeEngine;

import java.io.File;
import java.io.IOException;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class objectTexture {
    public int texture;

    objectTexture(GL2 gl, File textureFile) {
        try {
            Texture t = TextureIO.newTexture(textureFile, true);
            texture = t.getTextureObject(gl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int thisTexture() {
        return texture;
    }
}
