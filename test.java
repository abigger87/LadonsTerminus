import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.net.*;
import java.util.Scanner;
import java.util.Vector;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class test extends JFrame{
    public test(){
        //splashScreen();
        initUI();
    }

    public static void main(String[] args){
        test first = new test();
        //first.setVisible(true);
    }

    private void splashScreen(){
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        double width =  screensize.getWidth();
        double height = screensize.getHeight();
        JWindow window = new JWindow();
        try{
            BufferedImage img = ImageIO.read(new File("splashscreen.png"));
            (window.getContentPane()).add( new JLabel("", new ImageIcon(img), SwingConstants.CENTER));
        } catch(IOException e){
            e.printStackTrace();
        }
        window.setBounds((int)width/2 - 450, (int)height/2 - 325, 900, 650);
        window.setVisible(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(false);
        window.dispose();
    }

    private void createNewPanel(JScrollPane listScroller, JTextField textField, JLabel path){
        JPanel newpanel = new JPanel();//new GridLayout(0, 2)
        newpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        newpanel.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 25);
        newpanel.setBorder(new LineBorder(Color.RED));
        newpanel.add(new JLabel("Ladon:~ C:/Users/Ladon$ "));
        newpanel.add(new JTextField(10));
        //panels.add();
        listScroller.getViewport().add(newpanel);
        newpanel.revalidate();
        newpanel.repaint();
    }

    private void initUI(){
        final JFrame startscreen = new JFrame("LadonsTerminus");
        startscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startscreen.setTitle("2018 LadonsImperium");
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        final double width =  screensize.getWidth();
        final double height = screensize.getHeight();
        startscreen.setSize((int)(width/1.5), (int)(height/1.5));
        startscreen.setLocationRelativeTo(null);
        startscreen.getContentPane().setLayout(null);

        //No title bar, Background, Boarder 
        startscreen.setUndecorated(true);
        com.sun.awt.AWTUtilities.setWindowOpacity(startscreen,0.7f); 
        startscreen.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
        
        //Console code
        JScrollPane listScroller = new JScrollPane();
        listScroller.setBorder(BorderFactory.createEmptyBorder());
        listScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        listScroller.setBounds(0, 50, (int)(width/1.5), (int)(height/1.5 - 50));

        JTextField textField = new JTextField(10);
        textField.setBounds(0, 0, 10, 25);
        textField.setOpaque(false);
        textField.setBorder(BorderFactory.createEmptyBorder());

        JTextField textField2 = new JTextField(10);
        textField2.setBounds(0, 0, 10, 25);
        textField2.setOpaque(false);
        textField2.setBorder(BorderFactory.createEmptyBorder());


        String currpath = "Ladon:~ C:/Users/Ladon$ ";
        JLabel path = new JLabel(currpath);
        path.setPreferredSize(new Dimension(175, 20));

        JPanel panel = new JPanel();//new GridLayout(0, 1) new FlowLayout(FlowLayout.LEFT)
        panel.setLayout(new GridLayout(0, 1));
        panel.setSize((int)width, 25);
        panel.setBorder(new LineBorder(Color.RED));
        
        ArrayList<JPanel> panels = new ArrayList<JPanel>();
        Action action = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("User entered text");
                //test commands
                createNewPanel(listScroller, textField, path);

            }
        };

        //textField.setBounds(0, 10, 10, 40);
        textField.addActionListener(action);
        textField2.addActionListener(action);
        
        panel.add(new JLabel("Ladon:~ C:/Users/Ladon$ "));
        panel.add(textField);
        JPanel testpanel = new JPanel();//new GridLayout(0, 2)
        testpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        testpanel.setSize((int)width, 25);
        testpanel.setBorder(new LineBorder(Color.RED));
        testpanel.add(new JLabel("Ladon:~ C:/Users/Ladon$ "));
        testpanel.add(textField);
        JPanel test2panel = new JPanel();//new GridLayout(0, 2)
        test2panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        test2panel.setSize((int)width, 25);
        test2panel.setBorder(new LineBorder(Color.RED));
        test2panel.add(path);
        test2panel.add(textField2);
        panel.add(new JLabel("testing 123"));
        panel.add(new JLabel("testing 123"));
        panel.add(new JLabel("testing 123"));
        panel.add(new JLabel("testing 123"));
        panel.add(new JLabel("testing 123"));
        panel.add(new JLabel("testing 123"));
        panel.add(new JLabel("testing 123"));
        panel.add(new JLabel("testing 123"));
        panel.add(new JLabel("testing 123"));
        panel.add(new JLabel("testing 123"));
        panel.add(new JLabel("testing 123"));
        panel.add(new JLabel("testing 123"));
        panel.add(new JLabel("testing 123"));
        panel.add(new JLabel("testing 123"));
        panel.add(new JLabel("testing 123"));
        panel.add(new JLabel("testing 123"));
        //Container cont = new Container();
        //cont.add(listScroller);
        panel.add(testpanel);
        panel.add(test2panel);
        //cont.setLayout(new GridLayout(0, 1));

        //startscreen.add(cont);

        textField.requestFocusInWindow(); 
        textField.requestFocus();

        listScroller.getViewport().add(panel);


        //NOTES - IMPORTANT
        //set vertical scroll when contents overflow
        //prevent user from editing previous text-boxes
        //auto select current textbox
        startscreen.add(listScroller); 
        textField.requestFocusInWindow(); 
        FrameDragListener frameDragListener = new FrameDragListener(startscreen);
        startscreen.addMouseListener(frameDragListener);
        startscreen.addMouseMotionListener(frameDragListener);
        //Close, Maximize, minimize Buttons - FINISHED
        try{
            ImageIcon img = new ImageIcon("logo.png");
            startscreen.setIconImage(img.getImage());

            BufferedImage closelogo = ImageIO.read(new File("closelogo.png"));
            final JButton close = new JButton(new ImageIcon(closelogo));
            close.setBorder(BorderFactory.createEmptyBorder());
            close.setContentAreaFilled(false);
            close.setBounds((int)(width/1.5 - 50), 10, 20, 20);
            
            BufferedImage fulllogo = ImageIO.read(new File("fulllogo.png"));
            final JButton fullscreen = new JButton(new ImageIcon(fulllogo));
            fullscreen.setBorder(BorderFactory.createEmptyBorder());
            fullscreen.setContentAreaFilled(false);
            fullscreen.setBounds((int)(width/1.5 - 80), 10, 20, 20);
            
            BufferedImage minilogo = ImageIO.read(new File("minilogo.png"));
            final JButton minimize = new JButton(new ImageIcon(minilogo));
            minimize.setBounds((int)(width/1.5 - 110), 10, 20, 20);
            minimize.setBorder(BorderFactory.createEmptyBorder());
            minimize.setContentAreaFilled(false);
            
            close.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {
                        startscreen.dispose();
                    }

                });
                
            fullscreen.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {
                        if(startscreen.getSize().getWidth() > (int)(width/1.5)){
                            startscreen.setSize((int)(width/1.5), (int)(height/1.5));
                            close.setBounds((int)(width/1.5 - 50), 10, 20, 20);
                            fullscreen.setBounds((int)(width/1.5 - 80), 10, 20, 20);
                            minimize.setBounds((int)(width/1.5 - 110), 10, 20, 20);
                            startscreen.setExtendedState(JFrame.NORMAL);
                        } else {
                            startscreen.setExtendedState(JFrame.MAXIMIZED_BOTH);
                            close.setBounds((int)(width - 50), 10, 20, 20);
                            fullscreen.setBounds((int)(width - 80), 10, 20, 20);
                            minimize.setBounds((int)(width - 110), 10, 20, 20);
                        }
                    }

                });
                
            minimize.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {
                        startscreen.setState(Frame.ICONIFIED);
                    }

                });
            startscreen.add(close);
            startscreen.add(fullscreen);
            startscreen.add(minimize);
        } catch(IOException e){
            e.printStackTrace();
        }
        textField.requestFocusInWindow(); 
        startscreen.setVisible(true);
    }
    public static class FrameDragListener extends MouseAdapter {

        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }
}