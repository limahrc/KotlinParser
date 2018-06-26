package scanner

import java.io.File
import java.io.IOException

open class LexicalAnalyzer(inputFileName: String) {
    var readedSymbol: Char = ' '
    var input: String
    var line = 1
    var collumn = 1
    var position = 0
    lateinit var recognizedToken: Constants.Token

    init {
        try {
            input = File(inputFileName).readText()
            readSymbol()
        } catch (e: IOException) {
            throw RuntimeException("Error while reading file " +
                    inputFileName)
        }
    }

    fun readSymbol() {
        try {
            readedSymbol = input[position++]
            if (readedSymbol == '\n')
                line++
            if (!isSymbolIn(Constants.SEPARATORS))
                collumn++
        } catch (e: IndexOutOfBoundsException) {
            readedSymbol = Constants.EOF
        }
    }

    fun isSymbolIn(s: String): Boolean {
        return s.contains(readedSymbol)
    }
}
