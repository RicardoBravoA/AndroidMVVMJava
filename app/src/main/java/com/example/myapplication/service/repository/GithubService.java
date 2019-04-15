package com.example.myapplication.service.repository;

import com.example.myapplication.service.model.ProjectModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface GithubService {

    String HTTPS_API_GITHUB_URL = "https://api.github.com/";

    @GET("users/{user}/repos")
    Call<List<ProjectModel>> getProjectList(@Path("user") String user);

    @GET("/repos/{user}/{reponame}")
    Call<ProjectModel> getProjectDetails(@Path("user") String user, @Path("reponame") String projectName);

}
