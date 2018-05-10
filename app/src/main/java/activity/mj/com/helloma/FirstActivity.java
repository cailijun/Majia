package activity.mj.com.helloma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pc on 2018/2/1.
 */

public class FirstActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_first);
        initData();
    }

    private void initData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://1114600.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<ResultBean> call = service.listRepos();
        call.enqueue(new Callback<ResultBean>() {
            @Override
            public void onResponse(Call<ResultBean> call, Response<ResultBean> response) {

                if("1".equals(response.body().getStatus()))
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent=new Intent(FirstActivity.this,MainActivity.class);
                    intent.putExtra("url",response.body().getUrl());
                    startActivity(intent);
                    finish();
                }else if("0".equals(response.body().getStatus()))
                {
                    Intent intent=new Intent(FirstActivity.this,MainActivity.class);
                    intent.putExtra("url","http://www.lottery.gov.cn/");
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<ResultBean> call, Throwable t) {

                Intent intent=new Intent(FirstActivity.this,MainActivity.class);
                intent.putExtra("url","http://www.lottery.gov.cn/");
                startActivity(intent);
                finish();
            }
        });
    }
}
