
fun main(args: Array<String>) {

    val scanner = KotlinLexicalAnalyzer(Constants.DEFAULT_INPUT_FILE_NAME)

    println("\nfound in file: ${scanner.input}\n\n" +
            "Recognized tokens:")

    do {

        scanner.start()
        if (scanner.recognizedToken != Constants.Token.EOF)
            println(scanner.recognizedToken)

    } while (!scanner.endOfFile())

}