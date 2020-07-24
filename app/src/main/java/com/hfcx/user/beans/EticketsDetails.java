package com.hfcx.user.beans;

import java.util.List;

public class EticketsDetails {

    /**
     * code : 200
     * data : {"id":68,"contactMobile":"13223169066","contactName":"兔兔","otaOrderId":"TRAVEL_191231182623000001","productId":"860076","quantity":1,"salePrice":0.01,"createDate":"2019-12-31","type":0,"userId":4438,"sn":null,"orderId":"1211956871818711042","travelEtickets":[{"id":4,"travelTicketId":68,"credentialsNo":"123","sn":"123","url":"123","ticket":null,"isUse":0},{"id":5,"travelTicketId":68,"credentialsNo":"12","sn":"123","url":"123","ticket":null,"isUse":0}]}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 68
         * contactMobile : 13223169066
         * contactName : 兔兔
         * otaOrderId : TRAVEL_191231182623000001
         * productId : 860076
         * quantity : 1
         * salePrice : 0.01
         * createDate : 2019-12-31
         * type : 0
         * userId : 4438
         * sn : null
         * orderId : 1211956871818711042
         * travelEtickets : [{"id":4,"travelTicketId":68,"credentialsNo":"123","sn":"123","url":"123","ticket":null,"isUse":0},{"id":5,"travelTicketId":68,"credentialsNo":"12","sn":"123","url":"123","ticket":null,"isUse":0}]
         */

        private int id;
        private String contactMobile;
        private String contactName;
        private String otaOrderId;
        private String productId;
        private int quantity;
        private double salePrice;
        private String createDate;
        private int type;
        private int userId;
        private Object sn;
        private String orderId;
        private List<TravelEticketsBean> travelEtickets;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContactMobile() {
            return contactMobile;
        }

        public void setContactMobile(String contactMobile) {
            this.contactMobile = contactMobile;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getOtaOrderId() {
            return otaOrderId;
        }

        public void setOtaOrderId(String otaOrderId) {
            this.otaOrderId = otaOrderId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(double salePrice) {
            this.salePrice = salePrice;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Object getSn() {
            return sn;
        }

        public void setSn(Object sn) {
            this.sn = sn;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public List<TravelEticketsBean> getTravelEtickets() {
            return travelEtickets;
        }

        public void setTravelEtickets(List<TravelEticketsBean> travelEtickets) {
            this.travelEtickets = travelEtickets;
        }

        public static class TravelEticketsBean {
            /**
             * id : 4
             * travelTicketId : 68
             * credentialsNo : 123
             * sn : 123
             * url : 123
             * ticket : null
             * isUse : 0
             */

            private int id;
            private int travelTicketId;
            private String credentialsNo;
            private String sn;
            private String url;
            private Object ticket;
            private int isUse;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTravelTicketId() {
                return travelTicketId;
            }

            public void setTravelTicketId(int travelTicketId) {
                this.travelTicketId = travelTicketId;
            }

            public String getCredentialsNo() {
                return credentialsNo;
            }

            public void setCredentialsNo(String credentialsNo) {
                this.credentialsNo = credentialsNo;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getTicket() {
                return ticket;
            }

            public void setTicket(Object ticket) {
                this.ticket = ticket;
            }

            public int getIsUse() {
                return isUse;
            }

            public void setIsUse(int isUse) {
                this.isUse = isUse;
            }
        }
    }
}
