// Generated from GrammarExpression.g4 by ANTLR 4.13.0
package compiler.core;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarExpressionParser}.
 */
public interface GrammarExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(GrammarExpressionParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(GrammarExpressionParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(GrammarExpressionParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(GrammarExpressionParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(GrammarExpressionParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(GrammarExpressionParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdLeitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdLeitura(GrammarExpressionParser.CmdLeituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdLeitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdLeitura(GrammarExpressionParser.CmdLeituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdEscrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdEscrita(GrammarExpressionParser.CmdEscritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdEscrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdEscrita(GrammarExpressionParser.CmdEscritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdattrib}.
	 * @param ctx the parse tree
	 */
	void enterCmdattrib(GrammarExpressionParser.CmdattribContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdattrib}.
	 * @param ctx the parse tree
	 */
	void exitCmdattrib(GrammarExpressionParser.CmdattribContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(GrammarExpressionParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(GrammarExpressionParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(GrammarExpressionParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(GrammarExpressionParser.TermoContext ctx);
}