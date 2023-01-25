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

// 스트라이크 수 반환
fun countStrike(user: List<Int>, com: MutableList<Int>): Int {
    var strike: Int = 0
    for (i in 0..2) {
        if (user[i] == com[i]) strike++
    }
    return strike
}

// 볼 수 반환
fun countBall(user: List<Int>, com: MutableList<Int>): Int {
    var ball: Int = 0
    for (i in user) {
        if (com.contains(i) && (user.indexOf(i) != com.indexOf(i))) ball++
    }
    return ball
}

// 게임 실행
fun playBaseball() {
    print("숫자 야구 게임을 시작합니다.\n")
    print("숫자를 입력해주세요 : ")
    val answer = computerNum()
    var input: List<Int> = listOf()
    try {
        input = playerNum()
        print("com: $answer, user: $input\n")
        print("strike: ${countStrike(input, answer)}, ball: ${countBall(input, answer)}")
    } catch (e: IllegalArgumentException) {
        print(e.message)
    }
}
