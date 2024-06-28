import java.io.*;
 import java.util.Arrays;
 import java.util.ArrayList;
 import java.awt.Point;
 import java.util.*;
 import java.util.Random;
public class alphabetapruning extends connect4AI{
	public static boolean gameover(State s, int depth){
		   return (s.isGoal('X') | s.isGoal('O') | depth == 0);
		   }


		public static double minimaxAlgoPruning(State s, int depth, int alpha, int beta, boolean AI)throws CloneNotSupportedException{
		   ArrayList<Integer> legallocations = s.getLegalActions();
		   int size = legallocations.size();
		   int Changingheuristic=0;
		   double heuristic = 0;
		   int Best_Position = 0;
		 char player = ' ';

		   if(AI) player = 'O';
		   else if(!AI) player = 'X';
		    
		   //is it a terminal node?
		   if( gameover(s,depth) ){if ( s.isGoal('X') ) return 10000; 
		   else if(s.isGoal('O')) return -10000;
		   else return s.evaluationFunction();} 
		  
		    //is it maximizing player?  (agent)
		   if(AI){ 
		  heuristic = Integer.MIN_VALUE;
		       Random ran = new Random(); 
		       int rand = ran.nextInt(size);
		        Best_Position = legallocations.get(rand);      
		        for(int c = 0; c< size; c++){
		 Changingheuristic = (int) minimaxAlgoPruning((s.generateSuccessor(player, legallocations.get(c))),(int)depth-1, (int)alpha, (int)beta, false);
		  alpha = (int) Math.max(alpha, heuristic);
		   if(alpha >= beta) break;
		     if(Changingheuristic > heuristic){ heuristic = Changingheuristic; Best_Position = c;}
		 
		     } 

		   return Best_Position;}
		   
		      //is it minimizing player? (us)
		      else if(!AI){
		      
		     heuristic = Integer.MAX_VALUE;
		       Random ran = new Random(); 
		        int rand = ran.nextInt(size);
		       Best_Position = legallocations.get(rand);
		        for(int c = 0; c< size;c++){
		      Changingheuristic = (int) minimaxAlgoPruning(s.generateSuccessor(player, legallocations.get(c)),(int)depth-1,(int)alpha,(int)beta, true);
		        beta = (int) Math.min(beta, heuristic);
		   if( alpha >= beta)
		    break;
		    
		   if(Changingheuristic < heuristic){ heuristic = Changingheuristic; Best_Position = c;}
		     }  
		     return Best_Position; }
		     
		     return 0;} // end minimaxAlgoPruning
}