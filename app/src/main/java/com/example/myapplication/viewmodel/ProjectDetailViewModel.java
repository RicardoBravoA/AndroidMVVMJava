package com.example.myapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.example.myapplication.service.model.ProjectModel;
import com.example.myapplication.service.repository.ProjectRepository;

import javax.inject.Inject;

public class ProjectDetailViewModel extends ViewModel {

    private final LiveData<ProjectModel> projectObservable;
    private final MutableLiveData<String> projectID;
    public ObservableField<ProjectModel> project = new ObservableField<>();

    @Inject
    ProjectDetailViewModel(ProjectRepository projectRepository) {
        this.projectID = new MutableLiveData<>();

        projectObservable = projectRepository.getProjectDetails("RicardoBravoA", projectID.getValue());

    }

    public LiveData<ProjectModel> getObservableProject() {
        return projectObservable;
    }

    public void setProject(ProjectModel project) {
        this.project.set(project);
    }

    public void setProjectID(String projectID) {
        this.projectID.setValue(projectID);
    }
}
