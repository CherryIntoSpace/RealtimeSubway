package com.example.realtimesubway.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.realtimesubway.R
import com.example.realtimesubway.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLn1.setOnClickListener(this)
        binding.btnLn2.setOnClickListener(this)
        binding.btnLn3.setOnClickListener(this)
        binding.btnLn4.setOnClickListener(this)
        binding.btnLn5.setOnClickListener(this)
        binding.btnLn6.setOnClickListener(this)
        binding.btnLn7.setOnClickListener(this)
        binding.btnLn8.setOnClickListener(this)
        binding.btnLn9.setOnClickListener(this)
        binding.btnLnGo.setOnClickListener(this)
        binding.btnLnGy.setOnClickListener(this)
        binding.btnLnGye.setOnClickListener(this)
        binding.btnLnSin.setOnClickListener(this)
        binding.btnLnSu.setOnClickListener(this)
        binding.btnLnui.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.btn_ln1 -> gotoTrainInfo("1호선")
                R.id.btn_ln2 -> gotoTrainInfo("2호선")
                R.id.btn_ln3 -> gotoTrainInfo("3호선")
                R.id.btn_ln4 -> gotoTrainInfo("4호선")
                R.id.btn_ln5 -> gotoTrainInfo("5호선")
                R.id.btn_ln6 -> gotoTrainInfo("6호선")
                R.id.btn_ln7 -> gotoTrainInfo("7호선")
                R.id.btn_ln8 -> gotoTrainInfo("8호선")
                R.id.btn_ln9 -> gotoTrainInfo("9호선")
                R.id.btn_lnGy -> gotoTrainInfo("경의중앙선")
                R.id.btn_lnGo -> gotoTrainInfo("공항철도")
                R.id.btn_lnGye -> gotoTrainInfo("경춘선")
                R.id.btn_lnSu -> gotoTrainInfo("수인분당선")
                R.id.btn_lnSin -> gotoTrainInfo("신분당선")
                R.id.btn_lnui -> gotoTrainInfo("우이신설선")
            }
        }
    }

    private fun gotoTrainInfo(lineNm : String) {
        val intent = Intent(this, TrainInfo::class.java)
        intent.putExtra("LineNm", lineNm)
        startActivity(intent)
    }
}
