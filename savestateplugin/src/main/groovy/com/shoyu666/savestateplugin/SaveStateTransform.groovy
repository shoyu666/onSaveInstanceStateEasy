package com.shoyu666.savestateplugin

import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.Format

/**
 * Created by shoyu666 on 2016/11/9.
 */

public class SaveStateTransform extends Transform {
    @Override
    public String getName() {
        return "SaveStateTransform";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return Collections.singleton(QualifiedContent.DefaultContentType.CLASSES)
    }

    @Override
    public Set<QualifiedContent.Scope> getScopes() {
        return Collections.singleton(QualifiedContent.Scope.PROJECT)
    }

    @Override
    public boolean isIncremental() {
        return false;
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        L.d("begin transform")
        def outDir = transformInvocation.outputProvider.getContentLocation("SaveStateTransformClass", outputTypes, scopes, Format.DIRECTORY)
        outDir.deleteDir()
        outDir.mkdirs()
        transformInvocation.inputs.each {
            it.directoryInputs.each {
                int pathBitLen = it.file.toString().length()
                it.file.traverse {
                    def path = "${it.toString().substring(pathBitLen)}"
                    if (it.isDirectory()) {
                        new File(outDir, path).mkdirs()
                    } else {
                        if (!path.endsWith("BuildConfig.class")) {
                            File newFile = new File(outDir, path);
                            newFile.bytes = SaveStateAdder.hook(it,path)
                        }
                    }
                }
            }
        }
        L.d("end transform")
    }
}
