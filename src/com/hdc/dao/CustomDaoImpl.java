package com.hdc.dao;

import java.util.List;

import com.hdc.model.Activity;
import com.hdc.model.Analysis_activity;
import com.hdc.model.Community;
import com.hdc.model.Finance;
import com.hdc.model.Member;
import com.hdc.model.Nature;
import com.hdc.model.Notice;
import com.hdc.model.rowmap.ActivityAnalysisRowMapper;
import com.hdc.model.rowmap.ObjectRowMapper;
import com.hdc.page.Page;
import com.hdc.util.Constant;
import com.hdc.util.StringUtil;


public class CustomDaoImpl extends CommonDaoImpl implements CustomDao {

	@Override
	public List<Community> queryCommunitieList(Community community, Page page) {
		if (community == null) {
			return null;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ").append(Constant.COMMUNITY_TABLE_NAME)
				.append(" where 1=1  ");
		if(community!=null && !StringUtil.isNullOrBlank(community.getName())){
			sql.append("and name like '%"+community.getName()+"%' ");
		}
		sql.append(" order by  createDate desc");
		if (page != null) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select count(*) from (");
			buffer.append(sql);
			buffer.append(") a");
			int pageTotal = this.getJdbcTemplate().queryForInt(
					buffer.toString());
			page.setTotalRows(pageTotal);
			sql.append(" limit " + page.getOffset() + "," + page.getLimit());
		}
		log.info(sql);
		List<Community> list = this.getJdbcTemplate().query(
				sql.toString(),
				new ObjectRowMapper(community, super
						.getColumnList(Constant.COMMUNITY_TABLE_NAME)));
		if (page != null) {
			page.setResultList(list);
		}
		return list;
	}

	@Override
	public List<Member> queryMemberList(Member member, Page page) {
		// TODO Auto-generated method stub
		if (member == null) {
			return null;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ").append(Constant.MEMBER_TABLE_NAME)
				.append(" where 1=1  ");
		if(null!=member.getCom_id()){
			sql.append("and com_id = "+member.getCom_id());
		}
		sql.append(" order by  createDate desc");
		if (page != null) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select count(*) from (");
			buffer.append(sql);
			buffer.append(") a");
			int pageTotal = this.getJdbcTemplate().queryForInt(
					buffer.toString());
			page.setTotalRows(pageTotal);
			sql.append(" limit " + page.getOffset() + "," + page.getLimit());
		}
		log.info(sql);
		List<Member> list = this.getJdbcTemplate().query(
				sql.toString(),
				new ObjectRowMapper(member, super
						.getColumnList(Constant.MEMBER_TABLE_NAME)));
		if (page != null) {
			page.setResultList(list);
		}
		return list;
	}

	@Override
	public List<Nature> queryNatureList(Nature nature, Page page) {
		if (nature == null) {
			return null;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ").append(Constant.NATURE_TABLE_NAME)
				.append(" where 1=1  ");
		if(nature!=null && !StringUtil.isNullOrBlank(nature.getName())){
			sql.append("and name like '%"+nature.getName()+"%' ");
		}
		sql.append(" order by  id desc");
		if (page != null) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select count(*) from (");
			buffer.append(sql);
			buffer.append(") a");
			int pageTotal = this.getJdbcTemplate().queryForInt(
					buffer.toString());
			page.setTotalRows(pageTotal);
			sql.append(" limit " + page.getOffset() + "," + page.getLimit());
		}
		log.info(sql);
		List<Nature> list = this.getJdbcTemplate().query(
				sql.toString(),
				new ObjectRowMapper(nature, super
						.getColumnList(Constant.NATURE_TABLE_NAME)));
		if (page != null) {
			page.setResultList(list);
		}
		return list;
	}

	@Override
	public List<Activity> queryActivityList(Activity activity, Page page) {
		// TODO Auto-generated method stub
		if (activity == null) {
			return null;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ").append(Constant.ACTIVITY_TABLE_NAME)
				.append(" where 1=1  ");
		if(activity!=null && !StringUtil.isNullOrBlank(activity.getName())){
			sql.append("and name like '%"+activity.getName()+"%' ");
		}
		sql.append(" order by  applyDate desc");
		if (page != null) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select count(*) from (");
			buffer.append(sql);
			buffer.append(") a");
			int pageTotal = this.getJdbcTemplate().queryForInt(
					buffer.toString());
			page.setTotalRows(pageTotal);
			sql.append(" limit " + page.getOffset() + "," + page.getLimit());
		}
		log.info(sql);
		List<Activity> list = this.getJdbcTemplate().query(
				sql.toString(),
				new ObjectRowMapper(activity, super
						.getColumnList(Constant.ACTIVITY_TABLE_NAME)));
		if (page != null) {
			page.setResultList(list);
		}
		return list;
	}

	@Override
	public List<Notice> queryNoticeList(Notice notice) {
		// TODO Auto-generated method stub
		if (notice == null) {
			return null;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ").append(Constant.NOTICE_TABLE_NAME)
				.append(" where 1=1 ");
		sql.append("and toUser like '%"+notice.getToUser()+"%'");
		sql.append(" order by  isRead desc ,sendDate desc");
		log.info(sql);
		List<Notice> list = this.getJdbcTemplate().query(
				sql.toString(),
				new ObjectRowMapper(notice, super
						.getColumnList(Constant.NOTICE_TABLE_NAME)));
		return list;
	}

	@Override
	public List<Finance> queryFinanceList(Finance finance, Page page) {
		if (finance == null) {
			return null;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ").append(Constant.FINANCE_TABLE_NAME)
				.append(" where 1=1  ");
		if(finance.getCom_id()!=0){
			sql.append(" and com_id = "+finance.getCom_id());
		}
		sql.append(" order by  editDate asc");
		if (page != null) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select count(*) from (");
			buffer.append(sql);
			buffer.append(") a");
			int pageTotal = this.getJdbcTemplate().queryForInt(
					buffer.toString());
			page.setTotalRows(pageTotal);
			sql.append(" limit " + page.getOffset() + "," + page.getLimit());
		}
		log.info(sql);
		List<Finance> list = this.getJdbcTemplate().query(
				sql.toString(),
				new ObjectRowMapper(finance, super
						.getColumnList(Constant.FINANCE_TABLE_NAME)));
		if (page != null) {
			page.setResultList(list);
		}
		return list;
	}

	@Override
	public List<Analysis_activity> queryAnalysis_activities(
			Analysis_activity analysis_activity) {
		if(analysis_activity==null){
			return null;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select com_name,count(*)  from ").append(Constant.ACTIVITY_TABLE_NAME)
		.append(" group by com_id LIMIT 0,10 ");
		log.info(sql);
		List<Analysis_activity> list = this.getJdbcTemplate().query(
				sql.toString(),
				new ActivityAnalysisRowMapper());
		return list;
	}

}
