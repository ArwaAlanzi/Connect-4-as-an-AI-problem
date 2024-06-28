import java.io.*;
import java.nio.channels.AlreadyBoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.awt.Point;
import java.util.*;
import java.util.Random;	
public class connect4AI{

   public static void main(String[] args) throws CloneNotSupportedException, AlreadyBoundException{
      Scanner input = new Scanner(System.in);
      
      System.out.println("Plaese enter the depth:");
      int thedepth = input.nextInt();
      
      minmax minmax = new minmax(thedepth);
      alphabetapruning ab_Pruning = new alphabetapruning();
       
      State st1=new State(6,7);
      State st2=new State(6,7);
      State st3=new State(6,7);
      State st4=new State(6,7);
   
       
      long start1=0 , end1=0 ;
      long start2=0 , end2=0 ;
      long start3=0 , end3=0 ;
      long start4=0 , end4=0 ;
   
   
      int choose;
       
      do {
            
         System.out.print("\n1. User vs minmax \r\n"
                             + "2. Random vs minmax \r\n"
                             + "3. User vs alpha-beta \r\n"
                             + "4. Random vs alpha-beta.\r\n"
                             + "5. The time \r\n"
                             + "6. Exit the program.\r\n"+ "");
                             
         choose = input.nextInt();
            
         if(choose==1) {
             System.out.println("User[X] vs minmax[O]");
             start1 = System.currentTimeMillis();
            while(true){
               int action= minmax.getAction(st1);
    	        st1 = st1.generateSuccessor('O', action); 
               st1.printBoard();
            //check if O won?
               if(st1.isGoal('O')){System.out.println("minmax win!,you lose.."); 
                  break;}
               System.out.println("choose your move");
               int enemy_move = input.nextInt();
               st1 = st1.generateSuccessor('X', enemy_move);
               st1.printBoard();
            //check if X won? break
               if(st1.isGoal('X')){ System.out.println("you win!, minmax lose..");
                  break;}
            	//pause
            }
            end1 = System.currentTimeMillis();   
         }
       System.out.println("");

      
         if(choose==2) {
                         System.out.println("Random[X] vs minmax[O]");
                          start2 = System.currentTimeMillis();
            while(true){
               Random ran = new Random();
               int random_move = ran.nextInt(7);
               st2 = st2.generateSuccessor('X', random_move); 
               st2.printBoard();
            //check if O won?
               if(st2.isGoal('X')) {System.out.println("Random win!,minmax lose.."); 
                  break;} 
               int action2= minmax.getAction(st2);
               st2 = st2.generateSuccessor('O', action2); 
               st2.printBoard();
            //check if O won?
               if(st2.isGoal('O')){System.out.println("minmax win!,Random lose.."); 
                  break;}
            //pause
            }
            end2 = System.currentTimeMillis();   
         }
      
       System.out.println("");

         if(choose==3) {
         System.out.println("3.user[X] VS alpha-beta[O]");
            	   // 3.human VS alpha-beta
                        start3 = System.currentTimeMillis();
            while(true){
               double move = ab_Pruning.minimaxAlgoPruning(st3,thedepth,1000000,-1000000,true);
               st3 = st3.generateSuccessor('O',(int)move);
               st3.printBoard();
            	//check if X won? break
               if(st3.isGoal('O')){ System.out.println("alpha-beta win!,you lose..");
                  break;}
               System.out.println("choose your move");
               int enemy_move = input.nextInt();
               st3 = st3.generateSuccessor('X', enemy_move);
               st3.printBoard();
            			//check if X won? break
               if(st3.isGoal('X')){
                  System.out.println("you win!, alpha-beta lose..");
                  break;}
            			//pause
            }//end while
            end3 = System.currentTimeMillis();   
         }
         
                  System.out.println("");
    
                     
         if(choose==4) {
               System.out.println("4.random[X] VS alpha-beta[O]");
                // 4.random VS alpha-beta
                  start4 = System.currentTimeMillis();
            while(true){
               Random ran = new Random();
               int random_move = ran.nextInt(7);
               st4 = st4.generateSuccessor('X', random_move); 
               st4.printBoard();
            //check if O won?
               if(st4.isGoal('X')){
                  System.out.println("Random win!,alpha-beta lose..");
                  break;}
               double move = ab_Pruning.minimaxAlgoPruning(st4,thedepth,1000000,-1000000,true);
               st4 = st4.generateSuccessor('O', (int)move);
               st4.printBoard();
            //check if X won? break
               if(st4.isGoal('O')){ System.out.println("alpha-beta win!,random lose..");
                  break;}
            //pause
            }//while
            end4 = System.currentTimeMillis();   
         }
         
             
              System.out.println("");

         if(choose == 5){
            System.out.println("Time in milli seconds: (User vs minmax ) = "+ (end1-start1));
            System.out.println("Time in milli seconds: (Random vs minmax) = "+ (end2-start2));
            System.out.println("Time in milli seconds: (User vs alpha-beta) = "+ (end3-start3));
            System.out.println("Time in milli seconds: (Random vs alpha-beta) = "+ (end4-start4));
         }
         
            	      System.out.println("");

                     
      }while(choose!=6);
      System.out.println("Thank you , see you again ");}}
        
        
        
        