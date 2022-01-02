package graphe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;

public class Graphe{

	Vector<Sommet> sommets=new Vector<Sommet>();
	int size;
	
	public Graphe(int size){
		this.size=size;
		for(int id=0;id<size;id++) {
			sommets.add(new Sommet(id));
		}
	}
	
	public Graphe() {
	}

	public void addArete(int i, int j) {
		sommets.get(i).addVoisin(sommets.get(j));
		sommets.get(j).addVoisin(sommets.get(i));
	}
	
	public void addArete(int i, int j,int p) {
		sommets.get(i).addVoisin(sommets.get(j),p);
		sommets.get(j).addVoisin(sommets.get(i),p);
	}
		
	public boolean isVoisin(int i, int j) {
		return sommets.get(i).isVoisin(sommets.get(j));
	}

	@Override
	public String toString() {
		String str="";
		for (int i=0;i<size;i++)str+=sommets.get(i)+" ";
		return str;
	}
	
	public String toDOT() {
		String str="graph G{\n";
		for (int i=0;i<sommets.size();i++) {
			str+=sommets.get(i).toDOT();
		}
		return str+"}";
	}
	
	public int degreMax() {
		int dMax=0;
		for (Sommet s : sommets) {
			if (s.degre()>dMax) dMax=s.degre();
		}
		return dMax;
	}
	
	public String toDOT(Graphe arbre) {
		String str="graph G{\n";
		for (int i=0;i<sommets.size();i++) {
			str+=sommets.get(i).toDOT(arbre);
		}
		return str+"}";
	}
	
	public Graphe BFS(int racine) {
	Graphe arbre = new Graphe(size);
	LinkedList<Sommet> Q = new LinkedList<Sommet>();
	Q.add(sommets.get(racine));
	sommets.get(racine).flag=true;
	while(!Q.isEmpty()) {
		Sommet sommetCourant = Q.getFirst();
		for (Sommet voisinCourant : sommetCourant.voisins) {
			if (voisinCourant.flag==false) {
				Q.add(voisinCourant);
				voisinCourant.flag=true;
				arbre.addArete(voisinCourant.id,sommetCourant.id);
			}
		}
		Q.remove();
	}
	for (Sommet s : sommets) s.flag=false;
	return arbre;
	}
	
	public Graphe Dijkstra(int racine) {
		Graphe arbre = new Graphe(size);
		ArrayList<Sommet> S = new ArrayList<Sommet>(); // S pour sommet
		ArrayList<Integer> P = new ArrayList<Integer>(); // P pour poids
		ArrayList<Sommet> O = new ArrayList<Sommet>(); // O pour origine
		// Initialisation des listes
		for (Sommet voisinCourant : sommets.get(racine).voisins) {
			S.add(voisinCourant);
			O.add(sommets.get(racine));
			P.add(sommets.get(racine).getPoids(voisinCourant));
		}
		sommets.get(racine).flag=true;
		
		while(!S.isEmpty()) {
			int index=P.indexOf(Collections.min(P));
			Sommet sommetCourant = S.get(index);
			int poidsCourant = P.get(index);
			arbre.addArete(O.get(index).id,sommetCourant.id);
			
			for (Sommet voisinCourant : sommetCourant.voisins) {
				if (!S.contains(voisinCourant) && !voisinCourant.flag) {
					S.add(voisinCourant);
					P.add(poidsCourant+sommetCourant.getPoids(voisinCourant));
					O.add(sommetCourant);
				}
				else if(!voisinCourant.flag && poidsCourant+sommetCourant.getPoids(voisinCourant)<P.get(S.indexOf(voisinCourant))) {
					int indexVoisin=S.indexOf(voisinCourant);
					P.set(indexVoisin,poidsCourant+sommetCourant.getPoids(voisinCourant));
					O.set(indexVoisin,sommetCourant);
				}
			}
			
			sommetCourant.flag=true;
			S.remove(index);
			P.remove(index);
			O.remove(index);
		}
		for (Sommet s : sommets) s.flag=false;
		return arbre;
		}
	
	public void coloration() {
		for (Sommet sommetCourant : sommets) {
			//On cherche une couleur qui n'est pas présente chez les voisins.
			int couleur=-1;
			boolean fini=false;
			while(!fini) {
				couleur++;
				fini=true;
				for (Sommet voisinCourant: sommetCourant.voisins) {
					if (voisinCourant.getCouleur()==couleur) fini=false;
				}
			}
			//On donne la couleur qui convient à sommetCourant.
			sommetCourant.setCouleur(couleur);
		}
	}
	
	public void coloration2() {
		for (Sommet sommetCourant : sommets) {
			sommetCourant.setCouleur(sommetCourant.couleurAdmissible());
		}
	}
	
}
