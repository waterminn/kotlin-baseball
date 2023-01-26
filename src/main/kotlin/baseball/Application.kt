package baseball
import camp.nextstep.edu.missionutils.Randoms
import camp.nextstep.edu.missionutils.Console

// 게임 종료 여부
var isOver: Boolean = false
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
    print("\n숫자를 입력해주세요 : ")
    var user: List<Int> = Console.readLine().map { it.toString().toInt() }
//    var user: List<Int> = readLine()!!.map { it.toString().toInt() }

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

// 비교 결과 출력
fun compareNumber(user: List<Int>, com: MutableList<Int>) {
    val ball = countBall(user, com)
    val strike = countStrike(user, com)

    // 낫싱
    if (ball == 0 && strike == 0) print("낫싱")
    else {
        // 볼
        when(ball) {
            0 -> print("")
            else -> print("${ball}볼")
        }
        // 스트라이크
        when(strike) {
            0 -> print("")
            3 -> {
                print("3스트라이크\n")
                print("3개의 숫자를 모두 맞히셨습니다! 게임 종료")
                isOver = true
//                finishGame()
            }
            else -> {
                if (ball != 0) print(" ")
                print("${strike}스트라이크")
            }
        }
    }
}

// 게임 종료 로직
fun finishGame() {

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
    print("숫자 야구 게임을 시작합니다.")
    val answer = computerNum()
    var input: List<Int> = listOf()
    try {
        while (!isOver) {
            input = playerNum()
            compareNumber(input, answer)
        }
    } catch (e: IllegalArgumentException) {
        print(e.message)
    }
}
