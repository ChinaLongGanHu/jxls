package net.sf.jxls.parser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import net.sf.jxls.parser.Expression;
import net.sf.jxls.transformer.Configuration;

import java.util.Map;

/**
 * Expression parser class
 * @author Leonid Vysochyn
 */
public class ExpressionParser {
    protected final Log log = LogFactory.getLog(getClass());
    String expression;
    Map beans;
    Configuration configuration;


    public ExpressionParser(String expression, Map beans, Configuration configuration) {
        this.expression = expression;
        this.beans = beans;
        this.configuration = configuration;
    }

    public Expression parse(){
        Expression expr = null;
        if( expression!=null ){
            expression = expression.trim();
            if( expression.startsWith(configuration.getStartExpressionToken()) && expression.endsWith( configuration.getEndExpressionToken() )){
                try {
                    expr = new Expression( expression.substring(2, expression.length() - 1), beans, configuration);
                } catch (Exception e) {
                    log.error("Can't parse expression " + expression);
                }
            }else{
                log.warn("Expression should start with " + configuration.getStartExpressionToken() + " and end with " + configuration.getEndExpressionToken()
                + " but was " + expression);
            }
        }
        return expr;
    }

}