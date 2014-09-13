package com.codealike.android.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 8/11/14.
 */
public class Technology {

    public List<String> Categories;

    public String Extension;

    public String Name;

    public double Percentage;

    public Technology()
    {
        this.Categories = new ArrayList<String>();
    }
}
