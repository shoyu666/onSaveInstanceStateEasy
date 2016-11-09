package com.shoyu666.savestateplugin

import org.objectweb.asm.AnnotationVisitor


/**
 * Created by shoyu666 on 2016/11/9.
 */

public class SaveStateAnnotationVisitor extends AnnotationVisitor {
    FiledInfo fieldInfo;

    SaveStateAnnotationVisitor(int i) {
        super(i)
    }

    public SaveStateAnnotationVisitor(int i, AnnotationVisitor annotationVisitor, FiledInfo fieldInfo) {
        super(i, annotationVisitor)
        this.fieldInfo = fieldInfo;
    }

    @Override
    void visit(String s, Object o) {
        L.d(s + o);
        if ("value".equals(s) && o instanceof String) {
            fieldInfo.bundleKey = (String) o;
        }
        super.visit(s, o)
    }
}
