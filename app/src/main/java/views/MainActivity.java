package views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.felipemuniz.androidretrofitexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import models.Person;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.Service;
import services.ServiceGenerator;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvName)
    TextView mTvName;

    @BindView(R.id.tvGender)
    TextView mTvGender;

    @BindView(R.id.tvRegion)
    TextView mTvRegion;

    @BindView(R.id.tvAge)
    TextView mTvAge;

    @BindView(R.id.ivPhoto)
    ImageView mIvPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getPersonOnClick(null);
    }

    @OnClick(R.id.btnGetPerson)
    public void getPersonOnClick(View view){
        try {

            Service service = ServiceGenerator.createService(Service.class);

            final Call<Person> person = service.GetPerson();

            person.enqueue(new Callback<Person>() {
                @Override
                public void onResponse(Call<Person> call, Response<Person> response) {

                    Person person = response.body();

                    Glide.with(mIvPhoto.getContext())
                            .load(person.getPhoto())
                            .into(mIvPhoto);

                    mTvName.setText("Name: " + person.getFullName());
                    mTvGender.setText("Gender: " + person.getGender());
                    mTvRegion.setText("Region: " + person.getRegion());
                    mTvAge.setText("Age: " + person.getAge());
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
