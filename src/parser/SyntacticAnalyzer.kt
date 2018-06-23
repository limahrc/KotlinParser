package parser
import Analyzer
import Constants
import scanner.KotlinLexicalAnalyzer

class SyntacticAnalyzer(inputFileName: String) : Analyzer(inputFileName), Constants {
    private var scanner = KotlinLexicalAnalyzer(inputFileName)

    init {
        readToken()
    }

    fun readToken() {
        scanner.start()
    }

    fun recognize(token: Constants.Token) {

        val expected = mutableListOf<Constants.Token>()

        if (scanner.recognizedToken == token) {
            readToken()
        } else {
            expected.add(token)
            throw SyntaxError(scanner.recognizedToken, expected)
        }
    }

    fun isTokenIn(token: Constants.Token) {
        return token == scanner.recognizedToken
    }
}