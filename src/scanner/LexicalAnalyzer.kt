package scanner

import java.io.File
import java.io.IOException

open class LexicalAnalyzer(inputFileName: String) {
    var readedSymbol: Char = ' '
    var input: String
    var line = 1
    var collumn = 1
    var position = 0

    /**
     * Inicialização do construtor da classe.
     * Abre o arquivo armazenando seu conteúdo na string 'input'.
     * @obs Lança exceção caso falhe em ler o arquivo
     * @author Herick Lima
     */
    init {
        try {
            input = File(inputFileName).readText()
            readSymbol()
        } catch (e: IOException) {
            throw RuntimeException("Erro ao ler arquivo $inputFileName")
        }
    }

    /**
     * Efetua a leitura dos caracteres do arquivo.
     * @author Victor Cezari
     */
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

    /**
     * Verifica se o simbolo lido está contido no conjunto de simbolos passados via parametro
     * @author Victor Cezari
     */
    fun isSymbolIn(s: String): Boolean {
        return s.contains(readedSymbol)
    }
}
