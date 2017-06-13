package views;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import net.felipemuniz.androidretrofitexample.R;
import net.felipemuniz.androidretrofitexample.databinding.ActivityMainBinding;

import models.Person;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.Service;
import services.ServiceGenerator;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getPersonOnClick(null);

    }

    public void getPersonOnClick(View view){
        try {

            Service service = ServiceGenerator.createService(Service.class);

            final Call<Person> person = service.GetPerson();

            person.enqueue(new Callback<Person>() {
                @Override
                public void onResponse(Call<Person> call, Response<Person> response) {

                    Person person = response.body();


//                    Glide.with(mIvPhoto.getContext())
//                            .load(person.getPhoto())
//                            .into(mIvPhoto);

                    binding.setPerson(person);
                }

                @Override
                public void onFailure(Call<Person> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Sem conexão com internet!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e("IO","IO"+e);
            Toast.makeText(this, "Exeption: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        catch(OutOfMemoryError e1) {
            e1.printStackTrace();
            Log.e("Memory exceptions","exceptions"+e1);
        }
    }

}
