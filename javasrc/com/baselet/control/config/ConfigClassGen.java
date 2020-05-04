package com.baselet.control.config;

import com.baselet.control.enums.generator.FieldOptions;
import com.baselet.control.enums.generator.MethodOptions;
import com.baselet.control.enums.generator.SignatureOptions;
import com.baselet.control.enums.generator.SortOptions;

public class ConfigClassGen {
	private static final ConfigClassGen instance = new ConfigClassGen();

	public static ConfigClassGen getInstance() {
		return instance;
	}

	private ConfigClassGen() {}

	private boolean generateClassPackage = true;
	private FieldOptions generateClassFields = FieldOptions.ALL;
	private MethodOptions generateClassMethods = MethodOptions.ALL;
	private SignatureOptions generateClassSignatures = SignatureOptions.ALL;
	private SortOptions generateClassSortings = SortOptions.HEIGHT;

	public boolean isGenerateClassPackage() {
		return generateClassPackage;
	}

	public void setGenerateClassPackage(boolean gcp) {
		this.generateClassPackage = gcp;
	}

	public FieldOptions getGenerateClassFields() {
		return generateClassFields;
	}

	public void setGenerateClassFields(FieldOptions gcf) {
		this.generateClassFields = gcf;
	}

	public MethodOptions getGenerateClassMethods() {
		return generateClassMethods;
	}

	public void setGenerateClassMethods(MethodOptions gcm) {
		this.generateClassMethods = gcm;
	}

	public SignatureOptions getGenerateClassSignatures() {
		return generateClassSignatures;
	}

	public void setGenerateClassSignatures(SignatureOptions gcs) {
		this.generateClassSignatures = gcs;
	}

	public SortOptions getGenerateClassSortings() {
		return generateClassSortings;
	}

	public void setGenerateClassSortings(SortOptions gcs) {
		this.generateClassSortings = gcs;
	}

}
