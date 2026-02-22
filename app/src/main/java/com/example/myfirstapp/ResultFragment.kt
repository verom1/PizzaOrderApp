package com.example.myfirstapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class ResultFragment : Fragment(R.layout.fragment_result) {
    private val viewModel: FlowerViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtResult = view.findViewById<TextView>(R.id.txt_result_display)
        val btnBack = view.findViewById<Button>(R.id.btn_back)

        viewModel.orderDetails.observe(viewLifecycleOwner) { data ->
            txtResult.text = data
        }

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}