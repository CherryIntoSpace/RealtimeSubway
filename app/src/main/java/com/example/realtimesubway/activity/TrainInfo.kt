package com.example.realtimesubway.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import com.example.realtimesubway.R
import com.example.realtimesubway.databinding.ActivityMainBinding
import com.example.realtimesubway.databinding.ActivityTrainInfoBinding

class TrainInfo : AppCompatActivity() {
    private lateinit var lineNm : String
    private lateinit var binding : ActivityTrainInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lineNm = intent.getStringExtra("LineNm").toString()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = lineNm

        var listener = RadioListener()
        binding.rgToggle.setOnCheckedChangeListener(listener)
    }

    inner class RadioListener : RadioGroup.OnCheckedChangeListener {
        override fun onCheckedChanged(rg: RadioGroup?, rb: Int) {
            when (rg?.id) {
                R.id.rg_toggle -> {
                    when (rb) {
                        R.id.rb_up -> {

                        }
                        R.id.rb_down -> {

                        }
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun startToast(msg : String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}