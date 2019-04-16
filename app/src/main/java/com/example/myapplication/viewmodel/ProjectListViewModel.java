package com.example.myapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.myapplication.service.model.ProjectModel;
import com.example.myapplication.service.repository.ProjectRepository;

import java.util.List;

public class ProjectListViewModel extends ViewModel {
    private final LiveData<List<ProjectModel>> projectListObservable;

    public ProjectListViewModel() {

        // If any transformation is needed, this can be simply done by Transformations class ...
        projectListObservable = ProjectRepository.getInstance().getProjectList("RicardoBravoA");
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<List<ProjectModel>> getProjectListObservable() {
        return projectListObservable;
    }
}