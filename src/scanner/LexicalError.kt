package scanner

class LexicalError(var foundSymbol: Char, var expectedSymbol: String) : RuntimeException() {
    override fun toString(): String {
        return "Lexical error: found '$foundSymbol', expected '$expectedSymbol'"
    }
}