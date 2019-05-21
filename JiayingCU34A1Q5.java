/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiayingcu3.pkg4a1q5;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 *
 * @author jiayingcui
 */

public class JiayingCU34A1Q5 extends JFrame 
    implements ActionListener {
    JPanel window;
    JTextField branchtext;
    JTextField heighttext;
    JTextField angletext;
    static JButton start;
    int branching;
    int mainheight;
    int angle;
    static JiayingCU34A1Q5 frame;
    JPanel thetree;
    boolean firstround = true;
    int starting;
    int one;
    int[] allangle;
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    // the basic GUI setting
    public static void main(String[] args) {
        // TODO code application logic here
        frame = new JiayingCU34A1Q5();
        frame.setSize(1000,900);
        frame.setTitle("Drawing the Tree");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); 
        frame.createGUI();
        
        
        frame.setVisible(true);
    }
    private void createGUI(){
        // create a panel to hold all the user input
        JPanel panel = new JPanel();
        window = new JPanel();
        window.setBorder(BorderFactory.createTitledBorder("Setting"));
        window.setPreferredSize(new java.awt.Dimension(1000,100));
        
        
        // ask for the kind of tree that user want to draw
        JLabel askbranching = new JLabel("Number of branching");
        branchtext = new JTextField(10);
        branchtext.addActionListener(this);
        
        // the limit is set in case of the tree is too big and exceed the frame
        JLabel asklength = new JLabel("        Height of the main branch (Integer 100 to 275 only)");
        heighttext = new JTextField(10);
        heighttext.addActionListener(this);
        
        // to ask for the angle
        JLabel askangel = new JLabel("the biggest angel between two branch(0 to 360)");
        angletext = new JTextField(10);
        angletext.addActionListener(this);
        JLabel notify = new JLabel("          Notification: Angel has to be a multiple of number of branch");
        
        //add everything to the panel
        window.add(askbranching);
        window.add(branchtext);
        window.add(asklength);
        window.add(heighttext);
        window.add(askangel);
        window.add(angletext);
        window.add(notify);
        start = new JButton("Start");
        start.addActionListener(this);
        
        window.add(start,BorderLayout.SOUTH);
        
        add(window,BorderLayout.NORTH);
        // create the panel to draw the tree on
        thetree = new JPanel();
        thetree.setPreferredSize(new java.awt.Dimension(1000,800));
        thetree.setBackground(Color.PINK);
        add(thetree);
        
    }
    // check if the user input is a int
    private boolean check(String a){
        try{
            Integer.parseInt(a); 
            
        }catch(Exception e){
            return false;
            
        }
        return true;
    }
    // use a function to draw a line according to its angel
    private void drawtheline(Graphics drawingArea,
                              int x,
                              int y,
                              int angle,
                              double height
                              ) {
        int[] end = findEndPoint(x,y,angle,height);
        

        drawingArea.drawLine(x,y,end[0],end[1]);
    }
    // calculation to find the end point
    private int[] findEndPoint(int x, int y, int angle, double height){
        double radius = Math.toRadians(angle);
        // find sin and cos
        double sin = Math.sin(radius);
        double cos = Math.cos(radius);
        // add sin and cos to x y
        int endy = y -  (int)Math.round(sin*height);
        int endx = x + (int)Math.round(cos*height);
        
        
        int[] list = new int[]{endx,endy};
        
        return list;
    }
   
    // the recursion for drawing the tree
    public void drawTree(Graphics drawingArea, int x, int y, int anglenow, double lastheight){
        // get the endpoint
        int[] end = findEndPoint(x, y, anglenow, lastheight);
        // the ending condition
        if (lastheight <=1){
            return;
        }
        //draw the lind
        drawtheline(drawingArea, x,y, anglenow,lastheight);
        // to avoid running this code for multiple time
        if (firstround){
            starting = 90-angle/2;
            one = angle/(branching-1);
            firstround = false;
                        
        }
        
        
        
        // recursion
        for(int i = 0; i < branching; i++){
            
            
            drawTree(drawingArea,end[0],end[1],starting + i * one,lastheight/3*2);
            
            
            
        }
        
        
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("Start")){
            // get the user input
            String ba = branchtext.getText();
            String hei = heighttext.getText();
            String ang = angletext.getText();
            // check the input form
            if(check(ba) && check(hei) && check(ang) && Integer.parseInt(hei)<= 275 
                    && Integer.parseInt(hei) >= 100
                    && Integer.parseInt(ang) > 0 
                    && Integer.parseInt(ang)<360 && Integer.parseInt(ang)%Integer.parseInt(ba) == 0){
                branching = Integer.parseInt(ba);
                mainheight = Integer.parseInt(hei); 
                angle = Integer.parseInt(ang);
                
            }else{
                // send out the error message
                JFrame warning = new JFrame();
            
                JOptionPane.showMessageDialog(warning,
                "Please enter the right form for number",
                "Form Error",
                JOptionPane.INFORMATION_MESSAGE);
                warning.setVisible(true);
            }
            
            // call the function to draw the tree
            Graphics paper = thetree.getGraphics();
            
            drawTree(paper,500,750,90,mainheight);
        
            
        }
        
    }
    
}
