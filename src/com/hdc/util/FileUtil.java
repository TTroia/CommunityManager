package com.hdc.util;

import java.io.File;

import com.hdc.action.base.BaseAction;

public class FileUtil extends BaseAction  {
	private static final long serialVersionUID = 1L;

	public void upload(File file){
		String path=request.getSession().getServletContext().getRealPath("/upload");
		
		
	}
}
