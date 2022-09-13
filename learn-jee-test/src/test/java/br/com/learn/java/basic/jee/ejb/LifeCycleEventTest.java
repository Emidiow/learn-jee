package br.com.learn.java.basic.jee.ejb;

import br.com.learn.java.about.EJB;

/**
 * Eventos do Ciclo de Vida
 * 
 * M�todos interceptadores do ciclo de vida podem ser definidos tanto para <b>beans de sess�o</b> quanto para <b>beans de mensagens</b>.
 * 
 * As anota��es @AroundConstruct, @PostConstruct, @PreDestroy, @PostActivate, e @PrePassivate 
 * s�o utilizados para definir m�todos interceptadores para eventos do ciclo de vida dos beans.
 * 
 * A anota��es @AroundConstruct pode ser definida apenas numa classe interceptadora, 
 * enquanto que todas as outras anota��es podem ser definidos em uma classe interceptadora e/ou diretamente numa classe bean.
 * 
 */
public class LifeCycleEventTest implements EJB{

	
	
}
