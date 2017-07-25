package com.nttdata.web.talend;

public class ContextProperties extends java.util.Properties {

	private static final long serialVersionUID = 1L;

	public ContextProperties(java.util.Properties properties) {
		super(properties);
	}

	public ContextProperties() {
		super();
	}

	public void synchronizeContext() {

		if (new1 != null) {
			this.setProperty("new1", new1.toString());
		}

		if (custom_module != null) {
			this.setProperty("custom_module", custom_module.toString());
		}

		if (custom_phase_detection != null) {
			this.setProperty("custom_phase_detection", custom_phase_detection.toString());
		}

		if (custom_project_id != null) {
			this.setProperty("custom_project_id", custom_project_id.toString());
		}

		if (custom_QA != null) {
			this.setProperty("custom_QA", custom_QA.toString());
		}

		if (custom_UAT != null) {
			this.setProperty("custom_UAT", custom_UAT.toString());
		}

		if (db_server != null) {

			this.setProperty("db_server", db_server);
		}
		if (db_user != null) {

			this.setProperty("db_user", db_user);
		}
		if (db_passwd != null) {

			this.setProperty("db_passwd", db_passwd);
		}
		if (db_port != null) {

			this.setProperty("db_port", db_port);
		}
		if (db_name != null) {

			this.setProperty("db_name", db_name);
		}

	}

	public String new1;

	public String getNew1() {
		return this.new1;
	}

	public String custom_module;

	public String getCustom_module() {
		return this.custom_module;
	}

	public String custom_phase_detection;

	public String getCustom_phase_detection() {
		return this.custom_phase_detection;
	}

	public Integer custom_project_id;

	public Integer getCustom_project_id() {
		return this.custom_project_id;
	}

	public String custom_QA;

	public String getCustom_QA() {
		return this.custom_QA;
	}

	public String custom_UAT;

	public String getCustom_UAT() {
		return this.custom_UAT;
	}

	private String db_server;
	private String db_user;
	private String db_passwd;
	private String db_port;
	private String db_name;

	public String getDb_server() {
		return db_server;
	}

	public void setDb_server(String db_server) {
		this.db_server = db_server;
	}

	public String getDb_user() {
		return db_user;
	}

	public void setDb_user(String db_user) {
		this.db_user = db_user;
	}

	public String getDb_passwd() {
		return db_passwd;
	}

	public void setDb_passwd(String db_passwd) {
		this.db_passwd = db_passwd;
	}

	public String getDb_port() {
		return db_port;
	}

	public void setDb_port(String db_port) {
		this.db_port = db_port;
	}

	public void setNew1(String new1) {
		this.new1 = new1;
	}

	public String getDb_name() {
		return db_name;
	}

	public void setDb_name(String db_name) {
		this.db_name = db_name;
	}

}
