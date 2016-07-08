
 package Truck;
 
 import java.util.ArrayList;
 import java.util.Iterator;
 import Wheel.Wheel;

 /**
 *
 *Truck.java
 *@package Truck
 *@author Angel Eduardo Perez Cruz
 *08-Noviembre-2012
 *
 */
 public class Truck{
    
    public ArrayList Camion = new ArrayList<TruckPart>(); //Composicion del camion
     public double Km;      //Contador con el km 0-15k
    
 	
 	/**
 	 *Constructor para colocar llantas
 	 */
 	public Truck(int tipo,double Km){
        this.Km = Km;
        
        Conf(tipo);
    }
     
    public void Update(){
        TruckPart tmp = null;
        Iterator it = Camion.iterator();
 		
        while(it.hasNext()){
            tmp = (TruckPart) it.next();
            tmp.Update();
        }
        
        Km+=0.100;

    }
 	
 	/**
 	 *Metodo para rotar todas las llantas en 15KKM
 	 *@return Cadena que indica que el cambio fue realizado
 	 */
 	public String Rotar(){
        
        if( Km > 15000 ){
            TruckPart tmp = null;
            Iterator it = Camion.iterator();
 		
            while(it.hasNext()){
                tmp = (TruckPart) it.next();
                tmp.Rotar();
            }
            Km = 0 ;
        }
 		
 		return "Se han Rotado las llantas de todo el camion";		
 	}
 	
 	/**
 	 *Metodo para revitalizar la llanta en la posicion especifica del componente
 	 *@return Cadena que indica que la revitalizacion fue realizado
 	 */
 	public String Revitalizar(int ipieza,int i){
        TruckPart a = (TruckPart) Camion.get(ipieza-1);
        
		return a.Revitalizar(i);
 	}
 	
 	/**
 	 *Metodo para cambiarar la llanta en la posicion especifica del componente
 	 *@return Cadena que indica que el cambio fue realizado o no
 	 */
 	public String Cambiar(int ipieza,int i){
        TruckPart a = (TruckPart) Camion.get(ipieza-1);
 		TruckPart b = (TruckPart) Camion.get(0);
        
        if(b.ID.equals("Motor") && b.TwoWD >= b.Llantas.size()-1 ){
            b.TwoWD--;
            return a.Cambiar(i);
        }
        
        return "Has Agotado tus llantas";
 	}
 	
  	/**
 	 *Metodo obtener la informacion de las llantas de los componentes
 	 *@return Arreglo de la informacion de cada llanta
 	 */
 	public ArrayList getInfo(){
 		TruckPart tmp = null;
 		Iterator it = Camion.iterator();
 		ArrayList Dano = new ArrayList<ArrayList>();
 		Dano.add("Km : "+Km);
        
        while(it.hasNext()){
            tmp = (TruckPart) it.next();
            Dano.add( tmp.getWheelInfo());
        }
        
 		return Dano;
 	}
     
     public ArrayList getIDW(){
         TruckPart tmp = null;
         Iterator it = Camion.iterator();
         ArrayList Dano = new ArrayList<ArrayList>();
        
         while(it.hasNext()){
             tmp = (TruckPart) it.next();
             Dano.add( tmp.getIDS());
         }
         
         return Dano;
     }
 	
 	/**
     *Para hacer el camion de alguna configuracion
     **/
    public void Conf(int tipo){
         Camion.add( new TruckPart(12,"Motor") );
        
         switch(tipo){
             case 0: //WD
                 Camion.add( new TruckPart(8,"Dolly") );
                 break;
                 
             case 1: //WRG
                 Camion.add( new TruckPart(12,"Remolque Pesado") );
                 break;
                 
             case 2: //WRC
                 Camion.add( new TruckPart(8,"Remolque Ligero") );
                 break;
                 
             case 3: //WRGD
                 Camion.add( new TruckPart(12,"Remolque Pesado") );
                 Camion.add( new TruckPart(8,"Dolly") );
                 break;
                            
             case 4: //WRCD
                Camion.add( new TruckPart(8,"Remolque Ligero") );
                Camion.add( new TruckPart(8,"Dolly") );
                break;
                           
             case 5: //WRGDRG
                Camion.add( new TruckPart(12,"Remolque Pesado") );
                Camion.add( new TruckPart(8,"Dolly"));
                Camion.add( new TruckPart(12,"Remolque Pesado") );
                break;
                           
             case 6: //WRCDRC
                Camion.add( new TruckPart(8,"Remolque Ligero") );
                Camion.add( new TruckPart(8,"Dolly") );
                Camion.add( new TruckPart(8,"Remolque Ligero") );
                break;
    
             case 7: //WRGDRGD
                Camion.add( new TruckPart(12,"Remolque Pesado") );
                Camion.add( new TruckPart(8,"Dolly"));
                Camion.add( new TruckPart(12,"Remolque Pesado") );
                Camion.add( new TruckPart(8,"Dolly-2"));
                break;
                                                 
             case 8: //WRCDRCD
                Camion.add( new TruckPart(8,"Remolque Ligero") );
                Camion.add( new TruckPart(8,"Dolly") );
                Camion.add( new TruckPart(8,"Remolque Ligero") );
                Camion.add( new TruckPart(8,"Dolly-2"));
                break;
                           
             case 9: //WRGDRGDRG
                Camion.add( new TruckPart(12,"Remolque Pesado") );
                Camion.add( new TruckPart(8,"Dolly") );
                Camion.add( new TruckPart(12,"Remolque Pesado") );
                Camion.add( new TruckPart(8,"Dolly-2") );
                Camion.add( new TruckPart(12,"Remolque Pesado") );
                break;
                           
             case 10: //WRCSRCSRC
                Camion.add( new TruckPart(8,"Remolque Ligero") );
                Camion.add( new TruckPart(8,"Dolly") );
                Camion.add( new TruckPart(8,"Remolque Ligero") );
                Camion.add( new TruckPart(8,"Dolly-2") );
                Camion.add( new TruckPart(8,"Remolque Ligero") );
                break;
         }
     }
 }
                           
                           
