package com.yicha.app.Entity;

import java.util.List;

/**
 * Created by CYY on 2018/12/5.
 */

public class PC_DatalisEntity {
    private List<OptionsBean> options;

    public List<OptionsBean> getOptions()
    {
        return this.options;
    }

    public void setOptions(List<OptionsBean> paramList)
    {
        this.options = paramList;
    }

    public static class OptionsBean
    {
        private String name;
        private String param;
        private List<String> value;

        public String getName()
        {
            return this.name;
        }

        public String getParam()
        {
            return this.param;
        }

        public List<String> getValue()
        {
            return this.value;
        }

        public void setName(String paramString)
        {
            this.name = paramString;
        }

        public void setParam(String paramString)
        {
            this.param = paramString;
        }

        public void setValue(List<String> paramList)
        {
            this.value = paramList;
        }
    }
}
