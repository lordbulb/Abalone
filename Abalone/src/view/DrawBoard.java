//package view;
//
//import java.util.*;
//import java.awt.Color;
//import java.awt.Graphics;
//import javax.swing.*;
//import model.*;
//
//
//public class DrawBoard extends JPanel{
//	Board b;
//	public DrawBoard(Board bb){
//		super();
//		b=bb;
//	}
//	public void paint(Graphics g){
//		System.out.println("paint");
//	      int height = 40;
//	      int width = 40;
//	      int space = 20;
//	      Map<Position, Field> fields= b.get_fields();
//	      Collection<Field> setfields = fields.values();
////	      System.out.println(setfields);
////	      if (test1==0){
//
////	      g.setColor(Color.red);
////	      g.drawRect(10,10,height,width);
////	      g.setColor(Color.gray);
////	      g.fillRect(11,11,height,width); 
////	      g.setColor(Color.red);
//	      for (Field f : setfields){
////	    	  System.out.println(f);
////	    	  try{
////	    		  //do what you want to do before sleeping
////	    		  Thread.currentThread().sleep(10);//sleep for 1000 ms
////	    		  //do what you want to do after sleeptig
////	    		}
////	    		catch(InterruptedException ie){
////	    		//If this thread was intrrupted by nother thread 
////	    		}
//	    	  Position pos = f.get_position();
////	    	  if (pos.get_diagonal()==1){
//	    	  System.out.println(pos);
//	    	  System.out.println((5*(width/2))-(pos.get_row()*width/2)+(pos.get_diagonal()-1)*width);
////	    	  System.out.println(9-pos.get_row()*20);
//	    	  g.setColor(Color.gray);
//	    	  g.drawOval((5*(width/2))-(pos.get_row()*width/2)+(pos.get_diagonal()-1)*width, (9-pos.get_row())*height, width, height);
////	    	  g.drawOval(pos.get_diagonal()*20, (9-pos.get_row())*20, width, height);
//	    	  if (f.is_empty()) {g.setColor(Color.gray);}
//	    	  else {g.setColor(Color.white);}
//	    	  g.fillOval((5*(width/2))-(pos.get_row()*width/2)+(pos.get_diagonal()-1)*width, (9-pos.get_row())*height, width, height);
////	    	  }
//	      }
////	      for (int i=0; i<5; i++){
////	    	  g.drawOval(40+(i*space),0, height, width);
////		      if (fields.get(new Position(9,i+5)).is_empty()){g.setColor(Color.gray);}
////		      else {g.setColor(Color.white);}
////		      g.fillOval(40+(i*space),0, height, width);
////	      }
////	      for (int i=0; i<6; i++){
////	    	  g.drawOval(30+(i*space),20, height, width);
////	    	  if (fields.get(new Position(8,i+4)).is_empty()){g.setColor(Color.gray);}
////		      else {g.setColor(Color.white);}
////		      g.fillOval(30+(i*space),20, height, width);
////	      }
////	      for (int i=0; i<7; i++){
////	    	  g.drawOval(20+(i*space),40, height, width);
////	    	  if (fields.get(new Position(7,i+3)).is_empty()){g.setColor(Color.gray);}
////		      else {g.setColor(Color.white);}
////		      g.fillOval(20+(i*space),40, height, width);
////	      }
////	      for (int i=0; i<8; i++){
////	    	  g.drawOval(10+(i*space),60, height, width);
////	    	  if (fields.get(new Position(6,i+2)).is_empty()){g.setColor(Color.gray);}
////		      else {g.setColor(Color.white);}
////		      g.fillOval(10+(i*space),60, height, width);
////	      }
////        for (int i=0; i<9; i++){
////	    	  g.drawOval(0+(i*space),80, height, width);
////	    	  if (fields.get(new Position(5,i+1)).is_empty()){g.setColor(Color.gray);}
////		      else {g.setColor(Color.white);}
////		      g.fillOval(0+(i*space),80, height, width);
////	      }
////        for (int i=0; i<8; i++){
////	    	  g.drawOval(10+(i*space),100, height, width);
////	    	  if (fields.get(new Position(4,i+1)).is_empty()){g.setColor(Color.gray);}
////		      else {g.setColor(Color.white);}
////		      g.fillOval(10+(i*space),100, height, width);
////	      }
////        for (int i=0; i<7; i++){
////	    	  g.drawOval(20+(i*space),120, height, width);
////	    	  if (fields.get(new Position(3,i+1)).is_empty()){g.setColor(Color.gray);}
////		      else {g.setColor(Color.white);}
////		      g.fillOval(20+(i*space),120, height, width);
////	      }
////        for (int i=0; i<6; i++){
////	    	  g.drawOval(30+(i*space),140, height, width);
////	    	  if (fields.get(new Position(2,i+1)).is_empty()){g.setColor(Color.gray);}
////		      else {g.setColor(Color.white);}
////		      g.fillOval(30+(i*space),140, height, width);
////	      }
////        for (int i=0; i<5; i++){
////	    	  g.drawOval(40+(i*space),160, height, width);
////	    	  if (fields.get(new Position(1,i+1)).is_empty()){g.setColor(Color.gray);}
////		      else {g.setColor(Color.white);}
////		      g.fillOval(40+(i*space),160, height, width);
////	      }
//	}
//  	  public void repaint(){
//		  System.out.println("repaint");
//		  super.repaint();
//	  }
//        
//}
