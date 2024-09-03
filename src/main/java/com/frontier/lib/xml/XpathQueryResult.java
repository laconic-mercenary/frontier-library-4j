
package com.frontier.lib.xml;

public class XpathQueryResult {

	private String value = null;
	private String expression = null;
	private boolean succeeded = false;

	/**
	 * Used to create a formal result after an xpath evaluation, given an xml
	 * document.
	 * 
	 * @param expression
	 *           The xpath expression that was used
	 * @param value
	 *           The result of the evaluation of the xpath
	 * @param succeeded
	 *           The expression resulted in finding something
	 */
	public XpathQueryResult(String expression, String value, boolean succeeded) {
		this.value = value;
		this.expression = expression;
		this.succeeded = succeeded;
	}

	/**
	 * Obtains the value the xpath expression found. Should call hasSucceeded()
	 * before obtaining the value found here.
	 * 
	 * @return The value, as a string
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Obtains the xpath expression used
	 * 
	 * @return The expression as a string
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * Determines if the evaluated xpath expression returned a result.
	 * 
	 * @return
	 */
	public boolean hasSucceeded() {
		return succeeded;
	}
}
