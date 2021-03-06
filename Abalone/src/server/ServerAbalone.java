package server;
import controller.*;
import view.*;
import java.io.*;
import java.net.*;

import javax.swing.*;


import model.*;

import java.util.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;


public class ServerAbalone implements Runnable{
	
	DrawMain dm;
	
	ServerSocket server; 
	Socket sock;
	int serv_port;
	GameController gc;
	Position pos;
	static final int PORT=1500;
	
	BufferedReader in;
	PrintWriter out;
	
	static final Map<String,Integer> m_reversed = new HashMap<String, Integer>() {
		{
			put("A",1);
			put("B",2);
			put("C",3);
			put("D",4);
			put("E",5);
			put("F",6);
			put("G",7);
			put("H",8);
			put("I",9);
		}
	};
	public ServerAbalone(DrawMain d, int port){
		dm=d;
		serv_port=port;
		try{
			server = new ServerSocket(serv_port);
			
			
			
//			BufferedReader inBuff = new BufferedReader(new InputStreamReader(System.in));
//			String str = inBuff.readLine();
//			int i = Integer.valueOf(str).intValue();
//			
//			server=new ServerSocket(i);
//			sock=server.accept();
//			
//			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
//			PrintWriter out = new PrintWriter(new BufferedWriter( new OutputStreamWriter(sock.getOutputStream())),true);
		}
		catch (IOException i){
			System.out.println("SERVERSOCKET: IOEXCEPTION" + i.getMessage());
		}
		
	}
	public void run(){
		try{
			sock = server.accept();
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(new BufferedWriter( new OutputStreamWriter(sock.getOutputStream())),true);
			
//			System.setOut(new PrintStream(sock.getOutputStream()));
			
			while(true){
				System.out.println("SERVER: Listening");
				String mes = in.readLine();
				System.out.println("SERVER: " + mes.length());
				System.out.println("SERVER: " + mes);
//				System.out.println("1".equals(mes));
//				System.out.println(mes=="1\n");
				if ("!@#START1".equals(mes)){
					System.out.println("SERVER: try to start");
					ActionGameStart start = new ActionGameStart("test123", dm, new JDialog(), "Human-Human", true);
					start.actionPerformed(new ActionEvent(this, 123, "Hello World"));

				}
				if ("!@#START2".equals(mes)){
					System.out.println("SERVER: try to start");
					ActionGameStart start = new ActionGameStart("test123", dm, new JDialog(), "Human-Ordinateur", true);
					start.actionPerformed(new ActionEvent(this, 123, "Hello World"));
				}
				if ("!@#NOM".equals(mes)){
					gc.get_game().get_players().get(0).set_name(mes);
				}
				if (mes.matches("!@#CHAT.*")){
					
					String[] splitString = (mes.split("!@#CHAT"));
					System.out.println(splitString.length);// Should be 14
//					for (String string : splitString) {
//						System.out.println(string);
//						gc.get_board().getArea().append(string + "\n");
//					}
//					String chatm= mes.split("!@#CHAT").toString();
//					System.out.println("chatmessage: "+chatm);
					gc.get_board().getArea().append("Client: " + splitString[1]+"\n");
				}
				
				
	
				if (mes.matches("!@#MOVE(\\w\\d,){1,3}\\w\\d")){
				
					System.out.println(mes.matches("!@#MOVE(\\w\\d,){1,3}\\w\\d"));
					String[] splitString = (mes.split("!@#MOVE"));
					splitString = (splitString[1].split(","));
					
					System.out.println(mes + splitString.length);// Should be 14
//					for (String string : splitString) {
//						System.out.println(string);
//					}
					// getting the positions from the message
					
					for (int i=0;i<=splitString.length-2; i++){				
						int row = m_reversed.get(new String("") + (splitString[i].charAt(0))).intValue();
						int  diagonal = Integer.parseInt(new String("") + splitString[i].charAt(1));
						pos = new Position (row,diagonal);
						System.out.println("Selected pos: " + pos);
						this.gc.get_game().add_position(pos);
						
					}
					// row and diagonal  of the last position 
					for (String s : splitString){
						System.out.println("SPLIT: " + s);
					}
					System.out.println("Split len: " + splitString.length);
					System.out.println("Split last: " + splitString[splitString.length-1].charAt(0));
					System.out.println("Split lastrev: " + m_reversed.get(new String("") + splitString[splitString.length-1].charAt(0)));
					System.out.println("Split lastlast: " + splitString[splitString.length-1].charAt(1));
					System.out.println("Split lastint: " + Integer.parseInt(new String("") + splitString[splitString.length-1].charAt(1)));
					
//					System.out.println(splitString[splitString.length-1]);
					int last_row =  m_reversed.get(new String("") + (splitString[splitString.length-1].charAt(0))).intValue();
					int last_diagonal = Integer.parseInt(new String("") + splitString[splitString.length-1].charAt(1));
					Position last_pos = new Position(last_row, last_diagonal);
//					mouse.mouseClicked(new MouseEvent());
					Field finalfield = new Field(last_pos);
					System.out.println("SERVER: Field: " + finalfield);
					DrawField df;
					for (Field f: gc.get_board().get_drawfields().keySet())
						{
							if (f.get_position().equals(last_pos)){
								finalfield=f;
							}
						}
					System.out.println("SERVER: POS: " + last_pos);
					
					df=gc.get_board().get_drawfields().get(finalfield);
					System.out.println("SERVER: FieldFin: " + finalfield);
					System.out.println("SERVER: DField: " + df);
					MouseClicker mouse = new MouseClicker(gc, true, finalfield, df, last_pos);
//					mouse.mouseClicked(new MouseEvent(null, null, null, null, null, null, null, null));
					mouse.mouseClicked(new MouseEvent(new JButton(), 1432, 1, 1, 1, 1, 1, false));

				}
			}
		}
		catch (IOException i){
			System.out.println("SERVERSOCKET: IOEXCEPTION " + i.getMessage());
		}
	}
	
	public void setGC(GameController g){
		gc=g;
	}
	public BufferedReader get_in(){
		return in;
	}
	public PrintWriter get_out(){
		return out;
	}
	public Socket get_sock(){
		return sock;
	}
	
	
}
