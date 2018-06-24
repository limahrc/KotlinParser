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
            }
            readedTokenIs(Constants.Token.FOR) -> {
                println(currentToken())
                readToken()
                cmdFOR()
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
        exp()
        recognize(Constants.Token.FP)
        block()
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
        println(currentToken())
        when {
            readedTokenIs(Constants.Token.AP) -> { // exp -> AP exp FP
                readToken()
                exp()
                recognize(Constants.Token.FP)
            }
            readedTokenIs(Constants.Token.VAR) -> { // exp -> VAR expB
                readToken()
                expB()
            }
            readedTokenIs(Constants.Token.NOT) -> { // exp -> NOT expB
                readToken()
                exp()
            }
            readedTokenIs(Constants.Token.OP_ARIT_SIGNAL) || readedTokenIs(Constants.Token.NUM) -> {
                number()
                expB()
            }
        }
    }

    private fun expB() {
        val notAllowed = listOf(Constants.Token.FP, Constants.Token.FCH)
        if (!notAllowed.contains(currentToken())) {
            operator()
            exp()
        } else {
            lambda()
        }
    }

    private fun number() {
        if (readedTokenIs(Constants.Token.OP_ARIT_SIGNAL)) {
            readToken()
        }
        recognize(Constants.Token.NUM)
    }

    private fun operator() {
        val ops = listOf (
                Constants.Token.OP_ARIT,
                Constants.Token.OP_ARIT_SIGNAL,
                Constants.Token.LOGIC_BIN_OP,
                Constants.Token.RELATIONAL_OP
        )
        if (!ops.contains(currentToken())) {
            val expected = mutableListOf<Constants.Token>()
            expected.addAll(ops)
            throw SyntaxError(currentToken(), expected)
        } else {
            println(currentToken())
            readToken()
        }
    }
}

private fun lambda() {
    //does nothing.
}