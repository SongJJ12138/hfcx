package com.hfcx.user.beans;

import java.util.List;

public class CustomDetailsBean {

    /**
     * status : 2
     * startStation : 路北区
     * endStation : 路南区
     * upStation : 火车西站
     * rideDate : 2020-05-29 16:00
     * price : 0.1
     * peopleNum : 1
     * plate : 冀B33333
     * gonePeopleNum : 1
     * address : null
     * elTicket : 200518144833000001
     * pLists : [{"name":"熊巨铎","phone":"13739850622","idCard":"130221197608090010"}]
     * createDate : 1589784502000
     * orderNum : NEWPERSONAL_200518144821000001
     */
    private int status;
    private String startStation;
    private String endStation;
    private String upStation;
    private String rideDate;
    private double price;
    private int peopleNum;
    private String plate;
    private int gonePeopleNum;
    private String address;
    private String elTicket;
    private long createDate;
    private String orderNum;
    private List<PListsBean> pLists;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getUpStation() {
        return upStation;
    }

    public void setUpStation(String upStation) {
        this.upStation = upStation;
    }

    public String getRideDate() {
        return rideDate;
    }

    public void setRideDate(String rideDate) {
        this.rideDate = rideDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getGonePeopleNum() {
        return gonePeopleNum;
    }

    public void setGonePeopleNum(int gonePeopleNum) {
        this.gonePeopleNum = gonePeopleNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getElTicket() {
        return elTicket;
    }

    public void setElTicket(String elTicket) {
        this.elTicket = elTicket;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public List<PListsBean> getPLists() {
        return pLists;
    }

    public void setPLists(List<PListsBean> pLists) {
        this.pLists = pLists;
    }

    public static class PListsBean {
        /**
         * name : 熊巨铎
         * phone : 13739850622
         * idCard : 130221197608090010
         */

        private String name;
        private String phone;
        private String idCard;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }
    }
}
