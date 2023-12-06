package com.example.realtimesubway.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realtimesubway.R
import com.example.realtimesubway.adapter.TrainInfoAdapter
import com.example.realtimesubway.api.OpenApi
import com.example.realtimesubway.databinding.FragmentUpBinding
import com.example.realtimesubway.list.TrainInfoList
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


class UpFragment : Fragment() {
    private var lineNm: String? = null
    private lateinit var binding: FragmentUpBinding
    private val list = ArrayList<TrainInfoList>()
    private val trainInfoAdapter = TrainInfoAdapter(list)
    private val openApi = OpenApi(context)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            lineNm = it.getString("lineNm")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentUpBinding = FragmentUpBinding.bind(view)
        binding = fragmentUpBinding
        binding.rvList.layoutManager = LinearLayoutManager(context)
        binding.rvList.adapter = trainInfoAdapter
        apiCheckTrainInfo()
    }

    private fun apiCheckTrainInfo() {
        list.clear()
        object : Thread() {
            override fun run() {
                try {
                    val checkedInfo: JSONArray? = openApi.checkTrainInfo(lineNm)
                    Handler(Looper.getMainLooper()).post {
                        try {
                            if (checkedInfo != null) {
                                for (i in 0 until checkedInfo.length()) {
                                    var temp: JSONObject? = null
                                    temp = checkedInfo.getJSONObject(i)
                                    if (temp.getString("updnLine").equals("0")){
                                        list.add(
                                            TrainInfoList(
                                                ""+temp.getString("trainNo"),
                                                ""+temp.getString("statnNm"),
                                                ""+temp.getString("statnTnm"),
                                                ""+temp.getString("trainSttus")
                                            )
                                        )
                                    }
                                }
                            } else {
                                Toast.makeText(context, "해당하는 역의 정보가 없습니다",Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                        trainInfoAdapter.notifyDataSetChanged()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }
}