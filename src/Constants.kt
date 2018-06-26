interface Constants {

    enum class Token {
        DO, FOR, IF, IN, WHILE, EQUAL, AP, FP, VAR, ACH, FCH, NUM,
        OP_ARIT, OP_ARIT_SIGNAL, RELATIONAL_OP, LOGIC_BIN_OP, NOT, EOF
    }

    companion object {

        const val DIGITS        = "0123456789"
        const val LETTERS       = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        const val NON_RESERVED_LETTERS       = "abceghjklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        const val SIGNALS       = "+-"
        const val ARITH_OPS     = "*/"
        const val RELATIONALS   = "><"
        const val SEPARATORS    = " \r\n\t"
        const val EOF: Char     = 0.toChar()
        const val DEFAULT_INPUT_FILE_NAME    = "C:\\Users\\limah\\IdeaProjects\\KotlinParser\\src\\input.txt"
    }
}
