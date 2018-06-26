package parser
import Constants
import scanner.KotlinLexicalAnalyzer

open class SyntacticAnalyzer(inputFileName: String) : Constants {

    var scanner = KotlinLexicalAnalyzer(inputFileName)

    init {
        readNextToken()
    }

    fun readNextToken() {
        scanner.start()
    }

    fun recognize(token: Constants.Token): Boolean {
        val expected = mutableListOf<Constants.Token>()
        val isToken: Boolean
        if (scanner.recognizedToken == token) {
            println(scanner.recognizedToken)
            readNextToken()
            isToken = true
        } else {
            expected.add(token)
            throw SyntaxError(this, expected)
        }
        return isToken
    }

    fun readedTokenIs(token: Constants.Token): Boolean {
        return token == scanner.recognizedToken
    }

    fun currentToken(): Constants.Token {
        return scanner.recognizedToken
    }

    fun currentSymbol(): Char {
        return scanner.readedSymbol
    }

}