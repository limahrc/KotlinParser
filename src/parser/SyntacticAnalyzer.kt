package parser
import Constants
import scanner.KotlinLexicalAnalyzer

open class SyntacticAnalyzer(inputFileName: String) : Constants {

    var scanner = KotlinLexicalAnalyzer(inputFileName)
    private val hash = hashMapOf<Constants.TokenDescript, String>()

    init {
        readNextToken()
        hash[Constants.TokenDescript.ACH] = "{"
        hash[Constants.TokenDescript.DO] = "do"
        hash[Constants.TokenDescript.FOR] = "for"
        hash[Constants.TokenDescript.IF] = "if"
        hash[Constants.TokenDescript.IN] = "in"
        hash[Constants.TokenDescript.WHILE] = "while"
        hash[Constants.TokenDescript.EQUAL] = "="
        hash[Constants.TokenDescript.AP] = "("
        hash[Constants.TokenDescript.FP] = ")"
        hash[Constants.TokenDescript.FCH] = "}"
        hash[Constants.TokenDescript.VAR] = Constants.LETTERS
        hash[Constants.TokenDescript.OP_ARIT] = Constants.ARITH_OPS
        hash[Constants.TokenDescript.OP_ARIT_SIGNAL] = Constants.SIGNALS
        hash[Constants.TokenDescript.RELATIONAL_OP] = Constants.RELATIONALS+Constants.DIFFERENT
        hash[Constants.TokenDescript.LOGIC_BIN_OP] = Constants.LOGICS
        hash[Constants.TokenDescript.NOT] = "!"
    }

    fun readNextToken() {
        scanner.start()
    }

    fun recognize(descript: Constants.TokenDescript): Boolean {
        val expected = mutableListOf<Constants.TokenDescript>()
        val isToken: Boolean
        if (scanner.token.descript == descript) {
            println(scanner.token.descript)
            readNextToken()
            isToken = true
        } else {
            expected.add(descript)
            throw SyntaxError(this, hash[descript])
        }
        return isToken
    }

    fun readedTokenIs(tokenDescript: Constants.TokenDescript): Boolean {
        return tokenDescript == scanner.token.descript
    }

    fun currentToken(): Constants.TokenDescript {
        return scanner.token.descript
    }

    fun currentSymbol(): Char {
        return scanner.readedSymbol
    }

}