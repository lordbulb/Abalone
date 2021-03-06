package view;

import server.*;
import controller.*;

import javax.swing.*;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
//import java.util.*;
public class DrawMain extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5849682772446543981L;
	//Where the GUI is created:
	JMenuBar menuBar;
	JMenu menu, submenu ;
	JMenu menuhelp, submenuhelp;
	JMenuItem menuItem, menuItemHelp, menuClose;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;
	
	boolean isServer;
	ServerAbalone serverA;
	ClientAbalone clientA;
	
	public DrawMain(){
		super();
		build();//On initialise notre fenetre
		
// choice server/client

		Object[] options = {
		                    "Choose server",
		                    "Choose client"};
		int n = JOptionPane.showOptionDialog(this,
		    "You would like client or server",
		    "Choice between client and server",
		    JOptionPane.YES_NO_CANCEL_OPTION,
		    JOptionPane.QUESTION_MESSAGE,
		    null,
		    options,
		    options[1]);
		
		System.out.println("n is: " +n);

		
		
		// if client - enter port and name
		if (n==1) {
			
		
//			 String name_serv;
//			 name_serv = JOptionPane.showInputDialog(null, "Please, enter name of the server:");
//			
//			JDialog jd = new JDialog();
//			JTextField input_port  = new JTextField();
//			input_port.setSize(40, 40);
//			jd.add(input_port);
//			jd.setBounds(500, 400, 120, 70);
//			jd.setVisible(true);
			
			JTextField serverName = new JTextField();
			JTextField port = new JTextField();
			Object[] msg = {"Server name:", serverName, "Port:", port};
	 
			JOptionPane op = new JOptionPane(
				msg,
				JOptionPane.QUESTION_MESSAGE,
				JOptionPane.OK_CANCEL_OPTION,
				null,
				null);
	 
			JDialog dialog = op.createDialog(this, "Enter server name and port");
			dialog.setVisible(true);
	 
			int result = JOptionPane.OK_OPTION;
	 
			try
			{
			    result = ((Integer)op.getValue()).intValue();
			}
			catch(Exception uninitializedValue)
			{}
	 
			if(result == JOptionPane.OK_OPTION)
			{
				System.out.println(serverName.getText() + " : " + port.getText());
				
				isServer = false;
				
				clientA = new ClientAbalone(this, serverName.getText(), (Integer.parseInt(port.getText())));
				Thread th = new Thread(clientA,"CLIENTTHREAD");
				th.start();
			
			}
			else
			{
				System.out.println("Canceled");
			}
			
			
		}
		

		//if server - enter port
		else {
			String port= "";
			while ("".equals(port) || port==null){
	        port = JOptionPane.showInputDialog(null, "Please, enter port:");
	        System.out.println("port is "+ port+".");
			}
	        isServer=true;
	        
			serverA = new ServerAbalone(this,(Integer.parseInt(port)));
			new Thread(serverA, "SERVERTHREAD").start();
		}
		System.out.println("DrawMain Server:" + serverA);
		System.out.println("DrawMain Client:" + clientA);

	}


            class HelpListener extends AbstractAction{
                    public HelpListener(){
                         super();
                        }

                public void actionPerformed(ActionEvent e) {
                	JOptionPane.showMessageDialog(DrawMain.this, "<html>User manuel:<br>" +
                    		"<i>Commencer un nouveau jeu</i><br>" +
                	    "Dans le menu  <i>File-> Nouveau jeu.</i><br>" +
                	    "Apres, l'utilisateur doit choisir le type du jeu (contre ordinateur ou contre humain) et <br>" +
                	    "le confirmer avec le button Choisir. <br>" +
                	    "Puis, il doit entrer les noms des joueurs et le jeu va commencer<br>" +
                	    "<i>Fermer l'application</i>" +
                	    "Dans le menu  <i>File-> Fermer</i><br>" +
                	    "<i>Aide pour l'utilisateur</i><br>" +
                	    "Dans le menu <i>Aide -> A propos du jeu</i><br>" +
                	    "<i>Comment faire un coup</i><br>" +
                	    "l'utilisateur choit les billes qui il veut deplacer avec le souris<br>" +
                	    "(Si les billes sont bien choisi, ses couleurs changent),<br>" +
                	    "apres il doit choisir des cases ou il veut les deplacer.<br>" +
                	    "<i>Quoi faire apres la fin du jeu :</i><br>"+
                	    "Commencer un nouveau jeu - choisir button nouveau jeu du fenetre qui apparait apres le jeu est fini<br>" +
                	    "Fermer l'application - choisir le button fermer dans le meme fenetre<br>" +
                	    "Voir la statistique -  choisir le button statistique dans le meme fenetre<br>" +
                  	    "</html>");
                	}

                }
	
	private void build(){
		
		
	  setTitle("Jeu d'Abalone"); 
	  setSize(850,640); 
	  setLocationRelativeTo(null); 
	  setResizable(false); 
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		menuBar = new JMenuBar();


		menu = new JMenu("Menu");
		menuhelp = new JMenu("Aide");
		menuBar.add(menu);
		menuBar.add(menuhelp);
		setJMenuBar(menuBar);
		menuItem = new JMenuItem("Nouveau jeu",
		                         KeyEvent.VK_T);
                menuClose = new JMenuItem("Fermer",
		                         KeyEvent.VK_T);
		menuItemHelp = new JMenuItem("A propos du jeu",
                KeyEvent.VK_T);
		menu.add(menuItem);
                menu.add(menuClose);
		menuhelp.add(menuItemHelp);
		
		menuItem.addActionListener(new PopupController("New Game",this));
                menuClose.addActionListener(new CloseListener());
                menuItemHelp.addActionListener(new HelpListener());

	}
	
	public void setClientA(ClientAbalone c){
		clientA=c;
	}
	
	public ClientAbalone getClientA(){
		return clientA;
	}
	public void setServerA(ServerAbalone c){
		serverA=c;
	}
	
	public ServerAbalone getServerA(){
		return serverA;
	}
	

}
