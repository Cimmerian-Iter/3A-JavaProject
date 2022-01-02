package graphe;
public class Projet {

	  public static void main(String[] args) throws Exception
	  {
		String path = "C:\\\\Users\\\\Samilop\\\\Desktop\\\\graph.dot"; //on insère le chemin du fichier
	    String data = Fonctions.readFileAsString(path); //Il stock le fichier sous forme de texte
	    int lines = Fonctions.countLines(data);//On compte le nombre de lines que possède le fichier
	    
	    String [] stringArr = Fonctions.lines(path);//On stoque le fichier sous forme de tableau pour mieux naviguer dedans
	    
	    Graphe G = new Graphe(lines); //on crée une instance de graph
	    
	    
	    
	    String name = Fonctions.nom(stringArr); // On récupère le nom du graph
	    System.out.println("nom du Graphe : "+name);
	    
	    Fonctions.choixgraph(stringArr, lines, G);//Permet de transformer le dot en graph
	    System.out.println(G.toDOT()); //On récupère le Graph
	    
	    
	    
	    System.out.println(Fonctions.couleur(stringArr, lines, G));//On obtient les couleurs des arrètes
	    
	    int imax = Fonctions.nombre(stringArr, lines, 0) + 1;//On récupère le nombre maximum de sommet du graph, on ajoute +1 car on commence a 0
	    System.out.println(imax);
	    Fonctions.couleurSommet(stringArr, lines, imax);//On obtient un tableau concernant la couleur des sommets
	    	
	    	
	    }
	    	
	  }
	  
	  
	

