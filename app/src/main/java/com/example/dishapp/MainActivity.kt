package com.example.dishapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), DishAdapter.OnDishClickListner {

    private val dishesList = startList();
    private val dishAdapter = DishAdapter(dishesList,this)
    companion object {
        const val REQUEST_ADD_DISH = 1
        const val REQUEST_DISH_CLICK = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dishes_recycler_view: RecyclerView = findViewById(R.id.dishes_recycler_view)
        dishes_recycler_view.adapter = dishAdapter
        dishes_recycler_view.layoutManager = LinearLayoutManager(this)
        dishes_recycler_view.setHasFixedSize(true)
    }



    private fun startList(): ArrayList<Dish>{
        val list = ArrayList<Dish>()
        val starterTitleList = ArrayList<String>()

        starterTitleList.add("Fettuccine Alfredo")
        starterTitleList.add("Cheeseburger")
        starterTitleList.add("Chicken Schnitzel")



        for( i in 0..2){
            val item = Dish(starterTitleList.get(i), "Food Item Description")
            list.add(item)
        }


        return list
    }

    fun AddDish(view: View) {

        val intent = Intent(this, CreateDishActivity::class.java).apply {
            putExtra("ActionButtonClick",true)
        }
        startActivityForResult(intent, REQUEST_ADD_DISH)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_ADD_DISH){
            if(resultCode == RESULT_OK){
                //Toast.makeText(this,"On Activity Result",Toast.LENGTH_SHORT).show()
                val title = data!!.getStringExtra("title")
                val description = data!!.getStringExtra("description")
                val item = Dish(title,description)
                if(title!=""){
                    dishesList.add(item)
                }
                dishAdapter.notifyDataSetChanged()
            }
        }else if(requestCode == REQUEST_DISH_CLICK){
            if(resultCode == RESULT_OK){
                val isEditDish = data!!.getBooleanExtra("isEdit",false)

                if(isEditDish){
                    //The user clicked on Edit
                    val title = data!!.getStringExtra("title")
                    val description = data!!.getStringExtra("description")
                    val item = Dish(title,description)
                    val position = data!!.getIntExtra("position",0)
                    if(title!=""){
                        dishesList.set(position,item)
                    }
                    dishAdapter.notifyDataSetChanged()
                }else{
                    //The user clicked on Delete
                    val deletePosition = data!!.getIntExtra("position",0)
                    dishesList.removeAt(deletePosition)
                    dishAdapter.notifyItemRemoved(deletePosition)
                }


            }
        }


    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, CreateDishActivity::class.java).apply {
            putExtra("ActionButtonClick",false)
            putExtra("title",dishesList.get(position).title)
            putExtra("description",dishesList.get(position).description)
            putExtra("position",position)

        }
        startActivityForResult(intent, REQUEST_DISH_CLICK)
    }


}