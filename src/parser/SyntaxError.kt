package parser
import Constants


class SyntaxError(private var foundToken: Constants.Token,
                  private var expectedTokens: MutableList<Constants.Token>)
    : RuntimeException(), Constants
{

    override fun toString(): String {
        return "Syntax error: found token '$expectedTokens', " +
                "expected $foundToken\n"
    }

}