package graphe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Fonctions {
	public static String readFileAsString(String fileName)throws Exception
	  {
	    String data = "";
	    data = new String(Files.readAllBytes(Paths.get(fileName)));
	    return data;//On récupère le texte sous forme de string
	  }
	  
	  public static int countLines(String str)
	  //On compte le nombre de lignes que contient le fichier
	  {
	      if (str == null || str.length() == 0)
	          return 0;
	      int lines = 1;
	      int len = str.length();
	      for( int pos = 0; pos < len; pos++) {
	          char c = str.charAt(pos);//On fait defiler chaque caractère du texte
	          if( c == '\r' ) {
	              lines++;
	              if ( pos+1 < len && str.charAt(pos+1) == '\n' )
	                  pos++;
	          } else if( c == '\n' ) {
	              lines++;//On detecte a chaque fois qu'il y a un passage a la ligne pour compter le nombre de lignes
	          }
	      }
	      return lines;
	  }
	  
	  //La fonction suivante stock le texte sous forme de tableau pour une meilleur navigation
	  public static String[] lines(String path) throws IOException {
		    BufferedReader in = new BufferedReader(new FileReader(path));
		    String str;

		    List<String> list = new ArrayList<String>();
		    while((str = in.readLine()) != null){
		        list.add(str);
		 
	  }
		    String[] stringArr = list.toArray(new String[0]); 
		    return stringArr;
	  }
	  
	  //La fonction suivante transforme le .dot en instance de graph
	  public static void graph(String[] stringArr, int lines, Graphe G) { 
		  for (int i=1; i<lines-1;i++) {
		    	int sommet1 = stringArr[i].charAt(1) -48;//On soustrait 48 car la valeur en int du charAt (le sommet) est égal au chiffre + 48
		    	int sommet2 = stringArr[i].charAt(10) -48;
		    	int poid = stringArr[i].charAt(22) -48;//On récupère le poid
		    	G.addArete(sommet1,sommet2,poid);//On ajoute l'arrete au graph
		    	}
		    }
	  public static String couleur(String[] stringArr, int lines, Graphe G) {
		  String couleur_arrete="";
		  for (int i=1; i<lines-1;i++) {
			  int sommet1 = stringArr[i].charAt(1) -48;
			  int sommet2 = stringArr[i].charAt(10) -48;
		  
			  if (stringArr[i].charAt(23)==',') {
		    	
	    		int i1=30;//position de la virgule
	    			String couleur="";
	    			while (stringArr[i].charAt(i1)!= ']'){
	    				//fonction pour stocker la couleur
	    					couleur+=stringArr[i].charAt(i1);
	    						i1+=1;//On continue d'ajouter les lettres pour former le mot de la couleur correspondante jusqu'a ce qu'on arrive a ]
	    			}
	    		couleur_arrete+=sommet1+couleur+sommet2+"\n";//On stock l'information sous la forme chiffre + couleur + chiffre
		    
			  	}
		  }
		  return couleur_arrete;
		  
	
}
	  public static void digraph(String[] stringArr, int lines, Graphe G) { 
		  for (int i=1; i<lines-1;i++) {
			  if (stringArr[i].charAt(7)=='>') {
				  //La fleche est dans ce sens ->
				  int sommet1 = stringArr[i].charAt(1) -48;//On soustrait 48 car la valeur en int du charAt (le sommet) est égal au chiffre + 48
				  int sommet2 = stringArr[i].charAt(10) -48;
				  int poid = stringArr[i].charAt(22) -48;//On récupère le poid
				  G.addArete(sommet1,sommet2,poid);//On ajoute l'arrete au graph
		    	}
			  else {
				  //La fleche est dans l'autre sens <- donc on inverse les sommet dans le add Arete pour que l'instance de graph les met dans le bonne ordre pour la création du graph orienté après
				  int sommet1 = stringArr[i].charAt(10) -48;//On soustrait 48 car la valeur en int du charAt (le sommet) est égal au chiffre + 48
				  int sommet2 = stringArr[i].charAt(1) -48;
				  int poid = stringArr[i].charAt(22) -48;//On récupère le poid
				  G.addArete(sommet1,sommet2,poid);//On ajoute l'arrete au graph
			  }
		    }
	  }
	  
	  public static String nom(String[] stringArr)
	  {
		String nom="";
	    if (stringArr[0].charAt(0)=='d') {
	    	//c'est un digraph
	    	int i=8;
	    	while (stringArr[0].charAt(i)!= '{'){
	    		nom+=stringArr[0].charAt(i);//On récupère les lettres du nom jusqu'a ce qu'on arrive au {
	    		i++;
	    	}
	    	return nom;
	    }
	    else {
	    	//c'est un graph
	    	int i=6;
	    	while (stringArr[0].charAt(i)!= '{'){
	    		nom+=stringArr[0].charAt(i);
	    		i++;
	    	}
	    	return nom;
	    }
	  }

	public static void choixgraph(String[] stringArr, int lines, Graphe G) {
		    if (stringArr[0].charAt(0)=='d') {
		    	Fonctions.digraph(stringArr,lines,G); //On detecte si c'est un digraph
		    }
		    
		    else {
		    	Fonctions.graph(stringArr, lines, G);//c'est un graph normal donc on execute ce programme.
		    }
	  }
	  
	  
	  public static  int[] couleurSommet(String[] stringArr, int lines, int imax) {
		  int couleur[] = new int[imax];// On créer un tableau de la taille du nombre de sommets
		  for (int i=1; i<lines-1;i++) {
			  int sommet1 = stringArr[i].charAt(1) -48;
			  int sommet2 = stringArr[i].charAt(10) -48;
			  int couleur1 = stringArr[i].charAt(3) -48;
			  int couleur2 = stringArr[i].charAt(12) -48;
			  couleur[sommet1]=couleur1; //On stock pour le sommet1 la couleur1
			  couleur[sommet2]=couleur2;//L'indice du tableau correspond au chiffre du sommet
		  }
		  return couleur;
		  
	  }
	  public static  int nombre(String[] stringArr, int lines, int imax) {
		  //Le but est d'aller chercher le sommet le plus grand. Ce qui nous donne le nombre de sommets
		  for (int i=1; i<lines-1;i++) {
			  int sommet1 = stringArr[i].charAt(1) -48;
			  if (sommet1 > imax) {
				  imax=sommet1;
			  }
		  }
		  for (int i=1; i<lines-1;i++) {
			  int sommet2 = stringArr[i].charAt(10) -48;
			  if (sommet2 > imax) {
				  imax=sommet2;//de même on vérifie ici si on trouve pas un sommet avec un chiffre supérieur
			  }
		  }
		  return imax;
	  }

}

