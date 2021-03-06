package model;
import java.util.*;
/**
 * La classe utilise pour l'IA et pour le determinition de meilleur coup
 * @author E10A896G
 *
 */
public class AI {
	private Game game;
	private Player player;
	
	/**
	 * 
	 * @param g Le jeu pour qui l'IA est cree 
	 * @param p Le joueur pour qui l'IA est cree
	 */
	
	public AI(Game g, Player p){
		game=g;
		player=p;
	}
	/**
	 * 
	 * @return Le jeu
	 */
	public Game get_game(){
		return game;
	}
	/**
	 * 
	 * @param g Le jeu pour l'IA
	 */
	public void set_game(Game g){
		game=g;
	}
	/**
	 * 
	 * @return Le joueur pour l'IA
	 */
	public Player get_player(){
		return player;
	}
	/**
	 * 
	 * @param p Le joueur pour l'IA
	 */
	public void set_player(Player p){
		player=p;
	}
	
	
	/**
	 * Generer toutes les coups possibles d'un position de la jeu
	 * @return Un list avec toutes les coups possibles
	 */
	public List<Move> generate_moves(){
		List<Move> moves = new ArrayList<Move>();
		Map<Position, Field> positions_with_fields = game.get_board().get_fields();
		Set<Position> positions = new HashSet<Position>(); 
		for (Position pos : positions_with_fields.keySet()){
			Field f = positions_with_fields.get(pos);
			if (!f.is_empty() && f.get_piece().get_owner()==player){
				positions.add(pos);
			}
		}
//		System.out.println("Own positions are: " + positions);
		for (Position pos : positions){
			for (int i=1; i<7; i++){
				Position other_pos = pos.get_neighbour(i);
				if (other_pos!=null){
					if (!game.check_position(other_pos)){
						game.add_position(pos);
						Move m = game.gen_move(other_pos);
//						System.out.println(m.get_own_positions());
						if (game.check_position_for_move(other_pos, m)){
							moves.add(m);
						}
						game.add_position(pos);
//						System.out.println("Selected positions of end of trying a move: " + game.get_selected_positions());

					}
					else{
//						for (int j=1; j<7; j++){
						// generate parallel move with two
						for (int j=1; j<7; j++){
							Position parallel_pos = other_pos.get_neighbour(j);
							if (parallel_pos!=null){
								if (!game.check_position(parallel_pos)){
									game.add_position(pos);
									game.add_position(other_pos);
									Move m = game.gen_move(parallel_pos);
//									System.out.println(m.get_own_positions());
									if (game.check_position_for_move(parallel_pos, m)){
										moves.add(m);
									}
									game.add_position(pos);
									game.add_position(other_pos);
//									System.out.println("Selected positions of end of trying a move: " + game.get_selected_positions());

								}
							}
						}
						
							Position third_pos = other_pos.get_neighbour(i);
							if (third_pos!=null){
								if (!game.check_position(third_pos)){
									game.add_position(pos);
									game.add_position(other_pos);
									Move m = game.gen_move(third_pos);
//									System.out.println(m.get_own_positions());
									if (game.check_position_for_move(third_pos, m)){
										moves.add(m);
									}
									game.add_position(pos);
									game.add_position(other_pos);
//									System.out.println("Selected positions of end of trying a move: " + game.get_selected_positions());

								}
							else {
//								for (int k=1; k<7; k++){
//								System.out.println("Is the third position a valid one to be selected? " + game.check_position(third_pos));
//								System.out.println("The third selected position" + third_pos);

								//generate parallel moves with three:
								for (int j=1; j<7; j++){
									Position parallel_pos = third_pos.get_neighbour(j);
//									System.out.println(parallel_pos);
									if (parallel_pos!=null){
										if (!game.check_position(parallel_pos)){
											game.add_position(pos);
											game.add_position(other_pos);
											game.add_position(third_pos);
											Move m = game.gen_move(parallel_pos);
//											System.out.println(m.get_own_positions());
											if (game.check_position_for_move(parallel_pos, m)){
												moves.add(m);
											}
											game.add_position(pos);
											game.add_position(other_pos);
											game.add_position(third_pos);
//											System.out.println("Selected positions of end of trying a move: " + game.get_selected_positions());

										}
									}
								}
									Position last_pos = third_pos.get_neighbour(i);
									if (last_pos!=null){
										if (!game.check_position(last_pos)){
//											System.out.println("Selected positions of beginning of trying a move: " + game.get_selected_positions());
//											System.out.println("Position of first piece: " + pos);
//											System.out.println("Position of second piece: " + other_pos);
//											System.out.println("Position of third piece: " + third_pos);
											game.add_position(pos);
											game.add_position(other_pos);
											game.add_position(third_pos);
//											System.out.println("Selected positions in the middle of trying a move" + game.get_selected_positions());
											Move m = game.gen_move(last_pos);
//											if (m.get_other_positions().isEmpty() && m.get_own_positions().isEmpty()){
//												System.out.println("Current direction: " + i);
//												System.out.println("Selected positions: " + game.get_selected_positions());
//												System.out.println("Position to move to: " + last_pos);
//											}
//											System.out.println(m.get_own_positions());
											if (game.check_position_for_move(last_pos, m)){
												moves.add(m);
											}
											game.add_position(pos);
											game.add_position(other_pos);
											game.add_position(third_pos);
//											System.out.println("Selected positions of end of trying a move: " + game.get_selected_positions());
										}
									}
//								}
							}
							}
//						}
					}
//					moves.get(moves.size()-1)
				}
			}
		}
			
//		System.out.println(moves.size());
//		for (Move m : moves){
//			System.out.println("Own positions for a move: " + m.get_own_positions());
//			System.out.println("Other positions for a move: " + m.get_other_positions());
//			if (m.get_other_positions().isEmpty() && m.get_own_positions().isEmpty()){ moves.remove(m);}
//		}
		System.out.println(moves.size());
		return moves;
	}
	/**
	 * Calculation de la distance d'un position du centre de plateu de jeu
	 * @param pos La position pour evaluation
	 * @return un nombre entier qui represent la distance
	 */
	public int evaluate_distance(Position pos){
		int row = pos.get_row();
		int diagonal = pos.get_diagonal();
		int row_score = Math.abs(5-row);
		int d_score = Math.abs(5-diagonal);
		
		// ufffffffff :(
		if ((row==6 && diagonal == 2) || 
			(row==7 && diagonal == 3) ||
			(row==8 && diagonal == 4) ||
			(row==2 && diagonal == 6) ||
			(row==3 && diagonal == 7) ||
			(row==4 && diagonal == 8)) {row_score=4;}
		
		if ((row==6 && diagonal == 3) || 
				(row==7 && diagonal == 4) ||
				(row==4 && diagonal == 7) ||
				(row==3 && diagonal == 6)) {row_score=3;}
		
		if ((row==6 && diagonal == 4) || 
				(row==4 && diagonal == 6)) {row_score=2;}

		
		
		return Math.max(row_score, d_score);
	}
/**
 * Caculation de la score pour un coup
 * @param m Le coup pour evalue
 * @return un nombre entier qui represent la score pour le coup
 */	
	public int evaluate_move(Move m){
		System.out.println("AI: Evaluating Move: " + m);
//		Move undo_move = m.get_inverse();
		int score=0;
		game.move(m);
		Map <Position, Field> board = game.get_board().get_fields(); 
		Set<Position> own_positions = new HashSet<Position>();
		Set<Position> opponent_positions = new HashSet<Position>();
		for (Position pos : board.keySet()){
//			System.out.println("Position of my piece" + pos);
			if (!board.get(pos).is_empty())
				if(board.get(pos).get_piece().get_owner()==player){
					own_positions.add(pos);
				}
				else{
					opponent_positions.add(pos);
				}
		}
		
		for (Position pos : opponent_positions){
			score+=evaluate_distance(pos);
//			for (Position _ : pos.get_neighbours()){
//				if (opponent_positions.contains(_)){
//					score+=1;
//				}
//			}
		}
		System.out.println("Opponent center score: " + score);
		
		for (Position pos : opponent_positions){
//			score+=evaluate_distance(pos);
			for (Position _ : pos.get_neighbours()){
				if (opponent_positions.contains(_)){
					score-=1;
				}
			}
		}
		System.out.println("Opponent center+friendship score: " + score);
		
		for (Position pos : own_positions){
//			System.out.println("Score before addition" + score);
			score-=evaluate_distance(pos);
//			for (Position _ : pos.get_neighbours()){
//				if (own_positions.contains(_)){
//					score+=1;
//				}
//			}
//			System.out.println("Score after addition" + score);
		}
		System.out.println("Own center score: " + score);
		for (Position pos : own_positions){
//			System.out.println("Score before addition" + score);
//			score-=evaluate_distance(pos);
			for (Position _ : pos.get_neighbours()){
				if (own_positions.contains(_)){
					score+=1;
				}
			}
//			System.out.println("Score after addition" + score);
		}
		System.out.println("Own center+friendship score: " + score);
		
//		game.change_player();
		game.undo_move();
//		game.get_players().get_previous();
		if (m.is_removed()) {score+=10;}
//		if (m.is_pushed()) {score-=5;}
		System.out.println("Move's score is: " + score);
		return score;
		
	}
	/**
	 * Pour un list des coups on trouver le coup avec le plus grand score
	 * @param moves Un list des coups
	 * @return Un coup qui a le plus grand score
	 */
	public Move get_best_move(List<Move> moves){
//		System.out.println(Math.random());
//		System.out.println(moves.size());
//		return moves.get((int) (Math.random()*moves.size()));
//		return moves.get(moves.size()-1);
		Move best_move=null;
		int best_score=-1000;
		int avg_score=0;
		for (Move m : moves){
			int score = this.evaluate_move(m);
			avg_score+=score;
			if (score>best_score) {
				best_score=score; 
				best_move=m;
			}
		}
		avg_score/=moves.size();
		System.out.println("AI: Avg score for a given position: " + avg_score);
		System.out.println("AI: Best score for a given position: " + best_score + (best_move.is_pushed() ? " and is pushed" : ""));
		
		System.out.println("AI: Selected_positions before move: " + game.get_selected_positions());
		System.out.println("AI: Own positions for the best move: " + best_move.get_own_positions());
		System.out.println("AI: Other positions for the best move: " + best_move.get_other_positions());
		return best_move;
	}
	
//	public static void main(String[] args){
//		Ring<Player> r = new Ring<Player>();
//        r.add(new Player(1, "test1"));
//        r.add(new Player(1, "test2"));
//        Game g = new Game(r);
//        AI ai = new AI(g, g.get_current_player());
//        System.out.println(ai.get_best_move(ai.generate_moves()).get_own_positions());
//	}
	
	
}
