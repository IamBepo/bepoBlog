package com.blog.myblogsystem.enums;

public enum RecommendEnum {
    ONLY_RECOMMEND_LIST(1,"LIST"), //仅列表推荐标识符
    ONLY_RECOMMEND_WALK(2,"WALK"), //仅走马灯推荐标识符
    ALL_RECOMMEND(3,"ALL"); //都推荐标识符

    private final Integer type;
    private final String name;

    /**
     * 构造器
     * @param type
     * @param name
     */
    RecommendEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }
}