package parser
import Constants
import KotlinLexicalAnalyzer

open class SyntacticAnalyzer(inputFileName: String) : Constants {
    var scanner = KotlinLexicalAnalyzer(inputFileName)

    init {
        readToken()
    }

    fun readToken() {
        scanner.start()
    }

    fun recognize(token: Constants.Token) {

        val expected = mutableListOf<Constants.Token>()

        if (scanner.recognizedToken == token) {
            println(scanner.recognizedToken)
            readToken()
        } else {
            expected.add(token)
            throw SyntaxError(scanner.recognizedToken, expected)
        }
    }

    fun readedTokenIs(token: Constants.Token): Boolean {
        return token == scanner.recognizedToken
    }

    fun currentToken(): Constants.Token {
        return scanner.recognizedToken
    }
}