# Compilador para Java
Compilador desenvolvido para disciplina Compiladores. <br>
Converte de linguagem fonte inventada para Java.

## Desenvolvido por:
Fernanda Sayuri <br>
Lucas Prado <br>
Leopoldo Kenji <br>
João Melques <br>
Victor Fachini


## Compilação da gramática

Na raíz do projeto execute: 

```cmd
java -cp lib/antlr-4.13.0-complete.jar;. org.antlr.v4.Tool GrammarExpression.g4 -o ./src/compiler/core -package compiler.core
```
