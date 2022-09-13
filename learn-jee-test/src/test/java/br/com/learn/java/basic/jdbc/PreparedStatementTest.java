package br.com.learn.java.basic.jdbc;

/**
 * PreparedStatement estende a interface Statement<br/>
 * Possui m�todos para vincular v�rios tipos de objetos , incluindo arquivos e matrizes<br/>
 * Protege contra inje��o
 * <p>Usa pr�-compila��o: <br> 
 * 		Assim que o banco de dados receber uma consulta, ele verificar� o cache antes de pr�-compilar a consulta. Conseq�entemente, se n�o estiver armazenado em cache, o mecanismo de banco de dados o salvar� para o pr�ximo uso.<br/>
 * 		Al�m disso, esse recurso agiliza a comunica��o entre o banco de dados e a JVM por meio de um protocolo bin�rio n�o SQL. Ou seja, h� menos dados nos pacotes, ent�o a comunica��o entre os servidores fica mais r�pida.
 * </p>
 * Fornece uma execu��o em lote<br/>
 * Fornece uma maneira f�cil de armazenar e recuperar arquivos usando os tipos de dados BLOB e  CLOB<br/>
 * Na mesma linha, ajuda a armazenar listas convertendo java.sql.Array em um SQL Array<br/>
 * Implementa m�todos como getMetadata() que cont�m informa��es sobre o resultado retornado
 */
public class PreparedStatementTest {

}
