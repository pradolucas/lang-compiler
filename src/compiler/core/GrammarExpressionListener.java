// Generated from GrammarExpression.g4 by ANTLR 4.4

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
	import java.util.ArrayList;
	import java.util.Stack;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarExpressionParser}.
 */
public interface GrammarExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(@NotNull GrammarExpressionParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(@NotNull GrammarExpressionParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdescrita(@NotNull GrammarExpressionParser.CmdescritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdescrita(@NotNull GrammarExpressionParser.CmdescritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(@NotNull GrammarExpressionParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(@NotNull GrammarExpressionParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#declara}.
	 * @param ctx the parse tree
	 */
	void enterDeclara(@NotNull GrammarExpressionParser.DeclaraContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#declara}.
	 * @param ctx the parse tree
	 */
	void exitDeclara(@NotNull GrammarExpressionParser.DeclaraContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdwhile}.
	 * @param ctx the parse tree
	 */
	void enterCmdwhile(@NotNull GrammarExpressionParser.CmdwhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdwhile}.
	 * @param ctx the parse tree
	 */
	void exitCmdwhile(@NotNull GrammarExpressionParser.CmdwhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(@NotNull GrammarExpressionParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(@NotNull GrammarExpressionParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdif}.
	 * @param ctx the parse tree
	 */
	void enterCmdif(@NotNull GrammarExpressionParser.CmdifContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdif}.
	 * @param ctx the parse tree
	 */
	void exitCmdif(@NotNull GrammarExpressionParser.CmdifContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(@NotNull GrammarExpressionParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(@NotNull GrammarExpressionParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdexpr}.
	 * @param ctx the parse tree
	 */
	void enterCmdexpr(@NotNull GrammarExpressionParser.CmdexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdexpr}.
	 * @param ctx the parse tree
	 */
	void exitCmdexpr(@NotNull GrammarExpressionParser.CmdexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#fator}.
	 * @param ctx the parse tree
	 */
	void enterFator(@NotNull GrammarExpressionParser.FatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#fator}.
	 * @param ctx the parse tree
	 */
	void exitFator(@NotNull GrammarExpressionParser.FatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull GrammarExpressionParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull GrammarExpressionParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(@NotNull GrammarExpressionParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(@NotNull GrammarExpressionParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdleitura(@NotNull GrammarExpressionParser.CmdleituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdleitura(@NotNull GrammarExpressionParser.CmdleituraContext ctx);
}