package com.yicha.app.Entity;

import java.util.List;

/**
 * Created by CYY on 2018/11/25.
 */

public class DatalisEntity {
        /**
         * id : 1220
         * model : 苹果
         * modelName : 苹果iPhone 4S（8GB）
         * modelAlias : 苹果4s
         * modelSubtitle : Siri语音，IOS7系统，1080P视频录制
         * modelPrice : 788
         * mainScreenSize : 3.5英寸
         * battery : 1420mAh
         * cpuModel : 苹果 A5
         * romCapacity : 8GB
         * ramCapacity : 512MB
         * size : 115.2x58.6x9.3mm
         * os : iOS 7.0
         * listingDate : 2011年10月
         * bodyColor : 黑色，白色
         * options : [{"name":"内存","value":["8GB"]},{"name":"颜色","value":["黑色","白色"]}]
         */

        private int id;
        private String model;
        private String modelName;
        private String modelAlias;
        private String modelSubtitle;
        private String modelPrice;
        private String mainScreenSize;
        private String battery;
        private String cpuModel;
        private String romCapacity;
        private String ramCapacity;
        private String size;
        private String os;
        private String listingDate;
        private String bodyColor;
        private List<OptionsBean> options;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public String getModelAlias() {
            return modelAlias;
        }

        public void setModelAlias(String modelAlias) {
            this.modelAlias = modelAlias;
        }

        public String getModelSubtitle() {
            return modelSubtitle;
        }

        public void setModelSubtitle(String modelSubtitle) {
            this.modelSubtitle = modelSubtitle;
        }

        public String getModelPrice() {
            return modelPrice;
        }

        public void setModelPrice(String modelPrice) {
            this.modelPrice = modelPrice;
        }

        public String getMainScreenSize() {
            return mainScreenSize;
        }

        public void setMainScreenSize(String mainScreenSize) {
            this.mainScreenSize = mainScreenSize;
        }

        public String getBattery() {
            return battery;
        }

        public void setBattery(String battery) {
            this.battery = battery;
        }

        public String getCpuModel() {
            return cpuModel;
        }

        public void setCpuModel(String cpuModel) {
            this.cpuModel = cpuModel;
        }

        public String getRomCapacity() {
            return romCapacity;
        }

        public void setRomCapacity(String romCapacity) {
            this.romCapacity = romCapacity;
        }

        public String getRamCapacity() {
            return ramCapacity;
        }

        public void setRamCapacity(String ramCapacity) {
            this.ramCapacity = ramCapacity;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getOs() {
            return os;
        }

        public void setOs(String os) {
            this.os = os;
        }

        public String getListingDate() {
            return listingDate;
        }

        public void setListingDate(String listingDate) {
            this.listingDate = listingDate;
        }

        public String getBodyColor() {
            return bodyColor;
        }

        public void setBodyColor(String bodyColor) {
            this.bodyColor = bodyColor;
        }

        public List<OptionsBean> getOptions() {
            return options;
        }

        public void setOptions(List<OptionsBean> options) {
            this.options = options;
        }

        public static class OptionsBean {
            /**
             * name : 内存
             * value : ["8GB"]
             */

            private String name;
            private List<String> value;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getValue() {
                return value;
            }

            public void setValue(List<String> value) {
                this.value = value;
            }
        }
}
