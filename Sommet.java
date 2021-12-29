package graph;

import java.util.Vector;

public class Sommet {

	int id;
	Vector<Sommet> voisins=new Vector<Sommet>();
	boolean flag=false;
	
	/**
	 * Constructeur utilisant un identifiant pour le sommet.
	 * @param id La valeur de l'identifiant du sommet.
	 */
	Sommet(int id) {
		this.id=id;
	}
	
	/**
	 * Ajoute un sommet dans la lsite des voisins du sommet.
	 * @param s Le sommet $agrave; ajouter.
	 */
	void addVoisin(Sommet s) {
		voisins.add(s);
	}
	
	/**
	 * V&eacute;rifie si un sommet est bien voisin du sommet de l'instence courante.
	 * @param s Le voisin potentiel.
	 * @return true or false.
	 */
	boolean isVoisin(Sommet s) {
		return voisins.contains(s);
	}
	
	@Override
	public String toString() {
		String str=id+":(";
		for (int i=0;i<voisins.size()-1;i++)str+=voisins.get(i).id+",";
		return str+voisins.get(voisins.size()-1).id+")";
	}
}
