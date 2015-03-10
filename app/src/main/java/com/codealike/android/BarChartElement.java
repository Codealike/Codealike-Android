package com.codealike.android;

public class BarChartElement {

    private String name;

    private int percentage;

    private int color;

    public BarChartElement(String name, int percentage, int color)
    {
        this.name = name;
        this.percentage = percentage;
        this.color = color;
    }

    public String getName()
    {
        return  this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getPercentage()
    {
        return this.percentage;
    }

    public void setPercentage(int percentage)
    {
        this.percentage = percentage;
    }

    public int getColor()
    {
        return  this.color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }
}
