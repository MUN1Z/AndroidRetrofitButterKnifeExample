package views

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide


import net.felipemuniz.androidretrofitexample.R
import net.felipemuniz.androidretrofitexample.databinding.ActivityMainBinding

import models.Person
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import services.Service
import services.ServiceGenerator

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        getPersonOnClick(null)

    }

    fun getPersonOnClick(view: View?) {
        try {

            val service = ServiceGenerator.createService(Service::class.java)

            val person = service.GetPerson()

            person.enqueue(object : Callback<Person> {
                override fun onResponse(call: Call<Person>, response: Response<Person>) {

                    val person = response.body()

                    binding?.person = person
                }

                override fun onFailure(call: Call<Person>, t: Throwable) {
                    Toast.makeText(applicationContext, "Sem conexão com internet!", Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("IO", "IO" + e)
            Toast.makeText(this, "Exeption: " + e.message, Toast.LENGTH_SHORT).show()
        } catch (e1: OutOfMemoryError) {
            e1.printStackTrace()
            Log.e("Memory exceptions", "exceptions" + e1)
        }

    }



}
