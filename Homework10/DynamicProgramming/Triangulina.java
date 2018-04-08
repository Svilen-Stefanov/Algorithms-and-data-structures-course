package com.svilenstefanov.blatt10.a7;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Homework 10
 * @author Svilen Stefanov
 */
public class Triangulina {
  public static TriangulinaResult findMaxSumPath(int[] triangle) {
	  int [] arrayCopy = new int[triangle.length]; 
	  int n = (-1 + (int)Math.sqrt(1+8*triangle.length)) / 2;		//count of rows
	  int arraySize = triangle.length;
	  System.arraycopy(triangle, 0, arrayCopy, 0, arraySize);
	  int [][] elementsPath = new int[triangle.length][2];			// first index show the path, the second show the sum
	  int [] path = new int[n];
	  
	  for (int i = n-1; i > 0; i--) {				//bottom to top approach
		  for (int j = ((i+1)*(i))/2; j+1 < arraySize; j++) {
			  if(arrayCopy[j] > arrayCopy[j+1]){
				  elementsPath[j-i][0] = 0;				//0 - go left
				  arrayCopy[j - i] = arrayCopy[j] + arrayCopy[j-i];
				  
				  if(i==n)
					  elementsPath[j-i][1] = triangle[j] + triangle[j-i];
				  else elementsPath[j-i][1] = arrayCopy[j - i];
			  } else {
				  elementsPath[j-i][0] = 1;				//1 - go right
				  arrayCopy[j - i] = arrayCopy[j+1] + arrayCopy[j-i];
				  
				  if(i==n)
					  elementsPath[j-i][1] = triangle[j+1] + triangle[j-i];
				  else elementsPath[j-i][1] = arrayCopy[j - i];
			  }
		  }
		  arraySize-= (i+1);
	  }
	  
	  if(triangle.length == 1){
		  path[0] = 0;
		  return new TriangulinaResult(triangle[0], path);
	  }
	  
	  int vertexIndex =0;
	  for (int i = 1; i < n; i++) {
		if(elementsPath[vertexIndex][0] == 0){
			vertexIndex += i; 
			path[i] = vertexIndex;
		} else {
			vertexIndex += i+1;
			path[i] = vertexIndex;
		}
	  }
	  return new TriangulinaResult(elementsPath[0][1], path);
  }

  public static void main (String[] args) {
    int[] triangle = new int[] {3, 7, 4, 2, 4, 6, 8, 5, 9, 3};
    TriangulinaResult tr = findMaxSumPath(triangle);
    System.out.print("Path: ");
    for (int i = 0; i < tr.getPath().length; i++) {
      if(i > 0)
        System.out.print(" -> ");
      System.out.print(tr.getPath()[i]);
    }
    System.out.println();
    System.out.println("Revenue: " + tr.getRevenue());
  }
}
