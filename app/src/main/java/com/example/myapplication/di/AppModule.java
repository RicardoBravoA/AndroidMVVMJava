package com.example.myapplication.di;

import android.arch.lifecycle.ViewModelProvider;

import com.example.myapplication.service.repository.GithubService;
import com.example.myapplication.viewmodel.ProjectViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {

    @Singleton
    @Provides
    GithubService provideGithubService() {

        return new Retrofit.Builder()
                .baseUrl(GithubService.HTTPS_API_GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
                .create(GithubService.class);
    }

    @Singleton
    @Provides
    OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(logging).build();
    }


    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(ViewModelSubComponent.Builder builder) {
        return new ProjectViewModelFactory(builder.build());
    }

}
