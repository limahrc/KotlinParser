package parser
import Constants
import scanner.KotlinLexicalAnalyzer

open class SyntacticAnalyzer(inputFileName: String) : Constants {
    var scanner = KotlinLexicalAnalyzer(inputFileName)

    init {
        readToken()
    }

    fun readToken() {
        scanner.start()
    }

    fun recognize(token: Constants.Token): Boolean {
        val expected = mutableListOf<Constants.Token>()
        val isToken: Boolean
        if (scanner.recognizedToken == token) {
            println(scanner.recognizedToken)
            readToken()
            isToken = true
        } else {
            expected.add(token)
            throw SyntaxError(scanner.recognizedToken, expected)
        }
        return isToken
    }

    fun readedTokenIs(token: Constants.Token): Boolean {
        return token == scanner.recognizedToken
    }

    fun currentToken(): Constants.Token {
        return scanner.recognizedToken
    }
}