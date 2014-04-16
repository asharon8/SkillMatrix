package com.sdlc.skillMatrix.web.util;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

public class BeanUtil {

	@SuppressWarnings("deprecation")
	public static Object getBean(String beanConstant) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		
		if(ctx != null) {
			ValueBinding vb = ctx.getApplication().createValueBinding("#{" + beanConstant + "}");
			return vb.getValue(ctx);
		}
		
		return null;
	}

		
		
}
