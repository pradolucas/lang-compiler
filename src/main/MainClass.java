package main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import compiler.core.GrammarExpressionLexer;
import compiler.core.GrammarExpressionParser;
import exceptions.SemanticException;

public class MainClass {
	public static void main(String[] args) {
		try {

			// lexer a partir da leitura do arquivo de entrada
			GrammarExpressionLexer lexer = new GrammarExpressionLexer(CharStreams.fromFileName("input.duroc"));

			// TokenStream (o fluxo de tokens) a partir do lexer
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);

			// Parser a partir do tokenStream
			GrammarExpressionParser parser = new GrammarExpressionParser(tokenStream);

			System.out.println("Starting file parsing...");
			parser.prog();
			System.out.println("Compilation Success!");
			System.out.println("------- TOKENS --------------");
			parser.showTokens();
			System.out.println("------- COMMANDS --------------");
			parser.exibeComandos();
			System.out.println("------- TARGET --------------");
			parser.generateCode();
		} catch (SemanticException ex) {
			System.err.println("SEMANTIC ERROR - " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
