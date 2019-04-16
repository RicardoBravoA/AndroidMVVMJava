package com.example.myapplication.view.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentProjectListBinding;
import com.example.myapplication.di.Injectable;
import com.example.myapplication.service.model.ProjectModel;
import com.example.myapplication.view.adapter.ProjectAdapter;
import com.example.myapplication.view.callback.ProjectClickCallback;
import com.example.myapplication.viewmodel.ProjectListViewModel;

import javax.inject.Inject;

public class ProjectListFragment extends Fragment implements ProjectClickCallback, Injectable {

    public static final String TAG = "ProjectListFragment";
    private ProjectAdapter projectAdapter;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentProjectListBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_project_list, container, false);

        projectAdapter = new ProjectAdapter(this);
        binding.projectList.setAdapter(projectAdapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ProjectListViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProjectListViewModel.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(ProjectListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getProjectListObservable().observe(this, projects -> {
            if (projects != null) {
                projectAdapter.setProjectList(projects);
            }
        });
    }

    @Override
    public void onClick(ProjectModel projectModel) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) getActivity()).show(projectModel);
        }
    }

}
