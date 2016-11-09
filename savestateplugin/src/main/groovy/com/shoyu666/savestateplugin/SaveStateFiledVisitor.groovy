package com.shoyu666.savestateplugin

import org.objectweb.asm.*

/**
 * Created by shoyu666 on 2016/11/9.
 */

public class SaveStateFiledVisitor extends FieldVisitor {

    AnnoedClassInfo clazzInfo;
    FiledInfo fieldInfo;

    public SaveStateFiledVisitor(int api) {
        super(api);
    }

    public SaveStateFiledVisitor(int api, FieldVisitor fv, AnnoedClassInfo clazzInfo, FiledInfo fieldInfo) {
        super(api, fv);
        this.clazzInfo = clazzInfo;
        this.fieldInfo = fieldInfo;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        L.d(desc)
        if (BaseAdapter.DescSaveStatesAnnoe.equals(desc)) {
            if (clazzInfo == null) {
                throw new RuntimeException("clazzInfo can not be null")
            }
            clazzInfo.addAnnoedFiled(fieldInfo);
            AnnotationVisitor before = super.visitAnnotation(desc, visible)
            System.out.println("###############################"+fieldInfo)
            SaveStateAnnotationVisitor newSaveStateAnnotationVisitor = new SaveStateAnnotationVisitor(Opcodes.ASM4,before,fieldInfo);
            return newSaveStateAnnotationVisitor;
        } else {
            return super.visitAnnotation(desc, visible);
        }

    }
}
