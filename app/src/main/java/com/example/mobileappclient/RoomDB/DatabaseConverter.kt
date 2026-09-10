package com.example.mobileappclient.RoomDB

import androidx.room.TypeConverter

//Room DB cannot automatically store
//every datatype eg...[ List ]
//a db converter converts a List to string

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
