package com.yicha.app.Entity;

import java.util.List;

/**
 * Created by CYY on 2018/11/25.
 */

public class HoemPageEntity {
        /**
         * model : 苹果
         * son : [{"id":1220,"modelName":"苹果iPhone 4S（8GB）"},{"id":389,"modelName":"苹果iPhone 4S（电信版）"},{"id":741,"modelName":"苹果iPhone 5S（双4G）"},{"id":231,"modelName":"苹果iPhone 6 Plus（全网通）"},{"id":401,"modelName":"苹果iPhone 6 Plus（国际版/双4G）"},{"id":198,"modelName":"苹果iPhone 6 Plus（移动4G）"},{"id":170,"modelName":"苹果iPhone 6S Plus（全网通）"},{"id":276,"modelName":"苹果iPhone 6S Plus（国际版/双4G）"},{"id":171,"modelName":"苹果iPhone 6S Plus（移动4G）"},{"id":241,"modelName":"苹果iPhone 6S（全网通）"},{"id":400,"modelName":"苹果iPhone 6S（国际版/双4G）"},{"id":144,"modelName":"苹果iPhone 6S（移动4G）"},{"id":375,"modelName":"苹果iPhone 6（全网通）"},{"id":454,"modelName":"苹果iPhone 6（国际版/双4G）"},{"id":73,"modelName":"苹果iPhone 7 Plus（全网通）"},{"id":75,"modelName":"苹果iPhone 7 Plus（双4G）"},{"id":74,"modelName":"苹果iPhone 7 Plus（国际版/全网通）"},{"id":51,"modelName":"苹果iPhone 7 Plus（特别版/全网通）"},{"id":111,"modelName":"苹果iPhone 7（全网通）"},{"id":104,"modelName":"苹果iPhone 7（双4G）"},{"id":172,"modelName":"苹果iPhone 7（国际版/全网通）"},{"id":70,"modelName":"苹果iPhone 7（特别版/全网通）"},{"id":49,"modelName":"苹果iPhone 8 Plus（全网通）"},{"id":59,"modelName":"苹果iPhone 8 Plus（国际版/全网通）"},{"id":63,"modelName":"苹果iPhone 8（全网通）"},{"id":80,"modelName":"苹果iPhone 8（国际版/全网通）"},{"id":442,"modelName":"苹果iPhone SE（全网通）"},{"id":334,"modelName":"苹果iPhone SE（国际版）"},{"id":34,"modelName":"苹果iPhone XS Max（全网通）"},{"id":38,"modelName":"苹果iPhone XS（全网通）"},{"id":39,"modelName":"苹果iPhone X（全网通）"},{"id":40,"modelName":"苹果iPhone X（国际版/全网通）"}]
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
