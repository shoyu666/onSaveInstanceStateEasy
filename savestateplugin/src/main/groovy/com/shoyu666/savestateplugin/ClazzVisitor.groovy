package com.shoyu666.savestateplugin;

import org.objectweb.asm.*;

/**
 * Created by shoyu666 on 2016/11/9.
 */

public class ClazzVisitor extends BaseAdapter {
    AnnoedClassInfo info;
    public ClazzVisitor(int api) {
        super(api);
    }

    public ClazzVisitor(int api, ClassVisitor cv, AnnoedClassInfo info) {
        super(api, cv);
        this.info = info;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if (MethodName_OnSaveInstanceState.equals(name)) {
            L.d("has OnSaveInstanceState method")
            info.hasOSS(true);
        }
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        FieldVisitor before = super.visitField(access, name, desc, signature, value)
        FiledInfo fieldInfo = new FiledInfo.Builder().access(access).name(name).desc(desc).signature(signature).value(value).build();
        SaveStateFiledVisitor newSaveStateFiledVisitor = new SaveStateFiledVisitor(Opcodes.ASM4, before, info, fieldInfo);
        return newSaveStateFiledVisitor;
    }

    @Override
    public void visitEnd() {
        if (info != null && info.annoedFileds.size() > 0&&!info.hasOSS) {
            hookWithOutOSS(info);
        }
        super.visitEnd();
    }
}
