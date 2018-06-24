import Constants.Companion.ARITH_OPS
import Constants.Companion.DIGITS
import Constants.Companion.LETTERS
import Constants.Companion.RELATIONALS
import Constants.Companion.SEPARATORS
import Constants.Companion.SIGNALS

class KotlinLexicalAnalyzer(inputFileName: String) : LexicalAnalyzer(inputFileName) {

    fun start() {
        when {

            isSymbolIn(SEPARATORS) -> {
                readSymbol()
                start()
            }

            readedSymbol == 'd' -> {
                readSymbol()
                s12()
            }

            readedSymbol == 'f' -> {
                readSymbol()
                s5()
            }

            readedSymbol == 'i' -> {
                readSymbol()
                s1()
            }

            readedSymbol == 'w' -> {
                readSymbol()
                s14()
            }

            readedSymbol == '{' -> {
                readSymbol()
                setTokenAs(Constants.Token.ACH)
            }

            readedSymbol == '}' -> {
                readSymbol()
                setTokenAs(Constants.Token.FCH)
            }

            readedSymbol == '(' -> {
                readSymbol()
                setTokenAs(Constants.Token.AP)
            }

            readedSymbol == ')' -> {
                readSymbol()
                setTokenAs(Constants.Token.FP)
            }

            readedSymbol == '=' -> {
                readSymbol()
                s11()
            }

            readedSymbol == '!' -> {
                readSymbol()
                s19()
            }

            readedSymbol == '&' -> {
                readSymbol()
                s20()
            }

            readedSymbol == '|' -> {
                readSymbol()
                s21()
            }

            isSymbolIn(RELATIONALS) -> {
                readSymbol()
                s9()
            }

            isSymbolIn(SIGNALS) -> {
                readSymbol()
                setTokenAs(Constants.Token.OP_ARIT_SIGNAL)
            }

            isSymbolIn(ARITH_OPS) -> {
                readSymbol()
                setTokenAs(Constants.Token.OP_ARIT)
            }

            isSymbolIn(Constants.NON_RESERVED_LETTERS) -> {
                readSymbol()
                s3()
            }

            isSymbolIn(DIGITS) -> {
                readSymbol()
                s8()
            }

            readedSymbol == '.' -> {
                readSymbol()
                s8()
            }

            readedSymbol == Constants.EOF -> {
                readSymbol()
                s4()
            }

            else -> LexicalError(readedSymbol, DIGITS
                    + LETTERS + SIGNALS + ARITH_OPS + RELATIONALS)
        }
    }

    private fun s1() {
        setTokenAs(Constants.Token.VAR)
        if (readedSymbol == 'f') {
            readSymbol()
            s2()
        } else if(readedSymbol == 'n'){
            readSymbol()
            s2_1()
        }
        else s3()
    }

    private fun s2() {
        if (isSymbolIn(LETTERS+DIGITS)) {
            readSymbol()
            s3()
        }else setTokenAs(Constants.Token.IF)
    }

    private fun s2_1(){
        if (isSymbolIn(LETTERS+DIGITS)){
            readSymbol()
            s3()
        }else setTokenAs(Constants.Token.IN)
    }

    private fun s3() {
        setTokenAs(Constants.Token.VAR)
        if (isSymbolIn(LETTERS+DIGITS)) {
            readSymbol()
            s3()
        }
    }

    private fun s4() {
        setTokenAs(Constants.Token.EOF)
    }

    private fun s5() {
        setTokenAs(Constants.Token.VAR)
        if (readedSymbol == 'o') {
            readSymbol()
            s6()
        } else s3()
    }

    private fun s6() {
        if (readedSymbol == 'r') {
            readSymbol()
            s7()
        } else s3()
    }


    private fun s7() {
        if (isSymbolIn(LETTERS+DIGITS)) {
            readSymbol()
            s3()
        }else setTokenAs(Constants.Token.FOR)
    }

    private fun s8() {
        setTokenAs(Constants.Token.NUM)
        if (isSymbolIn(DIGITS)) {
            readSymbol()
            s8()
        }else if (readedSymbol == '.'){
            readSymbol()
            s8_1()
        }else if (isSymbolIn(LETTERS)){
            throw LexicalError(readedSymbol, DIGITS+'.')
        }
    }

    private fun s8_1() {
        setTokenAs(Constants.Token.NUM)
        if (isSymbolIn(DIGITS)){
            readSymbol()
            s8_2()
        }else if (!isSymbolIn(DIGITS)){
                throw LexicalError(readedSymbol, DIGITS)
        }
    }

    private fun s8_2() {
        setTokenAs(Constants.Token.NUM)
        if (isSymbolIn(DIGITS)) {
            readSymbol()
            s8_2()
        }else if(isSymbolIn(LETTERS)){
            throw LexicalError(readedSymbol, DIGITS)
        }
    }

    private fun s9() {
        setTokenAs(Constants.Token.RELATIONAL_OP)
        if (readedSymbol == '=') {
            readSymbol()
            s10()
        }
    }

    private fun s10() {
        setTokenAs(Constants.Token.RELATIONAL_OP)
    }

    private fun s11() {
        setTokenAs(Constants.Token.EQUAL)
        if (readedSymbol == '=') {
            readSymbol()
            s10()
        }
    }

    private fun s12() {
        setTokenAs(Constants.Token.VAR)
        if (readedSymbol == 'o') {
            readSymbol()
            s13()
        }
    }

    private fun s13() {
        if (isSymbolIn(LETTERS+DIGITS)) {
            readSymbol()
            s3()
        }else setTokenAs(Constants.Token.DO)
    }

    private fun s14() {
        setTokenAs(Constants.Token.VAR)
        if (readedSymbol == 'h') {
            readSymbol()
            s15()
        }
    }

    private fun s15() {
        setTokenAs(Constants.Token.VAR)
        if (readedSymbol == 'i') {
            readSymbol()
            s16()
        }
    }

    private fun s16() {
        setTokenAs(Constants.Token.VAR)
        if (readedSymbol == 'l') {
            readSymbol()
            s17()
        }
    }
    private fun s17() {
        setTokenAs(Constants.Token.VAR)
        if (readedSymbol == 'e') {
            readSymbol()
            s18()
        }
    }

    private fun s18() {
        if (isSymbolIn(LETTERS+DIGITS)) {
            readSymbol()
            s3()
        }else setTokenAs(Constants.Token.WHILE)
    }

    private fun s19() {
        setTokenAs(Constants.Token.NOT)
        if (readedSymbol == '=') {
            readSymbol()
            s10()
        }
    }

    private fun s20() {
        if(readedSymbol == '&') {
            readSymbol()
            s22()
        }else throw LexicalError(readedSymbol, "&")//erro lexico
    }

    private fun s21() {
        if(readedSymbol == '|') {
            readSymbol()
            s22()
        }else throw LexicalError(readedSymbol, "|")//erro lexico
    }

    private fun s22() {
        setTokenAs(Constants.Token.LOGIC_BIN_OP)
    }

    fun endOfFile(): Boolean {
        return recognizedToken == Constants.Token.EOF
    }

    private fun setTokenAs(token: Constants.Token) {
        recognizedToken = token
    }

}
