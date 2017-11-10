package com.fosun.tableObjects.rawTables;

import java.io.Serializable;

import org.apache.spark.sql.Row;

import com.fosun.tableObjects.RawTable;
import com.fosun.tableObjects.targetTables.CleanedPerson;

public class YongAn extends RawTable{

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
	public String fosun_create_date;
	public String fosunuuid;
	

	// cleaned data

	@Override
	public void extractRow(Row row) {
		this.expiration_date = row.getAs("EXPIRATION_DATE");
		this.accident_place = row.getAs("ACCIDENT_PLACE") ;
		this.certi_type = row.getAs("CERTI_TYPE");
		this.license_no = row.getAs("LICENSE_NO");
		this.purchase_price = row.getAs("PURCHASE_PRICE");
		this.c_certf_cde = row.getAs("C_CERTF_CDE");
		this.tel = row.getAs("TEL");
		this.account_name = row.getAs("ACCOUNT_NAME");
		this.account_code = row.getAs("ACCOUNT_CODE");
		this.bank_name = row.getAs("BANK_NAME");
		this.mobile = row.getAs("MOBILE");
		this.c_certf_cls = row.getAs("C_CERTF_CLS");
		this.address = row.getAs("ADDRESS");
		this.certi_code = row.getAs("CERTI_CODE");
		this.brand_name_jy = row.getAs("BRAND_NAME_JY");
		this.party_id = row.getAs("PARTY_ID");
		this.updated_ts = row.getAs("UPDATED_TS");
		this.party_type = row.getAs("PARTY_TYPE");
		this.name = row.getAs("NAME");
		this.accdt_time = row.getAs("ACCDT_TIME");
		this.case_no = row.getAs("CASE_NO");
		this.fosun_source = row.getAs("fosun_source");
		this.gender = row.getAs("GENDER");
		this.statistic_prm = row.getAs("STATISTIC_PRM");
		this.fosun_create_date_day = row.getAs("fosun_create_date_day");
		this.fosun_create_date = row.getAs("fosun_create_date");
		this.fosunuuid = row.getAs("fosunUUID");
	}
}



