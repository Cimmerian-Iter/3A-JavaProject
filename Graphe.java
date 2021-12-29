package graph;

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
		
	public boolean isVoisin(int i, int j) {
		return sommets.get(i).isVoisin(sommets.get(j));
	}

	@Override
	public String toString() {
		String str="";
		for (int i=0;i<size;i++)str+=sommets.get(i)+" ";
		return str;
	}
}
