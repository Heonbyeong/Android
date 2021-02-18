package com.example.socket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket

class MainActivity : AppCompatActivity() {

    val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            val data = editText1.text.toString()
            Thread(object : Runnable{
                override fun run() {
                    send(data)
                }
            }).start()
        }

        button2.setOnClickListener{
            Thread(object : Runnable{
                override fun run() {
                    startServer()
                }
            }).start()
        }
    }

    fun printClientLog(data : String){
        Log.d("MainActivity", data)

        handler.post(object: Runnable{
            override fun run() {
                textView.append(data + "\n")
            }
        })
    }

    fun printServerLog(data : String){
        Log.d("MainActivity", data)

        handler.post(object : Runnable{
            override fun run() {
                textView2.append(data + "\n")
            }
        })
    }

    fun send(data: String){
        try{
            val portNumber = 5001
            val sock = Socket("localHost", portNumber) // 소켓 객체 만들기
            printClientLog("소켓 연결함")

            val outstream = ObjectOutputStream(sock.getOutputStream()) // 소켓 객체로 데이터 보내기
            outstream.writeObject(data)
            outstream.flush()
            printClientLog("데이터 전송함")

            val instream = ObjectInputStream(sock.getInputStream())
            printClientLog("서버로부터 받음 : ${instream.readObject()}")
            sock.close()
        } catch (e : Exception){
            e.printStackTrace()
        }
    }

    fun startServer(){
        try{
            val portNumber = 5001

            val server = ServerSocket(portNumber)
            printServerLog("서버 시작함 : $portNumber")

            while (true){ // 클라이언트가 접속했을 때 만들어지는 소켓 객체 참조하기
                val sock = server.accept()
                val clientHost = sock.localAddress
                val clientPort = sock.port
                printServerLog("클라이언트 연결됨: $clientHost : $clientPort")

                val instream = ObjectInputStream(sock.getInputStream())
                val obj = instream.readObject()
                printServerLog("데이터 받음: $obj")

                val outstream = ObjectOutputStream(sock.getOutputStream())
                outstream.writeObject("$obj from Server.")
                outstream.flush()
                printServerLog("데이터 보냄")

                sock.close()
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}