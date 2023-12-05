package BongoyeEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.jogamp.opengl.GL2;

public class gameObject {
   public Transform transform;
   public Component[] component;
   public String name, tag;
   public GL2 mesh = null;

   gameObject(String Objname, String Objtag) {
      this.name = Objname;
      this.tag = Objtag;
      transform = new Transform();
     Animation.Play(this,this.transform.position,new Vector3(2,0,0),62);
   }

   public GL2 setMesh(GL2 gl, File f) {
      try {
         gl.glBindTexture(GL2.GL_TEXTURE_2D, mainRenderer.texture[1]);
         gl.glBegin(GL2.GL_QUADS);

         if (f.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String a;
            int line = 0;
            while ((a = br.readLine()) != null) {
               String verts[] = a.split(",");
               if (line >= 2)
                  if ((line % 2) == 0)
                     gl.glTexCoord2f(Float.parseFloat(verts[0]), Float.parseFloat(verts[1]));
                  else
                     gl.glVertex3f(Float.parseFloat(verts[0]), Float.parseFloat(verts[1]), Float.parseFloat(verts[2]));
               line++;
            }
            br.close();
         }

         gl.glEnd();
         gl.glFlush();

         mesh = gl;
      } catch (Exception e) {
         Debug.Log(e.getMessage());
      }

      return mesh;
   }

   void setTexture(File f) {

   }

   public GL2 meshh(GL2 gl) {
      gl.glBindTexture(GL2.GL_TEXTURE_2D, mainRenderer.texture[1]);
      gl.glBegin(GL2.GL_QUADS);

      // Front Face
      gl.glTexCoord2f(0.0f, 0.0f);
      gl.glVertex3f(-1.0f, -1.0f, 1.0f);
      gl.glTexCoord2f(1.0f, 0.0f);
      gl.glVertex3f(1.0f, -1.0f, 1.0f);
      gl.glTexCoord2f(1.0f, 1.0f);
      gl.glVertex3f(1.0f, 1.0f, 1.0f);
      gl.glTexCoord2f(0.0f, 1.0f);
      gl.glVertex3f(-1.0f, 1.0f, 1.0f);

      // Back Face
      gl.glTexCoord2f(1.0f, 0.0f);
      gl.glVertex3f(-1.0f, -1.0f, -1.0f);
      gl.glTexCoord2f(1.0f, 1.0f);
      gl.glVertex3f(-1.0f, 1.0f, -1.0f);
      gl.glTexCoord2f(0.0f, 1.0f);
      gl.glVertex3f(1.0f, 1.0f, -1.0f);
      gl.glTexCoord2f(0.0f, 0.0f);
      gl.glVertex3f(1.0f, -1.0f, -1.0f);

      // Top Face
      gl.glTexCoord2f(0.0f, 1.0f);
      gl.glVertex3f(-1.0f, 1.0f, -1.0f);
      gl.glTexCoord2f(0.0f, 0.0f);
      gl.glVertex3f(-1.0f, 1.0f, 1.0f);
      gl.glTexCoord2f(1.0f, 0.0f);
      gl.glVertex3f(1.0f, 1.0f, 1.0f);
      gl.glTexCoord2f(1.0f, 1.0f);
      gl.glVertex3f(1.0f, 1.0f, -1.0f);

      // Bottom Face
      gl.glTexCoord2f(1.0f, 1.0f);
      gl.glVertex3f(-1.0f, -1.0f, -1.0f);
      gl.glTexCoord2f(0.0f, 1.0f);
      gl.glVertex3f(1.0f, -1.0f, -1.0f);
      gl.glTexCoord2f(0.0f, 0.0f);
      gl.glVertex3f(1.0f, -1.0f, 1.0f);
      gl.glTexCoord2f(1.0f, 0.0f);
      gl.glVertex3f(-1.0f, -1.0f, 1.0f);

      // Right face
      gl.glTexCoord2f(1.0f, 0.0f);
      gl.glVertex3f(1.0f, -1.0f, -1.0f);
      gl.glTexCoord2f(1.0f, 1.0f);
      gl.glVertex3f(1.0f, 1.0f, -1.0f);
      gl.glTexCoord2f(0.0f, 1.0f);
      gl.glVertex3f(1.0f, 1.0f, 1.0f);
      gl.glTexCoord2f(0.0f, 0.0f);
      gl.glVertex3f(1.0f, -1.0f, 1.0f);

      // Left Face
      gl.glTexCoord2f(0.0f, 0.0f);
      gl.glVertex3f(-1.0f, -1.0f, -1.0f);
      gl.glTexCoord2f(1.0f, 0.0f);
      gl.glVertex3f(-1.0f, -1.0f, 1.0f);
      gl.glTexCoord2f(1.0f, 1.0f);
      gl.glVertex3f(-1.0f, 1.0f, 1.0f);
      gl.glTexCoord2f(0.0f, 1.0f);
      gl.glVertex3f(-1.0f, 1.0f, -1.0f);
      gl.glEnd();
      gl.glFlush();

      mesh = gl;
      return mesh;
   }

   /*
   private void setName(String Objname) {
      this.name = Objname;
   }

   private String getName() {
      return this.name;
   }

   private void setRotation(float rotationAngle, float x, float y, float z) {
      transform.rotation.x = transform.rotation.x + (rotationAngle * x);
      transform.rotation.y = transform.rotation.y + (rotationAngle * y);
      transform.rotation.z = transform.rotation.z + (rotationAngle * z);
   }

   private void setPosition(float x, float y, float z) {
      transform.position.x = x;
      transform.position.y = y;
      transform.position.z = z;
   }

   private void setScale(float x, float y, float z) {
      transform.scale.x = x;
      transform.scale.y = y;
      transform.scale.z = z;
   }

   private Vector3 getRotation() {
      return transform.rotation;
   }

   private Vector3 getPosition() {
      return transform.position;
   }

   private Vector3 getScale() {
      return transform.scale;
   }

   */
}
