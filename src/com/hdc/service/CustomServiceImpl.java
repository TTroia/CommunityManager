package com.hdc.service;

import java.util.List;

import com.hdc.dao.CustomDao;
import com.hdc.model.Activity;
import com.hdc.model.Community;
import com.hdc.model.Member;
import com.hdc.model.Nature;
import com.hdc.model.Notice;
import com.hdc.page.Page;

public class CustomServiceImpl implements CustomService {
	private CustomDao customDao;

	public CustomDao getCustomDao() {
		return customDao;
	}

	public void setCustomDao(CustomDao customDao) {
		this.customDao = customDao;
	}

	@Override
	public List<Community> queryCommunitieList(Community community, Page page) {
		return this.customDao.queryCommunitieList(community,page);
	}

	@Override
	public List<Member> queryMemberList(Member member, Page page) {
		return this.customDao.queryMemberList(member,page);
	}

	@Override
	public List<Nature> queryNatureList(Nature nature, Page page) {
		return this.customDao.queryNatureList(nature,page);
	}

	@Override
	public List<Activity> queryActivityList(Activity activity, Page page) {
		return this.customDao.queryActivityList(activity,page);
	}

	@Override
	public List<Notice> queryNoticeList(Notice notice) {
		return this.customDao.queryNoticeList(notice);
	}
}
