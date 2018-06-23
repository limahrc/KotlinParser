import java.io.File
import java.io.IOException

open class LexicalAnalyzer internal constructor(inputFileName: String) : Analyzer(inputFileName) {
    protected var readedSymbol: Char = ' '
    var input: String
    private var position = 0
    lateinit var recognizedToken: Constants.Token

    init {
        try {
            input = File(inputFileName).readText()
            readSymbol()
        } catch (e: IOException) {
            throw RuntimeException("Error while reading file " +
                    Constants.DEFAULT_INPUT_FILE_NAME)
        }
    }

    fun readSymbol() {
        readedSymbol = try {
            input[position++]
        } catch (e: IndexOutOfBoundsException) {
            Constants.EOF
        }
    }

    internal fun isSymbolIn(s: String): Boolean {
        return s.contains(readedSymbol)
    }

}
