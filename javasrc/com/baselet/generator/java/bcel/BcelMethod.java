package com.baselet.generator.java.bcel;

import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.Type;

public class BcelMethod extends BcelAccessible implements com.baselet.generator.java.Method {

	private final Method method;
	private final String className;
	private boolean isConstructor;

	public BcelMethod(Method m, String cn) {
		super(m);
		this.method = m;
		this.className = cn;
		if (m.getName().equals("<init>") || m.getName().equals("<clinit>")) {
			isConstructor = true;
		}
		else {
			isConstructor = false;
		}
	}

	@Override
	public String getName() {
		if (isConstructor) {
			return className;
		}
		return method.getName();
	}

	@Override
	public String getReturnType() {
		if (isConstructor) {
			return "ctor";
		}
		return method.getReturnType().toString();
	}

	@Override
	public String getSignature() {
		StringBuilder sb = new StringBuilder("");
		Type[] arguments = method.getArgumentTypes();
		boolean first = true;
		for (Type argument : arguments) {
			if (first) {
				first = false;
				sb.append(argument);
			}
			else {
				sb.append(", ").append(argument);
			}
		}
		return sb.toString();
	}
}
