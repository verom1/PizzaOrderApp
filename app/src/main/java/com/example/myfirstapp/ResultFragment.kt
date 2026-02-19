package com.example.myfirstapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class ResultFragment : Fragment(R.layout.fragment_result) {

    private val viewModel: PizzaViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtResult = view.findViewById<TextView>(R.id.txt_final_order)
        val btnCancel = view.findViewById<Button>(R.id.btn_cancel)

        // Спостерігаємо за змінами у ViewModel
        viewModel.orderDetails.observe(viewLifecycleOwner) { data ->
            txtResult.text = data
        }

        btnCancel.setOnClickListener {
            // Очищаємо дані (вимога лаби)
            viewModel.orderDetails.value = ""
            // Повертаємося на головний екран
            parentFragmentManager.popBackStack()
        }
    }
}