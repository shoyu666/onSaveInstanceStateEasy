package com.shoyu666.savestateplugin

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by shoyu666 on 2016/11/9.
 */
class SaveSatePlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        def isApp = project.plugins.hasPlugin(AppPlugin)
        if (isApp) {
            L.d("SaveSatePlugin init")
            def android = project.extensions.getByType(AppExtension)
            def transform = new SaveStateTransform()
            android.registerTransform(transform)
        }
    }
}
