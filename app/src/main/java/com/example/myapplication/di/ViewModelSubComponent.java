package com.example.myapplication.di;

import com.example.myapplication.viewmodel.ProjectListViewModel;
import com.example.myapplication.viewmodel.ProjectDetailViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    ProjectListViewModel projectListViewModel();

    ProjectDetailViewModel projectViewModel();

}
