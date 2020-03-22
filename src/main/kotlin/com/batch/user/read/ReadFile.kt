package com.batch.user.read

import com.batch.user.domain.User
import com.google.gson.Gson
import org.h2.util.New.arrayList
import java.io.File
import java.io.InputStream

class ReadFile {
    fun generateJson(): String{

        val inputStream: InputStream = File("C://dev/spring/pessoa.csv").inputStream()
        val lineList = mutableListOf<String>()

        val users =  mutableListOf<User>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }
        for(item in lineList)
        {
            if(item.trim().isNotEmpty() && !item.contains("----"))
            {
                users.add(User(item.substring(0, 41), item.substring(42, 55)))
            }
        }

        var gson: Gson = Gson()

        var json = gson.toJson(users)

        println(json)
        return json
    }
}