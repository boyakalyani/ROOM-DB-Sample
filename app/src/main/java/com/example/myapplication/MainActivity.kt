package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myapplication.adapters.ItemAdapter
import com.example.myapplication.apidata.ApiService
import com.example.myapplication.apidata.Repository
import com.example.myapplication.apidata.RetrofitClient
import com.example.myapplication.dataclasses.itemDataclass
import com.example.myapplication.db.Appdatabse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    lateinit var adapters: ItemAdapter
    private lateinit var db: Appdatabse
    private lateinit var dataRepository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// Initialize Room database
//        db = Room.databaseBuilder(
//            applicationContext,
//            Appdatabse::class.java, "app-database"
//        ).build()
        db = Room.databaseBuilder(
            applicationContext,
            Appdatabse::class.java, "database-name"
        ).fallbackToDestructiveMigration().build()

        // Initialize DataRepository with ApiService and ItemDao
        val apiService = ApiService.create()
        val itemDao = db.itemDao()
        dataRepository = Repository(apiService, itemDao)

//        val items = listOf(
//            itemDataclass(1,"Item 1", androidx.constraintlayout.widget.R.drawable.abc_ic_star_black_16dp),
//            itemDataclass(2,"Item 2", androidx.constraintlayout.widget.R.drawable.btn_checkbox_checked_mtrl),
//            itemDataclass(3,"Item 1", androidx.constraintlayout.widget.R.drawable.abc_ic_star_black_16dp),
//            itemDataclass(4,"Item 2", androidx.constraintlayout.widget.R.drawable.btn_checkbox_checked_mtrl),
//            itemDataclass(5,"Item 1", androidx.constraintlayout.widget.R.drawable.abc_ic_star_black_16dp),
//            itemDataclass(6,"Item 2", androidx.constraintlayout.widget.R.drawable.btn_checkbox_checked_mtrl),
//            itemDataclass(7,"Item 1", androidx.constraintlayout.widget.R.drawable.abc_ic_star_black_16dp),
//            itemDataclass(8,"Item 2", androidx.constraintlayout.widget.R.drawable.btn_checkbox_checked_mtrl),
//
//            itemDataclass(9,"Item 1", androidx.constraintlayout.widget.R.drawable.abc_ic_star_black_16dp),
//            itemDataclass(10,"Item 2", androidx.constraintlayout.widget.R.drawable.btn_checkbox_checked_mtrl),
//            itemDataclass(11,"Item 1", androidx.constraintlayout.widget.R.drawable.abc_ic_star_black_16dp),
//            itemDataclass(12,"Item 2", androidx.constraintlayout.widget.R.drawable.btn_checkbox_checked_mtrl),
//            itemDataclass(13,"Item 1", androidx.constraintlayout.widget.R.drawable.abc_ic_star_black_16dp),
//            itemDataclass(14,"Item 2", androidx.constraintlayout.widget.R.drawable.btn_checkbox_checked_mtrl),
//
//            )

        adapters = ItemAdapter(emptyList())

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapters

//        lifecycleScope.launch(Dispatchers.IO) {
//            for (itemData in items) {
//                db.itemDao().insertItem(itemData)
//            }
//            fetchDataFromDatabase()
////        }
//
//    }
//    private fun fetchDataFromDatabase() {
//        lifecycleScope.launch(Dispatchers.IO) {
//            // Run database operation in IO thread
//            val items = db.itemDao().getAllItems()
//            // Update UI in Main thread
//            runOnUiThread {
//                adapters.setItems(items)
//            }
//        }
        // Fetch data from API and update Room database
        // Fetch data from API and update Room database
        fetchDataAndUpdateDatabase()
    }

    private fun fetchDataAndUpdateDatabase() {
        lifecycleScope.launch(Dispatchers.IO) {
            // Fetch data from API and insert into Room database
            dataRepository.fetchDataAndInsertIntoDb()

            // Fetch data from Room database and update RecyclerView
            val items = db.itemDao().getAllItems()
            // Update UI in Main thread
            withContext(Dispatchers.Main) {
                adapters.setItems(items)
            }
        }
    }


}
