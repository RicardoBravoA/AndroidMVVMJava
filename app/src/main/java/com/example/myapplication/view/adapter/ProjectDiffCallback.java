package com.example.myapplication.view.adapter;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.myapplication.service.model.ProjectModel;

import java.util.List;

public class ProjectDiffCallback extends DiffUtil.Callback {

    private final List<ProjectModel> oldProjectModelList;
    private final List<ProjectModel> newProjectModelList;

    ProjectDiffCallback(List<ProjectModel> oldProjectModelList, List<ProjectModel> newProjectModelList) {
        this.oldProjectModelList = oldProjectModelList;
        this.newProjectModelList = newProjectModelList;
    }

    @Override
    public int getOldListSize() {
        return oldProjectModelList.size();
    }

    @Override
    public int getNewListSize() {
        return newProjectModelList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldProjectModelList.get(oldItemPosition).getId() == newProjectModelList.get(
                newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final ProjectModel oldProjectModel = oldProjectModelList.get(oldItemPosition);
        final ProjectModel newProjectModel = newProjectModelList.get(newItemPosition);

        return oldProjectModel.getName().equals(newProjectModel.getName());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
