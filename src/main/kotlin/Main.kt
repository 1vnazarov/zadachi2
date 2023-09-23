fun main() {
    when (readln()) {
        "1" -> {
            print("Введите количество строк массива: ")
            val rows = readln().toInt()
            print("Введите количество столбцов массива: ")
            val cols = readln().toInt()
            var str = ""
            val arr: Array<Array<Int>> = Array(rows) {
                Array(cols) {
                    readln().toInt()
                }
            }
            for (i in 0..rows - 1) {
                for (j in 0..cols - 1) {
                    print(arr[i][j].toString() + "\t")
                    str += arr[i][j].toString()
                }
                println()
            }
            println("Количество различных цифр в данном массиве: ${str.toSet().size}")
        }
        "2" -> {
            val arr: Array<Array<Int>> = Array(5) {
                Array(5) {
                    readln().toInt()
                }
            }
            var b = true
            for (i in 0..4) {
                for (j in 0..4) {
                    if (arr[i][j] != arr[j][i]) {
                        b = false
                        break
                    }
                }
            }
            if (b == true) println("Массив симметричен")
            else println("Массив не симметричен")
        }
        "4" -> {
            print("Введите размерность первого массива: ")
            val s1 = readln().toInt()
            print("Заполните массив: ")
            val arr1 = IntArray(s1) { readln().toInt() }
            print("Введите размерность второго массива: ")
            val s2 = readln().toInt()
            print("Заполните массив: ")
            val arr2 = IntArray(s2) { readln().toInt() }
            val set1 = arr1.toSet()
            val set2 = arr2.toSet()
            val res = mutableListOf<Int>()
            for (item in set1) {
                if (set2.contains(item)) {
                    val numRepeats = minOf(arr1.count{it == item}, arr2.count{it == item})
                    repeat(numRepeats) {res.add(item)}
                }
            }
            println("Пересечение массивов: " + res)
        }
        "5" -> {
            print("Введите размерность массива: ")
            val s = readln().toInt()
            val arr = Array<String>(s){
                readln()
            }
        }
    }
}