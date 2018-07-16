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

public class cstour extends JFrame{
    public cstour(){
        splashScreen();
        initUI();
    }

    public static void main(String[] args){
        cstour first = new cstour();
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

    private void initUI(){
        final JFrame startscreen = new JFrame("TheTour");
        startscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startscreen.setTitle("2018 CS Data Structures");
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

        //StartScreen Start Label
        final JPanel comboPanel= new JPanel();
        JLabel comboLbl = new JLabel("Computer Science Data Structures 2018 Tour");
        comboLbl.setFont(comboLbl.getFont().deriveFont(32.0f));
        comboLbl.setForeground(Color.RED);
        comboPanel.add(comboLbl);
        comboPanel.setBounds((int)(width/3 - 400), 100, 800, 50);
        startscreen.getContentPane().add(comboPanel);

        //Close, Maximize, minimize Buttons
        try{
            BufferedImage closelogo = ImageIO.read(new File("closelogo.png"));
            final JButton close = new JButton(new ImageIcon(closelogo));
            close.setBorder(BorderFactory.createEmptyBorder());
            close.setContentAreaFilled(false);
            close.setBounds((int)(width/1.5 - 75), 10, 50, 50);
            
            BufferedImage fulllogo = ImageIO.read(new File("fulllogo.png"));
            final JButton fullscreen = new JButton(new ImageIcon(fulllogo));
            fullscreen.setBorder(BorderFactory.createEmptyBorder());
            fullscreen.setContentAreaFilled(false);
            fullscreen.setBounds((int)(width/1.5 - 150), 10, 50, 50);
            
            BufferedImage minilogo = ImageIO.read(new File("minilogo.png"));
            final JButton minimize = new JButton(new ImageIcon(minilogo));
            minimize.setBounds((int)(width/1.5 - 225), 10, 50, 50);
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
                            close.setBounds((int)(width/1.5 - 75), 10, 50, 50);
                            fullscreen.setBounds((int)(width/1.5 - 150), 10, 50, 50);
                            minimize.setBounds((int)(width/1.5 - 225), 10, 50, 50);
                            startscreen.setLocationRelativeTo(null);
                            comboPanel.setBounds((int)(width/3 - 400), 100, 800, 50);
                        } else {
                            startscreen.setExtendedState(JFrame.MAXIMIZED_BOTH);
                            close.setBounds((int)(width - 75), 10, 50, 50);
                            fullscreen.setBounds((int)(width - 150), 10, 50, 50);
                            minimize.setBounds((int)(width - 225), 10, 50, 50);
                            comboPanel.setBounds((int)(width/2 - 400), 100, 800, 50);
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
        startscreen.setVisible(true);
    }

    private static JButton createSimpleButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        return button;
    }
}