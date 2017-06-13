package com.wuhainan.utils;

/**
 * 常量
 * <br>Created by Admin on 2017/5/9.
 * <br>星期二 at 10:11.
 */
public class Constant {
    /**
     * 超级管理员ID
     */
    public static final long SUPER_ADMIN = 1L;
    /**
     * 管理员角色ID
     */
    public static final long SUPER_ROLE = 1L;

    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0, "目录"),
        /**
         * 菜单
         */
        MENU(1, "菜单"),
        /**
         * 按钮
         */
        BUTTON(2, "按钮");

        private int value;
        private String description;

        MenuType(int value, String description) {
            this.value = value;
            this.description = description;
        }

        public int getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }
    }
}
