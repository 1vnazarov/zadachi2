import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val scanner = Scanner(System.`in`)
    print(
        """
        1: запрашивается размерность двумерного массиве, его заполнение. Выводится двумерный массив и количество различных цифр, встреченных в нем
        2: запрашивается ввод массива 5х5 и выводится, симметричен ли он относительно главной диагонали
        3: запрашивается ключевое слово, выбор, зашифровать или расшифровать задаваемый текст и выводится результат. (Номер символа ключевого слова показывает, на сколько нужно сдвинуться по массиву из символов русского алфавита)
        4: запрашивается размерность и заполнение двух массивов. Выводится пересечение массивов
        5: запрашивается массив слов (признак окончания ввода - пустая строка) и выводятся слова, состоящие из одинаковых букв
        Выберите программу для проверки: """.trimIndent()
    )
    when (readln()) {
        "1" -> {
            print("Введите количество строк массива: ")
            val rows = readln().toInt()
            print("Введите количество столбцов массива: ")
            val cols = readln().toInt()
            var str = ""
            println("Заполните массив через пробел:")
            val arr = Array(rows) {IntArray(cols)}
            for (i in 0 until rows) {
                for (j in 0 until cols) {
                    arr[i][j] = scanner.nextInt()
                    print(arr[i][j].toString() + "\t")
                    str += arr[i][j].toString()
                }
                println()
            }
            println("Количество различных цифр в данном массиве: ${str.toSet().size}")
        }
        "2" -> {
            val arr = Array(5) {IntArray(5)}
            println("Заполните массив 5х5 через пробел:")
            for (i in 0 until 5) {
                for (j in 0 until 5) {
                    arr[i][j] = scanner.nextInt()
                    print(arr[i][j].toString() + "\t")
                }
                println()
            }

            var b = true
            for (i in 0 until 5) {
                for (j in 0 until 5) {
                    if (arr[i][j] != arr[j][i]) {
                        b = false
                        break
                    }
                }
            }
            if (b) println("Массив симметричен")
            else println("Массив не симметричен")
        }
        "3" -> {
            val alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toCharArray()
            alphabet.shuffle()
            for (i in 0 ..32) println("[$i] = ${alphabet[i]}")
            fun cipher(message:String, keyword:String):String {
                val msg = message.lowercase()
                var res = ""
                var i = 0
                var j = 0
                while (j < msg.length) {
                    res += alphabet[(alphabet.indexOf(msg[j]) + alphabet.indexOf(keyword[i])) % (alphabet.size)]
                    j++
                    i = (i + 1) % keyword.length
                }
                return res
            }
            fun decipher(message:String, keyword:String):String {
                val msg = message.lowercase()
                var res = ""
                var i = 0
                var j = 0
                while (j < msg.length) {
                    res += alphabet[(alphabet.indexOf(msg[j]) - alphabet.indexOf(keyword[i]) + alphabet.size) % alphabet.size]
                    j++
                    i = (i + 1) % keyword.length
                }
                return res
            }
            print("Введите ключевое слово: ")
            val keyword = readln()
            print("1 - зашифровать\n2 - расшифровать\nВыберите функцию: ")
            when (readln()) {
                "1" -> {
                    print("Введите слово, которое хотите зашифровать: ")
                    val cip = cipher(readln(), keyword)
                    println("Зашифровано как: $cip\nРасшифровка: ${decipher(cip, keyword)}")
                }
                "2" -> {
                    print("Введите шифр: ")
                    println("Расшифровано как: ${decipher(readln(), keyword)}")
                }
            }
        }
        "4" -> {
            print("Введите размерность первого массива: ")
            val s1 = readln().toInt()
            print("Заполните массив через пробел: ")
            val arr1 = IntArray(s1)
            for (i in 0 until s1) arr1[i] = scanner.nextInt()
            print("Введите размерность второго массива: ")
            val s2 = readln().toInt()
            print("Заполните массив через пробел: ")
            val arr2 = IntArray(s2)
            for (i in 0 until s2) arr2[i] = scanner.nextInt()
            val set1 = arr1.toSet()
            val set2 = arr2.toSet()
            val res = mutableListOf<Int>()
            for (item in set1) {
                if (set2.contains(item)) {
                    val numRepeats = minOf(arr1.count{it == item}, arr2.count{it == item})
                    repeat(numRepeats) {res.add(item)}
                }
            }
            println("Пересечение массивов: $res")
        }
        "5" -> {
            fun groupWords(words: List<String>): List<List<String>> {
                val groupedWords = mutableMapOf<String, MutableList<String>>()
                for (word in words) {
                    val key = word.toCharArray().sorted().joinToString(",")
                    if (!groupedWords.containsKey(key)) {
                        groupedWords[key] = mutableListOf()
                    }
                    groupedWords[key]?.add(word)
                }
                return groupedWords.values.toList()
            }
            val words: ArrayList<String> = ArrayList()
            //val words = listOf("eat", "tea", "tan", "ate", "nat", "bat")
            println("Введите массив слов. Чтобы окончить ввод, введите пустую строку")
            while (true) {
                val enteredString = readln()
                if (enteredString == "") break
                words.add(enteredString)
            }
            val groupedWords = groupWords(words)
            println("Слова, которые состоят из одинаковых букв:")
            for (group in groupedWords) {
                println(group.joinToString(", "))
            }
        }
    }
}