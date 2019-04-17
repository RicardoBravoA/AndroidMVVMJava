package com.example.myapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.example.myapplication.service.model.ProjectModel;
import com.example.myapplication.service.repository.ProjectRepository;

import javax.inject.Inject;

public class ProjectDetailViewModel extends ViewModel {

    private LiveData<ProjectModel> projectObservable;
    private final MutableLiveData<String> projectID;
    public ObservableField<ProjectModel> project = new ObservableField<>();
    @Inject
    ProjectRepository projectRepository;

    @Inject
    ProjectDetailViewModel() {
        this.projectID = new MutableLiveData<>();
        this.projectObservable = new MutableLiveData<>();
    }

    public LiveData<ProjectModel> getObservableProject() {
        return projectObservable;
    }

    public void setProject(ProjectModel project) {
        this.project.set(project);
    }

    public void setProjectID(String projectID) {
        this.projectID.setValue(projectID);

        projectObservable = projectRepository.getProjectDetails("RicardoBravoA", this.projectID.getValue());

    }

}
