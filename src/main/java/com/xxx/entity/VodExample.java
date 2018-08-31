package com.xxx.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class VodExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VodExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPostUrlIsNull() {
            addCriterion("post_url is null");
            return (Criteria) this;
        }

        public Criteria andPostUrlIsNotNull() {
            addCriterion("post_url is not null");
            return (Criteria) this;
        }

        public Criteria andPostUrlEqualTo(String value) {
            addCriterion("post_url =", value, "postUrl");
            return (Criteria) this;
        }

        public Criteria andPostUrlNotEqualTo(String value) {
            addCriterion("post_url <>", value, "postUrl");
            return (Criteria) this;
        }

        public Criteria andPostUrlGreaterThan(String value) {
            addCriterion("post_url >", value, "postUrl");
            return (Criteria) this;
        }

        public Criteria andPostUrlGreaterThanOrEqualTo(String value) {
            addCriterion("post_url >=", value, "postUrl");
            return (Criteria) this;
        }

        public Criteria andPostUrlLessThan(String value) {
            addCriterion("post_url <", value, "postUrl");
            return (Criteria) this;
        }

        public Criteria andPostUrlLessThanOrEqualTo(String value) {
            addCriterion("post_url <=", value, "postUrl");
            return (Criteria) this;
        }

        public Criteria andPostUrlLike(String value) {
            addCriterion("post_url like", value, "postUrl");
            return (Criteria) this;
        }

        public Criteria andPostUrlNotLike(String value) {
            addCriterion("post_url not like", value, "postUrl");
            return (Criteria) this;
        }

        public Criteria andPostUrlIn(List<String> values) {
            addCriterion("post_url in", values, "postUrl");
            return (Criteria) this;
        }

        public Criteria andPostUrlNotIn(List<String> values) {
            addCriterion("post_url not in", values, "postUrl");
            return (Criteria) this;
        }

        public Criteria andPostUrlBetween(String value1, String value2) {
            addCriterion("post_url between", value1, value2, "postUrl");
            return (Criteria) this;
        }

        public Criteria andPostUrlNotBetween(String value1, String value2) {
            addCriterion("post_url not between", value1, value2, "postUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlIsNull() {
            addCriterion("screenshot_url is null");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlIsNotNull() {
            addCriterion("screenshot_url is not null");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlEqualTo(String value) {
            addCriterion("screenshot_url =", value, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlNotEqualTo(String value) {
            addCriterion("screenshot_url <>", value, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlGreaterThan(String value) {
            addCriterion("screenshot_url >", value, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlGreaterThanOrEqualTo(String value) {
            addCriterion("screenshot_url >=", value, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlLessThan(String value) {
            addCriterion("screenshot_url <", value, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlLessThanOrEqualTo(String value) {
            addCriterion("screenshot_url <=", value, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlLike(String value) {
            addCriterion("screenshot_url like", value, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlNotLike(String value) {
            addCriterion("screenshot_url not like", value, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlIn(List<String> values) {
            addCriterion("screenshot_url in", values, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlNotIn(List<String> values) {
            addCriterion("screenshot_url not in", values, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlBetween(String value1, String value2) {
            addCriterion("screenshot_url between", value1, value2, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlNotBetween(String value1, String value2) {
            addCriterion("screenshot_url not between", value1, value2, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andSeriesIdIsNull() {
            addCriterion("series_id is null");
            return (Criteria) this;
        }

        public Criteria andSeriesIdIsNotNull() {
            addCriterion("series_id is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesIdEqualTo(Long value) {
            addCriterion("series_id =", value, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdNotEqualTo(Long value) {
            addCriterion("series_id <>", value, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdGreaterThan(Long value) {
            addCriterion("series_id >", value, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("series_id >=", value, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdLessThan(Long value) {
            addCriterion("series_id <", value, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdLessThanOrEqualTo(Long value) {
            addCriterion("series_id <=", value, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdIn(List<Long> values) {
            addCriterion("series_id in", values, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdNotIn(List<Long> values) {
            addCriterion("series_id not in", values, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdBetween(Long value1, Long value2) {
            addCriterion("series_id between", value1, value2, "seriesId");
            return (Criteria) this;
        }

        public Criteria andSeriesIdNotBetween(Long value1, Long value2) {
            addCriterion("series_id not between", value1, value2, "seriesId");
            return (Criteria) this;
        }

        public Criteria andVodTypeIsNull() {
            addCriterion("vod_type is null");
            return (Criteria) this;
        }

        public Criteria andVodTypeIsNotNull() {
            addCriterion("vod_type is not null");
            return (Criteria) this;
        }

        public Criteria andVodTypeEqualTo(Integer value) {
            addCriterion("vod_type =", value, "vodType");
            return (Criteria) this;
        }

        public Criteria andVodTypeNotEqualTo(Integer value) {
            addCriterion("vod_type <>", value, "vodType");
            return (Criteria) this;
        }

        public Criteria andVodTypeGreaterThan(Integer value) {
            addCriterion("vod_type >", value, "vodType");
            return (Criteria) this;
        }

        public Criteria andVodTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("vod_type >=", value, "vodType");
            return (Criteria) this;
        }

        public Criteria andVodTypeLessThan(Integer value) {
            addCriterion("vod_type <", value, "vodType");
            return (Criteria) this;
        }

        public Criteria andVodTypeLessThanOrEqualTo(Integer value) {
            addCriterion("vod_type <=", value, "vodType");
            return (Criteria) this;
        }

        public Criteria andVodTypeIn(List<Integer> values) {
            addCriterion("vod_type in", values, "vodType");
            return (Criteria) this;
        }

        public Criteria andVodTypeNotIn(List<Integer> values) {
            addCriterion("vod_type not in", values, "vodType");
            return (Criteria) this;
        }

        public Criteria andVodTypeBetween(Integer value1, Integer value2) {
            addCriterion("vod_type between", value1, value2, "vodType");
            return (Criteria) this;
        }

        public Criteria andVodTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("vod_type not between", value1, value2, "vodType");
            return (Criteria) this;
        }

        public Criteria andCountryTypeIsNull() {
            addCriterion("country_type is null");
            return (Criteria) this;
        }

        public Criteria andCountryTypeIsNotNull() {
            addCriterion("country_type is not null");
            return (Criteria) this;
        }

        public Criteria andCountryTypeEqualTo(Integer value) {
            addCriterion("country_type =", value, "countryType");
            return (Criteria) this;
        }

        public Criteria andCountryTypeNotEqualTo(Integer value) {
            addCriterion("country_type <>", value, "countryType");
            return (Criteria) this;
        }

        public Criteria andCountryTypeGreaterThan(Integer value) {
            addCriterion("country_type >", value, "countryType");
            return (Criteria) this;
        }

        public Criteria andCountryTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("country_type >=", value, "countryType");
            return (Criteria) this;
        }

        public Criteria andCountryTypeLessThan(Integer value) {
            addCriterion("country_type <", value, "countryType");
            return (Criteria) this;
        }

        public Criteria andCountryTypeLessThanOrEqualTo(Integer value) {
            addCriterion("country_type <=", value, "countryType");
            return (Criteria) this;
        }

        public Criteria andCountryTypeIn(List<Integer> values) {
            addCriterion("country_type in", values, "countryType");
            return (Criteria) this;
        }

        public Criteria andCountryTypeNotIn(List<Integer> values) {
            addCriterion("country_type not in", values, "countryType");
            return (Criteria) this;
        }

        public Criteria andCountryTypeBetween(Integer value1, Integer value2) {
            addCriterion("country_type between", value1, value2, "countryType");
            return (Criteria) this;
        }

        public Criteria andCountryTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("country_type not between", value1, value2, "countryType");
            return (Criteria) this;
        }

        public Criteria andPublishDateIsNull() {
            addCriterion("publish_date is null");
            return (Criteria) this;
        }

        public Criteria andPublishDateIsNotNull() {
            addCriterion("publish_date is not null");
            return (Criteria) this;
        }

        public Criteria andPublishDateEqualTo(Date value) {
            addCriterionForJDBCDate("publish_date =", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("publish_date <>", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateGreaterThan(Date value) {
            addCriterionForJDBCDate("publish_date >", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("publish_date >=", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateLessThan(Date value) {
            addCriterionForJDBCDate("publish_date <", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("publish_date <=", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateIn(List<Date> values) {
            addCriterionForJDBCDate("publish_date in", values, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("publish_date not in", values, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("publish_date between", value1, value2, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("publish_date not between", value1, value2, "publishDate");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}