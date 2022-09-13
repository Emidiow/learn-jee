package br.com.learn.java.basic.jee.ejb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ejb.Stateful;
import javax.interceptor.AroundConstruct;
import javax.interceptor.Interceptor;
import javax.interceptor.InterceptorBinding;
import javax.interceptor.InvocationContext;

public class InterceptorAroundConstruct extends LifeCycleEventTest{

	/**
	 * Interceptador @AroundConstruct
	 */
	
	/**
	 * A anota���o @AroundConstruct designa um m�todo interceptor que 
	 * recebe uma chamada de retorno quando o construtor da classe de destino � invocado.
	 * Este m�todo interceptor pode ser definido apenas em classes de interceptoras 
	 * e/ou superclasses de classes de interceptores e n�o pode ser definido na classe alvo.
	 *
	 */
	@Inherited
	@InterceptorBinding
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.TYPE})
	//Definindo um AroundConstructor.
	public @interface MeuAroundConstruct {
	}

	//Um interceptador vinculado (Interceptor binding)
	@MeuAroundConstruct
	@Interceptor
	public class MeuAroundConstructInterceptador {
	  @AroundConstruct
	  public void validateConstructor(InvocationContext context) {}
	}

	//E finalmente, o interceptador pode ser especificado no bean
	//O m�todo validateConstructor � chamado toda vez que o construtor do bean MeuBean � chamado.
	@MeuAroundConstruct
	@Stateful
	public class MeuBean {}
	
}
