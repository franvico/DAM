package ContarVocales;

import java.util.ArrayList;
import java.util.List;

public class ContadorVocales implements Runnable{
	
	public static int numVocales = 0;
	public int numVocal = 0;
	private char vocal;
	private static char vocales[] = {'a', 'e', 'i','o','u'};
	private Object o;
	
	public ContadorVocales(char vocal, Object o) {
		this.vocal = vocal;
		this.o = o;
	}
	
	String texto = "sdfasoqndasm,oasdaosdnaisndanqndasdzxdaeuisocasocasiu";

	public static void main(String[] args) throws InterruptedException {
		
		int n = 5;
		
		ArrayList<Thread> lt = new ArrayList<Thread>();
				Object o=new Object();
		for(int i = 0; i < n; i++) {
			Thread t = new Thread(new ContadorVocales(vocales[i],o));
			lt.add(t);
		}
		
		for(Thread t : lt) {
			t.start();
		}
		for(Thread t : lt) {
			t.join();
		}
		
		System.out.println(numVocales);
	}
	
	public void run() {
		contarVocal();
		
		
	}
	
	public void contarVocal() {
		for(int i = 0; i < texto.length(); i++) {
			if(texto.charAt(i) == (char)this.vocal) {
				sumaVocalesTotal();
			}
		}
	}
	
	public void sumaVocalesTotal() {
		synchronized(o) {
		numVocales++;
		}
	}

}
