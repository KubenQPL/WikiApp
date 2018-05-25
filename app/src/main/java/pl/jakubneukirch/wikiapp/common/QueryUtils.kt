package pl.jakubneukirch.wikiapp.common

fun getMultipleParameters(list: List<Int>): String {
    return buildString {
        list.mapIndexed { index, id ->
            append(id)
            if(index < (list.size-1)){
                append("%7C")
            }
        }
    }
}