import parser.KotlinSyntacticAnalyzer
import parser.SyntaxError

fun main(args: Array<String>) {

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