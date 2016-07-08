
package GUI;

import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JRootPane;
import javax.swing.JOptionPane;
import java.awt.Point;
import java.util.Random;
import Truck.*;

/**
*
*Data.java
*@package GUI
*@author Angel Eduardo Perez Cruz
*06-Noviembre-2012
*
*/

public class MainWindow {
    
    private static JFrame frame;
    private static MainFrame mf;
    public static Random Random = new Random();
   
    
    public static void main(String[] args){
        mf = new MainFrame();
        frame = new JFrame("null");
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        frame.add( mf );
        frame.setVisible(true);
        frame.setSize(800,600);
        frame.addMouseListener( new MouseListener(){
        
        
            
            public void mousePressed( MouseEvent e){
                
                    if( isOn(e,740,10,50,50) ){   //Boton Salir
                        System.exit(0);
                    }else if( isOn(e,600,150,203,86)){  //Boton Llantas
                        mf.B1 = Data.BtnW[1];
                        mf.showLlantas();//
                    }else if( isOn(e,600,260,200,86)){  //Boton Camion
                        mf.B2 = Data.BtnT[1];
                        String s = (String) JOptionPane.showInputDialog( null,"Que camion desea ver de 0-5( IRZQ --> DRC )",null,JOptionPane.PLAIN_MESSAGE );
                        
                        mf.showTruck( Integer.parseInt( s) );
                    }else if( isOn(e,600,370,203,86)){  //Boton Informe
                        mf.B3 = Data.BtnIf[1];
                        mf.showInfor();//
                    }else if( isOn(e,600,540,50,50)){  //Desarrollo
                        mf.B5 = Data.BtnCs[1];
                        mf.showGuys();//
                    }else if( isOn(e,150,500,100,50)){
                        JOptionPane.showMessageDialog( null, "Se Rotaron las llantas");
                        mf.clean();
                    }else if( isOn(e,300,500,100,50)){
                        JOptionPane.showMessageDialog( null, "Se Cambiaron las llantas");
                        mf.clean();
                    }else if( isOn(e,1500,500,100,50)){
                        JOptionPane.showMessageDialog( null, "Se Revitalizarion las llantas");
                        mf.clean();
                    }
                
                
                mf.repaint();
            
            }
            public void mouseExited( MouseEvent e){}
            public void mouseEntered( MouseEvent e){}
            public void mouseClicked( MouseEvent e){}
                               
            public void mouseReleased( MouseEvent e){
                mf.repaint();
                mf.B1 = Data.BtnW[0];
                mf.B2 = Data.BtnT[0];
                mf.B3 = Data.BtnIf[0];
                mf.B5 = Data.BtnCs[0];
            
            }
            
        });
    }
    
    /*Comprueba si esta en la posicion del boton*/
    public static boolean isOn( MouseEvent p,int X,int Y,int Width,int Height ){
        
        if( ( (p.getX() >= X) && (p.getX() < (X+Width-1)) ) && ((p.getY() >= Y) && ( p.getY() < (Y+Height-1)) ) )
            return true;
        
        return false;
    }
    
    
    
    
    
    
    
    
}

                           
