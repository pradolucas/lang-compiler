// Generated from GrammarExpression.g4 by ANTLR 4.13.0
package compiler.core;

	import symbols.DataType;
	import symbols.Identifier;
	import symbols.SymbolTable;
	import exceptions.SemanticException;
	import abstract_syntax_tree.Program;
	import abstract_syntax_tree.AbstractCommand;
	import abstract_syntax_tree.CommandLeitura;
	import abstract_syntax_tree.CommandEscrita;
	import abstract_syntax_tree.CommandAtribuicao;
	import abstract_syntax_tree.CommandDecisao;
	import abstract_syntax_tree.CommandRepeticao;
	import java.util.ArrayList;
	import java.util.Stack;

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
	 * Enter a parse tree produced by {@link GrammarExpressionParser#declara}.
	 * @param ctx the parse tree
	 */
	void enterDeclara(GrammarExpressionParser.DeclaraContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#declara}.
	 * @param ctx the parse tree
	 */
	void exitDeclara(GrammarExpressionParser.DeclaraContext ctx);
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
	 * Enter a parse tree produced by {@link GrammarExpressionParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(GrammarExpressionParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(GrammarExpressionParser.TipoContext ctx);
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
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdleitura(GrammarExpressionParser.CmdleituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdleitura(GrammarExpressionParser.CmdleituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdescrita(GrammarExpressionParser.CmdescritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdescrita(GrammarExpressionParser.CmdescritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdexpr}.
	 * @param ctx the parse tree
	 */
	void enterCmdexpr(GrammarExpressionParser.CmdexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdexpr}.
	 * @param ctx the parse tree
	 */
	void exitCmdexpr(GrammarExpressionParser.CmdexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdif}.
	 * @param ctx the parse tree
	 */
	void enterCmdif(GrammarExpressionParser.CmdifContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdif}.
	 * @param ctx the parse tree
	 */
	void exitCmdif(GrammarExpressionParser.CmdifContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdwhile}.
	 * @param ctx the parse tree
	 */
	void enterCmdwhile(GrammarExpressionParser.CmdwhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdwhile}.
	 * @param ctx the parse tree
	 */
	void exitCmdwhile(GrammarExpressionParser.CmdwhileContext ctx);
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
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#fator}.
	 * @param ctx the parse tree
	 */
	void enterFator(GrammarExpressionParser.FatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#fator}.
	 * @param ctx the parse tree
	 */
	void exitFator(GrammarExpressionParser.FatorContext ctx);
}