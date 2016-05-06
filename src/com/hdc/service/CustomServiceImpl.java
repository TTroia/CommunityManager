package com.hdc.service;

import java.util.List;

import com.hdc.dao.CustomDao;
import com.hdc.model.Activity;
import com.hdc.model.Analysis_activity;
import com.hdc.model.Analysis_finance;
import com.hdc.model.Analysis_member;
import com.hdc.model.Community;
import com.hdc.model.Finance;
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

	@Override
	public List<Finance> queryFinanceList(Finance finance, Page page) {
		return this.customDao.queryFinanceList(finance,page);
	}

	@Override
	public List<Analysis_activity> queryAnalysis_activities(
			Analysis_activity analysis_activity) {
		return this.customDao.queryAnalysis_activities(analysis_activity);
	}

	@Override
	public List<Analysis_finance> queryAnalysis_finances(
			Analysis_finance analysis_finance,String comid) {
		// TODO Auto-generated method stub
		return this.customDao.queryAnalysis_finances(analysis_finance,comid);
	}

	@Override
	public List<Analysis_member> queryAnalysis_members(
			Analysis_member analysis_member) {
		// TODO Auto-generated method stub
		return this.customDao.queryAnalysis_member(analysis_member);
	}
}
