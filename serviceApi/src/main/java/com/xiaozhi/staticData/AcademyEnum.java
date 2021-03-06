package com.xiaozhi.staticData;

/**
 * Created by 小智 on 2017/4/18 0018.
 * 学院数据保存对应数据
 */

public enum AcademyEnum{
    Conputer(1, "计算机学院"),
    English(2, "外语学院"),
    Science(3, "理学院"),
    Management(4, "管理学院");

    private int academyId;
    private String academyDescribe;

    AcademyEnum(int academyId, String academyDescribe){
        this.academyId = academyId;
        this.academyDescribe = academyDescribe;
    }

    public int getAcademyId(){
        return academyId;
    }

    public String getAcademyDescribe(){
        return academyDescribe;
    }

    public static String getDescribeByCode(int code){
        for (AcademyEnum academyEnum : AcademyEnum.values()) {
            if (code == academyEnum.academyId) {
                return academyEnum.academyDescribe;
            }
        }
        return null;
    }
}
