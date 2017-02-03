package com.example.yipengzhou.thesizebook;

import java.io.Serializable;

/**
 * Created by yipengzhou on 2017/1/31.
 */

public class TheSize implements Serializable {

    private String _name, _date, _neck, _bust, _chest,
            _waist, _hip, _inseam, _comment;

    public TheSize (String name, String date, String neck,
                    String bust, String chest, String waist,
                    String hip, String inseam, String comment) {
        _name = name;
        _date = date;
        _neck = neck;
        _bust = bust;
        _chest = chest;
        _waist = waist;
        _hip = hip;
        _inseam = inseam;
        _comment = comment;
    }

    public String getName(){
        return _name;
    }
    public String getDate(){
        return _date;
    }
    public String getNeck(){
        return _neck;
    }
    public String getBust(){
        return _bust;
    }
    public String getChest(){
        return _chest;
    }
    public String getWaist(){
        return _waist;
    }
    public String getHip(){
        return _hip;
    }
    public String getInseam(){
        return _inseam;
    }
    public String getComment(){
        return _comment;
    }

}
