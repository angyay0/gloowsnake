/**
 *
 *@class TruckPart.java
 *@package Truck
 *@author Angel Eduardo Perez Cruz
 *@date 06-Noviembre-2012
 *
 */
 package Truck;
 
 import java.util.ArrayList;
 import java.util.Iterator;
 import Wheel.*;
 
 public class TruckPart{
 	
 	public ArrayList Llantas = new ArrayList<Wheel>();	//Cuantas llantas tiene cada parte de un camion
 	public int TwoWD;	//Controlador de llantas disponibles
    public String ID= null; //Identificador de la parte del camion
 	
 	/**
 	 *Constructor para colocar llantas
 	 */
 	public TruckPart(int size,String ID){
 		TwoWD = size;
 		this.ID = ID;
        
 		for(int i=0;i<size;i++){
 			
 			Llantas.add( new Wheel( IDWL.Serie ) );
            if(IDWL.Serie > 0 )
                IDWL.Serie -= 1;
 		}
 	}
 	
 	/**
 	 *Metodo para rotar todas las llantas
 	 *@return Cadena que indica que el cambio fue realizado
 	 */
 	public String Rotar(){
 		Wheel tmp = null;
 		Iterator it = Llantas.iterator();
 		
 		while(it.hasNext()){
 			tmp = (Wheel) it.next();
 			tmp.Crossed = true;
 		}
 		
 		return "Se han Rotado las llantas del Remolque";		
 	}
 	
 	/**
 	 *Metodo para revitalizar la llanta en la posicion especifica
 	 *@return Cadena que indica que la revitalizacion fue realizado
 	 */
 	public String Revitalizar(int i){
 		Wheel tmp = null;
 		tmp = (Wheel) Llantas.get(i-1);
 		
 		if(tmp.Revitalized == false){
 			tmp.Revitalize();
 			return "Se ha Revitalizado la llanta: "+i;
 		}
 		
 		return "La llanta ya se revitalizo, se debe cambiar";		
 	}
 	
 	/**
 	 *Metodo para cambiarar la llanta en la posicion especifica
 	 *@return Cadena que indica que el cambio fue realizado o no
 	 */
 	public String Cambiar(int i){
 		Wheel tmp = null;
 		tmp = (Wheel) Llantas.get(i-1);
        
        Llantas.set( i-1, (Wheel) Llantas.get(TwoWD) );
        Llantas.set( TwoWD, tmp );

        TwoWD--;
 		
        return "Se ha Cambiado la llanta: "+i;
 		
 	}
 	
 	/**
 	 *Metodo para actualizar los datos de las llantas
 	 */
 	public void Update(){
 		Wheel tmp = null;
 		Iterator it = Llantas.iterator();
 		int i=1;
        
 		while(it.hasNext()){
 			tmp = (Wheel) it.next();
            
            if( i%2 == 1 && i > 7 )
                tmp.update();
            i++;
 		}
 			
 	}
 	
 	/**
 	 *Metodo obtener la informacion de las llantas
 	 *@return Arreglo de la informacion de cada llanta
 	 */
 	public ArrayList getWheelInfo(){
 		Wheel tmp = null;
 		Iterator it = Llantas.iterator();
 		ArrayList Dano = new ArrayList<String>();
 		int i=1;
 		
        Dano.add( ID );
        
 		while(it.hasNext()){
 			tmp = (Wheel) it.next();
 			
 			if( tmp.Damaged && !tmp.isBlowed ){
 				switch( tmp.wisDamage ){
 					case 0: 
 						Dano.add(i+" id: "+tmp.IDW+", el daño es por llanta muy inflada");
 						break;
 					case 1:
 						Dano.add(i+" id: "+tmp.IDW+", el daño es por llanta poco inflada");
 						break;
 					case 2:
 						Dano.add(i+" id: "+tmp.IDW+", el daño es parejo");
 						break;
 					case 3:
 						Dano.add(i+" id: "+tmp.IDW+", el daño es el lado interno");
 						break;
 					case 4:
 						Dano.add(i+" id: "+tmp.IDW+", el daño es del lado externo");
 						break;
 					default :
 						Dano.add(i+" id: "+tmp.IDW+", esta en perfecta condicion");
 						break;
 				}
 				
 			}
 			
 			if( tmp.isBlowed ){
 				Dano.add("La llanta "+i+" id: "+tmp.IDW+" esta ponchada");
 			}
 			
 			i++;
 		}
 		return Dano;
 	}
 	
 	
     public ArrayList getIDS(){
         Wheel tmp = null;
         Iterator it = Llantas.iterator();
         ArrayList Dano = new ArrayList<String>();
         int i=1;
         
         Dano.add( ID );
         
         while(it.hasNext()){
             tmp = (Wheel) it.next();
             Dano.add("llanta: "+i+" id: "+tmp.IDW );
             i++;
         }
         
         return Dano;
     }
 	
 }
