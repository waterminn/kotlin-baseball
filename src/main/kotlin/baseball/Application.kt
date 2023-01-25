package baseball
import camp.nextstep.edu.missionutils.Randoms
import camp.nextstep.edu.missionutils.Console

fun main() {
    playBaseball()
}

// 컴퓨터 숫자 생성
fun computerNum() : MutableList<Int> {
    val com = mutableListOf<Int>()
    while (com.size < 3) {
        val num = Randoms.pickNumberInRange(1, 9)
        if (!com.contains(num)) com.add(num) else continue
    }
    return com
}

// 사용자 숫자 입력받기
fun playerNum(): List<Int> {
    var user: List<Int> = Console.readLine().map { it.toString().toInt() }

    if (!user.isNullOrEmpty() && isValidInput(user)) {
        return user
    } else {
        throw IllegalArgumentException("서로 다른 3자리의 숫자를 입력해주세요. (1-9)")
    }
}

// 서로 다른 세자리 숫자이면 true
fun isValidInput(input: List<Int>): Boolean {
    if (input.size != 3) return false
    else if (input.contains(0)) return false
    for (i in input) {
        val predicate: (Int) -> Boolean = { it == i }
        if (input.count(predicate) > 1) return false
    }
    return true
}

fun playBaseball() {
    print("숫자 야구 게임을 시작합니다.\n")
    print("숫자를 입력해주세요 : ")
    val answer = computerNum()
    var input: List<Int> = listOf()
    try {
        input = playerNum()
    } catch (e: IllegalArgumentException) {
        print(e.message)
    }
}
