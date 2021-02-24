package com.example.dishapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateDishActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var addButton: Button
    private lateinit var editButton: Button
    private lateinit var deleteButton: Button
    private var position:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_dish)

        titleEditText = findViewById(R.id.edit_text_title)
        descriptionEditText = findViewById(R.id.description_edit_test)
        addButton = findViewById(R.id.add_button)
        editButton = findViewById(R.id.edit_button)
        deleteButton = findViewById(R.id.delete_button)

        val intent = getIntent()

        val isAddDishEvent:Boolean = intent.getBooleanExtra("ActionButtonClick",true)


        if(isAddDishEvent){
            editButton.visibility = View.GONE
            deleteButton.visibility = View.GONE
        }else{
            titleEditText.setText(intent.getStringExtra("title"))
            descriptionEditText.setText(intent.getStringExtra("description"))
            position = intent.getIntExtra("position",0)
            addButton.visibility = View.GONE
        }


    }


//    override fun onBackPressed() {
//        super.onBackPressed()
//        val replyIntent = Intent()
//        setResult(RESULT_OK, replyIntent)
//        finish()
//    }

    fun returnDish(view: View) {

        var title = ""
        var description = ""

        if(titleEditText.text!=null){
            title = titleEditText.text.toString()
        }

        if(descriptionEditText.text!=null){
            description = descriptionEditText.text.toString()
        }

        val addIntent = Intent().apply {
            putExtra("title", title)
            putExtra("description", description)
        }
        setResult(RESULT_OK, addIntent)
        finish()
    }

    fun editDish(view: View) {
        var title = ""
        var description = ""

        if(titleEditText.text!=null){
            title = titleEditText.text.toString()
        }

        if(descriptionEditText.text!=null){
            description = descriptionEditText.text.toString()
        }

        val editIntent = Intent().apply {
            putExtra("title", title)
            putExtra("description", description)
            putExtra("isEdit",true)
            putExtra("position",position)
        }

        setResult(RESULT_OK, editIntent)
        finish()
    }
    fun deleteDish(view: View) {
        val deleteIntent = Intent().apply{
            putExtra("isEdit",false)
            putExtra("position",position)
        }
        setResult(RESULT_OK, deleteIntent)
        finish()
    }
}