package com.example.emote

import android.util.Log
import com.beust.klaxon.Klaxon

class DB{
    private val IP_ADDRESS = "http://kiseok.dothome.co.kr/sql.php"

    class User(val uid : String,var tel : String, var sad : String, var pw : String, var caution  : String)
    class Post(val pid : String,var pb : String, var date : String, var contents : String, var place  : String,
               var heart_count  : String, var title  : String, var activity  : String, var uid  : String)
    class Emotion(val eid : String,val pid : String, val percent : String)

    fun getUsers() : List<User>? {
        try {
            val json = InsertData().execute(IP_ADDRESS, "select * from user").get()
            return Klaxon().parseArray(json)
            //result?.forEach { Log.d("title : ", "${it.title}") }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("error!!!! : ", "Error!!", e)
        }
        return null
    }
    fun getUser(uid : Int) : List<User>? {
        try {
            val json = InsertData().execute(IP_ADDRESS, "select * from user where uid = $uid").get()
            return Klaxon().parseArray(json)

        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("error!!!! : ", "Error!!", e)
        }
        return null
    }
    fun getUsersByQuery(q : String) : List<User>? {
        try {
            val json = InsertData().execute(IP_ADDRESS, "select * from user where $q").get()
            return Klaxon().parseArray(json)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("error!!!! : ", "Error!!", e)
        }
        return null
    }
    fun getPosts() : List<Post>? {
        try {
            val json = InsertData().execute(IP_ADDRESS, "select * from post").get()
            return Klaxon().parseArray(json)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("error!!!! : ", "Error!!", e)
        }
        return null

    }
    fun getPost(pid : Int) : List<Post>? {
        try {
            val json = InsertData().execute(IP_ADDRESS, "select * from post where pid = `$pid`").get()
            return Klaxon().parseArray(json)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("error!!!! : ", "Error!!", e)
        }
        return null

    }
    fun getPostsByQuery(q : String) : List<Post>? {
        try {
            val json = InsertData().execute(IP_ADDRESS, "select * from post where $q").get()


            return Klaxon().parseArray(json)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("error!!!! : ", "Error!!", e)
        }
        return null
    }
    fun getEmotions() : List<Emotion>? {
        try {
            val json = InsertData().execute(IP_ADDRESS, "select * from emotion").get()
            return Klaxon().parseArray(json)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("error!!!! : ", "Error!!", e)
        }
        return null
    }
    fun getEmotion(pid : Int) : List<Emotion>? {
        try {
            val json = InsertData().execute(IP_ADDRESS, "select * from emotion where pid = $pid").get()
            return Klaxon().parseArray(json)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("error!!!! : ", "Error!!", e)
        }
        return null
    }
    fun getEmotionsbyQuery(q : String) : List<Emotion>? {
        try {
            val json = InsertData().execute(IP_ADDRESS, "select * from emotion where $q").get()
            Log.d("erorr!!!!!","select * from emotion where $q")
            Log.d("erorr!!!!!",json)
            return Klaxon().parseArray(json)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("error!!!! : ", "Error!!", e)
        }
        return null
    }

    fun insert(user : User) {
        try {
            val result = InsertData().execute(IP_ADDRESS, "INSERT INTO `user` (`tel`, `pw`, `caution`, `sad`) VALUES ('${user.tel}', '${user.pw}', '${user.caution}', '${user.sad}')").get()
            Log.d("sql insert result : ", result)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("error!!!! : ", "Error!!", e)
        }
    }
    fun insert(post : Post) {
        Log.d("query : ","INSERT INTO `post` (`pb`, `date`, `contents`, `place`, `heart_count`, `title`, `activity`, `uid`) VALUES ('${post.pb}', NOW(), '${post.contents}', '${post.place}', '${post.heart_count}', '${post.title}', '${post.activity}', '${post.uid}')")
        val result = InsertData().execute(IP_ADDRESS, "INSERT INTO `post` (`pb`, `date`, `contents`, `place`, `heart_count`, `title`, `activity`, `uid`) VALUES ('${post.pb}', NOW(), '${post.contents}', '${post.place}', '${post.heart_count}', '${post.title}', '${post.activity}', '${post.uid}')").get()
        try {
            Log.d("sql insert result : ", result)

        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("error!!!! : ", "Error!!", e)

        }
    }
    fun insert(emotion : Emotion) {
        try {
            val result = InsertData().execute(IP_ADDRESS, "INSERT INTO `emotion` (`eid`, `pid`, `percent`) VALUES ('${emotion.eid}', '${emotion.pid}', '${emotion.percent}')").get()
            Log.d("sql insert result : ", result)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("error!!!! : ", "Error!!", e)
        }
    }
    fun getName(post : Post) : String{
        return InsertData().execute(IP_ADDRESS, "select * from activities where a_id = 5").get().drop(1).dropLast(2)
    }
    fun getName(emotion : Emotion) : String{
        return InsertData().execute(IP_ADDRESS, "select * from enames where eid = ${emotion.eid}").get().drop(1).dropLast(2)
    }
    fun update(user : User){
        InsertData().execute(IP_ADDRESS, """UPDATE user SET tel= '${user.tel}'
            |, SET sad = '${user.sad}'
            |, SET pw = '${user.sad}'
            |, SET caution = '${user.caution}'
            | WHERE uid is '${user.uid}'
        """.trimMargin()
        )
    }
    fun update(post : Post){
        InsertData().execute(IP_ADDRESS, "UPDATE post SET pb = '${post.pb}' WHERE pid = '${post.pid}'")
        InsertData().execute(IP_ADDRESS, "UPDATE post SET date = '${post.date}' WHERE pid = '${post.pid}'")
        InsertData().execute(IP_ADDRESS, "UPDATE post SET contents = '${post.contents}' WHERE pid = '${post.pid}'")
        InsertData().execute(IP_ADDRESS, "UPDATE post SET place = '${post.place}' WHERE pid = '${post.pid}'")
        InsertData().execute(IP_ADDRESS, "UPDATE post SET heart_count = '${post.heart_count}' WHERE pid = '${post.pid}'")
        InsertData().execute(IP_ADDRESS, "UPDATE post SET title = '${post.title}' WHERE pid = '${post.pid}'")
        InsertData().execute(IP_ADDRESS, "UPDATE post SET activity = '${post.activity}' WHERE pid = '${post.pid}'")

    }
}
