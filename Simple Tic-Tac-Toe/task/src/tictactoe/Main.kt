package tictactoe

fun main() {
    //println("Enter cells:")
    var input = "_________"
    val fst = input.substring(0,3).toCharArray()
    val snd = input.substring(3,6).toCharArray()
    val thd = input.substring(6,9).toCharArray()
    val field = mutableListOf(fst, snd, thd)

    println("---------")
    println("| ${fst.joinToString(" ")} |")
    println("| ${snd.joinToString(" ")} |")
    println("| ${thd.joinToString(" ")} |")
    println("---------")




    while (checkEnd(fst.joinToString("")+snd.joinToString("")+thd.joinToString("")))
    {
        input = fst.joinToString("")+snd.joinToString("")+thd.joinToString("")
        println("Enter the coordinates:")
        val xCount = input.filter { e -> e == 'X' }.length
        val oCount = input.filter { e -> e == 'O' }.length
        val turn = if (xCount == oCount) 'X' else 'O'
        val coorInp = readLine()!!
        //if(coorInp.matches(Regex("[1-3][ ][1-3]]")))
        if(Regex("""[\d][\s][\d]""").matches(coorInp)){
            val row = coorInp.split(" ")[0].toInt() - 1
            val col = coorInp.split(" ")[1].toInt() - 1
            if (row > 2 || col > 2) {
                println("Coordinates should be from 1 to 3!")
            } else {
                if (field[row][col] == '_') {
                    field[row][col] = turn
                    println("---------")
                    println("| ${fst.joinToString(" ")} |")
                    println("| ${snd.joinToString(" ")} |")
                    println("| ${thd.joinToString(" ")} |")
                    println("---------")
                } else {
                    println("This cell is occupied! Choose another one!")
                }
            }
        } else {
            println("You should enter numbers!")
        }
    }


}

fun checkEnd(input: String): Boolean {
    val fst = input.substring(0,3).toCharArray()
    val snd = input.substring(3,6).toCharArray()
    val thd = input.substring(6,9).toCharArray()
    val field = mutableListOf(fst, snd, thd)

    val xCount = input.filter { e -> e == 'X' }.length
    val oCount = input.filter { e -> e == 'O' }.length
    val xWin =  fst.all { e -> e == 'X' } ||
            snd.all { e -> e == 'X' } ||
            thd.all { e -> e == 'X' } ||
            fst[0] == 'X' && snd[0] == 'X' && thd[0] == 'X' ||
            fst[1] == 'X' && snd[1] == 'X' && thd[1] == 'X' ||
            fst[2] == 'X' && snd[2] == 'X' && thd[2] == 'X' ||
            fst[0] == 'X' && snd[1] == 'X' && thd[2] == 'X' ||
            fst[2] == 'X' && snd[1] == 'X' && thd[0] == 'X'
    val oWin =  fst.all { e -> e == 'O' } ||
            snd.all { e -> e == 'O' } ||
            thd.all { e -> e == 'O' } ||
            fst[0] == 'O' && snd[0] == 'O' && thd[0] == 'O' ||
            fst[1] == 'O' && snd[1] == 'O' && thd[1] == 'O' ||
            fst[2] == 'O' && snd[2] == 'O' && thd[2] == 'O' ||
            fst[0] == 'O' && snd[1] == 'O' && thd[2] == 'O' ||
            fst[2] == 'O' && snd[1] == 'O' && thd[0] == 'O'

    var result = "Game not finished"
    when {
        (xWin && oWin) || oCount > xCount + 1 || oCount < xCount - 1 -> result = "Impossible"
        xWin -> result = "X wins"
        oWin -> result = "O wins"
        xCount + oCount == 9 -> result = "Draw"
    }
    if (result != "Game not finished") {
        println(result)
        return  false
    }
    return true
}