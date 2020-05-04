package com.baselet.control.config;

public class ConfigMail {
	private static final ConfigMail instance = new ConfigMail();

	public static ConfigMail getInstance() {
		return instance;
	}

	private String mail_smtp = "";
	private boolean mail_smtp_auth = false;
	private String mail_smtp_user = "";
	private boolean mail_smtp_pw_store = false;
	private String mail_smtp_pw = "";
	private String mail_from = "";
	private String mail_to = "";
	private String mail_cc = "";
	private String mail_bcc = "";
	private boolean mail_xml = true;
	private boolean mail_gif = true;
	private boolean mail_pdf = false;

	private ConfigMail() {}

	public String getMail_smtp() {
		return mail_smtp;
	}

	public void setMail_smtp(String ms) {
		this.mail_smtp = ms;
	}

	public boolean isMail_smtp_auth() {
		return mail_smtp_auth;
	}

	public void setMail_smtp_auth(boolean msa) {
		this.mail_smtp_auth = msa;
	}

	public String getMail_smtp_user() {
		return mail_smtp_user;
	}

	public void setMail_smtp_user(String msu) {
		this.mail_smtp_user = msu;
	}

	public boolean isMail_smtp_pw_store() {
		return mail_smtp_pw_store;
	}

	public void setMail_smtp_pw_store(boolean msps) {
		this.mail_smtp_pw_store = msps;
	}

	public String getMail_smtp_pw() {
		return mail_smtp_pw;
	}

	public void setMail_smtp_pw(String msp) {
		this.mail_smtp_pw = msp;
	}

	public String getMail_from() {
		return mail_from;
	}

	public void setMail_from(String mf) {
		this.mail_from = mf;
	}

	public String getMail_to() {
		return mail_to;
	}

	public void setMail_to(String mt) {
		this.mail_to = mt;
	}

	public String getMail_cc() {
		return mail_cc;
	}

	public void setMail_cc(String mcc) {
		this.mail_cc = mcc;
	}

	public String getMail_bcc() {
		return mail_bcc;
	}

	public void setMail_bcc(String bcc) {
		this.mail_bcc = bcc;
	}

	public boolean isMail_xml() {
		return mail_xml;
	}

	public void setMail_xml(boolean xml) {
		this.mail_xml = xml;
	}

	public boolean isMail_gif() {
		return mail_gif;
	}

	public void setMail_gif(boolean gif) {
		this.mail_gif = gif;
	}

	public boolean isMail_pdf() {
		return mail_pdf;
	}

	public void setMail_pdf(boolean pdf) {
		this.mail_pdf = pdf;
	}
}
