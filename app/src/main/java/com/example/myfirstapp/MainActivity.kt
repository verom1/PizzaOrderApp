package com.example.myfirstapp // Перевір, щоб назва пакету співпадала з твоєю

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editName = findViewById<EditText>(R.id.edit_client_name)
        val checkCheese = findViewById<CheckBox>(R.id.check_cheese)
        val checkMeat = findViewById<CheckBox>(R.id.check_meat)
        val checkMushrooms = findViewById<CheckBox>(R.id.check_mushrooms)
        val btnOk = findViewById<Button>(R.id.btn_ok)
        val txtResult = findViewById<TextView>(R.id.txt_result)

        btnOk.setOnClickListener {
            val name = editName.text.toString().trim()

            val hasIngredients =
                checkCheese.isChecked || checkMeat.isChecked || checkMushrooms.isChecked

            if (name.isEmpty() || !hasIngredients) {
                Toast.makeText(
                    this,
                    "Будь ласка, введіть ім'я та оберіть інгредієнти!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                var orderDetails = "Замовник: $name\nСклад: "

                val ingredients = mutableListOf<String>()
                if (checkCheese.isChecked) ingredients.add("Подвійний сир")
                if (checkMeat.isChecked) ingredients.add("М'ясо")
                if (checkMushrooms.isChecked) ingredients.add("Гриби")

                orderDetails += ingredients.joinToString(", ")

                txtResult.text = orderDetails
            }
        }
    }
}