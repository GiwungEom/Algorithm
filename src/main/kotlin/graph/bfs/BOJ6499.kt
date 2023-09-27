package graph.bfs

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue
import java.util.Stack
import java.util.StringTokenizer

/**
 * 문제
 * 이번 가을학기에 '문제 해결' 강의를 신청한 학생들은 텀 프로젝트를 수행해야 한다. 프로젝트 팀원 수에는 제한이 없다.
 * 심지어 모든 학생들이 동일한 팀의 팀원인 경우와 같이 한 팀만 있을 수도 있다. 프로젝트 팀을 구성하기 위해, 모든 학생들은 프로젝트를 함께하고 싶은 학생을 선택해야 한다.
 * (단, 단 한 명만 선택할 수 있다.) 혼자 하고 싶어하는 학생은 자기 자신을 선택하는 것도 가능하다.
 *
 * 학생들이(s1, s2, ..., sr)이라 할 때, r=1이고 s1이 s1을 선택하는 경우나, s1이 s2를 선택하고, s2가 s3를 선택하고,..., sr-1이 sr을 선택하고,
 * sr이 s1을 선택하는 경우에만 한 팀이 될 수 있다.
 *
 * 예를 들어, 한 반에 7명의 학생이 있다고 하자. 학생들을 1번부터 7번으로 표현할 때, 선택의 결과는 다음과 같다.
 *
 * 1	2	3	4	5	6	7
 * 3	1	3	7	3	4	6
 * 위의 결과를 통해 (3)과 (4, 7, 6)이 팀을 이룰 수 있다. 1, 2, 5는 어느 팀에도 속하지 않는다.
 *
 * 주어진 선택의 결과를 보고 어느 프로젝트 팀에도 속하지 않는 학생들의 수를 계산하는 프로그램을 작성하라.
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스의 첫 줄에는 학생의 수가 정수 n (2 ≤ n ≤ 100,000)으로 주어진다.
 * 각 테스트 케이스의 둘째 줄에는 선택된 학생들의 번호가 주어진다. (모든 학생들은 1부터 n까지 번호가 부여된다.)
 *
 * 출력
 * 각 테스트 케이스마다 한 줄에 출력하고, 각 줄에는 프로젝트 팀에 속하지 못한 학생들의 수를 나타내면 된다.
 *
 * 예제 입력 1
 * 2
 * 7
 * 3 1 3 7 3 4 6
 * 8
 * 1 2 3 4 5 6 7 8
 * 예제 출력 1
 * 3
 * 0
 */

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))

    val t = bufferedReader.readLine().toInt()

    repeat(t) {
        val n = bufferedReader.readLine().toInt()
        val queue: Queue<Int> = LinkedList()
        val wishMate = IntArray(size = n + 1)   // 1부터 시작. 0은 사용하지 않음.
        var teamCount = 0
        val visit = IntArray(size = n + 1) { -1 }
        val roundVisit = IntArray(size = n + 1) { -1 }  // 해당 라운드에 팀원 포함 되는지 저장/검색

        val tokenizer = StringTokenizer(bufferedReader.readLine())
        repeat(n) { index ->
            wishMate[index + 1] = tokenizer.nextToken().toInt()
        }

        // n 제곱 형태는 시간 초과. 최대 n 은 10만.
        // 한번 방문 후에는 재탐색 진행 되지 말아야 한다.
        // 한번 방문 시에 Circle 여부 판단.
        for (i in 1..wishMate.lastIndex) {
            if (visit[i] != -1) continue

            // Stack 의 Contain 에서 시간 소요 될 수 있음
            // 배열 메모리로 접근성 향상 시켜서 진행 해 보기
            val members: Stack<Int> = Stack()

            queue.add(i)
            members.push(i)
            visit[i] = 1
            roundVisit[i] = i

            while (queue.isNotEmpty()) {
                val curMate = queue.remove()
                val nextMate = wishMate[curMate]

//                if (members.contains(nextMate)) { // --> stack 의 contains 는 n 번 순차탐색
                if (roundVisit[nextMate] == i) { // --> 메모리 추가 후 배열 index 로 찾기
                    while (members.isNotEmpty()) {
                        val mate = members.pop()
                        teamCount++
                        if (mate == nextMate) break
                    }
                    break
                }

                if (visit[nextMate] != -1) continue

                queue.add(nextMate)
                members.push(nextMate)
                visit[nextMate] = 1
                roundVisit[nextMate] = i
            }
        }
        // 전체 배열에서 팀이 아닌 팀원을 고르는 방식에서 (n 번 순차탐색)
        // 변수 카운팅 방식으로 변경
        bufferedWriter.append((n - teamCount).toString()).appendLine()
//        bufferedWriter.append(team.count { it == -1 }).toString()).appendLine()
    }
    bufferedWriter.flush()
    bufferedWriter.close()
    bufferedReader.close()
}





















