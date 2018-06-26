package scanner

class LexicalError(var scanner: LexicalAnalyzer , var expectedSymbol: String) : RuntimeException() {

    private val RETURN_MSG_EN = "Lexical error in line ${scanner.line}," +
            " collum ${scanner.collumn}: " +
            "found '${scanner.readedSymbol}', expected '$expectedSymbol'"

    private val RETURN_MSG_BR = "Erro Léxico na linha ${scanner.line}," +
            " coluna ${scanner.collumn}: " +
            "encontrado '${scanner.readedSymbol}', esperado '$expectedSymbol'"

    override fun toString(): String {
        return RETURN_MSG_BR
    }
}