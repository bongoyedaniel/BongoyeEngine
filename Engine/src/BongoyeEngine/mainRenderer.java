package BongoyeEngine;

import java.io.File;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

public class mainRenderer implements GLEventListener {
   private GLU glu = new GLU();
   private float FOV = 60f;

   public static gameObject[] object = new gameObject[2];
   static objectTexture[] objectTextures;

   gameObject cube = new gameObject("cube", "01");

   static File[] f = {
         new File("D:\\images\\IMG_20210504_172238_5082.jpg"),
         new File("D:\\images\\Screenshot_20210812-174058_2.png")
   };

   File objf = new File("E:\\GameEngine\\Engine\\src\\BongoyeEngine\\obj.Obng");
   static int[] texture = new int[f.length];

   @Override
   public void display(GLAutoDrawable drawable) {
      final GL2 gl = drawable.getGL().getGL2();
      gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
      gl.glLoadIdentity(); // Reset The View
      gl.glTranslatef(0f, 0f, -5.0f);

      gl.glPushMatrix();
      gl.glTranslatef(0f, 0f, 0.0f);
      gl.glRotatef(90, 0.0f, 0.0f, 1.0f);
      gl.glScalef(2, 2, 1);
      plane(gl);
      gl.glPopMatrix();

      ///////////////
      for (int i = 0; i < object.length; i++) {
         gl.glPushMatrix();
         gl.glTranslatef(object[i].transform.position.x, object[i].transform.position.y,
               object[i].transform.position.z);
         gl.glRotatef(object[i].transform.rotation.x, 1, 0, 0);
         gl.glRotatef(object[i].transform.rotation.y, 0, 1, 0);
         gl.glRotatef(object[i].transform.rotation.z, 0, 0, 1);
         gl.glScalef(object[i].transform.scale.x, object[i].transform.scale.y, object[i].transform.scale.z);

         object[i].setMesh(gl, objf);
         gl.glPopMatrix();
      }
   }

   @Override
   public void dispose(GLAutoDrawable drawable) {
      // method body
   }

   @Override
   public void init(GLAutoDrawable drawable) {
      final GL2 gl = drawable.getGL().getGL2();
      gl.glShadeModel(GL2.GL_SMOOTH);
      gl.glClearColor(0f, 0f, 255f, 0f);
      gl.glClearDepth(1.0f);
      gl.glEnable(GL2.GL_DEPTH_TEST);
      gl.glDepthFunc(GL2.GL_LEQUAL);
      gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
      //
      gl.glEnable(GL2.GL_TEXTURE_2D);

      for (int i = 0; i < texture.length; i++) {
         texture[i] = new objectTexture(gl, f[i]).thisTexture();
      }
   }

   @Override
   public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

      float far = 500f;
      final GL2 gl = drawable.getGL().getGL2();
      if (height == 0)
         height = 1;

      final float h = (float) width / (float) height;
      gl.glViewport(0, 0, width, height);
      gl.glMatrixMode(GL2.GL_PROJECTION);
      gl.glLoadIdentity();

      glu.gluPerspective(FOV, h, 1.0, far);
      gl.glMatrixMode(GL2.GL_MODELVIEW);
      gl.glLoadIdentity();

   }

   public GLCanvas returnRenderer() {
      GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);

      mainRenderer r = new mainRenderer();
      GLCanvas glcanvas = new GLCanvas(capabilities);
      
      glcanvas.addGLEventListener(r);
      glcanvas.setSize(1100, 500);

      final FPSAnimator animator = new FPSAnimator(glcanvas, 60, true);
      animator.start();
      return glcanvas;
   }

   public void plane(GL2 gl) {

      gl.glBindTexture(GL2.GL_TEXTURE_2D, texture[0]);
      gl.glBegin(GL2.GL_QUADS);
      // Front Face
      gl.glTexCoord2f(0.0f, 0.0f);
      gl.glVertex3f(-1.0f, -1.0f, 1.0f);
      gl.glTexCoord2f(0.2f, 0.0f);
      gl.glVertex3f(1.0f, -1.0f, 1.0f);
      gl.glTexCoord2f(0.2f, 0.2f);
      gl.glVertex3f(1.0f, 1.0f, 1.0f);
      gl.glTexCoord2f(0.0f, 0.2f);
      gl.glVertex3f(-1.0f, 1.0f, 1.0f);

      gl.glEnd();
      gl.glFlush();

   }

}