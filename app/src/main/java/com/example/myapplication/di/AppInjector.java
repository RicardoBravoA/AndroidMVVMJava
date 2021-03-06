package com.example.myapplication.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.myapplication.app.ProjectApplication;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

public class AppInjector {

    private AppInjector() {
    }

    public static void init(ProjectApplication projectApplication) {

        DaggerAppComponent.builder().application(projectApplication).build().inject(projectApplication);

        projectApplication.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                handleActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                //Do nothing
            }

            @Override
            public void onActivityResumed(Activity activity) {
                //Do nothing
            }

            @Override
            public void onActivityPaused(Activity activity) {
                //Do nothing
            }

            @Override
            public void onActivityStopped(Activity activity) {
                //Do nothing
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                //Do nothing
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                //Do nothing
            }
        });

    }

    private static void handleActivity(Activity activity) {
        if (activity instanceof HasSupportFragmentInjector) {
            AndroidInjection.inject(activity);
        }
        if (activity instanceof FragmentActivity) {
            ((FragmentActivity) activity).getSupportFragmentManager()
                    .registerFragmentLifecycleCallbacks(
                            new FragmentManager.FragmentLifecycleCallbacks() {
                                @Override
                                public void onFragmentCreated(@NonNull FragmentManager fm, @NonNull Fragment fragment,
                                                              Bundle savedInstanceState) {
                                    if (fragment instanceof Injectable) {
                                        AndroidSupportInjection.inject(fragment);
                                    }
                                }
                            }, true);
        }
    }

}
