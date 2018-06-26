package parser
import Constants

class SyntaxError(parser: SyntacticAnalyzer, expected: String?) : RuntimeException(), Constants {

    private val RETURN_MSG_EN = "Syntax error: found descript '${parser.scanner.token.lexem}', " +
            "expected '$expected'\n"

    private val RETURN_MSG_BR = "Erro sintático na linha ${parser.scanner.line}," +
            " coluna ${parser.scanner.collumn}: encontrado '${parser.scanner.token.lexem}', " +
            "esperado '$expected'\n"


    override fun toString(): String {
        return RETURN_MSG_BR
    }
}