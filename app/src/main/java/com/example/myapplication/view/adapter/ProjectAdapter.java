package com.example.myapplication.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ProjectListItemBinding;
import com.example.myapplication.service.model.ProjectModel;
import com.example.myapplication.view.callback.ProjectClickCallback;

import java.util.ArrayList;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    private List<ProjectModel> projectModelList;

    @Nullable
    private final ProjectClickCallback projectClickCallback;

    public ProjectAdapter(@Nullable ProjectClickCallback projectClickCallback) {
        this.projectClickCallback = projectClickCallback;
        projectModelList = new ArrayList<>();
    }

    public void setProjectList(final List<ProjectModel> projectModelList) {
        ProjectDiffCallback projectDiffCallback = new ProjectDiffCallback(this.projectModelList, projectModelList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(projectDiffCallback);
        diffResult.dispatchUpdatesTo(this);
        this.projectModelList.clear();
        this.projectModelList.addAll(projectModelList);

    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ProjectListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.project_list_item,
                        viewGroup, false);

        binding.setCallback(projectClickCallback);

        return new ProjectViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder projectViewHolder, int i) {
        projectViewHolder.binding.setProject(projectModelList.get(i));
        projectViewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return projectModelList == null ? 0 : projectModelList.size();
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {

        final ProjectListItemBinding binding;

        ProjectViewHolder(ProjectListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
