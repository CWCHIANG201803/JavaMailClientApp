package com.chaowei.view;

public enum ColorTheme {
    DEFAULT,
    LIGHT,
    DARK;

    public static String getCssPath(ColorTheme colorTheme){
        switch (colorTheme){
            case LIGHT:
                return "css/themeLight.css";
            case DEFAULT:
                return "css/themeDefault.css";
            case DARK:
                return "css/themeDark.css";
            default:
                return null;
        }
    }

}
