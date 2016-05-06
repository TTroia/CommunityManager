package com.hdc.dao;

import java.util.List;

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

public interface CustomDao {

	public List<Community> queryCommunitieList(Community community,Page page);
	public List<Member> queryMemberList(Member member,Page page);
	public List<Nature> queryNatureList(Nature nature,Page page);
	public List<Activity> queryActivityList(Activity activity,Page page);
	public List<Notice> queryNoticeList(Notice notice);
	public List<Finance> queryFinanceList(Finance finance,Page page);
	public List<Analysis_activity> queryAnalysis_activities(Analysis_activity analysis_activity);
	public List<Analysis_finance> queryAnalysis_finances(Analysis_finance analysis_finance,String comid);
	public List<Analysis_member> queryAnalysis_member(Analysis_member analysis_member);
}	
