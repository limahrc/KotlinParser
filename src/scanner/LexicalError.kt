package scanner

class LexicalError(var scanner: LexicalAnalyzer , var expectedSymbol: String) : RuntimeException() {
    override fun toString(): String {
        return "Lexical error in line ${scanner.line}, collum ${scanner.collumn}: " +
                "found '${scanner.readedSymbol}', expected '$expectedSymbol'"
    }
}