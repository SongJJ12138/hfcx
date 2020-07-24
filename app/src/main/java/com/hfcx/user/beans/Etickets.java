package com.hfcx.user.beans;

import java.util.List;

public class Etickets {

    /**
     * code : 200
     * data : [{"id":1,"contactMobile":"13591256466","contactName":"于迪","otaOrderId":"TIC_19961244664","productId":"1564987944","quantity":2,"salePrice":20,"createDate":"1996-12-0517:57:06","type":1,"userId":10,"sn":null,"orderId":null,"travelEtickets":[{"id":1,"travelTicketId":1,"credentialsNo":"123","sn":"123","url":"123","ticket":null},{"id":2,"travelTicketId":1,"credentialsNo":"123","sn":"132","url":"123","ticket":null},{"id":3,"travelTicketId":1,"credentialsNo":"123","sn":"123","url":"123","ticket":null}]}]
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * contactMobile : 13591256466
         * contactName : 于迪
         * otaOrderId : TIC_19961244664
         * productId : 1564987944
         * quantity : 2
         * salePrice : 20.0
         * createDate : 1996-12-0517:57:06
         * type : 1
         * userId : 10
         * sn : null
         * orderId : null
         * travelEtickets : [{"id":1,"travelTicketId":1,"credentialsNo":"123","sn":"123","url":"123","ticket":null},{"id":2,"travelTicketId":1,"credentialsNo":"123","sn":"132","url":"123","ticket":null},{"id":3,"travelTicketId":1,"credentialsNo":"123","sn":"123","url":"123","ticket":null}]
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
        private Object orderId;
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

        public Object getOrderId() {
            return orderId;
        }

        public void setOrderId(Object orderId) {
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
             * id : 1
             * travelTicketId : 1
             * credentialsNo : 123
             * sn : 123
             * url : 123
             * ticket : null
             */

            private int id;
            private int travelTicketId;
            private String credentialsNo;
            private String sn;
            private String url;
            private Object ticket;

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
        }
    }
}
