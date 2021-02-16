package com.example.fragmen_2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment(){
    interface ImageSelectionCallBack{
        fun onImageSelected(position: Int)
    }

    lateinit var callBack: ImageSelectionCallBack

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is ImageSelectionCallBack){
            callBack = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_list, container, false)


        //각각의 버튼을 클릭하면 callback 객체의 onImageSelected()를 호출한다
        rootView.button1.setOnClickListener{
            if(callBack != null){
                callBack.onImageSelected(0)
            }
        }

        rootView.button2.setOnClickListener{
            if(callBack != null){
                callBack.onImageSelected(1)
            }
        }

        rootView.button3.setOnClickListener{
            if(callBack != null){
                callBack.onImageSelected(2)
            }
        }

        return rootView
    }
}