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
//----------------------------------------------TODO----------------------------------------------
//Matrix
//Save file


public class gui extends JFrame{
    boolean scrollAdjusted = false;
    public gui(){
        //splashScreen();
        initUI();
    }

    public static void main(String[] args){
        gui first = new gui();
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

    private String[] searchAnswers(String input, JPanel panel){
        String[] temparr = new String[3];
        if(input.equals("help")){
            temparr[0] = "<html>echo<br>exit<br>help<html>";
            temparr[1] = "60";
        }
        else if(input.length() > 3 && input.substring(0,4).equals("echo")){
            temparr[0] = input.substring(4);
            temparr[1] = "25";
        }
        else if(input.equals("exit")){
            System.exit(0);
        }
        else if(input.equals("matrix")){
            temparr[3] = "matrix";
            while(1!=0){
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            temparr[0] = "'" + input + "' is not a known command.";
            temparr[1] = "25";
        }
        return temparr;
    }

    private void createNewPanel(JScrollPane listScroller, JPanel mypane, JFrame startscreen){
        JPanel newpanel = new JPanel();//new GridLayout(0, 2)
        newpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        newpanel.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 25);
        newpanel.setBorder(BorderFactory.createEmptyBorder());
        newpanel.add(new JLabel("Ladon:~ C:/Users/Ladon$ "));
        JTextField text = new JTextField(10);
        text.setBounds(0, 0, 10, 25);
        text.setOpaque(false);
        text.setBorder(BorderFactory.createEmptyBorder());
        listScroller.revalidate();
        scrollAdjusted = false;
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        final double width =  screensize.getWidth();
        final double height = screensize.getHeight();
        Action action = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                String textinbox = text.getText();
                System.out.println("User entered text: " + textinbox);
                JPanel results = new JPanel();
                results.setLayout(new FlowLayout(FlowLayout.LEFT));
                results.setBorder(BorderFactory.createEmptyBorder());
                String[] temparr = searchAnswers(textinbox, results);
                results.add(new JLabel(temparr[0]));
                if(temparr[3].equals("matrix")){
                    //results.add()MAtrix function
                }
                results.setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), Integer.parseInt(temparr[1])));
                System.out.println(temparr[1]);
                mypane.add(results);
                if(startscreen.getSize().getWidth() > (int)(width/1.5)){
                    if(listScroller.getHeight() + (results.getHeight() + 20) <= (int)(height - 150)){
                        System.out.println(results.getHeight());
                        listScroller.setBounds(0, 50, (int)(width - 8), listScroller.getHeight() + (Integer.parseInt(temparr[1])));
                    }
                    else{
                        listScroller.setBounds(0, 50, (int)(width - 8), (int)(height - 150));
                    }
                }
                else{
                    if(listScroller.getHeight() + (results.getHeight() + 20) <= (int)(height/1.5 - 60)){
                        System.out.println(results.getHeight());
                        listScroller.setBounds(0, 50, (int)(width/1.5 - 8), listScroller.getHeight() + (Integer.parseInt(temparr[1])));
                    }
                    else{
                        listScroller.setBounds(0, 50, (int)(width/1.5 - 8), (int)(height/1.5 - 60));
                    }
                }
                text.setEditable(false);
                listScroller.revalidate();
                createNewPanel(listScroller, mypane, startscreen);
            }
        };
        if(startscreen.getSize().getWidth() > (int)(width/1.5)){
            if(listScroller.getHeight() + 25 <= (int)(height - 150)){
                listScroller.setBounds(0, 50, listScroller.getWidth(), listScroller.getHeight() + 25);
            }
        }
        else{
            if(listScroller.getHeight() + 25 <= (int)(height/1.5 - 60)){
                listScroller.setBounds(0, 50, listScroller.getWidth(), listScroller.getHeight() + 25);
            }
        }
        text.addActionListener(action);
        newpanel.add(text);
        mypane.add(newpanel);
        newpanel.revalidate();
        newpanel.repaint();
        text.requestFocusInWindow();
        listScroller.revalidate();
        scrollAdjusted = false;
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
        //boolean scrollAdjusted = false;
        //No title bar, Background, Boarder 
        startscreen.setUndecorated(true);
        com.sun.awt.AWTUtilities.setWindowOpacity(startscreen,0.7f); 
        startscreen.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));

        //Console code
        JScrollPane listScroller = new JScrollPane();
        listScroller.setBorder(BorderFactory.createEmptyBorder());
        listScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        listScroller.getVerticalScrollBar().setUnitIncrement(12);
        listScroller.setBounds(0, 50, (int)(width/1.5 - 8), 25);
        listScroller.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
            public void adjustmentValueChanged(AdjustmentEvent e) {  
                if(scrollAdjusted == false){
                    e.getAdjustable().setValue(e.getAdjustable().getMaximum());
                    scrollAdjusted = true;  
                }
            }
        });

        JPanel row = new JPanel();
        row.setLayout(new FlowLayout(FlowLayout.LEFT));
        row.setPreferredSize(new Dimension((int)width, 25));
        row.setBorder(BorderFactory.createEmptyBorder());

        JTextField textField = new JTextField(10);
        textField.setBounds(0, 0, 10, 25);
        textField.setOpaque(false);
        textField.setBorder(BorderFactory.createEmptyBorder());

        String currpath = "Ladon:~ C:/Users/Ladon$ ";
        JLabel path = new JLabel(currpath);
        path.setPreferredSize(new Dimension(175, 20));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        listScroller.setAlignmentX(LEFT_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder());
        //DefaultCaret caret = (DefaultCaret)panel.getCaret();
        //caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        Action action = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                String textinbox = textField.getText();
                System.out.println("User entered text: " + textinbox);
                JPanel results = new JPanel();
                results.setLayout(new FlowLayout(FlowLayout.LEFT));
                String[] temparr = searchAnswers(textinbox, results);
                results.setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), Integer.parseInt(temparr[1])));
                results.setBorder(BorderFactory.createEmptyBorder());
                results.add(new JLabel(temparr[0]));//searchAnswers(textinbox, results)));
                panel.add(results);
                textField.setEditable(false);
                if(startscreen.getSize().getWidth() > (int)(width/1.5)){
                    if(listScroller.getHeight() + 60 <= (int)(height - 150)){
                        listScroller.setBounds(0, 50, (int)(width - 8), listScroller.getHeight() + (Integer.parseInt(temparr[1])));
                    }
                    else{
                        listScroller.setBounds(0, 50, (int)(width - 8), (int)(height - 150));
                    }
                }
                else{
                    if(listScroller.getHeight() + 50 <= (int)(height/1.5 - 60)){
                        listScroller.setBounds(0, 50, (int)(width/1.5 - 8), listScroller.getHeight() + (Integer.parseInt(temparr[1])));
                    }
                    else{
                        listScroller.setBounds(0, 50, (int)(width/1.5 - 8), (int)(height/1.5 - 60));
                    }
                }
                createNewPanel(listScroller, panel, startscreen);
            }
        };

        textField.addActionListener(action);
        row.add(path);
        row.add(textField);
        panel.add(row);

        listScroller.getViewport().add(panel);

        startscreen.add(listScroller); 
        FrameDragListener frameDragListener = new FrameDragListener(startscreen);
        startscreen.addMouseListener(frameDragListener);
        startscreen.addMouseMotionListener(frameDragListener);

        //Close, Maximize, minimize Buttons
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
                        System.exit(0);
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
                            listScroller.setBounds(0, 50, (int)(width/1.5 - 8), (int)(height/1.5 - 60));
                            startscreen.setExtendedState(JFrame.NORMAL);
                        } else {
                            startscreen.setExtendedState(JFrame.MAXIMIZED_BOTH);
                            close.setBounds((int)(width - 50), 10, 20, 20);
                            fullscreen.setBounds((int)(width - 80), 10, 20, 20);
                            minimize.setBounds((int)(width - 110), 10, 20, 20);
                            //listScroller.setBounds(0, 50, (int)(width - 8), (int)(height - 60));
                            listScroller.setBounds(0, 50, (int)(width - 8), listScroller.getHeight());
                        }
                        listScroller.revalidate();
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
        pack();
        startscreen.setVisible(true);
        textField.requestFocusInWindow();
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