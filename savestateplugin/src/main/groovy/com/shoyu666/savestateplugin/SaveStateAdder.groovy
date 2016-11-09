package com.shoyu666.savestateplugin

import org.objectweb.asm.*;

/**
 * Created by n on 2016/11/8.
 */
/**
 * Created by shoyu666 on 2016/11/9.
 */
public class SaveStateAdder {

    public static byte[] hook(File oldClazzFile,String path) {
        AnnoedClassInfo info = new AnnoedClassInfo();
        info.ower(path.split("\\.")[0])
        List<FiledInfo> result = new ArrayList<>();
        info.fileds(result);
        info.oldClazzFile(oldClazzFile);
        ClassReader cr = new ClassReader(info.oldClazzFile.bytes);
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        ClazzVisitor cv = new ClazzVisitor(Opcodes.ASM4, cw, info)
        cr.accept(cv, 0);
        return cw.toByteArray();
    }
}
