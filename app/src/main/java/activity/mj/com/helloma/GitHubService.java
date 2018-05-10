package activity.mj.com.helloma;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by pc on 2017/12/19.
 */

public interface GitHubService {
    @GET("appgl/appShow/getByAppId?appId=sm20181234001")
    Call<ResultBean> listRepos();



}
