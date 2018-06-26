import parser.KotlinSyntacticAnalyzer
import parser.SyntaxError
import scanner.KotlinLexicalAnalyzer
import scanner.LexicalError

fun lexicalTest() {
    try {

        val scanner = KotlinLexicalAnalyzer(Constants.DEFAULT_INPUT_FILE_NAME)
        do {
            scanner.start()
            println("${scanner.token.descript}\t: ${scanner.token.lexem}")
        } while (scanner.token.descript != Constants.TokenDescript.EOF)
    } catch (e: LexicalError) {
        print(e.toString())
    }
}

fun parserTest() {
    try {

        val parser = KotlinSyntacticAnalyzer(Constants.DEFAULT_INPUT_FILE_NAME)
        println("\nfound in file:\n${parser.scanner.input}")
        parser.begin()
        println("\nfile sucessfully analyzed")

    } catch (e: LexicalError) {
        print(e.toString())
    } catch (e: SyntaxError) {
        print(e.toString())
    }
}



fun main(args: Array<String>) {
    parserTest()
}