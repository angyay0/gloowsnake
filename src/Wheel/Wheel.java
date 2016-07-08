/**
 *
 *@class Wheel.java
 *@package Wheels
 *@author Angel Eduardo Perez Cruz
 *@date 06-Noviembre-2012
 *
 */
 package Wheel;
 
 import java.util.Random;
 
 public class Wheel{
 	
 	public boolean Damaged = false;	//Saber si esta desgastada
 	public boolean isBlowed = false;    //Saber si esta ponchada
 	public boolean Revitalized = false; //Se revitalizo, falso = se hace, si no de desecha 
 	public boolean Crossed = false;	///Saber si se Roto
 	public int wisDamage = -1;          //Tipo de desgaste( -1 = no esta desgastada )
 	public double Conditional = 2.01 ;  //Profundidas de los surcos
    public Random Random = new Random();
     
    public int IDW;
 	/**
 	 *Constructor general para el camion
 	 **/
 	public Wheel(int IDW){
        this.IDW = IDW;
 	}
 	
 	/**
 	 *Metodo en el cual se Revitaliza la llanta
 	 **/
 	public void Revitalize(){
 		
 		Revitalized = true;
 		wisDamage = -1;
 		Conditional = 2.01;
 		Damaged = false;
 		
 	}
 	
 	/**
 	 *Metodo para actualizar los datos
 	 **/
 	public void update(){
 		Conditional -= Random.nextDouble();
 		isBlowed =  (Random.nextDouble() >0.99999999)? true : false;
 		wisDamage = Damaged ? Random.nextInt(5) : -1;

        
 		if(Conditional <= 1.60)
 			Damaged = true;
 			
 		wisDamage = Damaged ? Random.nextInt(5) : -1;
 		
 	}
 	
 	
 
 }
