package com.example.a190306app

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class RequestHttpURLConnection {
//url연결해서 json 데이터 가져오는 클래스
    fun request(_url: String): String {
        // HttpURLConnection 참조 변수.
        val urlConn: HttpURLConnection? = null

        try {

            var url: URL = URL(_url)
            var urlConn = url.openConnection() as HttpURLConnection

            // [2-1]. urlConn 설정.

            urlConn.requestMethod = "GET"
            urlConn.setRequestProperty("Accept-Charset", "UTF-8") // Accept-Charset 설정.
            urlConn.setRequestProperty("Accept", "application/json")

            // [2-2]. parameter 전달 및 데이터 읽어오기.

            // [2-3]. 연결 요청 확인.
            // 실패 시 null을 리턴하고 메서드를 종료.
            if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK)
                return urlConn.getResponseCode().toString() + "  " + urlConn.responseMessage

            // [2-4]. 읽어온 결과물 리턴.
            // 요청한 URL의 출력물을 BufferedReader로 받는다.
            var reader = BufferedReader(InputStreamReader(urlConn.getInputStream(), "UTF-8"))

            // 출력물의 라인과 그 합에 대한 변수.
            lateinit var line: String;
            var page = ""

            // 라인을 받아와 합친다.
            while (true) {
                var line = reader.readLine()
                if (line != null)
                    page += line
                else
                    break
            }

            return page;

        } catch (e: MalformedURLException) { // for URL.
            e.printStackTrace()
        } catch (e: IOException) { // for openConnection().
            e.printStackTrace()
        } finally {
            if (urlConn != null)
                urlConn.disconnect();
        }

        return "ERROR"

    }
}
