package com.example.myapplication.view.ui;

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
import com.example.myapplication.databinding.FragmentProjectDetailsBinding;
import com.example.myapplication.di.Injectable;
import com.example.myapplication.viewmodel.ProjectDetailViewModel;

import javax.inject.Inject;

public class ProjectDetailFragment extends Fragment implements Injectable {

    private static final String KEY_PROJECT_ID = "project_id";
    private FragmentProjectDetailsBinding binding;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_details, container, false);

        // Create and set the adapter for the RecyclerView.
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ProjectDetailViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ProjectDetailViewModel.class);

        viewModel.setProjectID(getArguments().getString(KEY_PROJECT_ID));
        binding.setProjectDetailViewModel(viewModel);
        binding.setIsLoading(true);

        observeViewModel(viewModel);
    }

    private void observeViewModel(final ProjectDetailViewModel viewModel) {
        // Observe project data
        viewModel.getObservableProject().observe(this, projectModel -> {
            if (projectModel != null) {
                binding.setIsLoading(false);
                viewModel.setProject(projectModel);
            }
        });
    }

    public static ProjectDetailFragment forProject(String projectID) {
        ProjectDetailFragment fragment = new ProjectDetailFragment();
        Bundle args = new Bundle();

        args.putString(KEY_PROJECT_ID, projectID);
        fragment.setArguments(args);

        return fragment;
    }

}
