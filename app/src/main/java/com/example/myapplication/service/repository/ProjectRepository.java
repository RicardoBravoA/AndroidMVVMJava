package com.example.myapplication.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.myapplication.service.model.ProjectModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class ProjectRepository {

    private GithubService gitHubService;

    @Inject
    public ProjectRepository(GithubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    public LiveData<List<ProjectModel>> getProjectList(String userId) {
        final MutableLiveData<List<ProjectModel>> data = new MutableLiveData<>();

        gitHubService.getProjectList(userId).enqueue(new Callback<List<ProjectModel>>() {
            @Override
            public void onResponse(Call<List<ProjectModel>> call, Response<List<ProjectModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ProjectModel>> call, Throwable t) {
                // TODO better error handling in part #2 ...
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<ProjectModel> getProjectDetails(String userID, String projectName) {
        final MutableLiveData<ProjectModel> data = new MutableLiveData<>();

        gitHubService.getProjectDetails(userID, projectName).enqueue(new Callback<ProjectModel>() {
            @Override
            public void onResponse(Call<ProjectModel> call, Response<ProjectModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ProjectModel> call, Throwable t) {
                // TODO better error handling in part #2 ...
                data.setValue(null);
            }
        });

        return data;
    }

}
