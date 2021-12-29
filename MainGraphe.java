package graph;

public class MainGraphe {

	public static void main(String[] args) {
		Graphe G = new Graphe(5);
		G.addArete(1,3);
		G.addArete(0,3);
		G.addArete(1,4);
		G.addArete(2,3);
		G.addArete(2,4);
		System.out.println(G);
	}

}
