package parser
class KotlinSyntacticAnalyzer(inputFileName: String) : SyntacticAnalyzer(inputFileName) {

    fun begin() {
        command()
        recognize(Constants.Token.EOF)
    }

    private fun command() {
        when {
            readedTokenIs(Constants.Token.IF) -> {
                println(currentToken())
                readToken()
                cmdIF()
                //command()
            }
            readedTokenIs(Constants.Token.FOR) -> {
                println(currentToken())
                readToken()
                cmdFOR()
                //command()
            }
            readedTokenIs(Constants.Token.DO) -> {
                println(currentToken())
                readToken()
                cmdDoWhile()
            }
            readedTokenIs(Constants.Token.WHILE) -> {
                println(currentToken())
                readToken()
                cmdWhile()
            }
            readedTokenIs(Constants.Token.VAR) -> {
                readToken()
                assignment()
            }
        }
    }

    private fun block() {
        recognize(Constants.Token.ACH)
        command()
        recognize(Constants.Token.FCH)
    }

    private fun cmdIF() {
        recognize(Constants.Token.AP)
        recognize(Constants.Token.FP)
    }

    private fun cmdFOR() {
        recognize(Constants.Token.AP)
        recognize(Constants.Token.VAR)
        recognize(Constants.Token.IN)
        exp()
        recognize(Constants.Token.FP)
        block()
    }

    private fun cmdDoWhile() {
        block()
        recognize(Constants.Token.WHILE)
        recognize(Constants.Token.AP)
        exp()
        recognize(Constants.Token.FP)
    }

    private fun cmdWhile() {
        recognize(Constants.Token.AP)
        exp()
        recognize(Constants.Token.FP)
        block()
    }

    private fun assignment() {
        recognize(Constants.Token.EQUAL)
        exp()
    }

    private fun exp() {

    }
}