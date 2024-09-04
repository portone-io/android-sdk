package io.portone.portonesdk

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<BINDING : ViewBinding> : AppCompatActivity() {
    private var _binding: BINDING? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = setViewBinding(layoutInflater)
        setContentView(binding.root)
    }


    abstract fun setViewBinding(inflater: LayoutInflater) : BINDING


}