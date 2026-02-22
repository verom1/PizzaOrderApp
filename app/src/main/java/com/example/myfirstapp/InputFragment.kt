package com.example.myfirstapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class InputFragment : Fragment(R.layout.fragment_input) {
    private val viewModel: FlowerViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editFlower = view.findViewById<EditText>(R.id.edit_flower_name)
        val groupColor = view.findViewById<RadioGroup>(R.id.group_color)
        val groupPrice = view.findViewById<RadioGroup>(R.id.group_price)
        val btnOk = view.findViewById<Button>(R.id.btn_ok)
        val btnHistory = view.findViewById<Button>(R.id.btn_history)

        btnOk.setOnClickListener {
            val flowerName = editFlower.text.toString()
            val colorId = groupColor.checkedRadioButtonId
            val priceId = groupPrice.checkedRadioButtonId

            if (flowerName.isEmpty() || colorId == -1 || priceId == -1) {
                Toast.makeText(context, "Заповніть усі дані!", Toast.LENGTH_SHORT).show()
            } else {
                val color = view.findViewById<RadioButton>(colorId).text.toString()
                val price = view.findViewById<RadioButton>(priceId).text.toString()
                val result = "Квітка: $flowerName, Колір: $color, Ціна: $price"

                // 1. Передаємо в ViewModel
                viewModel.orderDetails.value = result

                // 2. Записуємо у файл (Лаба 3)
                saveToFile(result)

                // 3. Перехід до ResultFragment
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ResultFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

        btnHistory.setOnClickListener {
            val intent = Intent(activity, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveToFile(data: String) {
        try {
            val fileContent = "$data\n"
            context?.openFileOutput("flower_orders.txt", Context.MODE_APPEND).use { output ->
                output?.write(fileContent.toByteArray())
            }
            Toast.makeText(context, "Запис успішний!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Помилка запису", Toast.LENGTH_SHORT).show()
        }
    }
}