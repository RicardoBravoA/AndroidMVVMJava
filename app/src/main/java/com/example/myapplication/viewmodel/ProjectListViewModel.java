package com.example.myapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.myapplication.service.model.ProjectModel;
import com.example.myapplication.service.repository.ProjectRepository;

import java.util.List;

import javax.inject.Inject;

public class ProjectListViewModel extends AndroidViewModel {

    private final LiveData<List<ProjectModel>> projectListObservable;

    @Inject
    public ProjectListViewModel(@NonNull ProjectRepository projectRepository, @NonNull Application application) {
        super(application);
        projectListObservable = projectRepository.getProjectList("Google");
    }

    public LiveData<List<ProjectModel>> getProjectListObservable() {
        return projectListObservable;
    }

}