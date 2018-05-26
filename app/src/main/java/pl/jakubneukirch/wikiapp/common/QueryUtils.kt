package pl.jakubneukirch.wikiapp.common

fun getMultipleParameters(list: List<Int>): String {
    val buffer = StringBuffer()
    list.mapIndexed { index, id ->
        if (index < (list.size - 1)) {
            buffer.append("$id|")
        } else {
            buffer.append("$id")
        }
    }
    return buffer.toString()
}
