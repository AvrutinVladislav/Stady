package com.example.study

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.study.databinding.ActivityMainBinding
import com.example.study.databinding.ItemReviewBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val reviews: MutableLiveData<Model.ReviewsResponse> = MutableLiveData<Model.ReviewsResponse>()
    val adapter by lazy { ReviewsListAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.rvReviewsList) {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        getReviews()
        reviews.observe(this) {
            adapter.reviewsList.addAll(it.reviews!!)
            adapter.notifyDataSetChanged()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true) //активация кнопки назад
    }

    //эта функция прописывает действия при нажатии на значки верхнего меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)/* если id совпадает с id из библиотеки андроид
        (стандартный тул бар --- кнопка назад). При большом колличестве
        пунктов меню, используем не if a when(кейсы: условие -> дейтвие) */
        { finish() } //при нажатии закрывает открытое активити, или прописать переход на предыдущее
        return true
    }

    //загружаем в память и активируем верхнее меню
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    fun getReviews() {
        val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/movies/v2/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val service = (retrofit.create(ReviewsService::class.java))

        GlobalScope.launch(Dispatchers.IO) {
            val response = service.getReviews()
            reviews.postValue(response)
        }
    }
}

interface ReviewsService {
    @GET("reviews/all.json?api-key=cqmVWmORvGkA3iKgGX7URNzutvC3RrQS")
    suspend fun getReviews(): Model.ReviewsResponse
}


class ReviewsListAdapter : RecyclerView.Adapter<ReviewsListAdapter.ViewHolder>() {
    var reviewsList: MutableList<Model.Review> = emptyList<Model.Review>().toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemBinding: ItemReviewBinding = ItemReviewBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = reviewsList[position]
        with(holder.itemBinding) {
            review.multimedia?.src?.let {
                Glide.with(holder.itemView).load(it).into(post)
            }
            header.text = review.displayTitle
            textPreview.text = review.summaryShort
            criticName.text = review.byline
            calendarData.text = review.dateUpdated
        }
    }

    override fun getItemCount(): Int {
        return reviewsList.size
    }
}


