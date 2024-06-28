import java.io.*;
	import java.util.Arrays;
	import java.util.ArrayList;
	import java.awt.Point;
	import java.util.*;
	import java.util.Random;
	
public class minmax extends connect4AI {
	
	int depth;
	int x=0;
	public minmax(int depth)
	{
		this.depth = depth;
	}	
	
	public int getAction(State st) throws CloneNotSupportedException
	{
		double val = max(st, depth);
		return x;
		
	}
	
	public double max(State st, int d) throws CloneNotSupportedException
	{
		ArrayList<Integer> children = new ArrayList<Integer>();
		if(d ==0)
		return st.evaluationFunction();
		else{
		children = st.getLegalActions();
		double heuristic = -10000000;
		
		double changingheuristic;
		//double z;
		for(int i =0; i<children.size();i++)
		{
			changingheuristic = min(st.generateSuccessor('O',children.get(i)),d);
			if(changingheuristic >= heuristic)
			{
				heuristic =changingheuristic;
				this.x = i;
			}
		}
		return heuristic;
		}
	}
	
	public double min(State st, int d) throws CloneNotSupportedException
	{
		
		ArrayList<Integer> children = new ArrayList<Integer>();
		if(d == 0)
		return st.evaluationFunction();
		else
		{
		children = st.getLegalActions();
		
		double heuristic = 10000000;
		int x=0;
		double changingheuristic;
		for(int i =0; i<children.size();i++)
		{
			changingheuristic= max(st.generateSuccessor('X',children.get(i)),d-1);
			if(changingheuristic <= heuristic)
				heuristic=changingheuristic;
		
		}
		return heuristic;
		}
	}
	
	
	
}