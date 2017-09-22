package com.fosun.tableObjects;

import java.io.Serializable;

import org.apache.spark.sql.Row;

public class YongAn implements Serializable{
	// original data
	public String expiration_date;
	public String accident_place;
	public String certi_type;
	public String license_no;
	public String purchase_price;
	public String c_certf_cde;
	public String tel;
	public String account_name;
	public String account_code;
	public String bank_name;
	public String mobile;
	public String c_certf_cls;
	public String address;
	public String certi_code;
	public String brand_name_jy;
	public String party_id;
	public String updated_ts;
	public String party_type;
	public String name;
	public String accdt_time;
	public String case_no;
	public String fosun_source;
	public String gender;
	public String statistic_prm;
	public String fosun_create_date_day;
	public String fosunuuid;
	
	public CleanedPerson cleanedPerson = new CleanedPerson();
	
	// cleaned data
	
	
	public YongAn (Row row) {
		this.expiration_date = row.get(0) == null ? null : row.get(0).toString();
		this.accident_place = row.get(1) == null ? null : row.get(1).toString();
		this.certi_type = row.get(2) == null ? null : row.get(2).toString();
		this.license_no = row.get(3) == null ? null : row.get(3).toString();
		this.purchase_price = row.get(4) == null ? null : row.get(4).toString();
		this.c_certf_cde = row.get(5) == null ? null : row.get(5).toString();
		this.tel = row.get(6) == null ? null : row.get(6).toString();
		this.account_name = row.get(7) == null ? null : row.get(7).toString();
		this.account_code = row.get(8) == null ? null : row.get(8).toString();
		this.bank_name = row.get(9) == null ? null : row.get(9).toString();
		this.mobile = row.get(10) == null ? null : row.get(10).toString();
		this.c_certf_cls = row.get(11) == null ? null : row.get(11).toString();
		this.address = row.get(12) == null ? null : row.get(12).toString();
		this.certi_code = row.get(13) == null ? null : row.get(13).toString();
		this.brand_name_jy = row.get(14) == null ? null : row.get(14).toString();
		this.party_id = row.get(15) == null ? null : row.get(15).toString();
		this.updated_ts = row.get(16) == null ? null : row.get(16).toString();
		this.party_type = row.get(17) == null ? null : row.get(17).toString();
		this.name = row.get(18) == null ? null : row.get(18).toString();
		this.accdt_time = row.get(19) == null ? null : row.get(19).toString();
		this.case_no = row.get(20) == null ? null : row.get(20).toString();
		this.fosun_source = row.get(21) == null ? null : row.get(21).toString();
		this.gender = row.get(22) == null ? null : row.get(22).toString();
		this.statistic_prm = row.get(23) == null ? null : row.get(23).toString();
		this.fosun_create_date_day = row.get(24) == null ? null : row.get(24).toString();
		this.fosunuuid = row.get(25) == null ? null : row.get(25).toString();	
	}
}



