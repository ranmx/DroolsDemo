package com.fosun.tableObjects.targetTables;

import com.fosun.tableObjects.TargetTable;

/**
 * Created by ranmx on 2017/11/3.
 */
public class FosunDB extends TargetTable{

    private String account_number;
    private String gender;
    private String cleanMode;
    private String real_name;
    private String third_party_id;
    private String car_type;
    private String bank;
    private String home_address;
    private String fosun_create_date;
    private String car_price;
    private String car_id;
    private String fosun_create_date_day;
    private String cleanSource;
    private String mobile;
    private String birth;
    private String cleanTableName;
    private String citizen_id;
    private String home_city;
    private String home_province;
    private String third_party_name;

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCleanMode() {
        return cleanMode;
    }

    public void setCleanMode(String cleanMode) {
        this.cleanMode = cleanMode;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getThird_party_id() {
        return third_party_id;
    }

    public void setThird_party_id(String third_party_id) {
        this.third_party_id = third_party_id;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getHome_address() {
        return home_address;
    }

    public void setHome_address(String home_address) {
        this.home_address = home_address;
    }

    public String getFosun_create_date() {
        return fosun_create_date;
    }

    public void setFosun_create_date(String fosun_create_date) {
        this.fosun_create_date = fosun_create_date;
    }

    public String getCar_price() {
        return car_price;
    }

    public void setCar_price(String car_price) {
        this.car_price = car_price;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getFosun_create_date_day() {
        return fosun_create_date_day;
    }

    public void setFosun_create_date_day(String fosun_create_date_day) {
        this.fosun_create_date_day = fosun_create_date_day;
    }

    public String getCleanSource() {
        return cleanSource;
    }

    public void setCleanSource(String cleanSource) {
        this.cleanSource = cleanSource;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getCleanTableName() {
        return cleanTableName;
    }

    public void setCleanTableName(String cleanTableName) {
        this.cleanTableName = cleanTableName;
    }

    public String getCitizen_id() {
        return citizen_id;
    }

    public void setCitizen_id(String citizen_id) {
        this.citizen_id = citizen_id;
    }

    public String getHome_city() {
        return home_city;
    }

    public void setHome_city(String home_city) {
        this.home_city = home_city;
    }

    public String getHome_province() {
        return home_province;
    }

    public void setHome_province(String home_province) {
        this.home_province = home_province;
    }

    public String getThird_party_name() {
        return third_party_name;
    }

    public void setThird_party_name(String third_party_name) {
        this.third_party_name = third_party_name;
    }

    @Override
    public String toString() {
        return "FosunDB{" +
          "account_number='" + account_number + '\'' +
          ", gender='" + gender + '\'' +
          ", cleanMode='" + cleanMode + '\'' +
          ", real_name='" + real_name + '\'' +
          ", third_party_id='" + third_party_id + '\'' +
          ", car_type='" + car_type + '\'' +
          ", bank='" + bank + '\'' +
          ", home_address='" + home_address + '\'' +
          ", fosun_create_date='" + fosun_create_date + '\'' +
          ", car_price='" + car_price + '\'' +
          ", car_id='" + car_id + '\'' +
          ", fosun_create_date_day='" + fosun_create_date_day + '\'' +
          ", cleanSource='" + cleanSource + '\'' +
          ", mobile='" + mobile + '\'' +
          ", birth='" + birth + '\'' +
          ", cleanTableName='" + cleanTableName + '\'' +
          ", citizen_id='" + citizen_id + '\'' +
          ", home_city='" + home_city + '\'' +
          ", home_province='" + home_province + '\'' +
          ", third_party_name='" + third_party_name + '\'' +
          '}';
    }
}
