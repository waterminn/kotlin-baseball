package baseball

fun main() {
    playBaseball()
}

fun computerNum() : MutableList<Int> {
    val com = mutableListOf<Int>()
    while (com.size < 3) {
        val num = Randoms.pickNumberInRange(1, 9)
        if (!com.contains(num)) com.add(num) else continue
    }
    return com
}

fun playBaseball() {
    print("숫자 야구 게임을 시작합니다.\n")
    computerNum()
}