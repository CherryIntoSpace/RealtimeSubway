package com.example.realtimesubway.activity

import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.realtimesubway.R
import com.example.realtimesubway.databinding.ActivityTrainInfoBinding
import com.example.realtimesubway.fragment.DownFragment
import com.example.realtimesubway.fragment.UpFragment

class TrainInfo : AppCompatActivity() {
    private lateinit var lineNm: String
    private lateinit var binding: ActivityTrainInfoBinding
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
        setDataAtFragment(UpFragment(), lineNm)
    }

    inner class RadioListener : RadioGroup.OnCheckedChangeListener {
        override fun onCheckedChanged(rg: RadioGroup?, rb: Int) {
            when (rg?.id) {
                R.id.rg_toggle -> {
                    when (rb) {
                        R.id.rb_up -> {
                            setDataAtFragment(UpFragment(), lineNm)
                        }

                        R.id.rb_down -> {
                            setDataAtFragment(DownFragment(), lineNm)
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

    private fun switchFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    private fun setDataAtFragment(fragment: Fragment, lineNm: String){
        val bundle = Bundle()
        bundle.putString("lineNm", lineNm)
        fragment.arguments = bundle
        switchFragment(fragment)
    }

    private fun startToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}