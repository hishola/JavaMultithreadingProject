package multithreading.threadLocal;

public class MonCompteurTL {
	 
	/*Pour que la valeur soit initialisée pour chaque thread, 
	 * il faut redéfinir la méthode initialValue() pour qu'elle renvoie la valeur initiale. 
	 * Celle-ci sera alors utilisée si le ThreadLocal ne possède pas encore de valeur pour le thread courant.*/
	
	private static ThreadLocal<Integer> compteur = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return Integer.valueOf(0);
	    }
	};
	 
	public int get() {
		return compteur.get();
	}

	public int incrementer() {
		int valeur = compteur.get();
		valeur++;
		compteur.set(valeur);
		return valeur;
	}
	 
	public void retirer() {
		compteur.remove();
	}
}