package br.com.learn.java.medium.threads;

import br.com.learn.java.about.BillPughSingleton;
import br.com.learn.java.about.EagerInitialization;
import br.com.learn.java.about.LazyInitialization;
import br.com.learn.java.about.MutualExclusion;
import br.com.learn.java.about.RaceCondition;
import br.com.learn.java.about.Semaphore;
import br.com.learn.java.about.SingleThread;
import br.com.learn.java.about.ThreadSafe;

public class SingletonTest implements ThreadSafe, SingleThread, RaceCondition, Semaphore, MutualExclusion,
		EagerInitialization, LazyInitialization, BillPughSingleton {

}
/**
 * Eager Initialization
 * 
 * Este � o m�todo mais simples de criar uma classe singleton. 
 * Neste, o objeto de classe � criado quando � carregado na mem�ria pela JVM. 
 * Isso � feito atribuindo a refer�ncia de uma inst�ncia diretamente.
 * Ele pode ser usado quando o programa sempre usar� inst�ncia dessa classe ou 
 * o custo de cria��o da inst�ncia n�o for muito grande em termos de recursos e tempo.
 * 
 */
class Singleton {
	private static Singleton instance = new Singleton();

	private Singleton() {
	}

	public static Singleton getInstance() {
		return instance;
	}
}

/**
 * Eager Initialization
 * 
 * Muito simples de implementar.
 * 
 * Pode levar ao desperd�cio de recursos. Porque a inst�ncia da classe � sempre criada, seja ela necess�ria ou n�o.
 * O tempo de CPU tamb�m � desperdi�ado na cria��o da inst�ncia se n�o for necess�rio.
 * O tratamento de exce��o n�o � poss�vel.
 * 
 * Usando bloco est�tico: Esta tamb�m � uma subparte da inicializa��o Eager. 
 * A �nica diferen�a � que o objeto � criado em um bloco est�tico para que possamos ter acesso em sua cria��o, como tratamento de exce��o. 
 * Desta forma tamb�m, o objeto � criado no momento do carregamento da classe.
 * Ele pode ser usado quando h� uma chance de exce��es na cria��o de objetos com inicializa��o antecipada.
 *
 */
class SingletonA {
	public static SingletonA instance;

	private SingletonA() {
	}

	static {
		instance = new SingletonA();
	}
}

/**
 * 
 * Lazy Inicialization
 * 
 * Pr�s:
 * Muito simples de implementar.
 * N�o h� necessidade de implementar o m�todo getInstance(). A inst�ncia pode ser acessada diretamente.
 * Exce��es podem ser tratadas em bloco est�tico.
 * Pode levar ao desperd�cio de recursos. Porque a inst�ncia da classe � sempre criada, seja ela necess�ria ou n�o.
 * O tempo de CPU tamb�m � desperdi�ado na cria��o da inst�ncia se n�o for necess�rio.
 * 
 * Inicializa��o lenta: Neste m�todo, o objeto � criado apenas se for necess�rio. 
 * Isso pode evitar o desperd�cio de recursos. 
 * � necess�ria uma implementa��o do m�todo getInstance() que retorna a inst�ncia. 
 * H� uma verifica��o nula de que, se o objeto n�o for criado, crie, caso contr�rio, retorne criado anteriormente. 
 * Para garantir que a classe n�o possa ser instanciada de outra forma, o construtor � finalizado. 
 * � medida que o objeto � criado com um m�todo, ele garante que o objeto n�o ser� criado at� e a menos que seja necess�rio. 
 * A inst�ncia � mantida privada para que ningu�m possa acess�-la diretamente.
 * Ele pode ser usado em um �nico ambiente de encadeamento porque v�rios encadeamentos podem quebrar a propriedade singleton, 
 * pois podem acessar o m�todo de inst�ncia get simultaneamente e criar v�rios objetos.
 *
 */

class SingletonB {
	private static SingletonB instance;

	public static SingletonB getInstance() {
		if (instance == null) {
			instance = new SingletonB();
		}
		return instance;
	}
}

/**
 * 
 * Lazy initialization with Thread Safe
 * 
 * O objeto � criado apenas se for necess�rio. Pode superar o desperd�cio de recursos e tempo de CPU.
 * O tratamento de exce��o tamb�m � poss�vel no m�todo.
 * Toda vez que uma condi��o de nulo deve ser verificada.
 * inst�ncia n�o pode ser acessada diretamente.
 * Em ambiente multithread, pode quebrar a propriedade singleton.
 * 
 * Thread Safe Singleton: Um singleton thread-safe � criado para que a propriedade singleton seja mantida mesmo em ambiente multithread. 
 * Para tornar um thread de classe singleton seguro, o m�todo getInstance() � sincronizado para que v�rios threads n�o possam acess�-lo simultaneamente.
 *
 */
class SingletonC {
	private static SingletonC instance;

	private SingletonC() {
	}

	synchronized public static SingletonC getInstance() {
		if (instance == null) {
			instance = new SingletonC();
		}
		return instance;
	}
}

/**
 * Lazy Initialization with double check locking and Thread Safe
 * 
 * A inicializa��o lenta � poss�vel.
 * Tamb�m � thread-safe.
 * O m�todo getInstance() � sincronizado, portanto, causa um desempenho lento, pois v�rios threads n�o podem acess�-lo simultaneamente.
 * Inicializa��o lenta com bloqueio de verifica��o dupla: Neste mecanismo, superamos o problema de sobrecarga do c�digo sincronizado. 
 * Neste m�todo, getInstance n�o � sincronizado, mas o bloco que cria a inst�ncia � sincronizado para que o n�mero m�nimo de threads tenha que esperar e isso � apenas pela primeira vez.
 *
 */
class SingletonD {
	private static SingletonD instance;

	private SingletonD() {
	}

	public static SingletonD getInstance() {
		if (instance == null) {
			synchronized (SingletonD.class) {
				if (instance == null) {
					instance = new SingletonD();
				}
			}
		}
		return instance;
	}
}
/**
 * Bill Pugh impl
 * 
 * A inicializa��o lenta � poss�vel.
 * Tamb�m � thread-safe.
 * A sobrecarga de desempenho � reduzida devido � palavra-chave sincronizada.
 * Na primeira vez, pode afetar o desempenho.
 * Implementa��o de Bill Pugh Singleton: Antes do Java5, o modelo de mem�ria tinha muitos problemas e os m�todos acima causavam falhas em certos cen�rios em ambiente multithread. 
 * Ent�o, Bill Pugh sugeriu um conceito de classes est�ticas internas para usar em singleton.
 * 
 * Quando a classe singleton � carregada, a classe interna n�o � carregada e, portanto, n�o cria objeto ao carregar a classe. 
 * A classe interna � criada somente quando o m�todo getInstance() � chamado. 
 * Portanto, pode parecer uma inicializa��o Eager, mas � uma inicializa��o Lazy.
 * Esta � a abordagem mais usada, pois n�o usa sincroniza��o.
 */
class SingletonE {

	private SingletonE() {
	}

	private static class SingletonBillPugh {
		private static final SingletonE INSTANCE = new SingletonE();
	}

	public static SingletonE getInstance() {
		return SingletonBillPugh.INSTANCE;
	}
}

/**
 * Considera��es
 * 
 * A inicializa��o Eager � f�cil de implementar, mas pode causar desperd�cio de recursos e tempo de CPU. 
 * Use-o apenas se o custo de inicializa��o de uma classe for menor em termos de recursos ou seu programa sempre precisar� da inst�ncia da classe.
 * 
 * Ao usar o bloco est�tico na inicializa��o do Eager, podemos fornecer tratamento de exce��o e tamb�m controlar a inst�ncia.
 * 
 * Usando sincronizado, podemos criar uma classe singleton em ambiente multi-threading tamb�m, 
 * mas isso pode causar um desempenho lento, para que possamos usar o mecanismo de bloqueio de verifica��o dupla.
 * 
 * A implementa��o de Bill Pugh � a abordagem mais usada para classes singleton. A maioria dos desenvolvedores o prefere por causa de sua simplicidade e vantagens.
 **/
