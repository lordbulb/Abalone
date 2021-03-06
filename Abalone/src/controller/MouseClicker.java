package controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.TreeSet;

import javax.swing.JButton;

import model.*;
import view.*;


public class MouseClicker implements MouseListener {

	private GameController gamec;
	private Game g;
	AI ai;
	Boolean dist;
	DrawField drawf;
	Field ff;
	Position p;
	
	public MouseClicker(GameController gc){
		gamec=gc;
		g = gc.get_game();
		dist = false;
	}
	
	public MouseClicker(GameController gc, Boolean distant, Field f, DrawField df, Position pos){
		gamec=gc;
		g = gc.get_game();
		dist = distant;
		drawf = df;
		ff = f;
		p = pos;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (!g.isOver()){
		
			DrawField df ;
			DrawBoardAbsolute dba;
			Field f;
			Position pos;
			
		if (!dist){
			df = (DrawField) e.getSource();
			dba = (DrawBoardAbsolute) df.getParent();
			f = df.get_field();
			pos = f.get_position();
		}
		
		else {
			df = drawf;
			dba = gamec.get_board();
			f = ff;
			pos = p;
		}
		
		System.out.println("Clicked position is: " + pos);
		System.out.println("Clicked field is: " + f);
		//creation for sets for the different objects
		HashSet<Position> posset = new HashSet<Position>(); 
		HashSet<Field> fieldset = new HashSet<Field>();
		HashSet<DrawField> drawset = new HashSet<DrawField>();
		
		// this part concerns selecting positions
		if (g.check_position(pos)) {
			System.out.println("Az sym! ...ili?");
			
			if (g.get_players().get(gamec.get_allowedPlayer())==g.get_current_player()){
				posset.addAll(g.add_position(pos));
				for (Position position : posset){
					fieldset.add(g.get_board().get_fields().get(position));
				}
				df.select();
				drawset.addAll(dba.get_drawfields(fieldset));
				for (DrawField dff : drawset){
					dff.deselect();
				}
				drawset.add(df);
			}
		
		}
		// this part concerns moves
		
		else if (g.check_neighbour_position(pos)){
			System.out.println("MESTYA");
			Move move = g.gen_move(pos);
			if (g.check_position_for_move(pos, move)){
				flag = true;
				System.out.println("USPYAVAM MESTYA");
				g.move(move);
				
			//deselect the selected pieces
//				System.out.println("E+mpty set : " + drawset);
				posset.addAll(move.get_own_positions().keySet());
				for (Position position : posset){
					fieldset.add(g.get_board().get_fields().get(position));
				}
				drawset.addAll(dba.get_drawfields(fieldset));
//				System.out.println("Set of my pieces to be moved: " + drawset);
				for (DrawField dff : drawset){
					dff.deselect();
				}
//				System.out.println("ALL AFFECTED POSITIONS! : " + move.get_affected_positions());
				posset.addAll(move.get_affected_positions());
				for (Position position : posset){
//					System.out.println("Position of a piece: " + position);
//					System.out.println("Fields corresponding to the position of the opponent's piece: " + g.get_board().get_fields().get(position));
					fieldset.add(g.get_board().get_fields().get(position));
				}
//				System.out.println("Set of other positions: " + move.get_other_positions().keySet());
//				System.out.println("Draw fields corresponding to the position of the opponent's piece: " + g.get_board().get_fields().get(position));
				drawset.addAll(dba.get_drawfields(fieldset));
//				System.out.println("Complete set to be changed: " + drawset);
//				System.out.println(move.get_affected_positions());
//				posset.addAll(move.get_affected_positions());
				
				if (!dist){
					String text ="";
					for (Position p : new TreeSet<Position>(move.get_own_positions().keySet())){
						text+=p.toString();
						text+=',';
					}
//					text = text.substring(beginIndex)
					text+=pos.toString();
					System.out.println("!@#MOVE" + text);
					gamec.out.println("!@#MOVE"+text);
				}
				
				
			}
		}
//		if (!posset.isEmpty()){
//		for (Position position : posset){
//			fieldset.add(g.get_board().get_fields().get(position));
//
//		}
//		df.select();
//		drawset.addAll(dba.get_drawfields(fieldset));
//		for (DrawField dff : drawset){
//			dff.deselect();
//		}
//		drawset.add(df);
//		}
		//update of the fields that need to be redrawn
		System.out.println("Final set of FIelds to be drawn: " + drawset);
		gamec.get_board().update_fields(drawset);
		
		if (flag){
			gamec.show_win_game(); 
			g.change_player();
//			new UndoListener(gamec, dba).actionPerformed(new ActionEvent(drawset, 0, null));
			if (g.get_players().get_current().get_type()==1) {
				if (dist){
					computer_move(dba, null);
				}
				else{
					
				}
				
			}
		}
		}
	}
	
	public void computer_move(DrawBoardAbsolute dba, Move distant_move ){
		Game game = g;
		Move m;
		if (distant_move==null){
			ai = new AI(game, game.get_current_player());
			System.out.println("COMPLIST: AI is player: " + ai.get_player());
			System.out.println("COMPLIST: Current player before AI searches for move: " + game.get_current_player());
			m = ai.get_best_move(ai.generate_moves());
			System.out.println("COMPLIST: Current player after AI searches for move: " + game.get_current_player());
		}
		else{ m = distant_move;}
		game.move(m);
		System.out.println("COMPLIST: Current player after AI makes a move: " + game.get_current_player());
		
//		JButton df = (JButton) e.getSource();
//		DrawBoardAbsolute dba = (DrawBoardAbsolute) df.getParent();
//		Field f = df.get_field();
		HashSet<Position> posset = new HashSet<Position>(); 
		HashSet<Field> fieldset = new HashSet<Field>();
		HashSet<DrawField> drawset = new HashSet<DrawField>();
		
	    posset.addAll(m.get_own_positions().keySet());
		for (Position position : posset){
			fieldset.add(game.get_board().get_fields().get(position));
		}
		drawset.addAll(dba.get_drawfields(fieldset));
		for (DrawField dff : drawset){
			dff.deselect();
		}
		posset.addAll(m.get_affected_positions());
		for (Position position : posset){
			fieldset.add(game.get_board().get_fields().get(position));
		}
		drawset.addAll(dba.get_drawfields(fieldset));
		gamec.get_board().update_fields(drawset);
		gamec.show_win_game();
		game.change_player();
		System.out.println("COMPLIST: Current player after AI makes and draws a move: " + game.get_current_player());
		if (dist){
			
			try {
				gamec.out.println("!@#AIMOVE");
				ObjectOutputStream Oout = new ObjectOutputStream(gamec.get_dm().getServerA().get_sock().getOutputStream());
				Oout.writeObject(m);
//				Oout.close();
			}
			catch (IOException e){
				System.out.println(e.toString());
			}
//			String text ="";
//			for (Position p : new TreeSet<Position>(m.get_own_positions().keySet())){
//				text+=p.toString();
//				text+=',';
//			}
//			text = text.substring(beginIndex)
//			text+=pos.toString();
//			System.out.println("!@#MOVE" + text);
//			gamec.out.println("!@#MOVE"+text);
		}

	}
	

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}