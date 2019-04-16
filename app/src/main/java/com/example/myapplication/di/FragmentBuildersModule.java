package com.example.myapplication.di;

import com.example.myapplication.view.ui.ProjectDetailFragment;
import com.example.myapplication.view.ui.ProjectListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProjectDetailFragment contributeProjectDetailFragment();

    @ContributesAndroidInjector
    abstract ProjectListFragment contributeProjectListFragment();

}
