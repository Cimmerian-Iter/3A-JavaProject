package graph;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;;

public class Projet {

	   
	  public static String readFileAsString(String fileName)throws Exception
	  {
	    String data = "";
	    data = new String(Files.readAllBytes(Paths.get(fileName)));
	    return data;
	  }
	  
	  public static int countLines(String str)
	  {
	      if (str == null || str.length() == 0)
	          return 0;
	      int lines = 1;
	      int len = str.length();
	      for( int pos = 0; pos < len; pos++) {
	          char c = str.charAt(pos);
	          if( c == '\r' ) {
	              lines++;
	              if ( pos+1 < len && str.charAt(pos+1) == '\n' )
	                  pos++;
	          } else if( c == '\n' ) {
	              lines++;
	          }
	      }
	      return lines;
	  }
	 
	  public static void main(String[] args) throws Exception
	  {
		
	    String data = readFileAsString("C:\\Users\\Cimmerian\\Desktop\\log.txt");
	    System.out.println(data);
	    int lines = countLines(data);
	    System.out.println(lines);
	    
	    BufferedReader in = new BufferedReader(new FileReader("C:\\\\Users\\\\Cimmerian\\\\Desktop\\\\log.txt"));
	    String str;

	    List<String> list = new ArrayList<String>();
	    while((str = in.readLine()) != null){
	        list.add(str);
	    }

	    String[] stringArr = list.toArray(new String[0]);
	    
	    Graphe G = new Graphe(lines);
	    
	    for (int i=1; i<lines-1;i++) {
	    	String mot1 = stringArr[i].replace(";","");
	    	String[] mot2 = mot1.split("--");
	    	int sommet1 = Integer.parseInt(mot2[0]);
	    	int sommet2 = Integer.parseInt(mot2[1]);
	    	G.addArete(sommet1, sommet2);
	    }
	    System.out.println(G);
	    
	    	
	    	
	    }
	    	
	  }
	  
	  
	

