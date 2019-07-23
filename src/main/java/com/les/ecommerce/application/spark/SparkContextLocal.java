package com.les.ecommerce.application.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;

public class SparkContextLocal {
	
	private SparkConf conf;
	
	private SparkContext ctx;
	
	public SparkContextLocal() {
		conf = new SparkConf().setMaster("local").setAppName("informatica");
		ctx = new SparkContext(conf);
	}

	public SparkConf getConf() {
		return conf;
	}

	public void setConf(SparkConf conf) {
		this.conf = conf;
	}

	public SparkContext getCtx() {
		return ctx;
	}

	public void setCtx(SparkContext ctx) {
		this.ctx = ctx;
	}
}
