package br.com.learn.java.basic.jee.ejb;

import javax.ejb.Remove;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import br.com.learn.java.about.EJB;

/**
 * Performance
 * Seguran�a
 * Transa��es
 * Integridade
 *
 */
public class EJBTest implements EJB{

	/**
	 * Um bean com estado de sess�o
	 * 
	 * Um bean com estado de sess�o(stateful session bean) tem como caracteristicas manter o estado conversacional para um cliente especifico. 
	 * O estado � armazenado nos valores das variaveis de instancia do bean e nos interceptadores associados.
	 * Podemos definir um simples bean com estado de sess�ousando a anota��o 
	 * 
	 * Todos os metodos publicos do bean exemplificados podem ser invocados por um cliente.
	 * 
	 * Este estilo de declara��o de bean � chamado de visualizacao sem interface. 
	 * Esse bean � apenas localmente acess�vel aos clientes empacotados no mesmo arquivo de deploy (como um war)
	 * 
	 * Se um bean precisa ser remotamente acess�vel, ele deve definir uma interface de neg�cio separada anotada com @Remote. 
	 */
	@Stateful
	class StatefulSessionBeans{
		
		/**
		 * Um cliente pode remover um bean com estado de sess�oapenas invocando o metodo "remover". 
		 * Chamando este m�todo temos como resultado uma chamado do container ao metodo com a anota��o @PreDestroy. 
		 * Remover um bean com estado de sess�osignifica que o estado da instancia espec�fica para aquele cliente nao existe mais.
		 */
		@Remove
		public void remover() {}
		
	}
	
	/**
	 * Um bean sem estado de sessao
	 * 
	 * Um bean sem estado de sess�o nao contem qualquer estado conversacional para um cliente especifico.
	 * Todas as instancias de um bean sem estado de sess�o sao equivalentes, 
	 * portanto o container pode escolher delegar um metodo invocado por um cliente para qualquer instancia disponivel no pool do container. 
	 * Visto que os beans sem estado de sess�o nao possuem qualquer estado, eles nao precisam ser passivados.
	 *
	 */
	@Stateless
	class StatelessSessionBeans{
		
	}
	
	/**
	 * Um bean de sess�oSingleton
	 *
	 * Um bean de sess�oSingleton � um componente que � instanciado uma �nica vez por aplica��o e fornece um acesso bastante facilitado ao estado compartilhado. 
	 * Se o container for distribu�do em m�ltiplas JVM, cada aplica��o ter� uma inst�ncia do Singleton para cada JVM. 
	 * Um bean de sess�oSingleton � explicitamente projetado para ser compartilhado e suportar concorr�ncia.
	 *
	 * O Container � respons�vel por quando inicializar uma inst�ncia de um bean de sess�oSingleton. 
	 * Contudo, podemos opcionalmente marcar o bean para inicializar antes, atrav�s da anota��o @Startup
	 * O Container agora inicializar� todos esses Singletons em tempo de inicializa��o, executando o c�digo que estiver anotado com @PostConstruct, 
	 * antes da aplica��o tornar-se dispon�vel e antes que qualquer solicita��o do cliente seja atendida.
	 * 
	 * Podemos especificar uma inicializa��o explicita de beans de sess�oSingleton usando @DependsOn("MinhaSessionBeanPrimaria")
	 * O container assegura que o bean MinhaSessionBeanPrimaria � inicializado antes do bean SingletonSesionBean.
	 * Um bean Singleton suporta os m�todos de callback do ciclo de vida "PostConstruct" e "PreDestroy". 
	 * Al�m disso, o Singleton tamb�m suporta acesso concorrente.
	 * 
	 * Por padr�o, um bean Singleton � marcado para ter concorr�ncia gerenciada pelo container, 
	 * mas opcionalmente pode ser marcado para ter concorr�ncia gerenciada por bean.
	 * 
	 * Concorr�ncia gerenciada por Container � baseada em metadados com bloqueio a n�vel de m�todo onde cada m�todo � associado com um bloqueio do tipo Read (compartilhado) ou Write (exclusivo).
	 * Um bloqueio de leitura (Read) permite chamadas simult�neas do m�todo. Um bloqueio de leitura (Write) 
	 * aguarda o processamento de uma invoca��o completar antes de permitir que a pr�xima invoca��o prossiga.
	 * As anota��es @Lock(LockType.READ) e @Lock(LockType.WRITE) s�o utilizadas para especificar o tipo de bloqueio. 
	 * Mas, por padr�o, um bloqueio Write � associado com cada m�todo do bean.
	 * Essas anota��es podem ser especificadas na classe, um m�todo de neg�cio da classe, ou ambos. 
	 * Um valor especificado no m�todo sobrescreve um valor especificado na classe. 
	 * A concorr�ncia gerenciada por bean requer que o desenvolvedor gerencie a concorr�ncia utilizando primitivas de sincroniza��o da linguagem Java como "synchronized" e "volatile".
	 *
	 */
	@Singleton
	class SingletonSesionBean{
		
	}
	
	
	
}
