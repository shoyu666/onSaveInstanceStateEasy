package com.shoyu666.savestateplugin

import org.objectweb.asm.*;

/**
 * Created by shoyu666 on 2016/11/9.
 */

public abstract class BaseAdapter extends ClassVisitor {
    public static final String DescBundle = "Landroid/os/Bundle;";
    public static final String DescSerializable = "Ljava/io/Serializable;";
    public static final String MethodName_OnSaveInstanceState = "onSaveInstanceState";
    public static final String DescSaveStatesAnnoe = "Lcom/shoyu666/savestate/SaveStatesAnno;";
    public static final String DescString = "Ljava/lang/String;";

    BaseAdapter(int api) {
        super(api)
    }

    BaseAdapter(int api, ClassVisitor cv) {
        super(api, cv)
    }

    public void hookWithOutOSS(AnnoedClassInfo info) {
        MethodVisitor mv = cv.visitMethod(Opcodes.ACC_PUBLIC, "onSaveInstanceState", "(" + DescBundle + ")V", null, null);
        mv.visitCode();
        info.annoedFileds.each {
            putSerializableData(mv, it, info.ower);
        }
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "android/support/v7/app/AppCompatActivity", "onSaveInstanceState", "(" + DescBundle + ")V", false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }

    public void putSerializableData(MethodVisitor mv, FiledInfo info, String ower) {
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitLdcInsn(info.bundleKey)
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(Opcodes.GETFIELD, ower, info.name, info.desc);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "android/os/Bundle", "putSerializable", "(" + DescString + DescSerializable + ")V", false);
    }
}
