package parser
import Constants

class KotlinSyntacticAnalyzer(inputFileName: String) : SyntacticAnalyzer(inputFileName) {

    /**
     * start -> command start | command
     */
    fun begin() {
        command()
    }

    /**
     * command -> while
        | doWhile
        | for
        | if
        | lambda
     * @author Herick Lima
     */
    private fun command() {
        when {
            readedTokenIs(Constants.TokenDescript.IF) -> {
                println(currentToken())
                readNextToken()
                cmdIf()
            }
            readedTokenIs(Constants.TokenDescript.FOR) -> {
                println(currentToken())
                readNextToken()
                cmdFor()
            }
            readedTokenIs(Constants.TokenDescript.DO) -> {
                println(currentToken())
                readNextToken()
                cmdDoWhile()
            }
            readedTokenIs(Constants.TokenDescript.WHILE) -> {
                println(currentToken())
                readNextToken()
                cmdWhile()
            }
            readedTokenIs(Constants.TokenDescript.VAR) -> {
                println(currentToken())
                readNextToken()
                assignment()
            }
            else -> lambda()
        }

    }

    /**
     * cmdList -> command cmdList | command
     * @author Victor Cezari
     */
    private fun cmdList() {
        val cmds = listOf(Constants.TokenDescript.WHILE, Constants.TokenDescript.IF,
                Constants.TokenDescript.FOR, Constants.TokenDescript.DO)
        //readNextToken()
        if (cmds.contains(currentToken())) {
            command()
            cmdList()
        } else {
            command()
        }
    }

    /**
     * assignment -> <VAR> <EQUAL> exp
     * @author Herick Lima
     */
    private fun assignment() {
        recognize(Constants.TokenDescript.EQUAL)
        exp()
    }

    /**
     * if -> <IF> <AP> exp <FP> block
     * @author Herick Lima
     */
    private fun cmdIf() {
        recognize(Constants.TokenDescript.AP)
        exp()
        recognize(Constants.TokenDescript.FP)
        block()
    }

    /**
     * doWhile -> <DO> block <WHILE> <AP> exp <FP>
     * @author Herick Lima
     */
    private fun cmdDoWhile() {
        block()
        recognize(Constants.TokenDescript.WHILE)
        recognize(Constants.TokenDescript.AP)
        exp()
        recognize(Constants.TokenDescript.FP)
    }

    /**
     * for -> <FOR> <AP> <VAR> <IN> exp <FP> block
     * @author Herick Lima
     */
    private fun cmdFor() {
        recognize(Constants.TokenDescript.AP)
        recognize(Constants.TokenDescript.VAR)
        recognize(Constants.TokenDescript.IN)
        exp()
        recognize(Constants.TokenDescript.FP)
        block()
    }

    /**
     * while -> <WHILE> <AP> exp <FP> block
     * @author Herick Lima
     */
    private fun cmdWhile() {
        recognize(Constants.TokenDescript.AP)
        exp()
        recognize(Constants.TokenDescript.FP)
        block()
    }

    /**
     * block -> <ACH> cmdList <FCH>
     * @author Herick Lima
     */
    private fun block() {
        recognize(Constants.TokenDescript.ACH)
        cmdList()
        cmdList()
        recognize(Constants.TokenDescript.FCH)
    }

    /**
     * number -> <OP-ARIT-SIGNAL> <NUM> | <NUM>
     * @author Herick Lima
     */
    private fun number() {
        if (readedTokenIs(Constants.TokenDescript.OP_ARIT_SIGNAL)) {
            readNextToken()
        }
        recognize(Constants.TokenDescript.NUM)
    }

    /**
     * operator -> <OP-ARIT-SIGNAL>
        | <OP-ARIT>
        | <LOGIC-BIN-OP>
        | <RELATIONAL-OP>
     * @author Victor Cezari
     */
    private fun operator() {
        when {
            readedTokenIs(Constants.TokenDescript.OP_ARIT_SIGNAL) -> {
                recognize(Constants.TokenDescript.OP_ARIT_SIGNAL)
            }
            readedTokenIs(Constants.TokenDescript.OP_ARIT) -> {
                recognize(Constants.TokenDescript.OP_ARIT)
            }
            readedTokenIs(Constants.TokenDescript.LOGIC_BIN_OP) -> {
                recognize(Constants.TokenDescript.LOGIC_BIN_OP)
            }
            readedTokenIs(Constants.TokenDescript.RELATIONAL_OP) -> {
                recognize(Constants.TokenDescript.RELATIONAL_OP)
            }
            else -> throw SyntaxError(this, Constants.ARITH_OPS+Constants.LOGICS+
                    Constants.DIFFERENT+Constants.RELATIONALS+Constants.SIGNALS )
        }
    }

    /**
     * exp -> <AP> exp <FP>
        | number expB
        | <VAR> expB
        | <NOT> exp
     * @author Herick Lima
     */
    private fun exp() {
        when {
            readedTokenIs(Constants.TokenDescript.AP) -> {
                readNextToken()
                exp()
                recognize(Constants.TokenDescript.FP)
            }
            readedTokenIs(Constants.TokenDescript.VAR) -> {
                println(currentToken())
                readNextToken()
                expB()
            }
            readedTokenIs(Constants.TokenDescript.NOT) -> {
                readNextToken()
                exp()
            }
            readedTokenIs(Constants.TokenDescript.OP_ARIT_SIGNAL) || readedTokenIs(Constants.TokenDescript.NUM) -> {
                number()
                expB()
            }
        }
    }

    /**
     * expB-> operator exp expB| lambda
     * @author Victor Cezari
     */
    private fun expB(){
        val ops = listOf (
                Constants.TokenDescript.OP_ARIT,
                Constants.TokenDescript.OP_ARIT_SIGNAL,
                Constants.TokenDescript.LOGIC_BIN_OP,
                Constants.TokenDescript.RELATIONAL_OP
        )
        if (!ops.contains(currentToken())) {
            lambda()
        } else {
            operator()
            exp()
            expB()
        }

    }
}

/**
 * Função que não faz nada
 * @author desconhecido
 */
private fun lambda() {

}