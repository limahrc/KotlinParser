package parser
import Constants


class SyntaxError(private val parser: SyntacticAnalyzer,
                  private val expectedTokens: MutableList<Constants.Token>)
    : RuntimeException(), Constants {

    var hash = hashMapOf<Constants.Token, String>()

    override fun toString(): String {
        return "Syntax error: found token '${parser.currentSymbol()}', " +
                "expected $expectedTokens\n"
    }

}