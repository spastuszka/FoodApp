package com.pastuszka.startapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_ticket.view.*

class MainActivity : AppCompatActivity() {

    var adapter:FoodAdapter?=null
    var listOfFoods = ArrayList<Food>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load food
        listOfFoods.add(Food("Coffee","This is a drink",R.drawable.coffee_pot))
        listOfFoods.add(Food("Espresso","This is a drink, like a coffee",R.drawable.espresso))
        listOfFoods.add(Food("Frytki","Dobre frytki",R.drawable.french_fries))
        listOfFoods.add(Food("Miodzik","To co Gumisi lubią najbardziej ;P",R.drawable.honey))
        listOfFoods.add(Food("Lody truskawkowe","Lody sa zajebiste",R.drawable.strawberry_ice_cream))
        listOfFoods.add(Food("Cukier","Lepiej nie jeść w nadmiarze",R.drawable.sugar_cubes))

        adapter = FoodAdapter(this,listOfFoods)

        gvListFood.adapter = adapter

    }

    class FoodAdapter:BaseAdapter{

        var listOfFood = ArrayList<Food>()
        var context:Context?= null
        constructor(context:Context, listOfFood:ArrayList<Food>):super(){

            this.context = context
            this.listOfFood = listOfFood

        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            val food = this.listOfFood[p0]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var foodView = inflator.inflate(R.layout.food_ticket, null)
            foodView.ivFoodImage.setImageResource(food.image!!)
            foodView.ivFoodImage.setOnClickListener {

                val intent = Intent(context, FoodDetails::class.java)
                intent.putExtra("name",food.name!!)
                intent.putExtra("des", food.des!!)
                intent.putExtra("image", food.image!!)
                context!!.startActivity(intent)

            }
            foodView.tvName.text = food.name!!
            return foodView

        }

        override fun getItem(p0: Int): Any {

            return listOfFood[p0]

        }

        override fun getItemId(p0: Int): Long {

            return p0.toLong()

        }

        override fun getCount(): Int {

            return listOfFood.size

        }

    }
}
