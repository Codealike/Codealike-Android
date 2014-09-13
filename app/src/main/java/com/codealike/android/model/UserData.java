package com.codealike.android.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 8/11/14.
 */
public class UserData {

    public Activity ActivityPercentage;

    public long ActivityTimeInMs;

    public List<Technology> ByTechnologies;

    public List<Date> DaysOnFire;

    public Date SinceUtc;

    public Date ToUtc;

    public UserData()
    {
        this.ByTechnologies = new ArrayList<Technology>();
        this.DaysOnFire = new ArrayList<Date>();
    }
}
