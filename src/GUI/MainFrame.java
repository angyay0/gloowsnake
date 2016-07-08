

package GUI;

import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import Truck.*;
import Wheel.*;

/**
*
*MainFrame.java
*@package GUI
*@author Angel Eduardo Perez Cruz
* 06-Noviembre-2012
*
*/

 public class MainFrame extends JPanel {
 	
    private Graphics g  = null; //Control de Grafico
    
     public boolean sCredit = false;
     public boolean sTruck = false; //
     public boolean sWhee = false; //
     public boolean sInfo = false;
 	
    public ImageIcon B1,B2,B3,B4,B5; //Auxiliares
     
     public Truck[] trucks = new Truck[5]; //Camiones
     int truck;
     
     
    /**Constructor para cargar la imagenes necesarias*/
 	public MainFrame(){
        trucks[0] = new Truck( 2, 20000);
        trucks[1] = new Truck( 0, 0);
        trucks[2] = new Truck( 1, 9000);
        trucks[3] = new Truck( 6, 0);
        trucks[4] = new Truck( 5, 1500);
        
 		Data.Base = new ImageIcon( getClass().getResource("imgs/fondo.jpg"));
        Data.Ext = new ImageIcon( getClass().getResource("imgs/mapa.png"));
        
        Data.BtnW[1] = new ImageIcon( getClass().getResource("imgs/w0.jpg"));
        Data.BtnW[0] = new ImageIcon( getClass().getResource("imgs/w1.jpg"));
        
        Data.BtnT[1] = new ImageIcon( getClass().getResource("imgs/c0.jpg"));
        Data.BtnT[0] = new ImageIcon( getClass().getResource("imgs/c1.jpg"));
        
        Data.BtnIf[1] = new ImageIcon( getClass().getResource("imgs/inf0.jpg"));
        Data.BtnIf[0] = new ImageIcon( getClass().getResource("imgs/inf1.jpg"));
        
        Data.BtnCs[1] = new ImageIcon( getClass().getResource("imgs/cs0.jpg"));
        Data.BtnCs[0] = new ImageIcon( getClass().getResource("imgs/cs1.jpg"));
      
        Data.Foto = new ImageIcon( getClass().getResource("imgs/kenway.jpg"));
        Data.BtnEx = new ImageIcon( getClass().getResource("imgs/exit.jpg"));
        
        Data.BtnCr[0] = new ImageIcon( getClass().getResource("imgs/cambiar0.jpg"));
        
        Data.BtnRz[0] = new ImageIcon( getClass().getResource("imgs/revitalizar0.jpg"));
        
        Data.BtnRr[0] = new ImageIcon( getClass().getResource("imgs/rotar0.jpg"));

       
        Data.FCrs = new ImageIcon( getClass().getResource("imgs/c.png"));
                                  
        B1 = Data.BtnW[0];
        B2 = Data.BtnT[0];
        B3 = Data.BtnIf[0];
        B5 = Data.BtnCs[0];
    }
 	
 	/**Graficar*/
 	public void paintComponent(Graphics g){
 		super.paintComponent(g);
 		this.g = g;
        g.setColor( Color.black);
        
        Simple();
        
        paintButtons();
        
        if(sWhee){
            g.setFont( new Font("Monospaced", Font.ITALIC, 10) );
            
            int X = sTruck? 410: 10;
            int Y = sTruck? 490: 90;
           
            for(int t=0;t<5;t++){
                ArrayList al =null, all =null;
                ArrayList Registro = new ArrayList<String>();
                
                al= (ArrayList) trucks[t].getIDW();
                Y= sTruck? 490 : 90;
            for(int i=0 ;i<al.size();i++){
                all = (ArrayList) al.get(i);
                
                for(int j=0;j<all.size();j++){
                    g.drawString( (String) all.get(j), X,Y );
                    Y+=10;
                }
                
                
            }
            X+=110;
            
          
            }
        }
        
        if( sTruck ){
            
            g.setFont( new Font("Monospaced", Font.ITALIC, 12) );
            g.setColor( Color.white);
            int X = 50;
            int Y = 98;
            
            ArrayList al = null,all = null;
            
            al= (ArrayList) trucks[truck-1].getInfo();
            g.drawString( (String) al.get(0), X,Y-10);
            for(int j=1;j<al.size();j++){
                all = (ArrayList) al.get(j);
            
                for(int i=0 ;i<all.size();i++){
                
                    g.drawString( (String) all.get(i), X,Y );
                    Y+=10;
                }
            }
        }
       
        if( sInfo ){
            Data.BtnRr[0].paintIcon( this,g,150,500 );
            
            Data.BtnCr[0].paintIcon( this,g,300,500);
            
            Data.BtnRz[0].paintIcon( this,g,450,500);
            
        }
        
        if( sCredit )
            Data.FCrs.paintIcon( this,g, 0,0 );
            
        

 	}
     
    /*Dibuja los Botones*/
    public void paintButtons(){

            Data.Foto.paintIcon( this,g,610,10 );
            Data.BtnEx.paintIcon( this,g,740,10 );
            B1.paintIcon( this,g,600,150 );
            B2.paintIcon( this,g,600,260 );
            B3.paintIcon( this,g,600,370 );
            B5.paintIcon( this,g,600,540 );
    }
 	
    
    /*Dibuja la Ventana sin auxiliar*/
    public void Simple(){
        Data.Base.paintIcon( this,g,0,0 );
    }
     
     public void showLlantas(){
        sTruck = false;
        sWhee = true;
        sInfo = false;
        sCredit = false;
     }
     
     public void showTruck( int i){
        sWhee = false;
        truck = i;
        sTruck = true;
        sCredit = false;
        sInfo = false;
     }
     
     public void showInfor(){
     
         sTruck = false;
         sWhee = false;
         sInfo = true;
     }
     
     public void clean(){
         sTruck = false;
         sWhee = false;
         sInfo = false;
         sCredit = false;
     }
     
     public void showGuys(){
         sTruck = false;
         sWhee = false;
         sInfo = false;
         sCredit = true;
     }

     
}
