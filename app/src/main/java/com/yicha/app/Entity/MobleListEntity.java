package com.yicha.app.Entity;

import java.util.List;

/**
 * Created by CYY on 2018/12/4.
 */

public class MobleListEntity {
        /**
         * model : 苹果
         * son : [{"id":1220,"modelName":"苹果iPhone 4S（8GB）"},{"id":389,"modelName":"苹果iPhone 4S（电信版）"},{"id":741,"modelName":"苹果iPhone 5S（双4G）"},{"id":231,"modelName":"苹果iPhone 6 Plus（全网通）"},{"id":401,"modelName":"苹果iPhone 6 Plus（国际版/双4G）"},{"id":198,"modelName":"苹果iPhone 6 Plus（移动4G）"},{"id":170,"modelName":"苹果iPhone 6S Plus（全网通）"},{"id":276,"modelName":"苹果iPhone 6S Plus（国际版/双4G）"},{"id":171,"modelName":"苹果iPhone 6S Plus（移动4G）"},{"id":241,"modelName":"苹果iPhone 6S（全网通）"}]
         */

        private String model;
        private List<SonBean> son;

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public List<SonBean> getSon() {
            return son;
        }

        public void setSon(List<SonBean> son) {
            this.son = son;
        }

        public static class SonBean {
            /**
             * id : 1220
             * modelName : 苹果iPhone 4S（8GB）
             */

            private int id;
            private String modelName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getModelName() {
                return modelName;
            }

            public void setModelName(String modelName) {
                this.modelName = modelName;
            }
        }
}
