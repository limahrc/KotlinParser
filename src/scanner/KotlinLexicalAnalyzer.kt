package scanner

import Constants.Companion.ARITH_OPS
import Constants.Companion.DIGITS
import Constants.Companion.LETTERS
import Constants.Companion.RELATIONALS
import Constants.Companion.SEPARATORS
import Constants.Companion.SIGNALS

class KotlinLexicalAnalyzer(inputFileName: String) : LexicalAnalyzer(inputFileName) {

    val token = Token(StringBuilder(), Constants.TokenDescript.EOF)

    fun start() {
        token.lexem.setLength(0)
        when {

            isSymbolIn(SEPARATORS) -> {
                readSymbol()
                start()
            }

            readedSymbol == 'd' -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                s12()
            }

            readedSymbol == 'f' -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                s5()
            }

            readedSymbol == 'i' -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                s1()
            }

            readedSymbol == 'w' -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                s14()
            }

            readedSymbol == '{' -> {

                token.lexem.append(readedSymbol)
                readSymbol()
                setTokenAs(Constants.TokenDescript.ACH)
            }

            readedSymbol == '}' -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                setTokenAs(Constants.TokenDescript.FCH)
            }

            readedSymbol == '(' -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                setTokenAs(Constants.TokenDescript.AP)
            }

            readedSymbol == ')' -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                setTokenAs(Constants.TokenDescript.FP)
            }

            readedSymbol == '=' -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                s11()
            }

            readedSymbol == '!' -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                s19()
            }

            readedSymbol == '&' -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                s20()
            }

            readedSymbol == '|' -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                s21()
            }

            isSymbolIn(RELATIONALS) -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                s9()
            }

            isSymbolIn(SIGNALS) -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                setTokenAs(Constants.TokenDescript.OP_ARIT_SIGNAL)
            }

            isSymbolIn(ARITH_OPS) -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                setTokenAs(Constants.TokenDescript.OP_ARIT)
            }

            isSymbolIn(Constants.NON_RESERVED_LETTERS) -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                s3()
            }

            isSymbolIn(DIGITS) -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                s8()
            }

            readedSymbol == '.' -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                s8()
            }

            readedSymbol == Constants.EOF -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                s4()
            }

            else -> throw LexicalError(this, DIGITS
                    + LETTERS + SIGNALS + ARITH_OPS + RELATIONALS)
        }
    }

    private fun s1() {
        when (readedSymbol) {
            'f' -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                s2()
            }
            'n' -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                s2_1()
            }
            else -> {
                setTokenAs(Constants.TokenDescript.VAR)
                s3()
            }
        }
    }

    private fun s2() {
        if (isSymbolIn(LETTERS+DIGITS)) {
            readSymbol()
            s3()
        } else {
            setTokenAs(Constants.TokenDescript.IF)
        }
    }

    private fun s2_1(){
        if (isSymbolIn(LETTERS+DIGITS)){
            readSymbol()
            s3()
        } else {
            setTokenAs(Constants.TokenDescript.IN)
        }
    }

    private fun s3() {
        if (isSymbolIn(LETTERS+DIGITS)) {
            token.lexem.append(readedSymbol)
            readSymbol()
            s3()
        }else{
            setTokenAs(Constants.TokenDescript.VAR)
        }
    }

    private fun s4() {
        setTokenAs(Constants.TokenDescript.EOF)
    }

    private fun s5() {
        if (readedSymbol == 'o') {
            token.lexem.append(readedSymbol)
            readSymbol()
            s6()
        } else {
            setTokenAs(Constants.TokenDescript.VAR)
            s3()
        }
    }

    private fun s6() {
        if (readedSymbol == 'r') {
            token.lexem.append(readedSymbol)
            readSymbol()
            s7()
        } else {
            setTokenAs(Constants.TokenDescript.VAR)
            s3()
        }
    }


    private fun s7() {
        if (isSymbolIn(LETTERS + DIGITS)) {
            token.lexem.append(readedSymbol)
            readSymbol()
            s3()
        } else{
            setTokenAs(Constants.TokenDescript.FOR)
        }
    }

    private fun s8() {
        setTokenAs(Constants.TokenDescript.NUM)
        when {
            isSymbolIn(DIGITS) -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                s8()
            }
            readedSymbol == '.' -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                s8_1()
            }
            isSymbolIn(LETTERS) -> throw LexicalError(this, "$DIGITS.")
        }
    }

    private fun s8_1() {
        setTokenAs(Constants.TokenDescript.NUM)
        if (isSymbolIn(DIGITS)){
            token.lexem.append(readedSymbol)
            readSymbol()
            s8_2()
        } else if (!isSymbolIn(DIGITS)){
            throw LexicalError(this, DIGITS)
        }else{
            setTokenAs(Constants.TokenDescript.NUM)
        }
    }

    private fun s8_2() {

        when {
            isSymbolIn(DIGITS) -> {
                token.lexem.append(readedSymbol)
                readSymbol()
                s8_2()
            }
            isSymbolIn(LETTERS) -> throw LexicalError(this, DIGITS)
            else -> {
                setTokenAs(Constants.TokenDescript.NUM)
            }
        }
    }

    private fun s9() {

        if (readedSymbol == '=') {
            token.lexem.append(readedSymbol)
            readSymbol()
            s10()
        }else{
            setTokenAs(Constants.TokenDescript.RELATIONAL_OP)
        }
    }

    private fun s10() {
        setTokenAs(Constants.TokenDescript.RELATIONAL_OP)
    }

    private fun s11() {

        if (readedSymbol == '=') {
            token.lexem.append(readedSymbol)
            readSymbol()
            s10()
        }else{
            setTokenAs(Constants.TokenDescript.EQUAL)
        }
    }

    private fun s12() {

        if (readedSymbol == 'o') {
            token.lexem.append(readedSymbol)
            readSymbol()
            s13()
        } else {
            setTokenAs(Constants.TokenDescript.VAR)
            s3()
        }
    }

    private fun s13() {
        if (isSymbolIn(LETTERS+DIGITS)) {
            readSymbol()
            s3()
        } else {
            setTokenAs(Constants.TokenDescript.DO)

        }
    }

    private fun s14() {
        setTokenAs(Constants.TokenDescript.VAR)
        if (readedSymbol == 'h') {
            token.lexem.append(readedSymbol)
            readSymbol()
            s15()
        } else s3()
    }

    private fun s15() {
        setTokenAs(Constants.TokenDescript.VAR)
        if (readedSymbol == 'i') {
            token.lexem.append(readedSymbol)
            readSymbol()
            s16()
        } else s3()
    }

    private fun s16() {
        setTokenAs(Constants.TokenDescript.VAR)
        if (readedSymbol == 'l') {
            token.lexem.append(readedSymbol)
            readSymbol()
            s17()
        } else s3()
    }

    private fun s17() {
        setTokenAs(Constants.TokenDescript.VAR)
        if (readedSymbol == 'e') {
            token.lexem.append(readedSymbol)
            readSymbol()
            s18()
        } else s3()
    }

    private fun s18() {
        if (isSymbolIn(LETTERS+DIGITS)) {
            token.lexem.append(readedSymbol)
            readSymbol()
            s3()
        } else{
            setTokenAs(Constants.TokenDescript.WHILE)
        }
    }

    private fun s19() {
        if (readedSymbol == '=') {
            token.lexem.append(readedSymbol)
            readSymbol()
            s10()
        }else{
            setTokenAs(Constants.TokenDescript.NOT)
        }
    }

    private fun s20() {
        if(readedSymbol == '&') {
            token.lexem.append(readedSymbol)
            readSymbol()
            s22()
        } else throw LexicalError(this, "&")
    }

    private fun s21() {
        if(readedSymbol == '|') {
            token.lexem.append(readedSymbol)
            readSymbol()
            s22()
        } else throw LexicalError(this, "|")
    }

    private fun s22() {
        setTokenAs(Constants.TokenDescript.LOGIC_BIN_OP)
    }

    private fun setTokenAs(descript: Constants.TokenDescript) {
        token.descript = descript
    }

}
