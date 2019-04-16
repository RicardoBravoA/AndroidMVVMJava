package com.example.myapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.myapplication.service.model.ProjectModel;
import com.example.myapplication.service.repository.ProjectRepository;

public class ProjectViewModel extends ViewModel {

    private final LiveData<ProjectModel> projectObservable;
    private final String projectID;

    public ObservableField<ProjectModel> project = new ObservableField<>();

    ProjectViewModel(final String projectID) {
        this.projectID = projectID;

        projectObservable = ProjectRepository.getInstance().getProjectDetails("RicardoBravoA", projectID);
    }

    public LiveData<ProjectModel> getObservableProject() {
        return projectObservable;
    }

    public void setProject(ProjectModel project) {
        this.project.set(project);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        private final String projectID;

        public Factory(String projectID) {
            this.projectID = projectID;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            //noinspection unchecked
            return (T) new ProjectViewModel(projectID);
        }
    }

}
