package org.r.idea.plugin.generator.impl;


import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.javadoc.PsiDocToken;

/**
 * @ClassName Utils
 * @Author Casper
 * @DATE 2019/6/21 14:31
 **/
public class Utils {


    public static String[] baseClass = {"String", "Long", "int", "long", "char", "Integer", "double", "Double",
        "BigDecimal","LocalDateTime","BigDecimal","boolean","Boolean","BindingResult"
};


    /**
     * 判断指定的额类名是否属于基本类
     *
     * @param classShortName 给定的类名
     */
    public static boolean isBaseClass(String classShortName) {
        for (String tmp : baseClass) {
            if (classShortName.contains(tmp)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 根据给定的注释集合构造注释字符串
     *
     * @param desc 给定的注释集合
     */
    public static String getDocCommentDesc(PsiElement[] desc) {
        StringBuilder sb = new StringBuilder();
        if (desc == null || desc.length == 0) {
            return "";
        }
        for (PsiElement tmp : desc) {
            if (tmp instanceof PsiDocToken) {
                sb.append(tmp.getText());
            }
        }
        return sb.toString();
    }


    /**
     * 判断指定的注解集合中是否包括指定的注解
     *
     * @param qualifiedName 指定的注解全名称
     * @param src 指定的注解集合
     */
    public static boolean isContainAnnotation(String qualifiedName, PsiAnnotation[] src) {
        return findAnnotationByName(qualifiedName, src) != null;
    }

    /**
     * 在指定的注解集合中找出指定的注解
     *
     * @param qualifiedName 指定的注解全名称
     * @param src 指定的注解集合
     */
    public static PsiAnnotation findAnnotationByName(String qualifiedName, PsiAnnotation[] src) {
        if (null == src || qualifiedName == null || src.length == 0) {
            return null;
        }
        for (PsiAnnotation annotation : src) {
            if (qualifiedName.equals(annotation.getQualifiedName())) {
                return annotation;
            }
        }
        return null;
    }

}
