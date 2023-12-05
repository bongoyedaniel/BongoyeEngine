package BongoyeEngine.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import com.jogamp.opengl.awt.GLCanvas;

import BongoyeEngine.Debug;
import BongoyeEngine.details;
import BongoyeEngine.fileManager;
import BongoyeEngine.mainRenderer;

public class window extends JFrame implements WindowListener {

    public static newPanel debugPanel;

    public window(String Title) {
        fileManager.init();

        JMenuBar mb = new menubar();
        window.this.setTitle(details.appName + " : " + details.version + " : " + Title);
        window.this.setJMenuBar(mb);
        window.this.setSize(1620, 740);
        window.this.setDefaultCloseOperation(window.EXIT_ON_CLOSE);

        JTabbedPane tp = new JTabbedPane(JTabbedPane.TOP);
        JFileChooser b = new JFileChooser();

        tp.add("View Port", new inspector());
        tp.add("Files", b);
        tp.add("Setting", new newPanel());

        debugPanel = new newPanel();
        tp.add("Console", debugPanel);
        Debug.Log("help");

        window.this.add(tp);
        window.this.setVisible(true);

    }

    public class newPanel extends JPanel {
        public newPanel() {
            newPanel.this.setMinimumSize(new Dimension(100, 100));
            newPanel.this.setBackground(Color.decode("#5555aa"));
            newPanel.this.setAlignmentX(JPanel.BOTTOM_ALIGNMENT);
            newPanel.this.setAlignmentY(JPanel.LEFT_ALIGNMENT);

        }
    }

    class inspector extends JSplitPane {
        public inspector() {

            mainRenderer mr = new mainRenderer();
            GLCanvas gl = mr.returnRenderer();
            BorderLayout bl = new BorderLayout();

            JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
            // viewport scene // object info
            newPanel p0 = new newPanel();
            JButton b1 = new JButton("Click me");
            bl.addLayoutComponent(b1,BorderLayout.PAGE_START);
            bl.addLayoutComponent(gl, BorderLayout.PAGE_END);
            

            p0.add(b1);
            p0.add(gl);
            sp.setLeftComponent(p0);
//console
            newPanel p1 = new newPanel();
            sp.setRightComponent(p1);
            //
            inspector.this.setRightComponent(sp);
            inspector.this.setBackground(Color.decode("#444444"));
            // scene objects

            newPanel p2 = new newPanel();
            JButton b = new JButton("Click me");
            b.setSize(200, 70);
            b.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    JFrame d = new JFrame("ViewPort");
                    d.setSize(600, 600);
                    d.add(gl);
                    d.setAlwaysOnTop(true);
                    d.addWindowListener(new WindowListener() {
                        @Override
                        public void windowOpened(WindowEvent e) {
                        }

                        @Override
                        public void windowClosing(WindowEvent e) {
                        }

                        @Override
                        public void windowClosed(WindowEvent e) {
                            sp.setLeftComponent(gl);
                        }

                        @Override
                        public void windowIconified(WindowEvent e) {
                        }

                        @Override
                        public void windowDeiconified(WindowEvent e) {
                        }

                        @Override
                        public void windowActivated(WindowEvent e) {
                        }

                        @Override
                        public void windowDeactivated(WindowEvent e) {
                        }
                    });
                    d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    d.setVisible(true);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

            });

            
            bl.addLayoutComponent(b, BorderLayout.PAGE_END);
            bl.setVgap(900);

            objectTable ot = new objectTable(1, 4);
            ot.table.setMaximumSize(new Dimension(300,100));
            bl.addLayoutComponent(ot.table, BorderLayout.PAGE_START);
            p2.add(ot.table);

            p2.add(b);
            p2.setLayout(bl);

            p2.setMinimumSize(new Dimension(300, newPanel.HEIGHT));
            p2.setMaximumSize(new Dimension(300, newPanel.HEIGHT));
            inspector.this.setLeftComponent(p2);
        }
    }

    class objectTable extends JTable {
        private JTable table;

        objectTable(int row, int col) {
            objectTable.this.table = new JTable(row, col);
            objectTable.this.table.editCellAt(0, 0);
            objectTable.this.table.setValueAt(50, 0, 1);
            objectTable.this.add(new JButton("hello"));
        }
    }

    class menubar extends JMenuBar {
        private JMenu menu;

        public menubar() {
            menu = new JMenu("File");
            JMenuItem[] j = {
                    new JMenuItem("New Scene"),
                    new JMenuItem("Open Scene"),
                    new JMenuItem("Exit"),
            };
            for (int i = 0; i < j.length; i++) {
                menu.add(j[i]);
                j[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getActionCommand().compareToIgnoreCase("Exit") == 0)
                            window.this.dispose();
                    }
                });
            }

            menubar.this.add(menu);
            menubar.this.menu = new JMenu("Scenes");
            menubar.this.add(menu);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
