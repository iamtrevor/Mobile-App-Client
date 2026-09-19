package com.example.mobileappclient.Data

import androidx.room.TypeConverter

//Room DB cannot automatically store
//every datatype eg...[ List ]
//a db converter converts a List to string


/*
class DatabaseConverter {

    private val separator = ","

    @TypeConverter
    fun converterListToString(list : List<String>) : String {
        val stringBuilder = StringBuilder()
        for(item in list){
            stringBuilder.append(item).append(separator)
        }
        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }

    @TypeConverter
    fun convertStringToList(string: String): List<String> {
        return string.split(separator)
    }
}

 */

class DatabaseConverter {

    private val separator = ","

    @TypeConverter
    fun converterListToString(list: List<String>): String {
        // joinToString safely handles empty lists by returning an empty string ""
        return list.joinToString(separator = separator)
    }

    @TypeConverter
    fun convertStringToList(string: String): List<String> {
        // If the string is empty, return an empty list immediately
        // to avoid getting a list containing one empty string [""]
        if (string.isEmpty()) {
            return emptyList()
        }
        return string.split(separator)
    }
}