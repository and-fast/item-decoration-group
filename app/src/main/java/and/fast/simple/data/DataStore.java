package and.fast.simple.data;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import and.fast.simple.adapters.AlbumAdapter;
import and.fast.simple.data.entities.ImageListResponse;
import androidx.lifecycle.LifecycleObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataStore implements LifecycleObserver {

    private int page = 1;

    public void refresh(final AlbumAdapter albumAdapter, final SmartRefreshLayout smartRefreshLayout) {
        page = 1;
        smartRefreshLayout.setEnableLoadMore(true);
        requestRetrofit(page, 10, new Callback<ImageListResponse>() {

            @Override
            public void onResponse(Call<ImageListResponse> call, Response<ImageListResponse> response) {
                smartRefreshLayout.finishRefresh();
                ImageListResponse body = response.body();
                albumAdapter.setNewData(body.getData());

                if (body.getPage() == body.getPage_count()){
                    smartRefreshLayout.setEnableLoadMore(false);
                }
            }

            @Override
            public void onFailure(Call<ImageListResponse> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }

    public void loadMore(final AlbumAdapter albumAdapter, final SmartRefreshLayout smartRefreshLayout) {
        requestRetrofit(++page, 10, new Callback<ImageListResponse>() {
            @Override
            public void onResponse(Call<ImageListResponse> call, Response<ImageListResponse> response) {
                smartRefreshLayout.finishLoadMore();
                ImageListResponse body = response.body();
                albumAdapter.addData(body.getData());

                if (body.getPage() == body.getPage_count()){
                    smartRefreshLayout.setEnableLoadMore(false);
                }
            }

            @Override
            public void onFailure(Call<ImageListResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void requestRetrofit(int page, int count, Callback<ImageListResponse> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gank.io/api/v2/data/category/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebService webService = retrofit.create(WebService.class);
        Call<ImageListResponse> call = webService.images(page, count);
        call.enqueue(callback);
    }

}
