package com.example.myfirstapp

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class InputFragment : Fragment(R.layout.fragment_input) {

    // Використовуємо делегат, який раніше видавав помилку
    private val viewModel: PizzaViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editName = view.findViewById<EditText>(R.id.edit_client_name)
        val checkCheese = view.findViewById<CheckBox>(R.id.check_cheese)
        val checkMeat = view.findViewById<CheckBox>(R.id.check_meat)
        val checkMushrooms = view.findViewById<CheckBox>(R.id.check_mushrooms)
        val btnOk = view.findViewById<Button>(R.id.btn_ok)

        btnOk.setOnClickListener {
            val name = editName.text.toString().trim()
            val hasIngredients = checkCheese.isChecked || checkMeat.isChecked || checkMushrooms.isChecked

            if (name.isEmpty() || !hasIngredients) {
                // Вимога лаби: вікно, що спливає (Toast)
                Toast.makeText(context, "Завершіть введення всіх даних!", Toast.LENGTH_SHORT).show()
            } else {
                val ingredients = mutableListOf<String>()
                if (checkCheese.isChecked) ingredients.add("Сир")
                if (checkMeat.isChecked) ingredients.add("М'ясо")
                if (checkMushrooms.isChecked) ingredients.add("Гриби")

                // Зберігаємо дані у ViewModel
                viewModel.orderDetails.value = "Замовник: $name\nСклад: ${ingredients.joinToString(", ")}"

                // Переходимо до ResultFragment
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ResultFragment())
                    .addToBackStack(null) // Дозволяє повернутися назад кнопкою Android
                    .commit()
            }
        }
    }
}