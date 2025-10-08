package com.example.lab_week_06

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender
import kotlin.lazy

class MainActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    private val catAdapter by lazy {
//        Glide is used here to load the images
//        Here we are passing the onClickListener function to the Adapter
        CatAdapter(layoutInflater, GlideImageLoader(this), object: CatAdapter.OnClickListener {
//            when this is triggered, the pop up dialog will be shown
            override fun onItemClick(cat: CatModel) = showSelectionDialog(cat)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Setup the adapter for the recycler view
        recyclerView.adapter = catAdapter

//        Setup the layout manager for the recycler view
//        A layout manager is used to set the structure of the item views
//        For this tutorial, we're using the vertical linear structure
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

//        Add data to the model list in the adapter
        catAdapter.setData(
            listOf(
                CatModel(
                    Gender.Male,
                    CatBreed.BalineseJavanese,
                    "Fred",
                    "Silent and deadly",
                    "https://cdn2.thecatapi.com/images/7dj.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.ExoticShorthair,
                    "Wilma",
                    "Cuddly assassin",
                    "https://cdn2.thecatapi.com/images/egv.jpg"
                ),
                CatModel(
                    Gender.Unknown,
                    CatBreed.AmericanCurl,
                    "Curious George",
                    "Award winning investigator",
                    "https://cdn2.thecatapi.com/images/bar.jpg"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.AmericanCurl,
                    "Leo",
                    "A calm gentleman who loves sunbeams.",
                    "https://cdn2.thecatapi.com/images/55k.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.BalineseJavanese,
                    "Cleo",
                    "Vocal queen who adores laser pointers.",
                    "https://cdn2.thecatapi.com/images/33l.jpg"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.ExoticShorthair,
                    "Jasper",
                    "A gentle giant with a loving heart.",
                    "https://cdn2.thecatapi.com/images/csh.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.AmericanCurl,
                    "Luna",
                    "Graceful master of surprise cuddles.",
                    "https://cdn2.thecatapi.com/images/MTc0MDI0MQ.jpg"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.BalineseJavanese,
                    "Milo",
                    "The ultimate purring lap cat.",
                    "https://cdn2.thecatapi.com/images/a5j.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.ExoticShorthair,
                    "Nala",
                    "Energetic adventurer who loves to climb.",
                    "https://cdn2.thecatapi.com/images/k7y.jpg"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.AmericanCurl,
                    "Simba",
                    "A social butterfly who loves playing.",
                    "https://cdn2.thecatapi.com/images/asv.jpg"
                )
            )
        )

//        instantiate ItemTouchHelper for the swipe to delete callback and attach it to the recycler view
        val itemTouchHelper = ItemTouchHelper(catAdapter.swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

//    This will create a pop up dialog when one of the items from the recycler view is clicked
    private fun showSelectionDialog(cat: CatModel) {
    AlertDialog.Builder(this)
//    Set the title for the dialog
        .setTitle("Cat Selected")
//    Set the message for the dialog
        .setMessage("You have selected cat ${cat.name}")
//    Set if the OK button should be enabled
        .setPositiveButton("OK") {_, _ ->}.show()
    }
}