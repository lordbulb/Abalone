package controller;

import java.awt.TextField;
import java.awt.event.*;
import javax.swing.AbstractAction;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import view.*;

public class ChatListener extends AbstractAction {
	
	DrawBoardAbsolute dba;
	JTextField tf;
	JTextArea ta;
    private final static String newline = "\n";
	
	public ChatListener(DrawBoardAbsolute db){
		super();
		dba=db;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String name;
		if (dba.get_gc().get_dm().getServerA()!=null){
			name="Server";
		}
		else{
			name="Client";
		}
		
		tf = dba.getField();
		ta = dba.getArea();
		 String text = tf.getText();
	        ta.append(name + ": " + text + newline);
	        tf.selectAll();
	        
	        dba.get_gc().out.println("!@#CHAT"+text);
//	        System.out.println("!@#CHAT"+text);
	        System.out.flush();

	        //Make sure the new text is visible, even if there
	        //was a selection in the text area.
	        ta.setCaretPosition(ta.getDocument().getLength());
	    }
		
	}


